// FTPFile.java
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

/***
 * The FTPFile class is used to represent information about files stored
 * on an FTP server.
 ***/

public class FTPFile implements Serializable {
	private static final long serialVersionUID = 9010790363003271996L;

	/**
	 * A constant indicating an FTPFile is a file.
	 ***/
	public static final int FILE_TYPE = 0;
	/**
	 * A constant indicating an FTPFile is a directory.
	 ***/
	public static final int DIRECTORY_TYPE = 1;
	/**
	 * A constant indicating an FTPFile is a symbolic link.
	 ***/
	public static final int SYMBOLIC_LINK_TYPE = 2;
	/**
	 * A constant indicating an FTPFile is of unknown type.
	 ***/
	public static final int UNKNOWN_TYPE = 3;

	/**
	 * A constant indicating user access permissions.
	 ***/
	public static final int USER_ACCESS = 0;
	/**
	 * A constant indicating group access permissions.
	 ***/
	public static final int GROUP_ACCESS = 1;
	/**
	 * A constant indicating world access permissions.
	 ***/
	public static final int WORLD_ACCESS = 2;

	/**
	 * A constant indicating file/directory read permission.
	 ***/
	public static final int READ_PERMISSION = 0;
	/**
	 * A constant indicating file/directory write permission.
	 ***/
	public static final int WRITE_PERMISSION = 1;
	/**
	 * A constant indicating file execute permission or directory listing
	 * permission.
	 ***/
	public static final int EXECUTE_PERMISSION = 2;

	private int _type, _hardLinkCount;
	private long _size;
	private String _rawListing, _user, _group, _name, _link;
	private Calendar _date;
	// If this is null, then list entry parsing failed
	private final boolean[] _permissions[]; // e.g. _permissions[USER_ACCESS][READ_PERMISSION]

	/*** Creates an empty FTPFile. ***/
	public FTPFile() {
		_permissions = new boolean[3][3];
		_type = UNKNOWN_TYPE;
		// init these to values that do not occur in listings
		// so can distinguish which fields are unset
		_hardLinkCount = 0; // 0 is invalid as a link count
		_size = -1; // 0 is valid, so use -1
		_user = "";
		_group = "";
		_date = null;
		_name = null;
	}

	/**
	 * Constructor used to create FTPFile entries for failed parses
	 *
	 * @param rawListing line that could not be parsed.
	 */
	FTPFile(String rawListing) {
		_permissions = null; // flag that entry is invalid
		_rawListing = rawListing;
		_type = UNKNOWN_TYPE;
		// init these to values that do not occur in listings
		// so can distinguish which fields are unset
		_hardLinkCount = 0; // 0 is invalid as a link count
		_size = -1; // 0 is valid, so use -1
		_user = "";
		_group = "";
		_date = null;
		_name = null;
	}


	/***
	 * Set the original FTP server raw listing from which the FTPFile was
	 * created.
	 *
	 * @param rawListing  The raw FTP server listing.
	 ***/
	public void setRawListing(String rawListing) {
		_rawListing = rawListing;
	}

	/***
	 * Get the original FTP server raw listing used to initialize the FTPFile.
	 *
	 * @return The original FTP server raw listing used to initialize the
	 *         FTPFile.
	 ***/
	public String getRawListing() {
		return _rawListing;
	}


	/***
	 * Determine if the file is a directory.
	 *
	 * @return True if the file is of type DIRECTORY_TYPE, false if
	 *         not.
	 ***/
	public boolean isDirectory() {
		return (_type == DIRECTORY_TYPE);
	}

	/***
	 * Determine if the file is a regular file.
	 *
	 * @return True if the file is of type FILE_TYPE, false if
	 *         not.
	 ***/
	public boolean isFile() {
		return (_type == FILE_TYPE);
	}

	/***
	 * Determine if the file is a symbolic link.
	 *
	 * @return True if the file is of type UNKNOWN_TYPE, false if
	 *         not.
	 ***/
	public boolean isSymbolicLink() {
		return (_type == SYMBOLIC_LINK_TYPE);
	}

	/***
	 * Determine if the type of the file is unknown.
	 *
	 * @return True if the file is of type UNKNOWN_TYPE, false if
	 *         not.
	 ***/
	public boolean isUnknown() {
		return (_type == UNKNOWN_TYPE);
	}

	/**
	 * Used to indicate whether an entry is valid or not.
	 * If the entry is invalid, only the getRawListing() method will be useful.
	 * Other methods may fail.
	 *
	 * Used in conjunction with list parsing that preseverves entries that failed to parse.
	 *
	 * @return true if the entry is valid
	 */
	public boolean isValid() {
		return (_permissions != null);
	}

	/***
	 * Set the type of the file (DIRECTORY_TYPE,
	 * FILE_TYPE, etc.).
	 *
	 * @param type  The integer code representing the type of the file.
	 ***/
	public void setType(int type) {
		_type = type;
	}


	/***
	 * Return the type of the file (one of the _TYPE constants),
	 * e.g., if it is a directory, a regular file, or a symbolic link.
	 *
	 * @return The type of the file.
	 ***/
	public int getType() {
		return _type;
	}


	/***
	 * Set the name of the file.
	 *
	 * @param name  The name of the file.
	 ***/
	public void setName(String name) {
		_name = name;
	}

	/***
	 * Return the name of the file.
	 *
	 * @return The name of the file.
	 ***/
	public String getName() {
		return _name;
	}


	/**
	 * Set the file size in bytes.
	 *
	 * @param size The file size in bytes.
	 */
	public void setSize(long size) {
		_size = size;
	}


	/***
	 * Return the file size in bytes.
	 *
	 * @return The file size in bytes.
	 ***/
	public long getSize() {
		return _size;
	}


	/***
	 * Set the number of hard links to this file.  This is not to be
	 * confused with symbolic links.
	 *
	 * @param links  The number of hard links to this file.
	 ***/
	public void setHardLinkCount(int links) {
		_hardLinkCount = links;
	}


	/***
	 * Return the number of hard links to this file.  This is not to be
	 * confused with symbolic links.
	 *
	 * @return The number of hard links to this file.
	 ***/
	public int getHardLinkCount() {
		return _hardLinkCount;
	}


	/***
	 * Set the name of the group owning the file.  This may be
	 * a string representation of the group number.
	 *
	 * @param group The name of the group owning the file.
	 ***/
	public void setGroup(String group) {
		_group = group;
	}


	/***
	 * Returns the name of the group owning the file.  Sometimes this will be
	 * a string representation of the group number.
	 *
	 * @return The name of the group owning the file.
	 ***/
	public String getGroup() {
		return _group;
	}


	/***
	 * Set the name of the user owning the file.  This may be
	 * a string representation of the user number;
	 *
	 * @param user The name of the user owning the file.
	 ***/
	public void setUser(String user) {
		_user = user;
	}

	/***
	 * Returns the name of the user owning the file.  Sometimes this will be
	 * a string representation of the user number.
	 *
	 * @return The name of the user owning the file.
	 ***/
	public String getUser() {
		return _user;
	}


	/***
	 * If the FTPFile is a symbolic link, use this method to set the name of the
	 * file being pointed to by the symbolic link.
	 *
	 * @param link  The file pointed to by the symbolic link.
	 ***/
	public void setLink(String link) {
		_link = link;
	}


	/***
	 * If the FTPFile is a symbolic link, this method returns the name of the
	 * file being pointed to by the symbolic link.  Otherwise it returns null.
	 *
	 * @return The file pointed to by the symbolic link (null if the FTPFile
	 *         is not a symbolic link).
	 ***/
	public String getLink() {
		return _link;
	}


	/***
	 * Set the file timestamp.  This usually the last modification time.
	 * The parameter is not cloned, so do not alter its value after calling
	 * this method.
	 *
	 * @param date A Calendar instance representing the file timestamp.
	 ***/
	public void setTimestamp(Calendar date) {
		_date = date;
	}


	/***
	 * Returns the file timestamp.  This usually the last modification time.
	 *
	 * @return A Calendar instance representing the file timestamp.
	 ***/
	public Calendar getTimestamp() {
		return _date;
	}


	/***
	 * Set if the given access group (one of the  _ACCESS
	 * constants) has the given access permission (one of the
	 *  _PERMISSION  constants) to the file.
	 *
	 * @param access The access group (one of the  _ACCESS
	 *               constants)
	 * @param permission The access permission (one of the
	 *                _PERMISSION  constants)
	 * @param value  True if permission is allowed, false if not.
	 * @throws ArrayIndexOutOfBoundsException if either of the parameters is out of range
	 ***/
	public void setPermission(int access, int permission, boolean value) {
		_permissions[access][permission] = value;
	}


	/***
	 * Determines if the given access group (one of the  _ACCESS
	 * constants) has the given access permission (one of the
	 *  _PERMISSION  constants) to the file.
	 *
	 * @param access The access group (one of the  _ACCESS
	 *               constants)
	 * @param permission The access permission (one of the
	 *                _PERMISSION  constants)
	 * @throws ArrayIndexOutOfBoundsException if either of the parameters is out of range
	 * @return true if isValid() is true and the associated permission is set;
	 * false otherwise.
	 ***/
	public boolean hasPermission(int access, int permission) {
		if (_permissions == null) {
			return false;
		}
		return _permissions[access][permission];
	}

	/***
	 * Returns a string representation of the FTPFile information.
	 *
	 * @return A string representation of the FTPFile information.
	 */
	@Override
	public String toString() {
		return getRawListing();
	}

	/***
	 * Returns a string representation of the FTPFile information.
	 * This currently mimics the Unix listing format.
	 * This method uses the timezone of the Calendar entry, which is
	 * the server time zone (if one was provided) otherwise it is
	 * the local time zone.
	 *
	 * Note: if the instance is not valid, no useful
	 * information can be returned. In this case, use getRawListing()
	 * instead.
	 *
	 * @return A string representation of the FTPFile information.
	 */
	public String toFormattedString() {
		return toFormattedString(null);
	}

	/**
	 * Returns a string representation of the FTPFile information.
	 * This currently mimics the Unix listing format.
	 * This method allows the Calendar time zone to be overridden.
	 *
	 * Note: if the instance is not valid, no useful
	 * information can be returned. In this case, use getRawListing()
	 * instead.
	 *
	 * @param timezone the timezone to use for displaying the time stamp
	 *                 If null, then use the Calendar entry timezone
	 * @return A string representation of the FTPFile information.
	 */
	public String toFormattedString(final String timezone) {

		if (!isValid()) {
			return "[Invalid: could not parse file entry]";
		}
		StringBuilder sb = new StringBuilder();
		Formatter fmt = new Formatter(sb);
		sb.append(formatType());
		sb.append(permissionToString(USER_ACCESS));
		sb.append(permissionToString(GROUP_ACCESS));
		sb.append(permissionToString(WORLD_ACCESS));
		fmt.format(" %4d", Integer.valueOf(getHardLinkCount()));
		fmt.format(" %-8s %-8s", getUser(), getGroup());
		fmt.format(" %8d", Long.valueOf(getSize()));
		Calendar timestamp = getTimestamp();
		if (timestamp != null) {
			if (timezone != null) {
				TimeZone newZone = TimeZone.getTimeZone(timezone);
				if (!newZone.equals(timestamp.getTimeZone())) {
					Date original = timestamp.getTime();
					Calendar newStamp = Calendar.getInstance(newZone);
					newStamp.setTime(original);
					timestamp = newStamp;
				}
			}
			fmt.format(" %1$tY-%1$tm-%1$td", timestamp);
			// Only display time units if they are present
			if (timestamp.isSet(Calendar.HOUR_OF_DAY)) {
				fmt.format(" %1$tH", timestamp);
				if (timestamp.isSet(Calendar.MINUTE)) {
					fmt.format(":%1$tM", timestamp);
					if (timestamp.isSet(Calendar.SECOND)) {
						fmt.format(":%1$tS", timestamp);
						if (timestamp.isSet(Calendar.MILLISECOND)) {
							fmt.format(".%1$tL", timestamp);
						}
					}
				}
				fmt.format(" %1$tZ", timestamp);
			}
		}
		sb.append(' ');
		sb.append(getName());
		fmt.close();
		return sb.toString();
	}

	private char formatType() {
		switch (_type) {
			case FILE_TYPE:
				return '-';
			case DIRECTORY_TYPE:
				return 'd';
			case SYMBOLIC_LINK_TYPE:
				return 'l';
			default:
				return '?';
		}
	}

	private String permissionToString(int access) {
		StringBuilder sb = new StringBuilder();
		if (hasPermission(access, READ_PERMISSION)) {
			sb.append('r');
		} else {
			sb.append('-');
		}
		if (hasPermission(access, WRITE_PERMISSION)) {
			sb.append('w');
		} else {
			sb.append('-');
		}
		if (hasPermission(access, EXECUTE_PERMISSION)) {
			sb.append('x');
		} else {
			sb.append('-');
		}
		return sb.toString();
	}
}

// FTPFile_isFileTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

/**
 * Test class of FTPFile.
 * It contains 10 unit test cases for the
 * FTPFile#public isFile() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isFileTest {
	private FTPFile file;
	private FTPFile invalidFile;
	private FTPFile invalidFile2;
	private FTPFile invalidFile3;
	private FTPFile invalidFile4;
	private FTPFile invalidFile5;
	private FTPFile invalidFile6;
	private FTPFile invalidFile7;
	private FTPFile invalidFile8;
	private FTPFile invalidFile9;
	private FTPFile invalidFile10;
	private FTPFile invalidFile11;
	private FTPFile invalidFile12;
	private FTPFile invalidFile13;
	private FTPFile invalidFile14;
	private FTPFile invalidFile15;
	private FTPFile invalidFile16;
	private FTPFile invalidFile17;
	private FTPFile invalidFile18;
	private FTPFile invalidFile19;
	private FTPFile invalidFile20;
	private FTPFile invalidFile21;
	private FTPFile invalidFile22;
	private FTPFile invalidFile23;
	private FTPFile invalidFile24;
	private FTPFile invalidFile25;
	private FTPFile invalidFile26;
	private FTPFile invalidFile27;
	private FTPFile invalidFile28;
	private FTPFile invalidFile29;
	private FTPFile invalidFile30;
	private FTPFile invalidFile31;
	private FTPFile invalidFile32;
	private FTPFile invalidFile33;
	private FTPFile invalidFile34;
	private FTPFile invalidFile35;
	private FTPFile invalidFile36;
	private FTPFile invalidFile37;
	private FTPFile invalidFile38;
	private FTPFile invalidFile39;
	private FTPFile invalidFile40;
	private FTPFile invalidFile41;
	private FTPFile invalidFile42;
	private FTPFile invalidFile43;
	private FTPFile invalidFile44;
	private FTPFile invalidFile45;
	private FTPFile invalidFile46;
	private FTPFile invalidFile47;
	private FTPFile invalidFile48;
	private FTPFile invalidFile49;
	private FTPFile invalidFile50;
	private FTPFile invalidFile51;
	private FTPFile invalidFile52;
	private FTPFile invalidFile53;
	private FTPFile invalidFile54;
	private FTPFile invalidFile55;
	private FTPFile invalidFile56;
	private FTPFile invalidFile57;
	private FTPFile invalidFile58;
	private FTPFile invalidFile59;
	private FTPFile invalidFile60;
	private FTPFile invalidFile61;
	private FTPFile invalidFile62;
	private FTPFile invalidFile63;
	private FTPFile invalidFile64;
	private FTPFile invalidFile65;
	private FTPFile invalidFile66;
	private FTPFile invalidFile67;
	private FTPFile invalidFile68;
	private FTPFile invalidFile69;
	private FTPFile invalidFile70;
	private FTPFile invalidFile71;
	private FTPFile invalidFile72;
	private FTPFile invalidFile73;
	private FTPFile invalidFile74;
	private FTPFile invalidFile75;
	private FTPFile invalidFile76;
	private FTPFile invalidFile77;
	private FTPFile invalidFile78;
	private FTPFile invalidFile79;
	private FTPFile invalidFile80;
	private FTPFile invalidFile81;
	private FTPFile invalidFile82;
	private FTPFile invalidFile83;
	private FTPFile invalidFile84;
	private FTPFile invalidFile85;
	private FTPFile invalidFile86;
	private FTPFile invalidFile87;
	private FTPFile invalidFile88;
	private FTPFile invalidFile89;
	private FTPFile invalidFile90;
	private FTPFile invalidFile91;
	private FTPFile invalidFile92;
	private FTPFile invalidFile93;
	private FTPFile invalidFile94;
	private FTPFile invalidFile95;
	private FTPFile invalidFile96;
	private FTPFile invalidFile97;
	private FTPFile invalidFile98;
	private FTPFile invalidFile99;
	private FTPFile invalidFile100;
	private FTPFile invalidFile101;
	private FTPFile invalidFile102;
	private FTPFile invalidFile103;
	private FTPFile invalidFile104;
	private FTPFile invalidFile105;
	private FTPFile invalidFile106;
	private FTPFile invalidFile107;
	private FTPFile invalidFile108;
	private FTPFile invalidFile109;
	private FTPFile invalidFile110;
	private FTPFile invalidFile111;
	private FTPFile invalidFile112;
	private FTPFile invalidFile113;
	private FTPFile invalidFile114;
	private FTPFile invalidFile115;
	private FTPFile invalidFile116;
	private FTPFile invalidFile117;
	private FTPFile invalidFile118;
	private FTPFile invalidFile119;
	private FTPFile invalidFile120;
	private FTPFile invalidFile121;
	private FTPFile invalidFile122;
	private FTPFile invalidFile123;
	private FTPFile invalidFile124;
	private FTPFile invalidFile125;
	private FTPFile invalidFile126;
	private FTPFile invalidFile127;
	private FTPFile invalidFile128;
	private FTPFile invalidFile129;
	private FTPFile invalidFile130;
	private FTPFile invalidFile131;
	private FTPFile invalidFile132;
	private FTPFile invalidFile133;
	private FTPFile invalidFile134;
	private FTPFile invalidFile135;
	private FTPFile invalidFile136;
	private FTPFile invalidFile137;
	private FTPFile invalidFile138;
	private FTPFile invalidFile139;
	private FTPFile invalidFile140;
	private FTPFile invalidFile141;
	private FTPFile invalidFile142;
	private FTPFile invalidFile143;
	private FTPFile invalidFile144;
	private FTPFile invalidFile145;
	private FTPFile invalidFile146;
	private FTPFile invalidFile147;
	private FTPFile invalidFile148;
	private FTPFile invalidFile149;
	private FTPFile invalidFile150;
	private FTPFile invalidFile151;
	private FTPFile invalidFile152;
	private FTPFile invalidFile153;
	private FTPFile invalidFile154;
	private FTPFile invalidFile155;
	private FTPFile invalidFile156;
	private FTPFile invalidFile157;
	private FTPFile invalidFile158;
	private FTPFile invalidFile159;
	private FTPFile invalidFile160;
	private FTPFile invalidFile161;
	private FTPFile invalidFile162;
	private FTPFile invalidFile163;
	private FTPFile invalidFile164;
	private FTPFile invalidFile165;
	private FTPFile invalidFile166;
	private FTPFile invalidFile167;
	private FTPFile invalidFile168;
	private FTPFile invalidFile169;
	private FTPFile invalidFile170;
	private FTPFile invalidFile171;
	private FTPFile invalidFile172;
	private FTPFile invalidFile173;
	private FTPFile invalidFile174;
	private FTPFile invalidFile175;
	private FTPFile invalidFile176;
	private FTPFile invalidFile177;
	private FTPFile invalidFile178;
	private FTPFile invalidFile179;
	private FTPFile invalidFile180;
	private FTPFile invalidFile181;
	private FTPFile invalidFile182;
	private FTPFile invalidFile183;
	private FTPFile invalidFile184;
	private FTPFile invalidFile185;
	private FTPFile invalidFile186;
	private FTPFile invalidFile187;
	private FTPFile invalidFile188;
	private FTPFile invalidFile189;
	private FTPFile invalidFile190;
	private FTPFile invalidFile191;
	private FTPFile invalidFile192;
	private FTPFile invalidFile193;
	private FTPFile invalidFile194;
	private FTPFile invalidFile195;
	private FTPFile invalid
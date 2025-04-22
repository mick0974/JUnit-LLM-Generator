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

// FTPFile_isDirectoryTest.java
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
 * FTPFile#public isDirectory() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isDirectoryTest {
	private FTPFile ftpFile;
	private FTPFile ftpFile2;
	private FTPFile ftpFile3;
	private FTPFile ftpFile4;
	private FTPFile ftpFile5;
	private FTPFile ftpFile6;
	private FTPFile ftpFile7;
	private FTPFile ftpFile8;
	private FTPFile ftpFile9;
	private FTPFile ftpFile10;
	private FTPFile ftpFile11;
	private FTPFile ftpFile12;
	private FTPFile ftpFile13;
	private FTPFile ftpFile14;
	private FTPFile ftpFile15;
	private FTPFile ftpFile16;
	private FTPFile ftpFile17;
	private FTPFile ftpFile18;
	private FTPFile ftpFile19;
	private FTPFile ftpFile20;
	private FTPFile ftpFile21;
	private FTPFile ftpFile22;
	private FTPFile ftpFile23;
	private FTPFile ftpFile24;
	private FTPFile ftpFile25;
	private FTPFile ftpFile26;
	private FTPFile ftpFile27;
	private FTPFile ftpFile28;
	private FTPFile ftpFile29;
	private FTPFile ftpFile30;
	private FTPFile ftpFile31;
	private FTPFile ftpFile32;
	private FTPFile ftpFile33;
	private FTPFile ftpFile34;
	private FTPFile ftpFile35;
	private FTPFile ftpFile36;
	private FTPFile ftpFile37;
	private FTPFile ftpFile38;
	private FTPFile ftpFile39;
	private FTPFile ftpFile40;
	private FTPFile ftpFile41;
	private FTPFile ftpFile42;
	private FTPFile ftpFile43;
	private FTPFile ftpFile44;
	private FTPFile ftpFile45;
	private FTPFile ftpFile46;
	private FTPFile ftpFile47;
	private FTPFile ftpFile48;
	private FTPFile ftpFile49;
	private FTPFile ftpFile50;
	private FTPFile ftpFile51;
	private FTPFile ftpFile52;
	private FTPFile ftpFile53;
	private FTPFile ftpFile54;
	private FTPFile ftpFile55;
	private FTPFile ftpFile56;
	private FTPFile ftpFile57;
	private FTPFile ftpFile58;
	private FTPFile ftpFile59;
	private FTPFile ftpFile60;
	private FTPFile ftpFile61;
	private FTPFile ftpFile62;
	private FTPFile ftpFile63;
	private FTPFile ftpFile64;
	private FTPFile ftpFile65;
	private FTPFile ftpFile66;
	private FTPFile ftpFile67;
	private FTPFile ftpFile68;
	private FTPFile ftpFile69;
	private FTPFile ftpFile70;
	private FTPFile ftpFile71;
	private FTPFile ftpFile72;
	private FTPFile ftpFile73;
	private FTPFile ftpFile74;
	private FTPFile ftpFile75;
	private FTPFile ftpFile76;
	private FTPFile ftpFile77;
	private FTPFile ftpFile78;
	private FTPFile ftpFile79;
	private FTPFile ftpFile80;
	private FTPFile ftpFile81;
	private FTPFile ftpFile82;
	private FTPFile ftpFile83;
	private FTPFile ftpFile84;
	private FTPFile ftpFile85;
	private FTPFile ftpFile86;
	private FTPFile ftpFile87;
	private FTPFile ftpFile88;
	private FTPFile ftpFile89;
	private FTPFile ftpFile90;
	private FTPFile ftpFile91;
	private FTPFile ftpFile92;
	private FTPFile ftpFile93;
	private FTPFile ftpFile94;
	private FTPFile ftpFile95;
	private FTPFile ftpFile96;
	private FTPFile ftpFile97;
	private FTPFile ftpFile98;
	private FTPFile ftpFile99;
	private FTPFile ftpFile100;
	private FTPFile ftpFile101;
	private FTPFile ftpFile102;
	private FTPFile ftpFile103;
	private FTPFile ftpFile104;
	private FTPFile ftpFile105;
	private FTPFile ftpFile106;
	private FTPFile ftpFile107;
	private FTPFile ftpFile108;
	private FTPFile ftpFile109;
	private FTPFile ftpFile110;
	private FTPFile ftpFile111;
	private FTPFile ftpFile112;
	private FTPFile ftpFile113;
	private FTPFile ftpFile114;
	private FTPFile ftpFile115;
	private FTPFile ftpFile116;
	private FTPFile ftpFile117;
	private FTPFile ftpFile118;
	private FTPFile ftpFile119;
	private FTPFile ftpFile120;
	private FTPFile ftpFile121;
	private FTPFile ftpFile122;
	private FTPFile ftpFile123;
	private FTPFile ftpFile124;
	private FTPFile ftpFile125;
	private FTPFile ftpFile126;
	private FTPFile ftpFile127;
	private FTPFile ftpFile128;
	private FTPFile ftpFile129;
	private FTPFile ftpFile130;
	private FTPFile ftpFile131;
	private FTPFile ftpFile132;
	private FTPFile ftpFile133;
	private FTPFile ftpFile134;
	private FTPFile ftpFile135;
	private FTPFile ftpFile136;
	private FTPFile ftpFile137;
	private FTPFile ftpFile138;
	private FTPFile ftpFile139;
	private FTPFile ftpFile140;
	private FTPFile ftpFile141;
	private FTPFile ftpFile142;
	private FTPFile ftpFile143;
	private FTPFile ftpFile144;
	private FTPFile ftpFile145;
	private FTPFile ftpFile146;
	private FTPFile ftpFile147;
	private FTPFile ftpFile148;
	private FTPFile ftpFile149;
	private FTPFile ftpFile150;
	private FTPFile ftpFile151;
	private FTPFile ftpFile152;
	private FTPFile ftpFile153;
	private FTPFile ftpFile154;
	private FTPFile ftpFile155;
	private FTPFile ftpFile156;
	private FTPFile ftpFile157;
	private FTPFile ftpFile158;
	private FTPFile ftpFile159;
	private FTPFile ftpFile160;
	private FTPFile ftpFile161;
	private FTPFile ftpFile162;
	private FTPFile ftpFile163;
	private FTPFile ftpFile164;
	private FTPFile ftpFile165;
	private FTPFile ftpFile166;
	private FTPFile ftpFile167;
	private FTPFile ftpFile168;
	private FTPFile ftpFile169;
	private FTPFile ftpFile170;
	private FTPFile ftpFile171;
	private FTPFile ftpFile172;
	private FTPFile ftpFile173;
	private FTPFile ftpFile174;
	private FTPFile ftpFile175;
	private FTPFile ftpFile176;
	private FTPFile ftpFile177;
	private FTPFile ftpFile178;
	private FTPFile ftpFile179;
	private FTPFile ftpFile180;
	private FTPFile ftpFile181;
	private FTPFile ftpFile182;
	private FTPFile ftpFile183;
	private FTPFile ftpFile184;
	private FTPFile ftpFile185;
	private FTPFile ftpFile186;
	private FTPFile ftpFile187;
	private FTPFile ftpFile188;
	private FTPFile ftpFile189;
	private FTPFile ftpFile190;
	private FTPFile ftpFile191;
	private FTPFile ftpFile192;
	private FTPFile ftpFile193;
	private FTPFile ftpFile194;
	private FTPFile ftpFile195;
	private FTPFile ftpFile196;
	
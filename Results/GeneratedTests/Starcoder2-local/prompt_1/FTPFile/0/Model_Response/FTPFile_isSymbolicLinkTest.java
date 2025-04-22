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

// FTPFile_isSymbolicLinkTest.java
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
 * FTPFile#public isSymbolicLink() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isSymbolicLinkTest {
	private FTPFile _ftpFile;
	private FTPFile _ftpFile2;
	private FTPFile _ftpFile3;
	private FTPFile _ftpFile4;
	private FTPFile _ftpFile5;
	private FTPFile _ftpFile6;
	private FTPFile _ftpFile7;
	private FTPFile _ftpFile8;
	private FTPFile _ftpFile9;
	private FTPFile _ftpFile10;
	private FTPFile _ftpFile11;
	private FTPFile _ftpFile12;
	private FTPFile _ftpFile13;
	private FTPFile _ftpFile14;
	private FTPFile _ftpFile15;
	private FTPFile _ftpFile16;
	private FTPFile _ftpFile17;
	private FTPFile _ftpFile18;
	private FTPFile _ftpFile19;
	private FTPFile _ftpFile20;
	private FTPFile _ftpFile21;
	private FTPFile _ftpFile22;
	private FTPFile _ftpFile23;
	private FTPFile _ftpFile24;
	private FTPFile _ftpFile25;
	private FTPFile _ftpFile26;
	private FTPFile _ftpFile27;
	private FTPFile _ftpFile28;
	private FTPFile _ftpFile29;
	private FTPFile _ftpFile30;
	private FTPFile _ftpFile31;
	private FTPFile _ftpFile32;
	private FTPFile _ftpFile33;
	private FTPFile _ftpFile34;
	private FTPFile _ftpFile35;
	private FTPFile _ftpFile36;
	private FTPFile _ftpFile37;
	private FTPFile _ftpFile38;
	private FTPFile _ftpFile39;
	private FTPFile _ftpFile40;
	private FTPFile _ftpFile41;
	private FTPFile _ftpFile42;
	private FTPFile _ftpFile43;
	private FTPFile _ftpFile44;
	private FTPFile _ftpFile45;
	private FTPFile _ftpFile46;
	private FTPFile _ftpFile47;
	private FTPFile _ftpFile48;
	private FTPFile _ftpFile49;
	private FTPFile _ftpFile50;
	private FTPFile _ftpFile51;
	private FTPFile _ftpFile52;
	private FTPFile _ftpFile53;
	private FTPFile _ftpFile54;
	private FTPFile _ftpFile55;
	private FTPFile _ftpFile56;
	private FTPFile _ftpFile57;
	private FTPFile _ftpFile58;
	private FTPFile _ftpFile59;
	private FTPFile _ftpFile60;
	private FTPFile _ftpFile61;
	private FTPFile _ftpFile62;
	private FTPFile _ftpFile63;
	private FTPFile _ftpFile64;
	private FTPFile _ftpFile65;
	private FTPFile _ftpFile66;
	private FTPFile _ftpFile67;
	private FTPFile _ftpFile68;
	private FTPFile _ftpFile69;
	private FTPFile _ftpFile70;
	private FTPFile _ftpFile71;
	private FTPFile _ftpFile72;
	private FTPFile _ftpFile73;
	private FTPFile _ftpFile74;
	private FTPFile _ftpFile75;
	private FTPFile _ftpFile76;
	private FTPFile _ftpFile77;
	private FTPFile _ftpFile78;
	private FTPFile _ftpFile79;
	private FTPFile _ftpFile80;
	private FTPFile _ftpFile81;
	private FTPFile _ftpFile82;
	private FTPFile _ftpFile83;
	private FTPFile _ftpFile84;
	private FTPFile _ftpFile85;
	private FTPFile _ftpFile86;
	private FTPFile _ftpFile87;
	private FTPFile _ftpFile88;
	private FTPFile _ftpFile89;
	private FTPFile _ftpFile90;
	private FTPFile _ftpFile91;
	private FTPFile _ftpFile92;
	private FTPFile _ftpFile93;
	private FTPFile _ftpFile94;
	private FTPFile _ftpFile95;
	private FTPFile _ftpFile96;
	private FTPFile _ftpFile97;
	private FTPFile _ftpFile98;
	private FTPFile _ftpFile99;
	private FTPFile _ftpFile100;
	private FTPFile _ftpFile101;
	private FTPFile _ftpFile102;
	private FTPFile _ftpFile103;
	private FTPFile _ftpFile104;
	private FTPFile _ftpFile105;
	private FTPFile _ftpFile106;
	private FTPFile _ftpFile107;
	private FTPFile _ftpFile108;
	private FTPFile _ftpFile109;
	private FTPFile _ftpFile110;
	private FTPFile _ftpFile111;
	private FTPFile _ftpFile112;
	private FTPFile _ftpFile113;
	private FTPFile _ftpFile114;
	private FTPFile _ftpFile115;
	private FTPFile _ftpFile116;
	private FTPFile _ftpFile117;
	private FTPFile _ftpFile118;
	private FTPFile _ftpFile119;
	private FTPFile _ftpFile120;
	private FTPFile _ftpFile121;
	private FTPFile _ftpFile122;
	private FTPFile _ftpFile123;
	private FTPFile _ftpFile124;
	private FTPFile _ftpFile125;
	private FTPFile _ftpFile126;
	private FTPFile _ftpFile127;
	private FTPFile _ftpFile128;
	private FTPFile _ftpFile129;
	private FTPFile _ftpFile130;
	private FTPFile _ftpFile131;
	private FTPFile _ftpFile132;
	private FTPFile _ftpFile133;
	private FTPFile _ftpFile134;
	private FTPFile _ftpFile135;
	private FTPFile _ftpFile136;
	private FTPFile _ftpFile137;
	private FTPFile _ftpFile138;
	private FTPFile _ftpFile139;
	private FTPFile _ftpFile140;
	private FTPFile _ftpFile141;
	private FTPFile _ftpFile142;
	private FTPFile _ftpFile143;
	private FTPFile _ftpFile144;
	private FTPFile _ftpFile145;
	private FTPFile _ftpFile146;
	private FTPFile _ftpFile147;
	private FTPFile _ftpFile148;
	private FTPFile _ftpFile149;
	private FTPFile _ftpFile150;
	private FTPFile _ftpFile151;
	private FTPFile _ftpFile152;
	private FTPFile _ftpFile153;
	private FTPFile _ftpFile154;
	private FTPFile _ftpFile155;
	private FTPFile _ftpFile156;
	private FTPFile _ftpFile157;
	private FTPFile _ftpFile158;
	private FTPFile _ftpFile159;
	private FTPFile _ftpFile160;
	private FTPFile _ftpFile161;
	private FTPFile _ftpFile162;
	private FTPFile _ftpFile163;
	private FTPFile _ftpFile164;
	private FTPFile _ftpFile165;
	private FTPFile _ftpFile166;
	private FTPFile _ftpFile167;
	private FTPFile _ftpFile168;
	private FTPFile _ftpFile169;
	private FTPFile _ftpFile170;
	private FTPFile _ftpFile171;
	private FTPFile _ftpFile172;
	private FTPFile _ftpFile173;
	private FTPFile _ftpFile174;
	private FTPFile _ftpFile175;
	private FTPFile _ftpFile176;
	private FTPFile _ftpFile177;
	private FTPFile _ftpFile178;
	private FTPFile _ftpFile179;
	private FTPFile _ftpFile18
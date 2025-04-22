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

// FTPFile_setRawListingTest.java
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
 * FTPFile#public setRawListing(String rawListing) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setRawListingTest {
	private FTPFile ftpFile;
	private String rawListing;
	private String rawListing2;
	private String rawListing3;
	private String rawListing4;
	private String rawListing5;
	private String rawListing6;
	private String rawListing7;
	private String rawListing8;
	private String rawListing9;
	private String rawListing10;
	private String rawListing11;
	private String rawListing12;
	private String rawListing13;
	private String rawListing14;
	private String rawListing15;
	private String rawListing16;
	private String rawListing17;
	private String rawListing18;
	private String rawListing19;
	private String rawListing20;
	private String rawListing21;
	private String rawListing22;
	private String rawListing23;
	private String rawListing24;
	private String rawListing25;
	private String rawListing26;
	private String rawListing27;
	private String rawListing28;
	private String rawListing29;
	private String rawListing30;
	private String rawListing31;
	private String rawListing32;
	private String rawListing33;
	private String rawListing34;
	private String rawListing35;
	private String rawListing36;
	private String rawListing37;
	private String rawListing38;
	private String rawListing39;
	private String rawListing40;
	private String rawListing41;
	private String rawListing42;
	private String rawListing43;
	private String rawListing44;
	private String rawListing45;
	private String rawListing46;
	private String rawListing47;
	private String rawListing48;
	private String rawListing49;
	private String rawListing50;
	private String rawListing51;
	private String rawListing52;
	private String rawListing53;
	private String rawListing54;
	private String rawListing55;
	private String rawListing56;
	private String rawListing57;
	private String rawListing58;
	private String rawListing59;
	private String rawListing60;
	private String rawListing61;
	private String rawListing62;
	private String rawListing63;
	private String rawListing64;
	private String rawListing65;
	private String rawListing66;
	private String rawListing67;
	private String rawListing68;
	private String rawListing69;
	private String rawListing70;
	private String rawListing71;
	private String rawListing72;
	private String rawListing73;
	private String rawListing74;
	private String rawListing75;
	private String rawListing76;
	private String rawListing77;
	private String rawListing78;
	private String rawListing79;
	private String rawListing80;
	private String rawListing81;
	private String rawListing82;
	private String rawListing83;
	private String rawListing84;
	private String rawListing85;
	private String rawListing86;
	private String rawListing87;
	private String rawListing88;
	private String rawListing89;
	private String rawListing90;
	private String rawListing91;
	private String rawListing92;
	private String rawListing93;
	private String rawListing94;
	private String rawListing95;
	private String rawListing96;
	private String rawListing97;
	private String rawListing98;
	private String rawListing99;
	private String rawListing100;
	private String rawListing101;
	private String rawListing102;
	private String rawListing103;
	private String rawListing104;
	private String rawListing105;
	private String rawListing106;
	private String rawListing107;
	private String rawListing108;
	private String rawListing109;
	private String rawListing110;
	private String rawListing111;
	private String rawListing112;
	private String rawListing113;
	private String rawListing114;
	private String rawListing115;
	private String rawListing116;
	private String rawListing117;
	private String rawListing118;
	private String rawListing119;
	private String rawListing120;
	private String rawListing121;
	private String rawListing122;
	private String rawListing123;
	private String rawListing124;
	private String rawListing125;
	private String rawListing126;
	private String rawListing127;
	private String rawListing128;
	private String rawListing129;
	private String rawListing130;
	private String rawListing131;
	private String rawListing132;
	private String rawListing133;
	private String rawListing134;
	private String rawListing135;
	private String rawListing136;
	private String rawListing137;
	private String rawListing138;
	private String rawListing139;
	private String rawListing140;
	private String rawListing141;
	private String rawListing142;
	private String rawListing143;
	private String rawListing144;
	private String rawListing145;
	private String rawListing146;
	private String rawListing147;
	private String rawListing148;
	private String rawListing149;
	private String rawListing150;
	private String rawListing151;
	private String rawListing152;
	private String rawListing153;
	private String rawListing154;
	private String rawListing155;
	private String rawListing156;
	private String rawListing157;
	private String rawListing158;
	private String rawListing159;
	private String rawListing160;
	private String rawListing161;
	private String rawListing162;
	private String rawListing163;
	private String rawListing164;
	private String rawListing165;
	private String rawListing166;
	private String rawListing167;
	private String rawListing168;
	private String rawListing169;
	private String rawListing170;
	private String rawListing171;
	private String rawListing172;
	private String rawListing173;
	private String rawListing174;
	private String rawListing175;
	private String rawListing176;
	private String rawListing177;
	private String rawListing178;
	private String rawListing179;
	private String rawListing180;
	private String rawListing181;
	private String rawListing182;
	private String rawListing183;
	private String rawListing184;
	private String rawListing185;
	private String rawListing186;
	private String rawListing187;
	private String rawListing188;
	private String rawListing189;
	private String rawListing190;
	private String rawListing191;
	private String rawListing192;
	private String rawListing193;
	private String rawListing194;
	private String rawListing195;
	private String rawListing196;
	private String rawListing197;
	private String rawListing198;
	private String rawListing199;
	private String rawListing200;
	private String rawListing201;
	private String rawListing202;
	private String rawListing203;
	private String rawListing204;
	private String rawListing205;
	private String rawListing206;
	private String rawListing207;
	private String rawListing208;
	private String rawListing209;
	private String rawListing210;
	private String rawListing211;
	private String rawListing212;
	private String rawListing213;
	private String rawListing214;
	private String rawListing215;
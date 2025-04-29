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

// FTPFile_setNameTest.java
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
 * FTPFile#public setName(String name) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setNameTest {
	private FTPFile ftpFile;
	private String name;
	private String name2;
	private String name3;
	private String name4;
	private String name5;
	private String name6;
	private String name7;
	private String name8;
	private String name9;
	private String name10;
	private String name11;
	private String name12;
	private String name13;
	private String name14;
	private String name15;
	private String name16;
	private String name17;
	private String name18;
	private String name19;
	private String name20;
	private String name21;
	private String name22;
	private String name23;
	private String name24;
	private String name25;
	private String name26;
	private String name27;
	private String name28;
	private String name29;
	private String name30;
	private String name31;
	private String name32;
	private String name33;
	private String name34;
	private String name35;
	private String name36;
	private String name37;
	private String name38;
	private String name39;
	private String name40;
	private String name41;
	private String name42;
	private String name43;
	private String name44;
	private String name45;
	private String name46;
	private String name47;
	private String name48;
	private String name49;
	private String name50;
	private String name51;
	private String name52;
	private String name53;
	private String name54;
	private String name55;
	private String name56;
	private String name57;
	private String name58;
	private String name59;
	private String name60;
	private String name61;
	private String name62;
	private String name63;
	private String name64;
	private String name65;
	private String name66;
	private String name67;
	private String name68;
	private String name69;
	private String name70;
	private String name71;
	private String name72;
	private String name73;
	private String name74;
	private String name75;
	private String name76;
	private String name77;
	private String name78;
	private String name79;
	private String name80;
	private String name81;
	private String name82;
	private String name83;
	private String name84;
	private String name85;
	private String name86;
	private String name87;
	private String name88;
	private String name89;
	private String name90;
	private String name91;
	private String name92;
	private String name93;
	private String name94;
	private String name95;
	private String name96;
	private String name97;
	private String name98;
	private String name99;
	private String name100;
	private String name101;
	private String name102;
	private String name103;
	private String name104;
	private String name105;
	private String name106;
	private String name107;
	private String name108;
	private String name109;
	private String name110;
	private String name111;
	private String name112;
	private String name113;
	private String name114;
	private String name115;
	private String name116;
	private String name117;
	private String name118;
	private String name119;
	private String name120;
	private String name121;
	private String name122;
	private String name123;
	private String name124;
	private String name125;
	private String name126;
	private String name127;
	private String name128;
	private String name129;
	private String name130;
	private String name131;
	private String name132;
	private String name133;
	private String name134;
	private String name135;
	private String name136;
	private String name137;
	private String name138;
	private String name139;
	private String name140;
	private String name141;
	private String name142;
	private String name143;
	private String name144;
	private String name145;
	private String name146;
	private String name147;
	private String name148;
	private String name149;
	private String name150;
	private String name151;
	private String name152;
	private String name153;
	private String name154;
	private String name155;
	private String name156;
	private String name157;
	private String name158;
	private String name159;
	private String name160;
	private String name161;
	private String name162;
	private String name163;
	private String name164;
	private String name165;
	private String name166;
	private String name167;
	private String name168;
	private String name169;
	private String name170;
	private String name171;
	private String name172;
	private String name173;
	private String name174;
	private String name175;
	private String name176;
	private String name177;
	private String name178;
	private String name179;
	private String name180;
	private String name181;
	private String name182;
	private String name183;
	private String name184;
	private String name185;
	private String name186;
	private String name187;
	private String name188;
	private String name189;
	private String name190;
	private String name191;
	private String name192;
	private String name193;
	private String name194;
	private String name195;
	private String name196;
	private String name197;
	private String name198;
	private String name199;
	private String name200;
	private String name201;
	private String name202;
	private String name203;
	private String name204;
	private String name205;
	private String name206;
	private String name207;
	private String name208;
	private String name209;
	private String name210;
	private String name211;
	private String name212;
	private String name213;
	private String name214;
	private String name215;
	private String name216;
	private String name217;
	private String name218;
	private String name219;
	private String name220;
	private String name221;
	private String name222;
	private String name223;
	private String name224;
	private String name225;
	private String name226;
	private String name227;
	private String name228;
	private String name229;
	private String name230;
	private String name231;
	private String name232;
	private String name233;
	private String name234;
	private String name235;
	private String name236;
	private String name237;
	private String name238;
	private String name239
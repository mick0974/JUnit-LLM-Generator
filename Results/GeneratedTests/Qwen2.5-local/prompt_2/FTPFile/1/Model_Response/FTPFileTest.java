Generate unit tests in Java for FTPFile to achieve
100% line coverage for this class.
Dont use @Before and @After test methods.
Make tests as atomic as possible.
All tests should be for JUnit 4.
Name all methods according to the template -
[MethodUnderTest][Scenario]Test, and use only
English letters.
The source code of class under test is as follows:
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

Here's how you might write some unit tests for the `FTPFile` class using JUnit 4:

```java
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class FTPFileTest {

    @Test
    public void testConstructorCreatesEmptyFTPFile() {
        FTPFile ftpFile = new FTPFile();
        assertEquals(UNKNOWN_TYPE, ftpFile.getType());
        assertTrue(ftpFile.getPermissions()[USER_ACCESS][READ_PERMISSION]);
        assertNull(ftpFile.getRawListing());
        assertEquals("", ftpFile.getUser());
        assertEquals("", ftpFile.getGroup());
        assertNull(ftpFile.getTimestamp());
        assertNull(ftpFile.getName());
    }

    @Test
    public void testConstructorWithRawListingCreatesInvalidFTPFile() {
        FTPFile ftpFile = new FTPFile("invalid listing");
        assertFalse(ftpFile.isValid());
        assertEquals("invalid listing", ftpFile.getRawListing());
    }

    @Test
    public void testSetAndGetRawListing() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setRawListing("valid listing");
        assertEquals("valid listing", ftpFile.getRawListing());
    }

    @Test
    public void testIsFileType() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(FILE_TYPE);
        assertTrue(ftpFile.isFile());
        assertFalse(ftpFile.isDirectory());
        assertFalse(ftpFile.isSymbolicLink());
        assertFalse(ftpFile.isUnknown());
    }

    @Test
    public void testIsDirectoryType() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(DIRECTORY_TYPE);
        assertFalse(ftpFile.isFile());
        assertTrue(ftpFile.isDirectory());
        assertFalse(ftpFile.isSymbolicLink());
        assertFalse(ftpFile.isUnknown());
    }

    @Test
    public void testIsSymbolicLinkType() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(SYMBOLIC_LINK_TYPE);
        assertFalse(ftpFile.isFile());
        assertFalse(ftpFile.isDirectory());
        assertTrue(ftpFile.isSymbolicLink());
        assertFalse(ftpFile.isUnknown());
    }

    @Test
    public void testIsUnknownType() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(UNKNOWN_TYPE);
        assertFalse(ftpFile.isFile());
        assertFalse(ftpFile.isDirectory());
        assertFalse(ftpFile.isSymbolicLink());
        assertTrue(ftpFile.isUnknown());
    }

    @Test
    public void testIsValid() {
        FTPFile ftpFile = new FTPFile();
        assertFalse(ftpFile.isValid());

        ftpFile.setRawListing("valid listing");
        assertTrue(ftpFile.isValid());
    }

    @Test
    public void testSetAndGetName() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setName("test.txt");
        assertEquals("test.txt", ftpFile.getName());
    }

    @Test
    public void testSetAndGetUser() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setUser("user1");
        assertEquals("user1", ftpFile.getUser());
    }

    @Test
    public void testSetAndGetGroup() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setGroup("group1");
        assertEquals("group1", ftpFile.getGroup());
    }

    @Test
    public void testSetAndGetLink() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setLink("target.txt");
        assertEquals("target.txt", ftpFile.getLink());
    }

    @Test
    public void testSetAndGetHardLinkCount() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setHardLinkCount(5);
        assertEquals(5, ftpFile.getHardLinkCount());
    }

    @Test
    public void testSetAndGetFileSize() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setSize(1024);
        assertEquals(1024, ftpFile.getSize());
    }

    @Test
    public void testSetAndGetTimestamp() {
        FTPFile ftpFile = new FTPFile();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        ftpFile.setTimestamp(calendar);
        assertEquals(calendar, ftpFile.getTimestamp());
    }

    @Test
    public void testSetAndGetPermissions() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setPermission(USER_ACCESS, READ_PERMISSION, true);
        ftpFile.setPermission(GROUP_ACCESS, WRITE_PERMISSION, true);
        ftpFile.setPermission(WORLD_ACCESS, EXECUTE_PERMISSION, true);

        assertTrue(ftpFile.hasPermission(USER_ACCESS, READ_PERMISSION));
        assertTrue(ftpFile.hasPermission(GROUP_ACCESS, WRITE_PERMISSION));
        assertTrue(ftpFile.hasPermission(WORLD_ACCESS, EXECUTE_PERMISSION));

        ftpFile.setPermission(USER_ACCESS, READ_PERMISSION, false);
        assertFalse(ftpFile.hasPermission(USER_ACCESS, READ_PERMISSION));
    }

    @Test
    public void testToString() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setRawListing("valid listing");
        assertEquals("valid listing", ftpFile.toString());
    }

    @Test
    public void testToFormattedStringWithoutTimezone() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setRawListing("valid listing");
        ftpFile.setType(FILE_TYPE);
        ftpFile.setPermission(USER_ACCESS, READ_PERMISSION, true);
        ftpFile.setPermission(GROUP_ACCESS, WRITE_PERMISSION, true);
        ftpFile.setPermission(WORLD_ACCESS, EXECUTE_PERMISSION, true);
        ftpFile.setHardLinkCount(5);
        ftpFile.setUser("user1");
        ftpFile.setGroup("group1");
        ftpFile.setSize(1024);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        ftpFile.setTimestamp(calendar);
        ftpFile.setName("test.txt");

        String formattedString = ftpFile.toFormattedString();
        assertNotNull(formattedString);
        assertTrue(formattedString.contains("valid listing"));
        assertTrue(formattedString.contains("-rw-r--r--"));
        assertTrue(formattedString.contains("   5"));
        assertTrue(formattedString.contains("user1"));
        assertTrue(formattedString.contains("group1"));
        assertTrue(formattedString.contains("     1024"));
        assertTrue(formattedString.contains("2022-01-01"));
        assertTrue(formattedString.contains("test.txt"));
    }

    @Test
    public void testToFormattedStringWithTimezone() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setRawListing("valid listing");
        ftpFile.setType(FILE_TYPE);
        ftpFile.setPermission(USER_ACCESS, READ_PERMISSION, true);
        ftpFile.setPermission(GROUP_ACCESS, WRITE_PERMISSION, true);
        ftpFile.setPermission(WORLD_ACCESS, EXECUTE_PERMISSION, true);
        ftpFile.setHardLinkCount(5);
        ftpFile.setUser("user1");
        ftpFile.setGroup("group1");
        ftpFile.setSize(1024);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        ftpFile.setTimestamp(calendar);
        ftpFile.setName("test.txt");

        String formattedString = ftpFile.toFormattedString("UTC");
        assertNotNull(formattedString);
        assertTrue(formattedString.contains("valid listing"));
        assertTrue(formattedString.contains("-rw-r--r--"));
        assertTrue(formattedString.contains("   5"));
        assertTrue(formattedString.contains("user1"));
        assertTrue(formattedString.contains("group1"));
        assertTrue(formattedString.contains("     1024"));
        assertTrue(formattedString.contains("2022-01-01"));
        assertTrue(formattedString.contains("test.txt"));
    }
}
```

These tests cover various aspects of the `FTPFile` class, including constructors, getters/setters, and methods like `toFormattedString`. Each test is atomic and checks specific functionality without relying on shared state or external resources.
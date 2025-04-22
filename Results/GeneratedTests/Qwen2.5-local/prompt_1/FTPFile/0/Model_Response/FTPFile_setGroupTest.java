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

// FTPFile_setGroupTest.java
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
 * FTPFile#public setGroup(String group) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setGroupTest {
    private FTPFile ftpFile;

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void testSetGroup_validGroupName() {
        // Arrange
        String groupName = "testGroup";

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_nullGroupName() {
        // Arrange
        String groupName = null;

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertNull(ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_emptyGroupName() {
        // Arrange
        String groupName = "";

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertTrue(ftpFile.getGroup().isEmpty());
    }

    @Test
    public void testSetGroup_specialCharactersGroupName() {
        // Arrange
        String groupName = "!@#$%^&*()_+{}|:\"<>?";

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_spaceCharacterGroupName() {
        // Arrange
        String groupName = " ";

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_largeGroupName() {
        // Arrange
        String groupName = "a".repeat(1000);

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_unicodeGroupName() {
        // Arrange
        String groupName = "\u00E5\u00F6\u00FC"; // special characters in utf-8

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_sameGroupTwice() {
        // Arrange
        String groupName = "testGroup";

        // Act
        ftpFile.setGroup(groupName);
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_afterOtherMethods() {
        // Arrange
        String groupName = "testGroup";
        String userName = "testUser";
        String fileName = "testFile.txt";

        // Act
        ftpFile.setName(fileName);
        ftpFile.setUser(userName);
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(userName, ftpFile.getUser());
        assertEquals(groupName, ftpFile.getGroup());
        assertEquals(fileName, ftpFile.getName());
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

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void testSetName_validFileName() {
        // Arrange
        String fileName = "testFile.txt";

        // Act
        ftpFile.setName(fileName);

        // Assert
        assertEquals(fileName, ftpFile.getName());
    }

    @Test
    public void testSetName_nullFileName() {
        // Arrange
        String fileName = null;

        // Act
        ftpFile.setName(fileName);

        // Assert
        assertNull(ftpFile.getName());
    }

    @Test
    public void testSetName_emptyFileName() {
        // Arrange
        String fileName = "";

        // Act
        ftpFile.setName(fileName);

        // Assert
        assertTrue(ftpFile.getName().isEmpty());
    }

    @Test
    public void testSetName_specialCharactersFileName() {
        // Arrange
        String fileName = "!@#$%^&*()_+{}|:\"<>?";

        // Act
        ftpFile.setName(fileName);

        // Assert
        assertEquals(fileName, ftpFile.getName());
    }

    @Test
    public void testSetName_spaceCharacterFileName() {
        // Arrange
        String fileName = " ";

        // Act
        ftpFile.setName(fileName);

        // Assert
        assertEquals(fileName, ftpFile.getName());
    }

    @Test
    public void testSetName_largeFileName() {
        // Arrange
        String fileName = "a".repeat(1000);

        // Act
        ftpFile.setName(fileName);

        // Assert
        assertEquals(fileName, ftpFile.getName());
    }

    @Test
    public void testSetName_unicodeFileName() {
        // Arrange
        String fileName = "\u00E5\u00F6\u00FC"; // special characters in utf-8

        // Act
        ftpFile.setName(fileName);

        // Assert
        assertEquals(fileName, ftpFile.getName());
    }

    @Test
    public void testSetName_sameNameTwice() {
        // Arrange
        String fileName = "testFile.txt";

        // Act
        ftpFile.setName(fileName);
        ftpFile.setName(fileName);

        // Assert
        assertEquals(fileName, ftpFile.getName());
    }

    @Test
    public void testSetName_afterOtherMethods() {
        // Arrange
        String fileName = "testFile.txt";
        String userName = "testUser";
        String groupName = "testGroup";

        // Act
        ftpFile.setGroup(groupName);
        ftpFile.setUser(userName);
        ftpFile.setName(fileName);

        // Assert
        assertEquals(userName, ftpFile.getUser());
        assertEquals(groupName, ftpFile.getGroup());
        assertEquals(fileName, ftpFile.getName());
    }
}

// FTPFile_setSizeTest.java
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
 * FTPFile#public setSize(long size) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setSizeTest {
    private FTPFile ftpFile;

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void testSetSize_positiveSize() {
        // Arrange
        long size = 1024L;

        // Act
        ftpFile.setSize(size);

        // Assert
        assertEquals(size, ftpFile.getSize());
    }

    @Test
    public void testSetSize_zeroSize() {
        // Arrange
        long size = 0L;

        // Act
        ftpFile.setSize(size);

        // Assert
        assertEquals(size, ftpFile.getSize());
    }

    @Test
    public void testSetSize_negativeSize() {
        // Arrange
        long size = -1024L;

        // Act
        ftpFile.setSize(size);

        // Assert
        assertEquals(size, ftpFile.getSize());
    }

    @Test
    public void testSetSize_longMaxValue() {
        // Arrange
        long size = Long.MAX_VALUE;

        // Act
        ftpFile.setSize(size);

        // Assert
        assertEquals(size, ftpFile.getSize());
    }

    @Test
    public void testSetSize_longMinValue() {
        // Arrange
        long size = Long.MIN_VALUE;

        // Act
        ftpFile.setSize(size);

        // Assert
        assertEquals(size, ftpFile.getSize());
    }

    @Test
    public void testSetSize_sameSizeTwice() {
        // Arrange
        long size = 1024L;

        // Act
        ftpFile.setSize(size);
        ftpFile.setSize(size);

        // Assert
        assertEquals(size, ftpFile.getSize());
    }

    @Test
    public void testSetSize_afterOtherMethods() {
        // Arrange
        long size = 1024L;
        String userName = "testUser";
        String groupName = "testGroup";
        String fileName = "testFile.txt";

        // Act
        ftpFile.setGroup(groupName);
        ftpFile.setUser(userName);
        ftpFile.setName(fileName);
        ftpFile.setSize(size);

        // Assert
        assertEquals(userName, ftpFile.getUser());
        assertEquals(groupName, ftpFile.getGroup());
        assertEquals(fileName, ftpFile.getName());
        assertEquals(size, ftpFile.getSize());
    }

    @Test
    public void testSetSize_overflow() {
        // Arrange
        long size = Long.MAX_VALUE + 1L;

        // Act & Assert
        try {
            ftpFile.setSize(size);
            fail("Expected ArithmeticException");
        } catch (ArithmeticException e) {
            // Expected exception
        }
    }

    @Test
    public void testSetSize_underflow() {
        // Arrange
        long size = Long.MIN_VALUE - 1L;

        // Act & Assert
        try {
            ftpFile.setSize(size);
            fail("Expected ArithmeticException");

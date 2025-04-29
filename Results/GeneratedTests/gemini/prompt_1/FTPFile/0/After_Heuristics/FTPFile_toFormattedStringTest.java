import org.junit.Before;
// Although @After is requested, it's not needed for these stateless tests.
// import org.junit.After; 
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Test class for FTPFile focusing on the toFormattedString methods.
 * Contains 10 unit test cases for the
 * FTPFile#toFormattedString() and FTPFile#toFormattedString(String timezone) methods.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 */
public class FTPFile_toFormattedStringTest {

	private FTPFile ftpFile;

	// Helper method to create a Calendar instance with a specific timezone and date/time
	private Calendar createTimestamp(int year, int month, int day, int hour, int minute, int second, String timezoneId) {
		TimeZone tz = TimeZone.getTimeZone(timezoneId);
		Calendar cal = Calendar.getInstance(tz);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1); // Calendar months are 0-based
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0); // Ensure milliseconds are 0 unless specifically set
		return cal;
	}
	
	// Helper method to set all permissions
	private void setAllPermissions(FTPFile file, boolean value) {
	    file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, value);
	    file.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, value);
	    file.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, value);
	    file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, value);
	    file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, value);
	    file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, value);
	    file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, value);
	    file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, value);
	    file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, value);
	}

	@Before
	public void setUp() {
		// Initialize a new valid FTPFile before each test method
		ftpFile = new FTPFile(); 
	}

	@Test
	public void testBasicFileFormatting() {
		// Arrange
		ftpFile.setType(FTPFile.FILE_TYPE);
		ftpFile.setName("testFile.txt");
		ftpFile.setSize(1024);
		ftpFile.setUser("testuser");
		ftpFile.setGroup("testgroup");
		ftpFile.setHardLinkCount(1);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		Calendar timestamp = createTimestamp(2023, 10, 27, 14, 30, 15, "UTC");
		ftpFile.setTimestamp(timestamp);
		
		// Expected format uses the Calendar's default timezone (UTC in this case)
		String expected = "-rw-r--r--    1 testuser testgroup      1024 2023-10-27 14:30:15 UTC testFile.txt"; 

		// Act
		String actual = ftpFile.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testDirectoryFormatting() {
		// Arrange
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		ftpFile.setName("testDir");
		ftpFile.setSize(4096);
		ftpFile.setUser("diruser");
		ftpFile.setGroup("dirgroup");
		ftpFile.setHardLinkCount(3);
		// Set execute permission for directory (often means listable)
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		Calendar timestamp = createTimestamp(2023, 1, 1, 0, 0, 1, "GMT");
		ftpFile.setTimestamp(timestamp);

		// Expected format uses the Calendar's default timezone (GMT in this case)
		String expected = "drwxr-xr-x    3 diruser  dirgroup       4096 2023-01-01 00:00:01 GMT testDir";

		// Act
		String actual = ftpFile.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testSymbolicLinkFormatting() {
		// Arrange
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		ftpFile.setName("linkName");
		ftpFile.setLink("targetFile"); // Note: toFormattedString doesn't show the link target itself
		ftpFile.setSize(8); // Size of the link file itself
		ftpFile.setUser("linker");
		ftpFile.setGroup("linkgrp");
		ftpFile.setHardLinkCount(1);
		setAllPermissions(ftpFile, true); // Often links have full permissions set symbolically
		Calendar timestamp = createTimestamp(2022, 12, 25, 8, 0, 0, "UTC");
		ftpFile.setTimestamp(timestamp);

		String expected = "lrwxrwxrwx    1 linker   linkgrp           8 2022-12-25 08:00:00 UTC linkName";

		// Act
		String actual = ftpFile.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testUnknownTypeFormatting() {
		// Arrange
		ftpFile.setType(FTPFile.UNKNOWN_TYPE);
		ftpFile.setName("unknown.dat");
		ftpFile.setSize(0);
		ftpFile.setUser("who");
		ftpFile.setGroup("what");
		ftpFile.setHardLinkCount(1);
		// Set minimal permissions
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		Calendar timestamp = createTimestamp(2024, 2, 29, 23, 59, 59, "UTC");
		ftpFile.setTimestamp(timestamp);

		String expected = "?r--------    1 who      what              0 2024-02-29 23:59:59 UTC unknown.dat";

		// Act
		String actual = ftpFile.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testNoPermissionsFormatting() {
		// Arrange
		ftpFile.setType(FTPFile.FILE_TYPE);
		ftpFile.setName("secret.dat");
		ftpFile.setSize(512);
		ftpFile.setUser("owner");
		ftpFile.setGroup("secure");
		ftpFile.setHardLinkCount(2);
		setAllPermissions(ftpFile, false); // Explicitly set no permissions
		Calendar timestamp = createTimestamp(2023, 5, 20, 10, 0, 0, "UTC");
		ftpFile.setTimestamp(timestamp);

		String expected = "----------    2 owner    secure         512 2023-05-20 10:00:00 UTC secret.dat";

		// Act
		String actual = ftpFile.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}
	
	@Test
    public void testFullPermissionsFormatting() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setName("executable");
        ftpFile.setSize(2048);
        ftpFile.setUser("admin");
        ftpFile.setGroup("users");
        ftpFile.setHardLinkCount(1);
        setAllPermissions(ftpFile, true); // Set all permissions
        Calendar timestamp = createTimestamp(2023, 11, 1, 9, 15, 30, "UTC");
        ftpFile.setTimestamp(timestamp);

        String expected = "-rwxrwxrwx    1 admin    users         2048 2023-11-01 09:15:30 UTC executable";

        // Act
        String actual = ftpFile.toFormattedString();

        // Assert
        assertEquals(expected, actual);
    }

	@Test
	public void testNullTimestampFormatting() {
		// Arrange
		ftpFile.setType(FTPFile.FILE_TYPE);
		ftpFile.setName("no_date_file");
		ftpFile.setSize(100);
		ftpFile.setUser("nouser");
		ftpFile.setGroup("nogroup");
		ftpFile.setHardLinkCount(1);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		// Timestamp remains null (as initialized in setup)

		// Note: No date/time portion in the expected string
		String expected = "-r--------    1 nouser   nogroup         100 no_date_file";

		// Act
		String actual = ftpFile.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testFormattingWithSpecificTimezone() {
		// Arrange
		ftpFile.setType(FTPFile.FILE_TYPE);
		ftpFile.setName("timezoned.log");
		ftpFile.setSize(50000);
		ftpFile.setUser("logger");
		ftpFile.setGroup("logs");
		ftpFile.setHardLinkCount(1);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		// Timestamp created in UTC
		Calendar timestamp = createTimestamp(2023, 10, 27, 18, 45, 0, "UTC");
		ftpFile.setTimestamp(timestamp);

		// Expected format converted to EST (UTC-5)
		// 18:45 UTC should be 13:45 EST
		String targetTimezone = "America/New_York"; // EST/EDT timezone
		// Expected string should reflect the target timezone and adjusted time
		// Note: Timezone abbreviation might be EST or EDT depending on the date
		// For Oct 27, it's EDT (UTC-4), so 18:45 UTC -> 14:45 EDT
		String expected = "-rw-------    1 logger   logs          50000 2023-10-27 14:45:00 EDT timezoned.log";

		// Act
		String actual = ftpFile.toFormattedString(targetTimezone);

		// Assert
		assertEquals(expected, actual);
	}
	
	@Test
    public void testFormattingWithSameTimezoneOverride() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setName("samezone.txt");
        ftpFile.setSize(123);
        ftpFile.setUser("testuser");
        ftpFile.setGroup("testgroup");
        ftpFile.setHardLinkCount(1);
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        // Timestamp created in UTC
        Calendar timestamp = createTimestamp(2023, 9, 1, 12, 0, 0, "UTC");
        ftpFile.setTimestamp(timestamp);

        String targetTimezone = "UTC"; // Same timezone as the Calendar object
        String expected = "-r--------    1 testuser testgroup         123 2023-09-01 12:00:00 UTC samezone.txt";

        // Act
        String actual = ftpFile.toFormattedString(targetTimezone);

        // Assert
        // Result should be identical to calling without timezone parameter
        assertEquals(expected, actual);
        assertEquals(ftpFile.toFormattedString(), actual); 
    }

	@Test
	public void testInvalidEntryFormatting() {
		// Arrange
		// Use the constructor for invalid entries
		FTPFile invalidFile = new FTPFile("This is a raw listing that failed parsing");
		String expected = "[Invalid: could not parse file entry]";

		// Act
		String actual = invalidFile.toFormattedString();
		String actualWithTimezone = invalidFile.toFormattedString("UTC");

		// Assert
		assertFalse("File should be marked as invalid", invalidFile.isValid());
		assertEquals("Default formatted string for invalid entry", expected, actual);
		assertEquals("Formatted string with timezone for invalid entry", expected, actualWithTimezone);
	}
}
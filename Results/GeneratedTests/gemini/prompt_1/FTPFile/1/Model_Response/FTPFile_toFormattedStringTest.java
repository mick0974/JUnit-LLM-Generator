```java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Test class for FTPFile#toFormattedString() and FTPFile#toFormattedString(String timezone).
 * Contains 10 unit test cases covering typical and edge scenarios.
 * Follows the Arrange-Act-Assert pattern.
 */
public class FTPFile_toFormattedStringTest {

	private FTPFile file;

	@Before
	public void setUp() {
		// Initialize a common FTPFile object for modification in tests
		file = new FTPFile();
	}

	// Helper method to create a Calendar instance with specific date/time and timezone
	private Calendar createTimestamp(int year, int month, int day, int hour, int minute, int second, String timezoneId) {
		TimeZone tz = TimeZone.getTimeZone(timezoneId);
		Calendar cal = new GregorianCalendar(tz);
		// Note: Calendar months are 0-based (January=0)
		cal.set(year, month - 1, day, hour, minute, second);
		cal.set(Calendar.MILLISECOND, 0); // Ensure milliseconds are zero unless specifically tested
		return cal;
	}

	// Helper method to set all standard permissions (rwx) for a given access level
	private void setAllPermissions(int access) {
		file.setPermission(access, FTPFile.READ_PERMISSION, true);
		file.setPermission(access, FTPFile.WRITE_PERMISSION, true);
		file.setPermission(access, FTPFile.EXECUTE_PERMISSION, true);
	}

	@Test
	public void testToFormattedString_TypicalFile() {
		// Arrange
		file.setType(FTPFile.FILE_TYPE);
		file.setName("test-file.txt");
		file.setSize(1024);
		file.setUser("testuser");
		file.setGroup("testgroup");
		file.setHardLinkCount(1);
		setAllPermissions(FTPFile.USER_ACCESS);
		file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		Calendar timestamp = createTimestamp(2023, 10, 27, 14, 30, 15, "UTC");
		file.setTimestamp(timestamp);

		// Expected format: -rwxr--r--    1 testuser testgroup       1024 2023-10-27 14:30:15 UTC test-file.txt
		String expected = "-rwxr--r--    1 testuser testgroup       1024 2023-10-27 14:30:15 UTC test-file.txt";

		// Act
		String actual = file.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testToFormattedString_TypicalDirectory() {
		// Arrange
		file.setType(FTPFile.DIRECTORY_TYPE);
		file.setName("test-dir");
		file.setSize(4096);
		file.setUser("dir_owner");
		file.setGroup("dir_group");
		file.setHardLinkCount(3);
		// Typical dir permissions: drwxr-xr-x
		setAllPermissions(FTPFile.USER_ACCESS);
		file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		Calendar timestamp = createTimestamp(2024, 1, 1, 0, 0, 1, "GMT");
		file.setTimestamp(timestamp);

		// Expected format: drwxr-xr-x    3 dir_owner dir_group       4096 2024-01-01 00:00:01 GMT test-dir
		String expected = "drwxr-xr-x    3 dir_owner dir_group       4096 2024-01-01 00:00:01 GMT test-dir";

		// Act
		String actual = file.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testToFormattedString_SymbolicLink() {
		// Arrange
		file.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		file.setName("link_name");
		file.setLink("target_file"); // Note: Link target is not part of formatted string
		file.setSize(11); // Size of the link itself
		file.setUser("symlinker");
		file.setGroup("users");
		file.setHardLinkCount(1);
		// Typical link permissions: lrwxrwxrwx
		setAllPermissions(FTPFile.USER_ACCESS);
		setAllPermissions(FTPFile.GROUP_ACCESS);
		setAllPermissions(FTPFile.WORLD_ACCESS);
		Calendar timestamp = createTimestamp(2023, 12, 25, 8, 0, 0, "CET");
		file.setTimestamp(timestamp);

		// Expected format: lrwxrwxrwx    1 symlinker users            11 2023-12-25 08:00:00 CET link_name
		String expected = "lrwxrwxrwx    1 symlinker users            11 2023-12-25 08:00:00 CET link_name";

		// Act
		String actual = file.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testToFormattedString_UnknownType() {
		// Arrange
		file.setType(FTPFile.UNKNOWN_TYPE);
		file.setName("unknown.dat");
		file.setSize(0);
		file.setUser("nobody");
		file.setGroup("nogroup");
		file.setHardLinkCount(1);
		// No permissions set
		Calendar timestamp = createTimestamp(2022, 5, 15, 10, 0, 0, "PST"); // PST is UTC-8
		timestamp.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles")); // Use Olson ID for daylight saving clarity
		file.setTimestamp(timestamp);
        // Get the actual TimeZone abbreviation for the specific date
        String tzShort = timestamp.getTimeZone().getDisplayName(timestamp.getTimeZone().inDaylightTime(timestamp.getTime()), TimeZone.SHORT);


		// Expected format: ?---------    1 nobody   nogroup            0 2022-05-15 10:00:00 PDT unknown.dat (or PST if not DST)
        // Check the timezone name carefully for the given date
		String expected = String.format("?---------    1 nobody   nogroup            0 2022-05-15 10:00:00 %s unknown.dat", tzShort);


		// Act
		String actual = file.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testToFormattedString_NullTimestamp() {
		// Arrange
		file.setType(FTPFile.FILE_TYPE);
		file.setName("no-time.log");
		file.setSize(50);
		file.setUser("logger");
		file.setGroup("logs");
		file.setHardLinkCount(1);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		file.setTimestamp(null); // Explicitly null timestamp

		// Expected format: -rw-------    1 logger   logs            50 no-time.log
		// Note the absence of the date/time part
		String expected = "-rw-------    1 logger   logs            50 no-time.log";

		// Act
		String actual = file.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testToFormattedString_TimestampDateOnly() {
		// Arrange
		file.setType(FTPFile.FILE_TYPE);
		file.setName("date-only.cfg");
		file.setSize(200);
		file.setUser("config");
		file.setGroup("admin");
		file.setHardLinkCount(1);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		// Create timestamp but clear time fields
		Calendar timestamp = createTimestamp(2023, 11, 1, 12, 0, 0, "UTC");
		timestamp.clear(Calendar.HOUR_OF_DAY);
		timestamp.clear(Calendar.MINUTE);
		timestamp.clear(Calendar.SECOND);
		timestamp.clear(Calendar.MILLISECOND);
		// Note: The code checks `isSet(Calendar.HOUR_OF_DAY)`, so clearing it should suppress time output.
		file.setTimestamp(timestamp);

		// Expected format: -r--------    1 config   admin          200 2023-11-01 date-only.cfg
		// Time part should be omitted
		String expected = "-r--------    1 config   admin          200 2023-11-01 date-only.cfg";

		// Act
		String actual = file.toFormattedString();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testToFormattedString_WithTimezoneOverride() {
		// Arrange
		file.setType(FTPFile.FILE_TYPE);
		file.setName("timezoned.file");
		file.setSize(3000);
		file.setUser("tester");
		file.setGroup("qa");
		file.setHardLinkCount(1);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		// Timestamp originally in EST (UTC-5)
		Calendar timestamp = createTimestamp(2023, 1, 15, 10, 0, 0, "EST"); // EST is UTC-5
		file.setTimestamp(timestamp);

		// Expected format with override to "GMT":
		// Original: 2023-01-15 10:00:00 EST
		// GMT is UTC+0, EST is UTC-5, so GMT time is 15:00:00
		// -r--------    1 tester   qa           3000 2023-01-15 15:00:00 GMT timezoned.file
		String expected = "-r--------    1 tester   qa           3000 2023-01-15 15:00:00 GMT timezoned.file";
		String targetTimezone = "GMT";

		// Act
		String actual = file.toFormattedString(targetTimezone);

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testToFormattedString_WithTimezoneOverride_Null() {
		// Arrange
		file.setType(FTPFile.FILE_TYPE);
		file.setName("null-tz.data");
		file.setSize(500);
		file.setUser("datauser");
		file.setGroup("datagrp");
		file.setHardLinkCount(1);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		Calendar timestamp = createTimestamp(2023, 6, 20, 18, 45, 0, "CET"); // CET is UTC+1 (or +2 in summer)
		timestamp.setTimeZone(TimeZone.getTimeZone("Europe/Paris")); // Use Olson ID for DST clarity
		file.setTimestamp(timestamp);
        // Get the actual TimeZone abbreviation for the specific date (CEST = UTC+2)
        String tzShort = timestamp.getTimeZone().getDisplayName(timestamp.getTimeZone().inDaylightTime(timestamp.getTime()), TimeZone.SHORT);


		// Expected format: Should be same as calling toFormattedString() without args
		// -r--------    1 datauser datagrp         500 2023-06-20 18:45:00 CEST null-tz.data (CEST likely)
		String expected = String.format("-r--------    1 datauser datagrp         500 2023-06-20 18:45:00 %s null-tz.data", tzShort);
		String targetTimezone = null; // Explicitly null

		// Act
		String actualWithNull = file.toFormattedString(targetTimezone);
		String actualWithoutArg = file.toFormattedString();

		// Assert
		assertEquals("Calling with null timezone should yield same result as no-arg call", expected, actualWithNull);
		assertEquals("No-arg call should match expected format", expected, actualWithoutArg);

	}

    @Test
	public void testToFormattedString_WithTimezoneOverride_SameZone() {
		// Arrange
		file.setType(FTPFile.FILE_TYPE);
		file.setName("same-tz.data");
		file.setSize(600);
		file.setUser("test");
		file.setGroup("test");
		file.setHardLinkCount(1);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        String originalTimezoneId = "Asia/Tokyo"; // UTC+9
		Calendar timestamp = createTimestamp(2024, 2, 29, 9, 0, 0, originalTimezoneId);
		file.setTimestamp(timestamp);
        // Get the actual TimeZone abbreviation for the specific date
        String tzShort = timestamp.getTimeZone().getDisplayName(false, TimeZone.SHORT); // JST


		// Expected format: Should be same as calling toFormattedString() without args
		// -r--------    1 test     test           600 2024-02-29 09:00:00 JST same-tz.data
		String expected = String.format("-r--------    1 test     test           600 2024-02-29 09:00:00 %s same-tz.data", tzShort);
		String targetTimezone = originalTimezoneId; // Use the same timezone ID

		// Act
		String actualWithSameZone = file.toFormattedString(targetTimezone);
		String actualWithoutArg = file.toFormattedString();

		// Assert
		assertEquals("Calling with same timezone should yield same result as no-arg call", expected, actualWithSameZone);
		assertEquals("No-arg call should match expected format", expected, actualWithoutArg);
	}

	@Test
	public void testToFormattedString_InvalidEntry() {
		// Arrange
		String rawListing = "This line failed parsing";
		FTPFile invalidFile = new FTPFile(rawListing); // Use constructor for invalid entries
		String expected = "[Invalid: could not parse file entry]";

		// Act
		String actual = invalidFile.toFormattedString();

		// Assert
		assertEquals(expected, actual);
		assertFalse("File created with raw listing constructor should be invalid", invalidFile.isValid());
		assertEquals("Raw listing should be preserved", rawListing, invalidFile.getRawListing());
	}
}
```
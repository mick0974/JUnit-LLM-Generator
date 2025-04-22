import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

/**
 * JUnit 4 tests for the FTPFile class, aiming for 100% line coverage.
 */
public class FTPFileTest {

	// --- Constructor Tests ---

	@Test
	public void constructorDefaultInitializesFieldsTest() {
		FTPFile file = new FTPFile();

		// Verify default values
		assertEquals(FTPFile.UNKNOWN_TYPE, file.getType());
		assertEquals(0, file.getHardLinkCount()); // Default set in constructor
		assertEquals(-1L, file.getSize());         // Default set in constructor
		assertEquals("", file.getUser());          // Default set in constructor
		assertEquals("", file.getGroup());         // Default set in constructor
		assertNull(file.getTimestamp());
		assertNull(file.getName());
		assertNull(file.getRawListing());
		assertNull(file.getLink());
		assertTrue(file.isValid()); // Permissions array is initialized

		// Check default permissions are false
		assertFalse(file.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse(file.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
		assertFalse(file.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
		assertFalse(file.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse(file.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
		assertFalse(file.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION));
		assertFalse(file.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse(file.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION));
		assertFalse(file.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
	}

	@Test
	public void constructorRawListingInitializesFieldsTest() {
		String raw = "drwxr-xr-x 2 user group 4096 Jan 1 10:00 file.txt";
		FTPFile file = new FTPFile(raw);

		// Verify values for invalid file constructor
		assertEquals(raw, file.getRawListing());
		assertFalse(file.isValid()); // Permissions array is null
		assertEquals(FTPFile.UNKNOWN_TYPE, file.getType());
		assertEquals(0, file.getHardLinkCount());
		assertEquals(-1L, file.getSize());
		assertEquals("", file.getUser());
		assertEquals("", file.getGroup());
		assertNull(file.getTimestamp());
		assertNull(file.getName());
		assertNull(file.getLink());

		// hasPermission should always return false for invalid files
		assertFalse(file.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
	}

	// --- Getter/Setter Tests ---

	@Test
	public void setGetRawListingTest() {
		FTPFile file = new FTPFile();
		String raw = "drwxr-xr-x 1 user group 4096 Jan 1 10:00 file.txt";
		file.setRawListing(raw);
		assertEquals(raw, file.getRawListing());
	}

	@Test
	public void setGetTypeTest() {
		FTPFile file = new FTPFile();
		file.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, file.getType());
		file.setType(FTPFile.FILE_TYPE);
		assertEquals(FTPFile.FILE_TYPE, file.getType());
		file.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, file.getType());
		file.setType(FTPFile.UNKNOWN_TYPE);
		assertEquals(FTPFile.UNKNOWN_TYPE, file.getType());
	}

	@Test
	public void setGetNameTest() {
		FTPFile file = new FTPFile();
		String name = "testfile.txt";
		file.setName(name);
		assertEquals(name, file.getName());
	}

	@Test
	public void setGetSizeTest() {
		FTPFile file = new FTPFile();
		long size = 1024L;
		file.setSize(size);
		assertEquals(size, file.getSize());
	}

	@Test
	public void setGetHardLinkCountTest() {
		FTPFile file = new FTPFile();
		int links = 5;
		file.setHardLinkCount(links);
		assertEquals(links, file.getHardLinkCount());
	}

	@Test
	public void setGetGroupTest() {
		FTPFile file = new FTPFile();
		String group = "users";
		file.setGroup(group);
		assertEquals(group, file.getGroup());
	}

	@Test
	public void setGetUserTest() {
		FTPFile file = new FTPFile();
		String user = "testuser";
		file.setUser(user);
		assertEquals(user, file.getUser());
	}

	@Test
	public void setGetLinkTest() {
		FTPFile file = new FTPFile();
		String link = "/path/to/target";
		file.setLink(link);
		assertEquals(link, file.getLink());
	}

	@Test
	public void setGetTimestampTest() {
		FTPFile file = new FTPFile();
		Calendar timestamp = Calendar.getInstance();
		file.setTimestamp(timestamp);
		// Note: setTimestamp does not clone, so the same instance should be returned
		assertEquals(timestamp, file.getTimestamp());
	}

	@Test
	public void setHasPermissionTest() {
		FTPFile file = new FTPFile();

		// Test setting true
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		assertTrue(file.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));

		// Test setting false explicitly
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, false);
		assertFalse(file.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));

		// Test other combinations
		file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
		assertTrue(file.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
		assertFalse(file.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION)); // Should be false by default

		file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		assertTrue(file.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
		assertFalse(file.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION)); // Should be false by default
	}

	// --- Type Check Method Tests ---

	@Test
	public void isDirectoryTrueTest() {
		FTPFile file = new FTPFile();
		file.setType(FTPFile.DIRECTORY_TYPE);
		assertTrue(file.isDirectory());
		assertFalse(file.isFile());
		assertFalse(file.isSymbolicLink());
		assertFalse(file.isUnknown());
	}

	@Test
	public void isFileTrueTest() {
		FTPFile file = new FTPFile();
		file.setType(FTPFile.FILE_TYPE);
		assertFalse(file.isDirectory());
		assertTrue(file.isFile());
		assertFalse(file.isSymbolicLink());
		assertFalse(file.isUnknown());
	}

	@Test
	public void isSymbolicLinkTrueTest() {
		FTPFile file = new FTPFile();
		file.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		assertFalse(file.isDirectory());
		assertFalse(file.isFile());
		assertTrue(file.isSymbolicLink());
		assertFalse(file.isUnknown());
	}

	@Test
	public void isUnknownTrueTest() {
		FTPFile file = new FTPFile();
		file.setType(FTPFile.UNKNOWN_TYPE); // Also the default
		assertFalse(file.isDirectory());
		assertFalse(file.isFile());
		assertFalse(file.isSymbolicLink());
		assertTrue(file.isUnknown());
	}

	// --- isValid() Method Tests ---

	@Test
	public void isValidTrueForDefaultConstructorTest() {
		FTPFile file = new FTPFile();
		assertTrue(file.isValid());
	}

	@Test
	public void isValidFalseForRawListingConstructorTest() {
		FTPFile file = new FTPFile("some raw listing");
		assertFalse(file.isValid());
	}

	// --- toString() Method Test ---

	@Test
	public void toStringReturnsRawListingTest() {
		FTPFile file = new FTPFile();
		assertNull(file.toString()); // Default raw listing is null

		String raw = "drwxr-xr-x 1 user group 4096 Jan 1 10:00 file.txt";
		file.setRawListing(raw);
		assertEquals(raw, file.toString());
	}

	// --- toFormattedString() Method Tests ---

	@Test
	public void toFormattedStringInvalidFileTest() {
		FTPFile file = new FTPFile("raw listing that failed parsing");
		assertEquals("[Invalid: could not parse file entry]", file.toFormattedString());
		assertEquals("[Invalid: could not parse file entry]", file.toFormattedString(null));
		assertEquals("[Invalid: could not parse file entry]", file.toFormattedString("UTC"));
	}

	@Test
	public void toFormattedStringMinimalValidFileTest() {
		FTPFile file = new FTPFile(); // Valid by default
		file.setType(FTPFile.FILE_TYPE);
		file.setName("file.dat");
		// Set minimal required fields for a basic valid entry
		file.setUser("user");
		file.setGroup("group");
		file.setSize(100);
		file.setHardLinkCount(1);

		// Expected format: type permissions links user group size date name
		// Permissions default to --- --- ---
		// Date is null, so it shouldn't appear
		String expected = "----rwxrwxrwx    1 user     group           100 file.dat"; // Example with all permissions for test coverage

		// Set all permissions to true to cover all branches in permissionToString
		for (int access : new int[]{FTPFile.USER_ACCESS, FTPFile.GROUP_ACCESS, FTPFile.WORLD_ACCESS}) {
			for (int perm : new int[]{FTPFile.READ_PERMISSION, FTPFile.WRITE_PERMISSION, FTPFile.EXECUTE_PERMISSION}) {
				file.setPermission(access, perm, true);
			}
		}

		// Now check the string with all permissions set
		expected = "-rwxrwxrwx    1 user     group           100 file.dat";
		assertEquals(expected, file.toFormattedString());
	}

	@Test
	public void toFormattedStringDirectoryTest() {
		FTPFile file = new FTPFile();
		file.setType(FTPFile.DIRECTORY_TYPE);
		file.setName("dir");
		file.setUser("usr");
		file.setGroup("grp");
		file.setSize(4096);
		file.setHardLinkCount(2);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);

		String expected = "drwxr-xr-x    2 usr      grp            4096 dir";
		assertEquals(expected, file.toFormattedString());
	}

	@Test
	public void toFormattedStringSymbolicLinkTest() {
		FTPFile file = new FTPFile();
		file.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		file.setName("link");
		file.setUser("root");
		file.setGroup("wheel");
		file.setSize(10);
		file.setHardLinkCount(1);
		// Set some permissions (e.g., often links are rwxrwxrwx)
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, false); // Example: -w-
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, false);
		file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true); // Example: r-x
		file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, false);
		file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, false);
		file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, false); // Example: ---

		String expected = "lrw-r-x---    1 root     wheel            10 link";
		assertEquals(expected, file.toFormattedString());
	}

    @Test
    public void toFormattedStringUnknownTypeTest() {
        FTPFile file = new FTPFile();
        file.setType(FTPFile.UNKNOWN_TYPE); // Should format as '?'
        file.setName("unknown.thing");
        file.setUser("nobody");
        file.setGroup("nogroup");
        file.setSize(0);
        file.setHardLinkCount(1);
        // Set some permissions
        file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        file.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
        file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);


        String expected = "?r--rw---x    1 nobody   nogroup           0 unknown.thing";
        assertEquals(expected, file.toFormattedString());
    }


	@Test
	public void toFormattedStringWithTimestampNoTimeTest() {
		FTPFile file = new FTPFile();
		file.setType(FTPFile.FILE_TYPE);
		file.setName("timed_file.txt");
		file.setUser("user");
		file.setGroup("group");
		file.setSize(12345);
		file.setHardLinkCount(1);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);

		Calendar timestamp = Calendar.getInstance();
		timestamp.set(2023, Calendar.OCTOBER, 26, 0, 0, 0); // Set date only
		timestamp.set(Calendar.MILLISECOND, 0);
		timestamp.clear(Calendar.HOUR_OF_DAY); // Clear time fields
		timestamp.clear(Calendar.MINUTE);
		timestamp.clear(Calendar.SECOND);
		timestamp.clear(Calendar.MILLISECOND);

		file.setTimestamp(timestamp);

		// Expected format: type permissions links user group size date name
		String expected = "-rw-------    1 user     group         12345 2023-10-26 timed_file.txt";
		assertEquals(expected, file.toFormattedString());
	}

    private Calendar createTimestamp(int year, int month, int day, int hour, int minute, int second, int millisecond, String timezoneId) {
        TimeZone tz = TimeZone.getTimeZone(timezoneId);
        Calendar timestamp = Calendar.getInstance(tz);
        timestamp.set(year, month, day, hour, minute, second);
        timestamp.set(Calendar.MILLISECOND, millisecond);
        return timestamp;
    }

	@Test
	public void toFormattedStringWithTimestampAndTimeTest() {
		FTPFile file = new FTPFile();
		file.setType(FTPFile.FILE_TYPE);
		file.setName("full_time.log");
		file.setUser("logger");
		file.setGroup("daemon");
		file.setSize(9876);
		file.setHardLinkCount(1);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		file.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);

        // Use a specific timezone for predictability
        String testTimezoneId = "GMT"; // Use GMT for simplicity
        Calendar timestamp = createTimestamp(2024, Calendar.FEBRUARY, 29, 14, 30, 15, 500, testTimezoneId);
        file.setTimestamp(timestamp);

		// Expected format includes date, HH:MM:SS.mmm and timezone
		String expected = "-r-----r--    1 logger   daemon         9876 2024-02-29 14:30:15.500 GMT full_time.log";
        assertEquals(expected, file.toFormattedString()); // Should use the calendar's timezone
	}

    @Test
    public void toFormattedStringWithTimestampAndTimeMissingPartsTest() {
        FTPFile file = new FTPFile();
        file.setType(FTPFile.FILE_TYPE);
        file.setName("partial_time.cfg");
        file.setUser("admin");
        file.setGroup("admin");
        file.setSize(50);
        file.setHardLinkCount(1);
        file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        file.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);


        String testTimezoneId = "GMT"; // Use GMT for simplicity

        // 1. Test with Hour only
        Calendar timestampHour = createTimestamp(2024, Calendar.MARCH, 1, 10, 0, 0, 0, testTimezoneId);
        timestampHour.clear(Calendar.MINUTE);
        timestampHour.clear(Calendar.SECOND);
        timestampHour.clear(Calendar.MILLISECOND);
        file.setTimestamp(timestampHour);
        String expectedHour = "-rw-------    1 admin    admin            50 2024-03-01 10 GMT partial_time.cfg";
        assertEquals(expectedHour, file.toFormattedString());

        // 2. Test with Hour and Minute
        Calendar timestampMin = createTimestamp(2024, Calendar.MARCH, 1, 10, 45, 0, 0, testTimezoneId);
        timestampMin.clear(Calendar.SECOND);
        timestampMin.clear(Calendar.MILLISECOND);
        file.setTimestamp(timestampMin);
        String expectedMin = "-rw-------    1 admin    admin            50 2024-03-01 10:45 GMT partial_time.cfg";
        assertEquals(expectedMin, file.toFormattedString());

        // 3. Test with Hour, Minute, Second
        Calendar timestampSec = createTimestamp(2024, Calendar.MARCH, 1, 10, 45, 22, 0, testTimezoneId);
        timestampSec.clear(Calendar.MILLISECOND);
        file.setTimestamp(timestampSec);
        String expectedSec = "-rw-------    1 admin    admin            50 2024-03-01 10:45:22 GMT partial_time.cfg";
        assertEquals(expectedSec, file.toFormattedString());

         // 4. Test with Hour, Minute, Second, Millisecond (already covered by toFormattedStringWithTimestampAndTimeTest)
         // Included here for completeness of this specific scenario test
        Calendar timestampMilli = createTimestamp(2024, Calendar.MARCH, 1, 10, 45, 22, 123, testTimezoneId);
        file.setTimestamp(timestampMilli);
        String expectedMilli = "-rw-------    1 admin    admin            50 2024-03-01 10:45:22.123 GMT partial_time.cfg";
        assertEquals(expectedMilli, file.toFormattedString());
    }

	@Test
	public void toFormattedStringWithTimezoneOverrideTest() {
		FTPFile file = new FTPFile();
		file.setType(FTPFile.FILE_TYPE);
		file.setName("tz_override.dat");
		file.setUser("user");
		file.setGroup("group");
		file.setSize(100);
		file.setHardLinkCount(1);
		file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);

        // Create timestamp in one zone (e.g., EST which is GMT-5, ignoring DST for simplicity)
        String originalTzId = "America/New_York"; // EST = GMT-5 (usually)
        Calendar timestamp = createTimestamp(2023, Calendar.NOVEMBER, 1, 10, 0, 0, 0, originalTzId); // 10:00 New York time
        file.setTimestamp(timestamp);

        // 1. Override to UTC (GMT+0) -> Should be 15:00
        String targetTzUtc = "UTC";
        String expectedUtc = "-r--------    1 user     group           100 2023-11-01 15:00:00.000 UTC tz_override.dat";
        assertEquals(expectedUtc, file.toFormattedString(targetTzUtc));

		// 2. Override to a different zone (e.g., Los Angeles, PST = GMT-8 usually) -> Should be 07:00
        String targetTzPst = "America/Los_Angeles";
        String expectedPst = "-r--------    1 user     group           100 2023-11-01 07:00:00.000 PST tz_override.dat"; // PST or PDT depending on date/Java version
		// We check contains because the timezone abbreviation (PST/PDT) might vary
		assertTrue(file.toFormattedString(targetTzPst).startsWith("-r--------    1 user     group           100 2023-11-01 07:00:00.000"));
        assertTrue(file.toFormattedString(targetTzPst).endsWith("tz_override.dat"));

		// 3. Override with the *same* timezone as the calendar's original timezone
		// This specifically tests the 'if (!newZone.equals(timestamp.getTimeZone()))' condition (false branch)
        TimeZone originalTZ = TimeZone.getTimeZone(originalTzId);
        String expectedOriginalTz = String.format("-r--------    1 user     group           100 2023-11-01 10:00:00.000 %s tz_override.dat",
                                           originalTZ.getDisplayName(false, TimeZone.SHORT)); // Get the short name dynamically
        // Use contains because abbreviation might vary (e.g. EST vs EDT)
        assertTrue(file.toFormattedString(originalTzId).startsWith("-r--------    1 user     group           100 2023-11-01 10:00:00.000"));
        assertTrue(file.toFormattedString(originalTzId).endsWith("tz_override.dat"));


        // 4. Override with null timezone (should behave like no-arg version)
        String expectedNullTz = String.format("-r--------    1 user     group           100 2023-11-01 10:00:00.000 %s tz_override.dat",
                                           originalTZ.getDisplayName(false, TimeZone.SHORT));
        assertTrue(file.toFormattedString(null).startsWith("-r--------    1 user     group           100 2023-11-01 10:00:00.000"));
        assertTrue(file.toFormattedString(null).endsWith("tz_override.dat"));


	}
}
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







    private Calendar createTimestamp(int year, int month, int day, int hour, int minute, int second, int millisecond, String timezoneId) {
        TimeZone tz = TimeZone.getTimeZone(timezoneId);
        Calendar timestamp = Calendar.getInstance(tz);
        timestamp.set(year, month, day, hour, minute, second);
        timestamp.set(Calendar.MILLISECOND, millisecond);
        return timestamp;
    }



}
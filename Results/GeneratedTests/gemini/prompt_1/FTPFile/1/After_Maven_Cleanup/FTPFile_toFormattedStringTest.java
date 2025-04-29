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
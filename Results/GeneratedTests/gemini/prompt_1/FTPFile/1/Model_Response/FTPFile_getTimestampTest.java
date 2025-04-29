```java
import org.junit.Before;
import org.junit.Test; // Ensure using JUnit 4 @Test
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Test class for the FTPFile#getTimestamp() method.
 * Focuses on verifying the retrieval of the timestamp Calendar object.
 * Uses JUnit 4 annotations and assertions.
 */
public class FTPFile_getTimestampTest {

	private FTPFile ftpFile;

	@Before
	public void setUp() {
		// Arrange: Create a new FTPFile instance before each test
		ftpFile = new FTPFile();
	}

	// Test case 1: Test getting the timestamp when it hasn't been set (should be null)
	@Test
	public void testGetTimestamp_WhenNotSet_ShouldReturnNull() {
		// Act
		Calendar timestamp = ftpFile.getTimestamp();

		// Assert
		assertNull("Timestamp should be null when not explicitly set", timestamp);
	}

	// Test case 2: Test getting the timestamp after setting it to a non-null value
	@Test
	public void testGetTimestamp_WhenSetToNonNull_ShouldReturnSameInstance() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance();
		ftpFile.setTimestamp(expectedTimestamp);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null after being set", actualTimestamp);
		// Important: The current implementation returns the same instance, not a clone.
		assertSame("Returned timestamp should be the same instance as the one set", expectedTimestamp, actualTimestamp);
	}

	// Test case 3: Test getting the timestamp after setting it to null
	@Test
	public void testGetTimestamp_WhenSetToNull_ShouldReturnNull() {
		// Arrange
		// Set it to something first, then to null
		ftpFile.setTimestamp(Calendar.getInstance());
		ftpFile.setTimestamp(null);

		// Act
		Calendar timestamp = ftpFile.getTimestamp();

		// Assert
		assertNull("Timestamp should be null after being explicitly set to null", timestamp);
	}

	// Test case 4: Test getting a timestamp with a specific date and time
	@Test
	public void testGetTimestamp_WithSpecificDateTime_ShouldReturnCorrectDateTime() {
		// Arrange
		Calendar expectedTimestamp = new GregorianCalendar(2023, Calendar.OCTOBER, 26, 10, 30, 15);
		// Clear milliseconds for predictable comparison if needed, though getTimeInMillis() handles it
		expectedTimestamp.set(Calendar.MILLISECOND, 0);
		ftpFile.setTimestamp(expectedTimestamp);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertEquals("Timestamps should represent the same moment in time",
				expectedTimestamp.getTimeInMillis(), actualTimestamp.getTimeInMillis());
		// Optionally check individual fields if necessary
		assertEquals("Year should match", 2023, actualTimestamp.get(Calendar.YEAR));
		assertEquals("Month should match", Calendar.OCTOBER, actualTimestamp.get(Calendar.MONTH));
		assertEquals("Day should match", 26, actualTimestamp.get(Calendar.DAY_OF_MONTH));
		assertEquals("Hour should match", 10, actualTimestamp.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute should match", 30, actualTimestamp.get(Calendar.MINUTE));
		assertEquals("Second should match", 15, actualTimestamp.get(Calendar.SECOND));
	}

	// Test case 5: Test getting a timestamp with a specific time zone
	@Test
	public void testGetTimestamp_WithSpecificTimeZone_ShouldReturnCalendarWithSameTimeZone() {
		// Arrange
		TimeZone specificTimeZone = TimeZone.getTimeZone("GMT+5");
		Calendar expectedTimestamp = Calendar.getInstance(specificTimeZone);
		ftpFile.setTimestamp(expectedTimestamp);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertEquals("TimeZones should match", expectedTimestamp.getTimeZone(), actualTimestamp.getTimeZone());
	}

	// Test case 6: Test getting the timestamp after modifying the original Calendar object (post-set)
	// Since setTimestamp does not clone, modification should be reflected.
	@Test
	public void testGetTimestamp_AfterModifyingOriginalCalendar_ShouldReflectChange() {
		// Arrange
		Calendar originalTimestamp = Calendar.getInstance();
		ftpFile.setTimestamp(originalTimestamp);
		long originalTimeMillis = originalTimestamp.getTimeInMillis();

		// Modify the original calendar AFTER setting it in FTPFile
		originalTimestamp.add(Calendar.HOUR, 1);
		long modifiedTimeMillis = originalTimestamp.getTimeInMillis();

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertNotEquals("Original time should differ from modified time", originalTimeMillis, modifiedTimeMillis);
		assertEquals("Returned timestamp should reflect the modification made to the original Calendar",
				modifiedTimeMillis, actualTimestamp.getTimeInMillis());
		assertSame("Returned timestamp should still be the same instance as the modified original",
				originalTimestamp, actualTimestamp);
	}

	// Test case 7: Test getting the timestamp, modifying the *returned* Calendar, and getting again
	// This confirms that getTimestamp returns the internal reference directly.
	@Test
	public void testGetTimestamp_ModifyReturnedCalendar_ShouldReflectOnNextGet() {
		// Arrange
		Calendar initialTimestamp = Calendar.getInstance();
		ftpFile.setTimestamp(initialTimestamp);

		// Act 1: Get the timestamp and modify it
		Calendar firstGet = ftpFile.getTimestamp();
		assertNotNull(firstGet);
		firstGet.add(Calendar.MINUTE, 10); // Modify the returned Calendar
		long modifiedTimeMillis = firstGet.getTimeInMillis();

		// Act 2: Get the timestamp again
		Calendar secondGet = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Second get should not be null", secondGet);
		assertSame("Both gets should return the same instance", firstGet, secondGet);
		assertEquals("Second get should reflect the modification made via the first get",
				modifiedTimeMillis, secondGet.getTimeInMillis());
	}

	// Test case 8: Test with a Calendar object representing a leap year date
	@Test
	public void testGetTimestamp_WithLeapYearDate_ShouldReturnCorrectDate() {
		// Arrange
		// February 29, 2024 (2024 is a leap year)
		Calendar expectedTimestamp = new GregorianCalendar(2024, Calendar.FEBRUARY, 29);
		ftpFile.setTimestamp(expectedTimestamp);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertEquals("Year should be 2024", 2024, actualTimestamp.get(Calendar.YEAR));
		assertEquals("Month should be February", Calendar.FEBRUARY, actualTimestamp.get(Calendar.MONTH));
		assertEquals("Day should be 29", 29, actualTimestamp.get(Calendar.DAY_OF_MONTH));
		assertEquals("Timestamps should represent the same moment",
				expectedTimestamp.getTimeInMillis(), actualTimestamp.getTimeInMillis());
	}

	// Test case 9: Test getting timestamp from an FTPFile created with the raw listing constructor (invalid state)
	@Test
	public void testGetTimestamp_ForInvalidFileEntry_ShouldReturnNull() {
		// Arrange: Create using the constructor for unparseable entries
		FTPFile invalidFile = new FTPFile("drwxr-xr-x 1 user group 4096 Jan 1 10:00 invalid");
		// This constructor leaves _date as null

		// Act
		Calendar timestamp = invalidFile.getTimestamp();

		// Assert
		assertNull("Timestamp should be null for an invalid FTPFile entry", timestamp);
	}

    // Test case 10: Test setting and getting a timestamp with millisecond precision
    @Test
    public void testGetTimestamp_WithMillisecondPrecision_ShouldRetainPrecision() {
        // Arrange
        Calendar expectedTimestamp = Calendar.getInstance();
        expectedTimestamp.set(Calendar.MILLISECOND, 123);
        ftpFile.setTimestamp(expectedTimestamp);
        long expectedMillis = expectedTimestamp.getTimeInMillis(); // Capture exact time

        // Act
        Calendar actualTimestamp = ftpFile.getTimestamp();

        // Assert
        assertNotNull("Timestamp should not be null", actualTimestamp);
        assertEquals("Timestamps should be equal including milliseconds",
                     expectedMillis, actualTimestamp.getTimeInMillis());
        assertEquals("Millisecond field should match",
                     123, actualTimestamp.get(Calendar.MILLISECOND));
        assertSame("Instances should be the same", expectedTimestamp, actualTimestamp);
    }
}
```
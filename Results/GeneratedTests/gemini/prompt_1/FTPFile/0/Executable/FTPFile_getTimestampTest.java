import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Test class for FTPFile#getTimestamp().
 * It contains 10 unit test cases for the
 * FTPFile#public getTimestamp() method.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 * Uses JUnit 4 annotations.
 */
public class FTPFile_getTimestampTest {

	private FTPFile ftpFile;

	/**
	 * Sets up the test fixture.
	 * Called before every test case method.
	 */
	@Before
	public void setUp() {
		// Arrange: Create a new FTPFile instance for each test
		ftpFile = new FTPFile();
	}

	/**
	 * Tears down the test fixture.
	 * Called after every test case method.
	 */
	@After
	public void tearDown() {
		ftpFile = null;
	}

	/**
	 * Test case 1: Test getTimestamp when no timestamp has been set.
	 * It should return null as per the default constructor initialization.
	 */
	@Test
	public void testGetTimestamp_WhenNotSet_ShouldReturnNull() {
		// Arrange (handled in setUp)

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNull("Timestamp should be null when not explicitly set", actualTimestamp);
	}

	/**
	 * Test case 2: Test getTimestamp after setting a specific non-null Calendar instance.
	 * It should return the exact same Calendar instance.
	 */
	@Test
	public void testGetTimestamp_WhenSetWithNonNullCalendar_ShouldReturnSameInstance() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance();
		expectedTimestamp.set(2023, Calendar.OCTOBER, 26, 14, 30, 0); // Example date
		ftpFile.setTimestamp(expectedTimestamp);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null after being set", actualTimestamp);
		assertSame("Should return the exact same Calendar instance provided to setTimestamp",
				expectedTimestamp, actualTimestamp);
	}

	/**
	 * Test case 3: Test getTimestamp after setting the timestamp to null explicitly.
	 * It should return null.
	 */
	@Test
	public void testGetTimestamp_WhenSetToNull_ShouldReturnNull() {
		// Arrange
		// Set an initial value to ensure we are testing the explicit set to null
		ftpFile.setTimestamp(Calendar.getInstance());
		// Now explicitly set to null
		ftpFile.setTimestamp(null);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNull("Timestamp should be null after being explicitly set to null", actualTimestamp);
	}

	/**
	 * Test case 4: Test getTimestamp after setting a Calendar with a specific time zone.
	 * The returned Calendar should retain the original time zone.
	 */
	@Test
	public void testGetTimestamp_WhenSetWithSpecificTimeZone_ShouldRetainTimeZone() {
		// Arrange
		TimeZone specificTimeZone = TimeZone.getTimeZone("PST"); // Pacific Standard Time
		Calendar expectedTimestamp = Calendar.getInstance(specificTimeZone);
		ftpFile.setTimestamp(expectedTimestamp);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertSame("Should return the exact same Calendar instance", expectedTimestamp, actualTimestamp);
		assertEquals("The time zone of the returned Calendar should match the set Calendar",
				specificTimeZone, actualTimestamp.getTimeZone());
	}

	/**
	 * Test case 5: Test getTimestamp after setting a Calendar representing the epoch time (0 ms).
	 * The returned Calendar should represent the epoch.
	 */
	@Test
	public void testGetTimestamp_WhenSetToEpoch_ShouldReturnEpoch() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		expectedTimestamp.setTimeInMillis(0L);
		ftpFile.setTimestamp(expectedTimestamp);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertSame("Should return the exact same Calendar instance", expectedTimestamp, actualTimestamp);
		assertEquals("The time in milliseconds should be 0 for epoch",
				0L, actualTimestamp.getTimeInMillis());
	}

	/**
	 * Test case 6: Test getTimestamp after setting a Calendar representing a future date.
	 */
	@Test
	public void testGetTimestamp_WhenSetToFutureDate_ShouldReturnFutureDate() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance();
		expectedTimestamp.add(Calendar.YEAR, 5); // 5 years into the future
		long expectedMillis = expectedTimestamp.getTimeInMillis();
		ftpFile.setTimestamp(expectedTimestamp);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertSame("Should return the exact same Calendar instance", expectedTimestamp, actualTimestamp);
		assertEquals("The time should match the future date set",
				expectedMillis, actualTimestamp.getTimeInMillis());
	}

	/**
	 * Test case 7: Test getTimestamp after setting a Calendar representing a past date.
	 */
	@Test
	public void testGetTimestamp_WhenSetToPastDate_ShouldReturnPastDate() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance();
		expectedTimestamp.add(Calendar.YEAR, -10); // 10 years in the past
		long expectedMillis = expectedTimestamp.getTimeInMillis();
		ftpFile.setTimestamp(expectedTimestamp);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertSame("Should return the exact same Calendar instance", expectedTimestamp, actualTimestamp);
		assertEquals("The time should match the past date set",
				expectedMillis, actualTimestamp.getTimeInMillis());
	}

	/**
	 * Test case 8: Test getTimestamp after setting the timestamp multiple times.
	 * It should return the last set Calendar instance.
	 */
	@Test
	public void testGetTimestamp_WhenSetMultipleTimes_ShouldReturnLastSetTimestamp() {
		// Arrange
		Calendar firstTimestamp = Calendar.getInstance();
		firstTimestamp.set(2020, Calendar.JANUARY, 1);
		Calendar secondTimestamp = Calendar.getInstance();
		secondTimestamp.set(2022, Calendar.JUNE, 15); // This is the last one set

		ftpFile.setTimestamp(firstTimestamp);
		ftpFile.setTimestamp(secondTimestamp); // Overwrite with the second one

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertSame("Should return the last set Calendar instance", secondTimestamp, actualTimestamp);
		assertNotSame("Should not return the first set Calendar instance", firstTimestamp, actualTimestamp);
		assertEquals("The time should match the last set timestamp",
				secondTimestamp.getTimeInMillis(), actualTimestamp.getTimeInMillis());
	}

	/**
	 * Test case 9: Test getTimestamp after setting it to null and then setting a valid Calendar.
	 * It should return the valid Calendar instance set last.
	 */
	@Test
	public void testGetTimestamp_WhenSetToNullThenSetToNonNull_ShouldReturnNonNull() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance();
		expectedTimestamp.set(2024, Calendar.DECEMBER, 31);

		ftpFile.setTimestamp(null); // Set to null first
		ftpFile.setTimestamp(expectedTimestamp); // Then set to a valid Calendar

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null after being set", actualTimestamp);
		assertSame("Should return the non-null Calendar instance set last", expectedTimestamp, actualTimestamp);
	}

	/**
	 * Test case 10: Verify getTimestamp doesn't clone the Calendar object.
	 * Modifying the Calendar object *after* setting it should be reflected
	 * when getting it back, as it should be the same instance.
	 */
	@Test
	public void testGetTimestamp_ModificationAfterSet_ShouldReflectChanges() {
		// Arrange
		Calendar originalTimestamp = Calendar.getInstance();
		originalTimestamp.set(2023, Calendar.MARCH, 10);
		ftpFile.setTimestamp(originalTimestamp);

		// Modify the original Calendar instance *after* setting it
		long expectedMillisAfterModification = originalTimestamp.getTimeInMillis() + 10000; // Add 10 seconds
		originalTimestamp.setTimeInMillis(expectedMillisAfterModification);

		// Act
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertSame("Should return the exact same Calendar instance", originalTimestamp, actualTimestamp);
		assertEquals("Modification to the original Calendar should be reflected",
				expectedMillisAfterModification, actualTimestamp.getTimeInMillis());
	}
}
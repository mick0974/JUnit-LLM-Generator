```java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.GregorianCalendar;

/**
 * Test class for the FTPFile#setTimestamp(Calendar date) method.
 * Contains 10 unit test cases following the Arrange-Act-Assert pattern,
 * covering typical and edge cases.
 */
public class FTPFile_setTimestampTest {

	private FTPFile ftpFile;

	@Before
	public void setUp() {
		// Arrange: Create a new FTPFile instance before each test
		ftpFile = new FTPFile();
	}

	@After
	public void tearDown() {
		// Clean up resources if needed (not necessary for this simple case)
		ftpFile = null;
	}

	// Test Case 1: Set a typical, non-null timestamp
	@Test
	public void testSetTimestamp_TypicalCase() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance();
		expectedTimestamp.set(2023, Calendar.OCTOBER, 26, 10, 30, 15); // Example date

		// Act
		ftpFile.setTimestamp(expectedTimestamp);
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertEquals("Timestamp should match the set value", expectedTimestamp, actualTimestamp);
		// Also verify object identity as per Javadoc (it's not cloned)
		assertSame("Timestamp object reference should be the same", expectedTimestamp, actualTimestamp);
	}

	// Test Case 2: Set timestamp to null
	@Test
	public void testSetTimestamp_NullCase() {
		// Arrange: ftpFile initially might have a null timestamp, but we ensure it
		ftpFile.setTimestamp(Calendar.getInstance()); // Set a non-null value first
		Calendar nullTimestamp = null;

		// Act
		ftpFile.setTimestamp(nullTimestamp);
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNull("Timestamp should be null after setting it to null", actualTimestamp);
	}

	// Test Case 3: Set a timestamp with a specific non-default timezone
	@Test
	public void testSetTimestamp_SpecificTimezone() {
		// Arrange
		TimeZone specificTimeZone = TimeZone.getTimeZone("GMT+5");
		Calendar expectedTimestamp = Calendar.getInstance(specificTimeZone);
		expectedTimestamp.set(2024, Calendar.JANUARY, 1, 0, 0, 0);

		// Act
		ftpFile.setTimestamp(expectedTimestamp);
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertEquals("Timestamp should match the set value", expectedTimestamp, actualTimestamp);
		assertEquals("Timezone should match the set timestamp's timezone", specificTimeZone, actualTimestamp.getTimeZone());
	}

	// Test Case 4: Set timestamp to the Unix epoch (January 1, 1970)
	@Test
	public void testSetTimestamp_EpochTime() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		expectedTimestamp.setTimeInMillis(0);

		// Act
		ftpFile.setTimestamp(expectedTimestamp);
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertEquals("Timestamp should match the epoch time", expectedTimestamp, actualTimestamp);
		assertEquals("Timestamp time in millis should be 0 for epoch", 0, actualTimestamp.getTimeInMillis());
	}

	// Test Case 5: Set timestamp to a future date
	@Test
	public void testSetTimestamp_FutureTime() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance();
		expectedTimestamp.add(Calendar.YEAR, 10); // 10 years in the future

		// Act
		ftpFile.setTimestamp(expectedTimestamp);
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertEquals("Timestamp should match the future date", expectedTimestamp, actualTimestamp);
		assertTrue("Timestamp should be after the current time", actualTimestamp.getTimeInMillis() > System.currentTimeMillis());
	}

	// Test Case 6: Set timestamp to a past date
	@Test
	public void testSetTimestamp_PastTime() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance();
		expectedTimestamp.set(1995, Calendar.MAY, 15, 12, 0, 0); // A specific past date

		// Act
		ftpFile.setTimestamp(expectedTimestamp);
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertEquals("Timestamp should match the past date", expectedTimestamp, actualTimestamp);
		assertTrue("Timestamp should be before the current time", actualTimestamp.getTimeInMillis() < System.currentTimeMillis());
	}

	// Test Case 7: Set timestamp to a date in a leap year (Feb 29)
	@Test
	public void testSetTimestamp_LeapYearDate() {
		// Arrange
		// Use GregorianCalendar directly for better control if needed, though Calendar.getInstance() usually suffices
		Calendar expectedTimestamp = new GregorianCalendar(2024, Calendar.FEBRUARY, 29, 11, 22, 33); // 2024 is a leap year

		// Act
		ftpFile.setTimestamp(expectedTimestamp);
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertEquals("Timestamp should match the leap year date", expectedTimestamp, actualTimestamp);
		assertEquals("Year should be 2024", 2024, actualTimestamp.get(Calendar.YEAR));
		assertEquals("Month should be February", Calendar.FEBRUARY, actualTimestamp.get(Calendar.MONTH));
		assertEquals("Day should be 29", 29, actualTimestamp.get(Calendar.DAY_OF_MONTH));
	}

	// Test Case 8: Set timestamp with specific time components (hour, minute, second, millisecond)
	@Test
	public void testSetTimestamp_SpecificTimeComponents() {
		// Arrange
		Calendar expectedTimestamp = Calendar.getInstance();
		expectedTimestamp.set(Calendar.HOUR_OF_DAY, 23);
		expectedTimestamp.set(Calendar.MINUTE, 59);
		expectedTimestamp.set(Calendar.SECOND, 58);
		expectedTimestamp.set(Calendar.MILLISECOND, 999);

		// Act
		ftpFile.setTimestamp(expectedTimestamp);
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertEquals("Timestamp should match the set value", expectedTimestamp, actualTimestamp);
		assertEquals("Hour should match", 23, actualTimestamp.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute should match", 59, actualTimestamp.get(Calendar.MINUTE));
		assertEquals("Second should match", 58, actualTimestamp.get(Calendar.SECOND));
		assertEquals("Millisecond should match", 999, actualTimestamp.get(Calendar.MILLISECOND));
	}

	// Test Case 9: Verify object reference behavior (no cloning)
	@Test
	public void testSetTimestamp_ObjectReferenceCheck() {
		// Arrange
		Calendar originalTimestamp = Calendar.getInstance();
		originalTimestamp.set(2000, Calendar.JULY, 1, 1, 1, 1);
		long initialTimeMillis = originalTimestamp.getTimeInMillis();

		// Act
		ftpFile.setTimestamp(originalTimestamp);
		// Modify the original Calendar object *after* setting it in FTPFile
		originalTimestamp.add(Calendar.YEAR, 1);
		Calendar actualTimestamp = ftpFile.getTimestamp(); // Get the timestamp stored in FTPFile

		// Assert
		assertNotNull("Timestamp should not be null", actualTimestamp);
		assertSame("The object reference retrieved should be the same as the original", originalTimestamp, actualTimestamp);
		assertNotEquals("The time in millis should have changed due to modification of the original object",
				initialTimeMillis, actualTimestamp.getTimeInMillis());
		assertEquals("The year should reflect the modification", 2001, actualTimestamp.get(Calendar.YEAR));
	}

	// Test Case 10: Reset timestamp from non-null back to null
	@Test
	public void testSetTimestamp_ResetToNull() {
		// Arrange
		Calendar initialTimestamp = Calendar.getInstance();
		ftpFile.setTimestamp(initialTimestamp); // Ensure it's initially non-null

		// Assert initial state
		assertNotNull("Timestamp should be non-null initially", ftpFile.getTimestamp());

		// Act
		ftpFile.setTimestamp(null);
		Calendar actualTimestamp = ftpFile.getTimestamp();

		// Assert final state
		assertNull("Timestamp should now be null after reset", actualTimestamp);
	}
}
```
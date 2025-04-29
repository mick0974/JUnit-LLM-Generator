import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Test class for FTPFile#setTimestamp(Calendar date).
 * Contains 10 unit test cases for the FTPFile#setTimestamp(Calendar date) method.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 * Contains only valid Java code.
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

    /**
     * Test case 1: Setting a typical, non-null Calendar instance.
     */
    @Test
    public void testSetTimestamp_TypicalNonNullValue_ShouldSetCorrectly() {
        // Arrange
        Calendar testCalendar = new GregorianCalendar(2023, Calendar.NOVEMBER, 21, 10, 30, 0);
        testCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Act
        ftpFile.setTimestamp(testCalendar);

        // Assert
        Calendar retrievedCalendar = ftpFile.getTimestamp();
        assertNotNull("Timestamp should not be null after setting", retrievedCalendar);
        // Important: The implementation does not clone, so it should be the same instance.
        assertSame("The retrieved Calendar should be the same instance as the one set", testCalendar, retrievedCalendar);
        assertEquals("The retrieved Calendar should be equal to the one set", testCalendar, retrievedCalendar);
        assertEquals("Timestamp value should match", testCalendar.getTimeInMillis(), retrievedCalendar.getTimeInMillis());
    }

    /**
     * Test case 2: Setting a null Calendar instance.
     */
    @Test
    public void testSetTimestamp_NullValue_ShouldSetToNull() {
        // Arrange: Ensure timestamp might have a previous value (optional)
        ftpFile.setTimestamp(Calendar.getInstance());
        assertNotNull("Timestamp should initially be non-null for this test", ftpFile.getTimestamp());

        // Act
        ftpFile.setTimestamp(null);

        // Assert
        assertNull("Timestamp should be null after setting null", ftpFile.getTimestamp());
    }

    /**
     * Test case 3: Setting the timestamp to the current time.
     */
    @Test
    public void testSetTimestamp_CurrentTime_ShouldSetCorrectly() {
        // Arrange
        Calendar now = Calendar.getInstance();

        // Act
        ftpFile.setTimestamp(now);

        // Assert
        Calendar retrievedCalendar = ftpFile.getTimestamp();
        assertNotNull("Timestamp should not be null", retrievedCalendar);
        assertSame("Should be the same instance", now, retrievedCalendar);
        // Check time equality, allowing for minor difference if execution takes time
        // Although assertSame should be sufficient given the implementation detail.
        assertEquals("Timestamp value should match", now.getTimeInMillis(), retrievedCalendar.getTimeInMillis());
    }

    /**
     * Test case 4: Setting a Calendar instance with a specific non-default time zone.
     */
    @Test
    public void testSetTimestamp_SpecificTimeZone_ShouldPreserveTimeZone() {
        // Arrange
        TimeZone specificTimeZone = TimeZone.getTimeZone("Asia/Tokyo");
        // Ensure the specificTimeZone is valid and different from default for a robust test
        assertNotEquals("Test requires a timezone different from default", TimeZone.getDefault(), specificTimeZone);

        Calendar testCalendar = new GregorianCalendar(specificTimeZone);
        testCalendar.set(2024, Calendar.FEBRUARY, 29, 15, 0, 0); // Use a leap year date

        // Act
        ftpFile.setTimestamp(testCalendar);

        // Assert
        Calendar retrievedCalendar = ftpFile.getTimestamp();
        assertNotNull("Timestamp should not be null", retrievedCalendar);
        assertSame("Should be the same instance", testCalendar, retrievedCalendar);
        assertEquals("Time zone should be preserved", specificTimeZone, retrievedCalendar.getTimeZone());
        assertEquals("Year should match", 2024, retrievedCalendar.get(Calendar.YEAR));
        assertEquals("Month should match", Calendar.FEBRUARY, retrievedCalendar.get(Calendar.MONTH));
        assertEquals("Day should match", 29, retrievedCalendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Test case 5: Overwriting an existing timestamp with a new one.
     */
    @Test
    public void testSetTimestamp_OverwriteExisting_ShouldUpdateCorrectly() {
        // Arrange
        Calendar initialCalendar = new GregorianCalendar(2000, Calendar.JANUARY, 1);
        Calendar newCalendar = new GregorianCalendar(2025, Calendar.DECEMBER, 31);
        ftpFile.setTimestamp(initialCalendar); // Set initial timestamp

        // Act
        ftpFile.setTimestamp(newCalendar);

        // Assert
        Calendar retrievedCalendar = ftpFile.getTimestamp();
        assertNotNull("Timestamp should not be null", retrievedCalendar);
        assertSame("Should be the new instance", newCalendar, retrievedCalendar);
        assertNotSame("Should not be the initial instance", initialCalendar, retrievedCalendar);
        assertEquals("Timestamp value should match the new calendar", newCalendar.getTimeInMillis(), retrievedCalendar.getTimeInMillis());
    }

    /**
     * Test case 6: Setting a Calendar instance representing the epoch (January 1, 1970, 00:00:00 GMT).
     */
    @Test
    public void testSetTimestamp_EpochTime_ShouldSetCorrectly() {
        // Arrange
        Calendar epochCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        epochCalendar.setTimeInMillis(0);

        // Act
        ftpFile.setTimestamp(epochCalendar);

        // Assert
        Calendar retrievedCalendar = ftpFile.getTimestamp();
        assertNotNull("Timestamp should not be null", retrievedCalendar);
        assertSame("Should be the same instance", epochCalendar, retrievedCalendar);
        assertEquals("Timestamp value should be 0 (epoch)", 0, retrievedCalendar.getTimeInMillis());
        assertEquals("Time zone should be GMT/UTC", TimeZone.getTimeZone("GMT"), retrievedCalendar.getTimeZone());
    }

    /**
     * Test case 7: Setting a Calendar instance far in the future.
     */
    @Test
    public void testSetTimestamp_FutureDate_ShouldSetCorrectly() {
        // Arrange
        Calendar futureCalendar = new GregorianCalendar(2999, Calendar.DECEMBER, 31, 23, 59, 59);
        futureCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Act
        ftpFile.setTimestamp(futureCalendar);

        // Assert
        Calendar retrievedCalendar = ftpFile.getTimestamp();
        assertNotNull("Timestamp should not be null", retrievedCalendar);
        assertSame("Should be the same instance", futureCalendar, retrievedCalendar);
        assertEquals("Timestamp value should match", futureCalendar.getTimeInMillis(), retrievedCalendar.getTimeInMillis());
        assertEquals("Year should be 2999", 2999, retrievedCalendar.get(Calendar.YEAR));
    }

    /**
     * Test case 8: Setting a Calendar instance far in the past (before epoch).
     * Note: Calendar handles dates before the epoch.
     */
    @Test
    public void testSetTimestamp_PastDateBeforeEpoch_ShouldSetCorrectly() {
        // Arrange
        Calendar pastCalendar = new GregorianCalendar(1960, Calendar.JULY, 4);
        pastCalendar.setTimeZone(TimeZone.getTimeZone("America/New_York"));

        // Act
        ftpFile.setTimestamp(pastCalendar);

        // Assert
        Calendar retrievedCalendar = ftpFile.getTimestamp();
        assertNotNull("Timestamp should not be null", retrievedCalendar);
        assertSame("Should be the same instance", pastCalendar, retrievedCalendar);
        assertEquals("Timestamp value should match", pastCalendar.getTimeInMillis(), retrievedCalendar.getTimeInMillis());
        assertTrue("Timestamp millis should be negative (before epoch)", retrievedCalendar.getTimeInMillis() < 0);
        assertEquals("Year should be 1960", 1960, retrievedCalendar.get(Calendar.YEAR));
    }

    /**
     * Test case 9: Verifying behavior when the original Calendar object is modified after setting.
     * Since the method does not clone, the FTPFile's internal state should reflect the change.
     */
    @Test
    public void testSetTimestamp_ModifyOriginalCalendarAfterSet_ShouldReflectChange() {
        // Arrange
        Calendar originalCalendar = new GregorianCalendar(2023, Calendar.JANUARY, 1);
        ftpFile.setTimestamp(originalCalendar);

        // Act: Modify the original calendar *after* it has been set in FTPFile
        originalCalendar.set(Calendar.YEAR, 2024);
        originalCalendar.add(Calendar.MONTH, 1); // Change month as well

        // Assert
        Calendar retrievedCalendar = ftpFile.getTimestamp();
        assertNotNull("Timestamp should not be null", retrievedCalendar);
        assertSame("Retrieved calendar should still be the *same instance* as the original", originalCalendar, retrievedCalendar);
        assertEquals("The year in the retrieved calendar should reflect the modification", 2024, retrievedCalendar.get(Calendar.YEAR));
        assertEquals("The month in the retrieved calendar should reflect the modification", Calendar.FEBRUARY, retrievedCalendar.get(Calendar.MONTH));
    }

    /**
     * Test case 10: Setting a Calendar with only date components set (time fields default).
     */
     @Test
     public void testSetTimestamp_DateOnlyCalendar_ShouldSetCorrectly() {
         // Arrange
         Calendar dateOnlyCalendar = new GregorianCalendar(2022, Calendar.MARCH, 15);
         // Clear time fields explicitly to ensure they are default (often 00:00:00 in the calendar's timezone)
         dateOnlyCalendar.set(Calendar.HOUR_OF_DAY, 0);
         dateOnlyCalendar.set(Calendar.MINUTE, 0);
         dateOnlyCalendar.set(Calendar.SECOND, 0);
         dateOnlyCalendar.set(Calendar.MILLISECOND, 0);


         // Act
         ftpFile.setTimestamp(dateOnlyCalendar);

         // Assert
         Calendar retrievedCalendar = ftpFile.getTimestamp();
         assertNotNull("Timestamp should not be null", retrievedCalendar);
         assertSame("Should be the same instance", dateOnlyCalendar, retrievedCalendar);
         assertEquals("Year should match", 2022, retrievedCalendar.get(Calendar.YEAR));
         assertEquals("Month should match", Calendar.MARCH, retrievedCalendar.get(Calendar.MONTH));
         assertEquals("Day should match", 15, retrievedCalendar.get(Calendar.DAY_OF_MONTH));
         // Verify default time fields if necessary for the specific Calendar implementation/timezone
         assertEquals("Hour should be default (0)", 0, retrievedCalendar.get(Calendar.HOUR_OF_DAY));
         assertEquals("Minute should be default (0)", 0, retrievedCalendar.get(Calendar.MINUTE));
     }
}
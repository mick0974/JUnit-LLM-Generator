import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * Test class for the TimeStamp.getCurrentTime() method.
 * Focuses on verifying the properties and behavior of the timestamp
 * generated based on the current system time.
 *
 * Uses JUnit 4 annotations and assertions.
 * Follows the Arrange-Act-Assert pattern.
 */
public class TimeStamp_getCurrentTimeTest {

    // Define a tolerance for time comparisons in milliseconds.
    // This accounts for the time taken to execute code between getting system time
    // and the TimeStamp object, plus potential rounding differences.
    private static final long TIME_TOLERANCE_MS = 100; // 100ms tolerance

    // Regex pattern to validate the hex string format (e.g., "c1a089bd.fc904f6d")
    private static final Pattern NTP_HEX_FORMAT_PATTERN = Pattern.compile("^[0-9a-f]{8}\\.[0-9a-f]{8}$");

    // Date formatters used for validation
    private DateFormat defaultFormatter;
    private DateFormat utcFormatter;

    @Before
    public void setUp() {
        // Initialize date formatters before each test
        defaultFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT, Locale.US);
        defaultFormatter.setTimeZone(TimeZone.getDefault());

        utcFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT + " 'UTC'", Locale.US);
        utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @After
    public void tearDown() {
        // Clean up resources if needed (not strictly necessary here)
        defaultFormatter = null;
        utcFormatter = null;
    }

    /**
     * Test case 1: Verify that getCurrentTime() returns a non-null TimeStamp object.
     */
    @Test
    public void testGetCurrentTimeNotNull() {
        // Act
        TimeStamp currentTimeStamp = TimeStamp.getCurrentTime();

        // Assert
        assertNotNull("TimeStamp.getCurrentTime() should not return null.", currentTimeStamp);
    }

    /**
     * Test case 2: Verify that the Java time obtained from the TimeStamp
     * is close to the system time when the TimeStamp was created.
     */
    @Test
    public void testGetCurrentTimeValueApproximation() {
        // Arrange
        long timeBefore = System.currentTimeMillis();

        // Act
        TimeStamp currentTimeStamp = TimeStamp.getCurrentTime();
        long timeAfter = System.currentTimeMillis();
        long timeStampJavaTime = currentTimeStamp.getTime();

        // Assert
        assertTrue("TimeStamp time (" + timeStampJavaTime + ") should be >= time before (" + timeBefore + ") minus tolerance.",
                timeStampJavaTime >= timeBefore - TIME_TOLERANCE_MS);
        assertTrue("TimeStamp time (" + timeStampJavaTime + ") should be <= time after (" + timeAfter + ") plus tolerance.",
                timeStampJavaTime <= timeAfter + TIME_TOLERANCE_MS);
    }

    /**
     * Test case 3: Verify that the Date object obtained from the TimeStamp
     * represents a time close to the system time when the TimeStamp was created.
     */
    @Test
    public void testGetCurrentTimeDateApproximation() {
        // Arrange
        long timeBefore = System.currentTimeMillis();

        // Act
        TimeStamp currentTimeStamp = TimeStamp.getCurrentTime();
        long timeAfter = System.currentTimeMillis();
        Date timeStampDate = currentTimeStamp.getDate();
        assertNotNull("TimeStamp.getDate() should return a non-null Date object.", timeStampDate);
        long timeStampDateMillis = timeStampDate.getTime();

        // Assert
        assertTrue("TimeStamp Date time (" + timeStampDateMillis + ") should be >= time before (" + timeBefore + ") minus tolerance.",
                timeStampDateMillis >= timeBefore - TIME_TOLERANCE_MS);
        assertTrue("TimeStamp Date time (" + timeStampDateMillis + ") should be <= time after (" + timeAfter + ") plus tolerance.",
                timeStampDateMillis <= timeAfter + TIME_TOLERANCE_MS);
    }

    /**
     * Test case 4: Verify the internal consistency of the NTP value.
     * The seconds and fraction parts should correctly reconstruct the full ntpValue.
     */
    @Test
    public void testGetCurrentTimeInternalConsistency() {
        // Arrange
        // Act
        TimeStamp currentTimeStamp = TimeStamp.getCurrentTime();
        long ntpValue = currentTimeStamp.ntpValue();
        long seconds = currentTimeStamp.getSeconds();
        long fraction = currentTimeStamp.getFraction();

        // Assert
        long reconstructedNtpValue = (seconds << 32) | fraction;
        assertEquals("Reconstructed NTP value should match the original ntpValue.", ntpValue, reconstructedNtpValue);
    }

    /**
     * Test case 5: Verify that toString() returns a non-null, non-empty string
     * in the expected hexadecimal format.
     */
    @Test
    public void testGetCurrentTimeToStringFormat() {
        // Arrange
        // Act
        TimeStamp currentTimeStamp = TimeStamp.getCurrentTime();
        String ntpString = currentTimeStamp.toString();

        // Assert
        assertNotNull("toString() should not return null.", ntpString);
        assertFalse("toString() should not return an empty string.", ntpString.isEmpty());
        assertTrue("toString() output '" + ntpString + "' should match the expected hex format.",
                   NTP_HEX_FORMAT_PATTERN.matcher(ntpString).matches());
    }

    /**
     * Test case 6: Verify that toDateString() returns a non-null, non-empty string,
     * and that it can be parsed back into a Date.
     */
    @Test
    public void testGetCurrentTimeToDateString() throws ParseException {
        // Arrange
        // Act
        TimeStamp currentTimeStamp = TimeStamp.getCurrentTime();
        String dateString = currentTimeStamp.toDateString();

        // Assert
        assertNotNull("toDateString() should not return null.", dateString);
        assertFalse("toDateString() should not return an empty string.", dateString.isEmpty());

        // Verify format can be parsed back (stronger check than just not empty)
        Date parsedDate = defaultFormatter.parse(dateString);
        assertNotNull("Parsed date from toDateString() should not be null.", parsedDate);

        // Check if parsed date is close to the original timestamp time
        long timeDiff = Math.abs(parsedDate.getTime() - currentTimeStamp.getTime());
        assertTrue("Parsed date time should be very close to the original timestamp time.", timeDiff < 1000); // Allow up to 1 second difference due to formatting precision
    }

    /**
     * Test case 7: Verify that toUTCString() returns a non-null, non-empty string,
     * contains "UTC", and can be parsed back into a Date.
     */
    @Test
    public void testGetCurrentTimeToUTCString() throws ParseException {
        // Arrange
        // Act
        TimeStamp currentTimeStamp = TimeStamp.getCurrentTime();
        String utcDateString = currentTimeStamp.toUTCString();

        // Assert
        assertNotNull("toUTCString() should not return null.", utcDateString);
        assertFalse("toUTCString() should not return an empty string.", utcDateString.isEmpty());
        assertTrue("toUTCString() should contain 'UTC'.", utcDateString.endsWith(" UTC"));

        // Verify format can be parsed back
        Date parsedDate = utcFormatter.parse(utcDateString);
        assertNotNull("Parsed date from toUTCString() should not be null.", parsedDate);

        // Check if parsed date matches the original timestamp time (already in UTC internally)
        assertEquals("Parsed UTC date time should match the original timestamp time.",
                     currentTimeStamp.getTime(), parsedDate.getTime());
    }

    /**
     * Test case 8: Verify the hashCode() method runs without error.
     * (We can't easily predict the exact hash code, but we can ensure it executes).
     */
    @Test
    public void testGetCurrentTimeHashCode() {
        // Arrange
        // Act
        TimeStamp currentTimeStamp = TimeStamp.getCurrentTime();
        int hashCode = currentTimeStamp.hashCode(); // If this throws, the test fails

        // Assert (optional: basic check on the nature of hashcode)
        // No specific assertion needed other than the method completing without error.
        // We could potentially create two identical timestamps (if possible via other constructors)
        // and check hashcodes, but for getCurrentTime, this is sufficient.
        assertTrue("hashCode() executed.", true);
    }

    /**
     * Test case 9: Verify the compareTo() method with another TimeStamp slightly later.
     * A timestamp created later should be greater than or equal to an earlier one.
     */
    @Test
    public void testGetCurrentTimeComparison() throws InterruptedException {
        // Arrange
        TimeStamp timeStamp1 = TimeStamp.getCurrentTime();
        // Ensure a small delay to increase the chance of different timestamps
        Thread.sleep(5);
        TimeStamp timeStamp2 = TimeStamp.getCurrentTime();

        // Act & Assert
        assertTrue("timeStamp2 should be greater than or equal to timeStamp1.",
                   timeStamp2.compareTo(timeStamp1) >= 0);
        assertTrue("timeStamp1 should be less than or equal to timeStamp2.",
                   timeStamp1.compareTo(timeStamp2) <= 0);

        // Optional: Test comparison with itself
        assertEquals("Comparing a timestamp with itself should return 0.",
                     0, timeStamp1.compareTo(timeStamp1));
         assertEquals("Comparing a timestamp with itself should return 0.",
                     0, timeStamp2.compareTo(timeStamp2));
    }

    /**
     * Test case 10: Verify the equals() method.
     * A timestamp obtained from getCurrentTime should equal itself, but likely
     * not equal another timestamp obtained slightly later.
     */
    @Test
    public void testGetCurrentTimeEquals() throws InterruptedException {
        // Arrange
        TimeStamp timeStamp1 = TimeStamp.getCurrentTime();
        // Ensure a small delay
        Thread.sleep(5);
        TimeStamp timeStamp2 = TimeStamp.getCurrentTime();
        TimeStamp timeStamp1_copy = new TimeStamp(timeStamp1.ntpValue()); // Create identical copy

        // Act & Assert
        // Using assertTrue/assertFalse for clarity with equals method
        assertTrue("A timestamp should be equal to itself.", timeStamp1.equals(timeStamp1));
        assertTrue("A timestamp should be equal to an identical copy.", timeStamp1.equals(timeStamp1_copy));
        assertTrue("An identical copy should be equal to the original timestamp.", timeStamp1_copy.equals(timeStamp1));

        // It's highly likely they are different due to time passing, but theoretically could be same
        // if clock resolution and execution speed align perfectly. Checking ntpValue is more robust.
        if (timeStamp1.ntpValue() != timeStamp2.ntpValue()) {
             assertFalse("Two timestamps retrieved at different times should generally not be equal.",
                        timeStamp1.equals(timeStamp2));
        }

        assertFalse("A timestamp should not be equal to null.", timeStamp1.equals(null));
        assertFalse("A timestamp should not be equal to an object of a different type.", timeStamp1.equals(new Object()));
    }
}
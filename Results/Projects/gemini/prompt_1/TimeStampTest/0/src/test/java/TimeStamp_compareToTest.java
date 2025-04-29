import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

/**
 * Test class for TimeStamp#compareTo(TimeStamp).
 * It contains 10 unit test cases focusing on comparing TimeStamp objects.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 * Uses JUnit 4 annotations and assertions.
 */
public class TimeStamp_compareToTest {

    private TimeStamp timestamp1;
    private TimeStamp timestamp2;
    private TimeStamp timestamp3;

    // Some representative NTP time values
    private static final long NTP_TIME_EARLY = 0x83AA7E8000000000L; // Corresponds roughly to 1970-01-01 00:00:00 GMT
    private static final long NTP_TIME_MID = 0xC1A089BDFC904F6DL;   // Corresponds roughly to 2002-12-10 10:41:49.986 GMT
    private static final long NTP_TIME_LATE = 0xE364F80000000000L;  // Corresponds roughly to 2030-01-01 00:00:00 GMT
    private static final long NTP_TIME_ZERO = 0x0000000000000000L;  // Zero NTP time

    @Before
    public void setUp() {
        // No specific setup needed for each test, instances created within tests.
        timestamp1 = null;
        timestamp2 = null;
        timestamp3 = null;
    }

    @After
    public void tearDown() {
        // No specific cleanup needed.
        timestamp1 = null;
        timestamp2 = null;
        timestamp3 = null;
    }

    /**
     * Test case 1: Comparing two identical TimeStamp objects created from the same long value.
     * Expectation: compareTo should return 0.
     */
    @Test
    public void testCompareTo_EqualTimestamps_FromLong() {
        // Arrange
        timestamp1 = new TimeStamp(NTP_TIME_MID);
        timestamp2 = new TimeStamp(NTP_TIME_MID);

        // Act
        int result = timestamp1.compareTo(timestamp2);
        int reverseResult = timestamp2.compareTo(timestamp1);

        // Assert
        assertEquals("Comparing identical timestamps should return 0", 0, result);
        assertEquals("Comparison should be symmetric", 0, reverseResult);
    }

    /**
     * Test case 2: Comparing a TimeStamp object to another representing an earlier time (from long).
     * Expectation: compareTo should return a positive value (1).
     */
    @Test
    public void testCompareTo_LaterThanEarlier_FromLong() {
        // Arrange
        timestamp1 = new TimeStamp(NTP_TIME_MID); // Later time
        timestamp2 = new TimeStamp(NTP_TIME_EARLY); // Earlier time

        // Act
        int result = timestamp1.compareTo(timestamp2);

        // Assert
        assertTrue("Comparing a later time to an earlier time should return > 0", result > 0);
        // Specifically, it should be 1 based on the implementation
        assertEquals("Comparing a later time to an earlier time should return 1", 1, result);
    }

    /**
     * Test case 3: Comparing a TimeStamp object to another representing a later time (from long).
     * Expectation: compareTo should return a negative value (-1).
     */
    @Test
    public void testCompareTo_EarlierThanLater_FromLong() {
        // Arrange
        timestamp1 = new TimeStamp(NTP_TIME_EARLY); // Earlier time
        timestamp2 = new TimeStamp(NTP_TIME_LATE); // Later time

        // Act
        int result = timestamp1.compareTo(timestamp2);

        // Assert
        assertTrue("Comparing an earlier time to a later time should return < 0", result < 0);
        // Specifically, it should be -1 based on the implementation
        assertEquals("Comparing an earlier time to a later time should return -1", -1, result);
    }

    /**
     * Test case 4: Comparing two identical TimeStamp objects created from the same Date object.
     * Expectation: compareTo should return 0.
     */
    @Test
    public void testCompareTo_EqualTimestamps_FromDate() {
        // Arrange
        Date date = new Date(System.currentTimeMillis()); // Use current time
        timestamp1 = new TimeStamp(date);
        timestamp2 = new TimeStamp(date);

        // Act
        int result = timestamp1.compareTo(timestamp2);
        int reverseResult = timestamp2.compareTo(timestamp1);

        // Assert
        // Note: Conversion Date -> NTP -> Date loses precision, but Date -> NTP should be consistent
        assertEquals("Comparing timestamps from the same date should return 0", 0, result);
        assertEquals("Comparison should be symmetric for dates", 0, reverseResult);
    }

    /**
     * Test case 5: Comparing TimeStamps from different Date objects (later vs earlier).
     * Expectation: compareTo should return a positive value (1).
     */
    @Test
    public void testCompareTo_LaterThanEarlier_FromDate() {
        // Arrange
        long nowMillis = System.currentTimeMillis();
        Date dateLater = new Date(nowMillis);
        Date dateEarlier = new Date(nowMillis - 5000); // 5 seconds earlier
        timestamp1 = new TimeStamp(dateLater);
        timestamp2 = new TimeStamp(dateEarlier);

        // Act
        int result = timestamp1.compareTo(timestamp2);

        // Assert
        assertTrue("Comparing a later date's timestamp to an earlier one should return > 0", result > 0);
        assertEquals("Comparing a later date's timestamp to an earlier one should return 1", 1, result);
    }

    /**
     * Test case 6: Comparing TimeStamps from different Date objects (earlier vs later).
     * Expectation: compareTo should return a negative value (-1).
     */
    @Test
    public void testCompareTo_EarlierThanLater_FromDate() {
        // Arrange
        long nowMillis = System.currentTimeMillis();
        Date dateEarlier = new Date(nowMillis - 5000); // 5 seconds earlier
        Date dateLater = new Date(nowMillis);
        timestamp1 = new TimeStamp(dateEarlier);
        timestamp2 = new TimeStamp(dateLater);

        // Act
        int result = timestamp1.compareTo(timestamp2);

        // Assert
        assertTrue("Comparing an earlier date's timestamp to a later one should return < 0", result < 0);
        assertEquals("Comparing an earlier date's timestamp to a later one should return -1", -1, result);
    }

    /**
     * Test case 7: Comparing two identical TimeStamp objects created from the same hex string.
     * Expectation: compareTo should return 0.
     */
    @Test
    public void testCompareTo_EqualTimestamps_FromHexString() {
        // Arrange
        String hex = "c1a089bd.fc904f6d"; // Corresponds to NTP_TIME_MID
        timestamp1 = new TimeStamp(hex);
        timestamp2 = new TimeStamp(hex);

        // Act
        int result = timestamp1.compareTo(timestamp2);
        int reverseResult = timestamp2.compareTo(timestamp1);

        // Assert
        assertEquals("Comparing timestamps from the same hex string should return 0", 0, result);
        assertEquals("Comparison should be symmetric for hex strings", 0, reverseResult);
    }

    /**
     * Test case 8: Comparing TimeStamps from different hex strings (later vs earlier).
     * Expectation: compareTo should return a positive value (1).
     */
    @Test
    public void testCompareTo_LaterThanEarlier_FromHexString() {
        // Arrange
        String hexLater = "e364f800.00000000";   // Corresponds to NTP_TIME_LATE
        String hexEarlier = "c1a089bd.fc904f6d"; // Corresponds to NTP_TIME_MID
        timestamp1 = new TimeStamp(hexLater);
        timestamp2 = new TimeStamp(hexEarlier);

        // Act
        int result = timestamp1.compareTo(timestamp2);

        // Assert
        assertTrue("Comparing a later hex timestamp to an earlier one should return > 0", result > 0);
        assertEquals("Comparing a later hex timestamp to an earlier one should return 1", 1, result);
    }

    /**
     * Test case 9: Comparing TimeStamps from different hex strings (earlier vs later).
     * Expectation: compareTo should return a negative value (-1).
     */
    @Test
    public void testCompareTo_EarlierThanLater_FromHexString() {
        // Arrange
        String hexEarlier = "83aa7e80.00000000"; // Corresponds to NTP_TIME_EARLY
        String hexLater = "c1a089bd.fc904f6d";   // Corresponds to NTP_TIME_MID
        timestamp1 = new TimeStamp(hexEarlier);
        timestamp2 = new TimeStamp(hexLater);

        // Act
        int result = timestamp1.compareTo(timestamp2);

        // Assert
        assertTrue("Comparing an earlier hex timestamp to a later one should return < 0", result < 0);
        assertEquals("Comparing an earlier hex timestamp to a later one should return -1", -1, result);
    }

    /**
     * Test case 10: Comparing TimeStamp objects involving the zero NTP time.
     * Expectation: Zero should compare correctly against itself and other times.
     */
}
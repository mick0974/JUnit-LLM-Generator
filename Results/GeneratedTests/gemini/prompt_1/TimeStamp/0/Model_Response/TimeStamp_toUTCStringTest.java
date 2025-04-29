```java
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

/**
 * Test class for TimeStamp#toUTCString().
 * Contains 10 unit test cases following the Arrange-Act-Assert pattern,
 * covering typical and edge cases for the UTC string representation.
 */
public class TimeStamp_toUTCStringTest {

    private static final TimeZone UTC_ZONE = TimeZone.getTimeZone("UTC");
    private static final String EXPECTED_DATE_FORMAT = "EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'";
    private DateFormat utcFormatter;

    @Before
    public void setUp() {
        // Ensure consistent formatting for verification
        utcFormatter = new SimpleDateFormat(EXPECTED_DATE_FORMAT, Locale.US);
        utcFormatter.setTimeZone(UTC_ZONE);
    }

    @After
    public void tearDown() {
        utcFormatter = null;
    }

    /**
     * Helper method to get the expected UTC string for a given millisecond timestamp.
     * @param millis The timestamp in milliseconds since the Java epoch.
     * @return The formatted UTC date string.
     */
    private String getExpectedUTCString(long millis) {
        return utcFormatter.format(new Date(millis));
    }

    /**
     * Test with a date before the 2036 rollover (MSB=1).
     * Corresponds to Jan 1, 2000 00:00:00.000 UTC.
     */
    @Test
    public void testUTCStringForKnownDatePre2036() {
        // Arrange
        long timeMillis = 946684800000L; // Jan 1, 2000 00:00:00.000 UTC
        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        String expected = "Sat, Jan 01 2000 00:00:00.000 UTC";

        // Act
        String actual = ts.toUTCString();

        // Assert
        assertEquals(expected, actual);
        assertEquals(timeMillis, ts.getTime()); // Verify conversion back
    }

    /**
     * Test with a date after the 2036 rollover (MSB=0).
     * Corresponds to Jan 1, 2040 00:00:00.000 UTC.
     */
    @Test
    public void testUTCStringForKnownDatePost2036() throws ParseException {
        // Arrange
        // Use SimpleDateFormat to avoid potential Calendar/Date constructor issues across locales/timezones
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
        parser.setTimeZone(UTC_ZONE);
        Date date = parser.parse("2040-01-01 00:00:00.000 +0000");
        long timeMillis = date.getTime();

        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        String expected = "Mon, Jan 01 2040 00:00:00.000 UTC";

        // Act
        String actual = ts.toUTCString();

        // Assert
        assertEquals(expected, actual);
        assertEquals(timeMillis, ts.getTime()); // Verify conversion back
    }

    /**
     * Test with the Java epoch (Jan 1, 1970 00:00:00.000 UTC).
     */
    @Test
    public void testUTCStringForJavaEpoch() {
        // Arrange
        long timeMillis = 0L; // Java Epoch
        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        String expected = "Thu, Jan 01 1970 00:00:00.000 UTC";

        // Act
        String actual = ts.toUTCString();

        // Assert
        assertEquals(expected, actual);
        assertEquals(timeMillis, ts.getTime()); // Verify conversion back
    }

    /**
     * Test with the NTP base time for MSB=1 (Jan 1, 1900 00:00:00.000 UTC).
     * This corresponds to NTP seconds = 0x80000000 and fraction = 0.
     */
    @Test
    public void testUTCStringForNTPBase1Epoch() {
        // Arrange
        // NTP time for 1 Jan 1900 00:00:00 UTC should have seconds=0 and MSB=1
        // However, the base time msb1baseTime is used as the offset.
        // Let's test the timestamp that *results* in msb1baseTime when converted.
        long ntpBase1Time = 0x8000000000000000L; // Seconds=0, MSB=1
        TimeStamp ts = new TimeStamp(ntpBase1Time);
        String expected = "Mon, Jan 01 1900 00:00:00.000 UTC";
        long expectedMillis = -2208988800000L; // TimeStamp.msb1baseTime

        // Act
        String actual = ts.toUTCString();

        // Assert
        assertEquals(expected, actual);
        assertEquals(expectedMillis, ts.getTime());
    }

    /**
     * Test with the NTP base time for MSB=0 (Feb 7, 2036 06:28:16.000 UTC).
     * This corresponds to NTP time = 0.
     */
    @Test
    public void testUTCStringForNTPBase0Epoch() {
        // Arrange
        long ntpBase0Time = 0L; // Zero NTP timestamp
        TimeStamp ts = new TimeStamp(ntpBase0Time);
        String expected = "Thu, Feb 07 2036 06:28:16.000 UTC";
        long expectedMillis = 2085978496000L; // TimeStamp.msb0baseTime

        // Act
        String actual = ts.toUTCString();

        // Assert
        assertEquals(expected, actual);
        assertEquals(expectedMillis, ts.getTime());
    }

    /**
     * Test with a time just before the 2036 rollover point.
     * (Feb 7, 2036 06:28:15.999 UTC).
     */
    @Test
    public void testUTCStringJustBeforeRollover() {
        // Arrange
        long timeMillis = TimeStamp.msb0baseTime - 1; // One millisecond before rollover
        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        String expected = "Thu, Feb 07 2036 06:28:15.999 UTC";

        // Act
        String actual = ts.toUTCString();

        // Assert
        assertEquals(expected, actual);
        assertEquals(timeMillis, ts.getTime());
    }

    /**
     * Test with the exact time of the 2036 rollover point.
     * (Feb 7, 2036 06:28:16.000 UTC). Should use MSB=0 base.
     */
    @Test
    public void testUTCStringAtRollover() {
        // Arrange
        long timeMillis = TimeStamp.msb0baseTime; // Exactly at rollover
        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        // This time should correspond to NTP time 0, using the msb0 base time
        String expected = "Thu, Feb 07 2036 06:28:16.000 UTC";

        // Act
        String actual = ts.toUTCString();

        // Assert
        assertEquals(expected, actual);
        assertEquals(timeMillis, ts.getTime());
        // The NTP value should be 0 for this exact time according to toNtpTime logic
        assertEquals(0L, ts.ntpValue());
    }

    /**
     * Test with a timestamp containing non-zero milliseconds.
     * March 15, 2023 13:21:01.234 UTC
     */
    @Test
    public void testUTCStringWithMilliseconds() {
        // Arrange
        long timeMillis = 1678886461234L; // Approx Mar 15 2023 13:21:01.234 UTC
        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        String expected = "Wed, Mar 15 2023 13:21:01.234 UTC";

        // Act
        String actual = ts.toUTCString();

        // Assert
        assertEquals(expected, actual);
        // Allow for potential minor rounding differences in fraction conversion
        long actualMillis = ts.getTime();
        assertEquals(timeMillis, actualMillis);
    }

    /**
     * Test using the current system time.
     */
    @Test
    public void testUTCStringForCurrentTime() {
        // Arrange
        long nowMillis = System.currentTimeMillis();
        TimeStamp ts = TimeStamp.getCurrentTime();
        String expected = getExpectedUTCString(nowMillis);

        // Act
        String actual = ts.toUTCString();

        // Assert
        // Since getCurrentTime() and the expected string calculation might happen
        // a millisecond apart, we check that the generated string matches the
        // formatted date derived from the TimeStamp's internal time.
        String actualFromTsMillis = getExpectedUTCString(ts.getTime());
        assertEquals(actualFromTsMillis, actual);

        // Also check if the time is very close (within a small threshold like 10ms)
        // to the original 'nowMillis', acknowledging potential slight delays.
        long timeDiff = Math.abs(ts.getTime() - nowMillis);
        assertTrue("Time difference (" + timeDiff + "ms) too large", timeDiff < 10);
    }

    /**
     * Test creating a TimeStamp from a valid hex string and getting the UTC string.
     * Using the NTP time for Jan 1, 2000 00:00:00.000 UTC (calculated as 0xBCF02C0000000000).
     */
    @Test
    public void testUTCStringFromHexString() {
        // Arrange
        String ntpHexString = "bcf02c00.00000000"; // NTP hex for Jan 1, 2000 00:00:00.000 UTC
        TimeStamp ts = TimeStamp.parseNtpString(ntpHexString);
        String expected = "Sat, Jan 01 2000 00:00:00.000 UTC";
        long expectedMillis = 946684800000L;

        // Act
        String actual = ts.toUTCString();

        // Assert
        assertEquals(expected, actual);
        assertEquals(expectedMillis, ts.getTime());
        assertEquals(0xBCF02C0000000000L, ts.ntpValue());
    }
}
```
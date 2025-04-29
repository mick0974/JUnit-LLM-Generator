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
 * Test class of TimeStamp.
 * It contains 10 unit test cases for the
 * TimeStamp#public ntpValue() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 * Contains only valid Java code.
 */
public class TimeStamp_ntpValueTest {

    // No setup or teardown needed for these tests as TimeStamp is immutable.

    /**
     * Test ntpValue() when constructed with a specific positive NTP long value.
     */
    @Test
    public void testNtpValueWithPositiveLong() {
        // Arrange
        long expectedNtpTime = 0xc1a089bd12345678L; // Example positive NTP time
        TimeStamp ts = new TimeStamp(expectedNtpTime);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
    }

    /**
     * Test ntpValue() when constructed with NTP long value zero.
     */
    @Test
    public void testNtpValueWithZeroLong() {
        // Arrange
        long expectedNtpTime = 0L;
        TimeStamp ts = new TimeStamp(expectedNtpTime);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
    }

    /**
     * Test ntpValue() when constructed with a specific negative NTP long value
     * (representing times before the 1970 Java epoch, using the 1900 NTP epoch).
     */
    @Test
    public void testNtpValueWithNegativeLong() {
        // Arrange
        // Corresponds to a date before 1970, e.g., using MSB=1 logic
        long expectedNtpTime = 0x83aa7e8000000000L; // Approx Jan 1, 1970 00:00:00 UTC
        TimeStamp ts = new TimeStamp(expectedNtpTime);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
    }

    /**
     * Test ntpValue() when constructed with Long.MAX_VALUE.
     */
    @Test
    public void testNtpValueWithMaxLong() {
        // Arrange
        long expectedNtpTime = Long.MAX_VALUE;
        TimeStamp ts = new TimeStamp(expectedNtpTime);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
    }

    /**
     * Test ntpValue() when constructed with Long.MIN_VALUE.
     */
    @Test
    public void testNtpValueWithMinLong() {
        // Arrange
        long expectedNtpTime = Long.MIN_VALUE; // Represents a time using the 1900 epoch (MSB=1)
        TimeStamp ts = new TimeStamp(expectedNtpTime);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
    }

    /**
     * Test ntpValue() when constructed with a valid hex string.
     */
    @Test
    public void testNtpValueWithValidHexString() throws NumberFormatException {
        // Arrange
        String hexStamp = "c1a089bd.fc904f6d";
        // Calculate expected long value from the hex string
        long expectedNtpTime = ((Long.parseLong("c1a089bd", 16) << 32) |
                                 Long.parseLong("fc904f6d", 16));
        TimeStamp ts = new TimeStamp(hexStamp);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
    }

    /**
     * Test ntpValue() when constructed with a hex string representing zero.
     */
    @Test
    public void testNtpValueWithZeroHexString() throws NumberFormatException {
        // Arrange
        String hexStamp = "0.0"; // or "00000000.00000000"
        long expectedNtpTime = 0L;
        TimeStamp ts = new TimeStamp(hexStamp);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
    }
     /**
     * Test ntpValue() when constructed with a hex string containing only seconds part.
     */
    @Test
    public void testNtpValueWithSecondsOnlyHexString() throws NumberFormatException {
        // Arrange
        String hexStamp = "d94f49c0"; // Approx Jan 1, 2017
        long expectedNtpTime = Long.parseLong("d94f49c0", 16) << 32;
        TimeStamp ts = new TimeStamp(hexStamp);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
    }


    /**
     * Test ntpValue() when constructed with a specific Date object (Java Epoch).
     */
    @Test
    public void testNtpValueWithEpochDate() {
        // Arrange
        long javaEpochMillis = 0L;
        Date date = new Date(javaEpochMillis);
        // Expected NTP time for Java epoch (Jan 1, 1970 00:00:00 UTC)
        // Seconds from Jan 1, 1900 to Jan 1, 1970 = (70 years * 365 days + 17 leap days) * 86400 seconds
        // = (25567 + 17) * 86400 = 25584 * 86400 = 2209838400 seconds
        // Hex is 0x83aa7e80. Use the static helper for precision.
        long expectedNtpTime = TimeStamp.toNtpTime(javaEpochMillis);
        TimeStamp ts = new TimeStamp(date);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
    }

    /**
     * Test ntpValue() when constructed with a specific Date object post-2036.
     * This tests the MSB=0 logic in the Date constructor path.
     */
    @Test
    public void testNtpValueWithPost2036Date() throws ParseException {
        // Arrange
        // Use a date slightly after the 2036 boundary
        // Boundary: 7-Feb-2036 @ 06:28:16 UTC (msb0baseTime = 2085978496000L)
        long postBoundaryMillis = TimeStamp.msb0baseTime + 5000; // 5 seconds after boundary
        Date date = new Date(postBoundaryMillis);
        long expectedNtpTime = TimeStamp.toNtpTime(postBoundaryMillis); // Should calculate with MSB=0
        TimeStamp ts = new TimeStamp(date);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
        // Verify MSB is indeed 0 for the seconds part
        assertEquals(0, (expectedNtpTime >>> 63) & 1);
    }

    /**
     * Test ntpValue() when constructed with a null Date object.
     * The constructor documentation implies this results in ntpTime = 0.
     */
    @Test
    public void testNtpValueWithNullDate() {
        // Arrange
        Date date = null;
        long expectedNtpTime = 0L;
        TimeStamp ts = new TimeStamp(date);

        // Act
        long actualNtpTime = ts.ntpValue();

        // Assert
        assertEquals(expectedNtpTime, actualNtpTime);
    }
}
```
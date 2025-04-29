import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

/**
 * Test class for TimeStamp#getSeconds().
 * Contains unit test cases focusing on extracting the seconds part (high-order 32 bits)
 * from the 64-bit NTP timestamp.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 * Uses JUnit 4 annotations.
 */
public class TimeStamp_getSecondsTest {

    // No setup or teardown needed for these stateless tests.
    @Before
    public void setUp() throws Exception {
        // Optional: Setup resources if needed in the future.
    }

    @After
    public void tearDown() throws Exception {
        // Optional: Teardown or clean up resources if needed.
    }

    /**
     * Test case for a zero NTP timestamp.
     * The seconds part should be 0.
     */
    @Test
    public void testGetSeconds_ZeroTimestamp() {
        // Arrange
        long ntpTimeValue = 0L;
        TimeStamp ts = new TimeStamp(ntpTimeValue);
        long expectedSeconds = 0L;

        // Act
        long actualSeconds = ts.getSeconds();

        // Assert
        assertEquals("Seconds should be 0 for zero NTP timestamp", expectedSeconds, actualSeconds);
    }

    /**
     * Test case for a timestamp with only the seconds part set (fraction is zero).
     * The seconds part should match the high-order 32 bits.
     */
    @Test
    public void testGetSeconds_OnlySecondsPart() {
        // Arrange
        long expectedSeconds = 0x12345678L;
        long ntpTimeValue = expectedSeconds << 32; // Shift seconds to high-order bits
        TimeStamp ts = new TimeStamp(ntpTimeValue);

        // Act
        long actualSeconds = ts.getSeconds();

        // Assert
        assertEquals("Seconds part extraction failed", expectedSeconds, actualSeconds);
    }

    /**
     * Test case for a timestamp with only the fractional part set (seconds are zero).
     * The seconds part should be 0.
     */
    @Test
    public void testGetSeconds_OnlyFractionPart() {
        // Arrange
        long ntpTimeValue = 0x00000000ABCDEF12L; // Only fractional part
        TimeStamp ts = new TimeStamp(ntpTimeValue);
        long expectedSeconds = 0L;

        // Act
        long actualSeconds = ts.getSeconds();

        // Assert
        assertEquals("Seconds should be 0 when only fraction exists", expectedSeconds, actualSeconds);
    }

    /**
     * Test case for a timestamp with both seconds and fractional parts set.
     * The seconds part should match the high-order 32 bits.
     */
    @Test
    public void testGetSeconds_BothPartsSet() {
        // Arrange
        long expectedSeconds = 0xABCDEF12L;
        long fraction = 0x12345678L;
        long ntpTimeValue = (expectedSeconds << 32) | fraction;
        TimeStamp ts = new TimeStamp(ntpTimeValue);

        // Act
        long actualSeconds = ts.getSeconds();

        // Assert
        assertEquals("Seconds part extraction failed with both parts set", expectedSeconds, actualSeconds);
    }

    /**
     * Test case for a timestamp where the seconds part is the maximum unsigned 32-bit value.
     */
    @Test
    public void testGetSeconds_MaxSecondsValue() {
        // Arrange
        long expectedSeconds = 0xFFFFFFFFL;
        long ntpTimeValue = expectedSeconds << 32; // Max seconds, zero fraction
        TimeStamp ts = new TimeStamp(ntpTimeValue);

        // Act
        long actualSeconds = ts.getSeconds();

        // Assert
        assertEquals("Maximum seconds value extraction failed", expectedSeconds, actualSeconds);
    }

    /**
     * Test case for a timestamp representing the maximum possible 64-bit NTP value.
     * The seconds part should be the maximum unsigned 32-bit value.
     */
    @Test
    public void testGetSeconds_MaxNtpValue() {
        // Arrange
        long ntpTimeValue = -1L; // Equivalent to 0xFFFFFFFFFFFFFFFFL
        TimeStamp ts = new TimeStamp(ntpTimeValue);
        long expectedSeconds = 0xFFFFFFFFL;

        // Act
        long actualSeconds = ts.getSeconds();

        // Assert
        assertEquals("Seconds extraction for max NTP value failed", expectedSeconds, actualSeconds);
    }

    /**
     * Test case using a known NTP hex string corresponding to a date before 2036.
     * The MSB of the seconds part should be 1.
     * Example: Dec 10 2002 10:41:49.986 UTC -> c1a089bd.fc904f6d
     */
    @Test
    public void testGetSeconds_KnownValueBefore2036() {
        // Arrange
        String hexStamp = "c1a089bd.fc904f6d";
        TimeStamp ts = new TimeStamp(hexStamp);
        long expectedSeconds = 0xc1a089bdL; // High 32 bits from hex string

        // Act
        long actualSeconds = ts.getSeconds();

        // Assert
        assertEquals("Seconds extraction for known pre-2036 timestamp failed", expectedSeconds, actualSeconds);
        assertTrue("MSB of seconds should be 1 for pre-2036 dates", (actualSeconds & 0x80000000L) != 0);
    }

     /**
     * Test case using a timestamp derived from a date just before the 2036 boundary.
     * `msb0baseTime` corresponds to NTP seconds 0 when using base 0, or 0x83AA7E80 when using base 1.
     * A time just before this should use base 1 (MSB=1).
     */
    @Test
    public void testGetSeconds_JustBefore2036Boundary() {
        // Arrange
        // Time just before the boundary (msb0baseTime represents 7-Feb-2036 06:28:16 UTC)
        long javaTime = TimeStamp.msb0baseTime - 1000; // 1 second before boundary
        TimeStamp ts = TimeStamp.getNtpTime(javaTime);
        // Expected seconds: The base-1 seconds value for this time.
        // (javaTime - msb1baseTime) / 1000
        long expectedSeconds = (javaTime - TimeStamp.msb1baseTime) / 1000;
        expectedSeconds |= 0x80000000L; // Base 1 sets the MSB

        // Act
        long actualSeconds = ts.getSeconds();

        // Assert
        assertEquals("Seconds just before 2036 boundary mismatch", expectedSeconds, actualSeconds);
        assertTrue("MSB should be 1 just before 2036 boundary", (actualSeconds & 0x80000000L) != 0);
    }

    /**
     * Test case using a timestamp derived from a date just after the 2036 boundary.
     * This time should use base 0 (MSB=0).
     */
    @Test
    public void testGetSeconds_JustAfter2036Boundary() {
        // Arrange
        // Time just after the boundary (msb0baseTime represents 7-Feb-2036 06:28:16 UTC)
        long javaTime = TimeStamp.msb0baseTime + 1000; // 1 second after boundary
        TimeStamp ts = TimeStamp.getNtpTime(javaTime);
         // Expected seconds: The base-0 seconds value for this time.
        // (javaTime - msb0baseTime) / 1000
        long expectedSeconds = (javaTime - TimeStamp.msb0baseTime) / 1000; // Should be 1

        // Act
        long actualSeconds = ts.getSeconds();

        // Assert
        assertEquals("Seconds just after 2036 boundary mismatch", expectedSeconds, actualSeconds);
        assertTrue("MSB should be 0 just after 2036 boundary", (actualSeconds & 0x80000000L) == 0);
    }

    /**
     * Test case using a timestamp derived from the Java Epoch (Jan 1, 1970).
     * This is before 2036, so it uses base 1 (MSB=1).
     * NTP epoch is Jan 1, 1900. Java epoch is Jan 1, 1970.
     * Seconds between 1900 and 1970 = 70 years * 365.25 days/year * 24 hours/day * 3600 seconds/hour approx.
     * Precise value from msb1baseTime: (0 - (-2208988800000L)) / 1000 = 2208988800 seconds
     */
    @Test
    public void testGetSeconds_FromJavaEpoch() {
        // Arrange
        Date epochDate = new Date(0); // Java Epoch: Jan 1, 1970 00:00:00 GMT
        TimeStamp ts = new TimeStamp(epochDate);
        // Calculate expected NTP seconds relative to Jan 1, 1900 00:00:00 UTC
        // Note: The TimeStamp class uses 1-Jan-1900 01:00:00 UTC as msb1baseTime according to its constant name,
        // although the RFC standard usually refers to 00:00:00. Let's trust the constant value.
        long expectedSecondsBase = (0L - TimeStamp.msb1baseTime) / 1000; // Seconds between Java epoch and NTP base 1
        long expectedSeconds = expectedSecondsBase | 0x80000000L; // Set MSB for base 1

        // Act
        long actualSeconds = ts.getSeconds();

        // Assert
        assertEquals("Seconds calculated from Java Epoch mismatch", expectedSeconds, actualSeconds);
        assertTrue("MSB should be 1 for Java Epoch timestamp", (actualSeconds & 0x80000000L) != 0);
        // Specific check for the known value 2208988800 | 0x80000000
        assertEquals("Specific seconds value for Java Epoch mismatch", 0x83AA7E80L, actualSeconds);
    }
}
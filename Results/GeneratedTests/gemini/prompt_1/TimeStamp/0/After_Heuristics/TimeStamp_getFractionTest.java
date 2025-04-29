import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the TimeStamp#getFraction() method.
 * Focuses on verifying the correct extraction of the fractional part
 * from the 64-bit NTP timestamp value.
 * Uses the Arrange-Act-Assert pattern.
 */
public class TimeStamp_getFractionTest {

    /**
     * Test case for a timestamp representing zero (0L).
     * Expects the fraction part to be 0.
     */
    @Test
    public void testGetFraction_ZeroTimestamp() {
        // Arrange
        long ntpTime = 0L;
        TimeStamp ts = new TimeStamp(ntpTime);
        long expectedFraction = 0L;

        // Act
        long actualFraction = ts.getFraction();

        // Assert
        assertEquals("Fraction should be 0 for a zero timestamp", expectedFraction, actualFraction);
    }

    /**
     * Test case for a timestamp with only the fractional part set to its maximum value.
     * The seconds part is zero.
     * Expects the fraction part to be 0xFFFFFFFFL.
     */
    @Test
    public void testGetFraction_MaxFractionOnly() {
        // Arrange
        long ntpTime = 0x00000000FFFFFFFFL; // 0 seconds, max fraction
        TimeStamp ts = new TimeStamp(ntpTime);
        long expectedFraction = 0xFFFFFFFFL;

        // Act
        long actualFraction = ts.getFraction();

        // Assert
        assertEquals("Fraction should be the maximum 32-bit value", expectedFraction, actualFraction);
    }

    /**
     * Test case for a timestamp with only the seconds part set (fraction is zero).
     * Expects the fraction part to be 0.
     */
    @Test
    public void testGetFraction_SecondsOnly() {
        // Arrange
        long ntpTime = 0xABCDEF1200000000L; // Non-zero seconds, zero fraction
        TimeStamp ts = new TimeStamp(ntpTime);
        long expectedFraction = 0L;

        // Act
        long actualFraction = ts.getFraction();

        // Assert
        assertEquals("Fraction should be 0 when only seconds are set", expectedFraction, actualFraction);
    }

    /**
     * Test case for a timestamp with the maximum possible 64-bit value (Long.MAX_VALUE).
     * Expects the fraction part to correspond to the lower 32 bits of Long.MAX_VALUE.
     */
    @Test
    public void testGetFraction_LongMaxValue() {
        // Arrange
        long ntpTime = Long.MAX_VALUE; // 0x7FFFFFFFFFFFFFFF
        TimeStamp ts = new TimeStamp(ntpTime);
        long expectedFraction = 0xFFFFFFFFL; // Lower 32 bits of Long.MAX_VALUE

        // Act
        long actualFraction = ts.getFraction();

        // Assert
        assertEquals("Fraction should be lower 32 bits of Long.MAX_VALUE", expectedFraction, actualFraction);
    }

    /**
     * Test case for a timestamp with a specific, non-trivial fraction value.
     * Uses a known NTP hex string representation.
     */
    @Test
    public void testGetFraction_SpecificKnownValue1() {
        // Arrange
        // From example: c1a089bd.fc904f6d
        long ntpTime = 0xc1a089bdfc904f6dL;
        TimeStamp ts = new TimeStamp(ntpTime);
        long expectedFraction = 0xfc904f6dL;

        // Act
        long actualFraction = ts.getFraction();

        // Assert
        assertEquals("Fraction should match the known lower 32 bits", expectedFraction, actualFraction);
    }

    /**
     * Test case for a timestamp with another specific, non-trivial fraction value.
     */
    @Test
    public void testGetFraction_SpecificKnownValue2() {
        // Arrange
        long ntpTime = 0x12345678abcdef01L;
        TimeStamp ts = new TimeStamp(ntpTime);
        long expectedFraction = 0xabcdef01L;

        // Act
        long actualFraction = ts.getFraction();

        // Assert
        assertEquals("Fraction should match the known lower 32 bits", expectedFraction, actualFraction);
    }

     /**
     * Test case for a timestamp where the fraction part has its most significant bit set.
     */
    @Test
    public void testGetFraction_FractionMsbSet() {
        // Arrange
        long ntpTime = 0x9876543280000000L; // Fraction starts with '8'
        TimeStamp ts = new TimeStamp(ntpTime);
        long expectedFraction = 0x80000000L;

        // Act
        long actualFraction = ts.getFraction();

        // Assert
        assertEquals("Fraction should correctly handle MSB set in lower 32 bits", expectedFraction, actualFraction);
    }

    /**
     * Test case for a timestamp with the minimum non-zero fraction value (1).
     * Seconds part is zero.
     */
    @Test
    public void testGetFraction_MinNonZeroFraction() {
        // Arrange
        long ntpTime = 0x0000000000000001L; // 0 seconds, fraction = 1
        TimeStamp ts = new TimeStamp(ntpTime);
        long expectedFraction = 1L;

        // Act
        long actualFraction = ts.getFraction();

        // Assert
        assertEquals("Fraction should be 1 for the smallest non-zero fraction", expectedFraction, actualFraction);
    }

     /**
     * Test case constructed from a specific Date before the 2036 threshold.
     * Note: Conversion Date -> NTP -> fraction might have rounding, but the mask operation should still be correct.
     * Let's use a known NTP value instead for precision.
     */
    @Test
    public void testGetFraction_FromDateBefore2036() {
        // Arrange: Use a known hex value corresponding to a date before 2036
        // Example: Jan 1, 2024 00:00:00 UTC corresponds roughly to NTP time
        // Let's use a value where the fraction is clearly defined from the hex.
        long ntpTime = 0xE66E07A012345678L; // Corresponds to a time in 2024
        TimeStamp ts = new TimeStamp(ntpTime);
        long expectedFraction = 0x12345678L;

        // Act
        long actualFraction = ts.getFraction();

        // Assert
        assertEquals("Fraction should be correct for time before 2036", expectedFraction, actualFraction);
    }

    /**
     * Test case constructed from a specific Date after the 2036 threshold.
     * Note: Conversion Date -> NTP -> fraction might have rounding, but the mask operation should still be correct.
     * Let's use a known NTP value instead for precision.
     */
    @Test
    public void testGetFraction_FromDateAfter2036() {
        // Arrange: Use a known hex value corresponding to a date after 2036
        // Example: Time in 2040. MSB of seconds will be 0.
        long ntpTime = 0x1A3B5C7D9E8F7A6BL; // Hypothetical time after 2036 (MSB of seconds is 0)
        TimeStamp ts = new TimeStamp(ntpTime);
        long expectedFraction = 0x9E8F7A6BL;

        // Act
        long actualFraction = ts.getFraction();

        // Assert
        assertEquals("Fraction should be correct for time after 2036", expectedFraction, actualFraction);
    }
}
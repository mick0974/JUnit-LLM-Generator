import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for TimeStamp#parseNtpString(String s).
 * Contains unit test cases for parsing NTP timestamp strings.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 */
public class TimeStamp_parseNtpStringTest {

    /**
     * Test parsing a valid NTP timestamp string with both seconds and fraction parts.
     */
    @Test
    public void testParseNtpString_ValidFullString() {
        // Arrange
        String ntpString = "c1a089bd.fc904f6d";
        // Expected value derived from parsing the hex parts:
        // seconds = 0xc1a089bdL = 3248515517L
        // fraction = 0xfc904f6dL = 4237311853L
        // ntpTime = (seconds << 32) | fraction
        long expectedNtpValue = 0xc1a089bdfc904f6dL;

        // Act
        TimeStamp result = TimeStamp.parseNtpString(ntpString);

        // Assert
        assertNotNull(result);
        assertEquals(expectedNtpValue, result.ntpValue());
    }

    /**
     * Test parsing a valid NTP timestamp string with only the seconds part.
     */
    @Test
    public void testParseNtpString_ValidSecondsOnly() {
        // Arrange
        String ntpString = "c1a089bd";
        // Expected value: seconds part shifted left by 32 bits
        long expectedNtpValue = 0xc1a089bd00000000L;

        // Act
        TimeStamp result = TimeStamp.parseNtpString(ntpString);

        // Assert
        assertNotNull(result);
        assertEquals(expectedNtpValue, result.ntpValue());
    }

    /**
     * Test parsing a valid NTP timestamp string with zero seconds part and a fractional part.
     */
    @Test
    public void testParseNtpString_ValidZeroSeconds() {
        // Arrange
        String ntpString = "0.fc904f6d";
        long expectedNtpValue = 0x00000000fc904f6dL;

        // Act
        TimeStamp result = TimeStamp.parseNtpString(ntpString);

        // Assert
        assertNotNull(result);
        assertEquals(expectedNtpValue, result.ntpValue());
    }

    /**
     * Test parsing a zero NTP timestamp string with a decimal point.
     */
    @Test
    public void testParseNtpString_ZeroTimestampPoint() {
        // Arrange
        String ntpString = "0.0";
        long expectedNtpValue = 0L;

        // Act
        TimeStamp result = TimeStamp.parseNtpString(ntpString);

        // Assert
        assertNotNull(result);
        assertEquals(expectedNtpValue, result.ntpValue());
    }

    /**
     * Test parsing a zero NTP timestamp string without a decimal point.
     */
    @Test
    public void testParseNtpString_ZeroTimestampNoPoint() {
        // Arrange
        String ntpString = "0";
        long expectedNtpValue = 0L; // Represents 0 seconds, 0 fraction

        // Act
        TimeStamp result = TimeStamp.parseNtpString(ntpString);

        // Assert
        assertNotNull(result);
        assertEquals(expectedNtpValue, result.ntpValue());
    }

    /**
     * Test parsing an NTP timestamp string representing the maximum 64-bit value.
     */
    @Test
    public void testParseNtpString_MaxValue() {
        // Arrange
        String ntpString = "ffffffff.ffffffff";
        // This hex value corresponds to -1 in signed 64-bit integer representation
        long expectedNtpValue = 0xffffffffffffffffL; // -1L

        // Act
        TimeStamp result = TimeStamp.parseNtpString(ntpString);

        // Assert
        assertNotNull(result);
        assertEquals(expectedNtpValue, result.ntpValue());
    }

    /**
     * Test parsing an empty string. The implementation treats this as zero.
     */
    @Test
    public void testParseNtpString_EmptyString() {
        // Arrange
        String ntpString = "";
        long expectedNtpValue = 0L;

        // Act
        TimeStamp result = TimeStamp.parseNtpString(ntpString);

        // Assert
        assertNotNull(result);
        assertEquals(expectedNtpValue, result.ntpValue());
    }

    /**
     * Test parsing a null string, expecting a NumberFormatException.
     */
    @Test(expected = NumberFormatException.class)
    public void testParseNtpString_NullInput() {
        // Arrange
        String ntpString = null;

        // Act
        TimeStamp.parseNtpString(ntpString);

        // Assert (Exception expected)
    }

    /**
     * Test parsing a string with invalid hexadecimal characters, expecting a NumberFormatException.
     */
    @Test(expected = NumberFormatException.class)
    public void testParseNtpString_InvalidHexChars() {
        // Arrange
        String ntpString = "c1a089bd.invalid"; // 'i', 'n', 'v', 'a', 'l', 'd' are not hex

        // Act
        TimeStamp.parseNtpString(ntpString);

        // Assert (Exception expected)
    }

    /**
     * Test parsing a string that is malformed (ends with a decimal point but no fraction),
     * expecting a NumberFormatException.
     */
    @Test(expected = NumberFormatException.class)
    public void testParseNtpString_MalformedDecimal() {
        // Arrange
        String ntpString = "c1a089bd."; // Missing fraction part after decimal

        // Act
        TimeStamp.parseNtpString(ntpString);

        // Assert (Exception expected)
    }
}
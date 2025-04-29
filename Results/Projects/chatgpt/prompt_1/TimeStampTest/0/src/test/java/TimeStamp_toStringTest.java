// TimeStamp_toStringTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Test class of TimeStamp.
 * It contains 10 unit test cases for the
 * TimeStamp#public toString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_toStringTest {

private static final String HEX_STRING_1 = "c1a089bd.fc904f6d"; // Corresponds to an arbitrary date
    private static final String HEX_STRING_2 = "83aa7e80.00000000"; // Corresponds to 7-Feb-2036 @ 06:28:16 UTC

    private TimeStamp timeStamp1;
    private TimeStamp timeStamp2;
    private TimeStamp currentTimestamp;

    @Before
    public void setUp() {
        timeStamp1 = new TimeStamp(0xc1a089bd_fc904f6dL);
        timeStamp2 = new TimeStamp(0x83aa7e80_00000000L);
        currentTimestamp = TimeStamp.getCurrentTime();
    }

    @After
    public void tearDown() {
        timeStamp1 = null;
        timeStamp2 = null;
        currentTimestamp = null;
    }

    @Test
    public void testToString() {
        String result = timeStamp1.toString();
        assertEquals("Hexadecimal string should match", HEX_STRING_1, result);
    }

    @Test
    public void testToStringEdgeCase() {
        String result = timeStamp2.toString();
        assertEquals("Hexadecimal string should match for MSB = 0 case", HEX_STRING_2, result);
    }

    @Test
    public void testToStringWithCurrentTime() {
        String result = currentTimestamp.toString();
        assertNotNull("Current timestamp should not be null", result);
        assertTrue("Current timestamp string should contain a '.' separator", result.contains("."));
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidHexString() {
        new TimeStamp("invalid_hex").toString();
    }

    @Test
    public void testToStringFormat() {
        String result = timeStamp1.toString();
        assertTrue("String should be in 'xxxx.yyyy' format with both parts being 8 characters long",
                result.matches("[0-9a-f]{8}\\.[0-9a-f]{8}"));
    }

    @Test
    public void testToStringMatchesConversionOutput() {
        String result = timeStamp1.toString();
        long parsedTime = TimeStamp.parseNtpString(result).ntpValue();
        assertEquals("Parsed NTP timestamp should match original value", timeStamp1.ntpValue(), parsedTime);
    }

    @Test
    public void testToStringEquality() {
        String result1 = timeStamp1.toString();
        String result2 = timeStamp1.toString();
        assertEquals("Calling toString twice should yield the same result", result1, result2);
    }

    @Test
    public void testToStringLength() {
        String result = timeStamp1.toString();
        assertEquals("String length should be 17 characters", 17, result.length());
    }

    @Test
    public void testToStringNullSafety() {
        TimeStamp nullDateTime = new TimeStamp((Date) null);
        String result = nullDateTime.toString();
        assertEquals("String should represent zero value", "00000000.00000000", result);
    }

    @Test
    public void testToStringConsistencyWithDate() {
        DateFormat formatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT, Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date1 = timeStamp1.getDate();
        Date date2 = new TimeStamp(timeStamp1.toString()).getDate();
        assertEquals("Dates should match when converting to and from string", formatter.format(date1), formatter.format(date2));
    }
}
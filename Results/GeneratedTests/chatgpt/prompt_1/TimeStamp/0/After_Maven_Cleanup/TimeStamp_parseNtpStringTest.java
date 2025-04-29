// TimeStamp_parseNtpStringTest.java
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
 * TimeStamp#public static parseNtpString(String s) throws NumberFormatException  method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_parseNtpStringTest {

private DateFormat formatter;

    @Before
    public void setUp() {
        formatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @After
    public void tearDown() {
        formatter = null;
    }

    @Test
    public void testParseValidNtpString() {
        String ntpString = "c1a089bd.fc904f6d";
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        assertEquals(ntpString, timestamp.toString());
    }

    @Test
    public void testParseNtpStringEdgeCaseAllZeros() {
        String ntpString = "00000000.00000000";
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        assertEquals(ntpString, timestamp.toString());
    }

    @Test(expected = NumberFormatException.class)
    public void testParseNtpStringInvalidHex() {
        String ntpString = "zzzzzzzz.zzzzzzzz";
        TimeStamp.parseNtpString(ntpString);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseNtpStringNull() {
        TimeStamp.parseNtpString(null);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseNtpStringEmpty() {
        TimeStamp.parseNtpString("");
    }

    @Test
    public void testParseNtpStringValid1900Epoch() {
        // Hex representation of Jan 1, 1900
        String ntpString = "80000000.00000000";
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        assertEquals(ntpString, timestamp.toString());
    }

    @Test
    public void testParseNtpStringValid2036Epoch() {
        // NTP time for 7-Feb-2036 06:28:16 UTC
        String ntpString = "00000000.00000000";
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        assertEquals(ntpString, timestamp.toString());
    }

    @Test
    public void testParseNtpStringMidRangeValue() {
        // Mid-range value check
        String ntpString = "ffffffff.ffffffff";
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        assertEquals(ntpString, timestamp.toString());
    }


    @Test
    public void testParseNtpStringWithRoundTripConversion() {
        String ntpString = "4b963f77.9a054be6"; // Another arbitrary NTP timestamp
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        String result = timestamp.toString();
        assertEquals(ntpString, result);
    }
}
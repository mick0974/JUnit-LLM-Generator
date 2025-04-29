// TimeStamp_toUTCStringTest.java
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
 * TimeStamp#public toUTCString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_toUTCStringTest {

 private TimeStamp timeStamp;
    private DateFormat utcFormatter;
    @Before
    public void setUp() {
        utcFormatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'", Locale.US);
        utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    @After
    public void tearDown() {
        timeStamp = null;
    }
    @Test
    public void testToUTCString_RFC1900_Base() {
        long ntpTime = Long.MAX_VALUE;
        timeStamp = new TimeStamp(ntpTime);
        String result = timeStamp.toUTCString();
        Date expectedDate = new Date(timeStamp.getTime());
        String expected = utcFormatter.format(expectedDate);
        assertEquals("Mismatch in UTC string for RFC 1900 base", expected, result);
    }
    @Test
    public void testToUTCString_CurrentTime() {
        timeStamp = TimeStamp.getCurrentTime();
        String result = timeStamp.toUTCString();
        Date currentDate = new Date(timeStamp.getTime());
        String expected = utcFormatter.format(currentDate);
        assertEquals("Mismatch between current UTC date string", expected, result);
    }
    @Test
    public void testToUTCString_FromJavaDate() {
        Date now = new Date();
        timeStamp = new TimeStamp(now);
        String result = timeStamp.toUTCString();
        String expected = utcFormatter.format(now);
        assertEquals("Mismatch between Java Date and UTC formatted string", expected, result);
    }
    @Test
    public void testToUTCString_MidnightDate() {
        Date midnight = new Date(0);
        timeStamp = new TimeStamp(midnight);
        String result = timeStamp.toUTCString();
        String expected = utcFormatter.format(midnight);
        assertEquals("Mismatch for midnight UTC formatted string", expected, result);
    }
    @Test
    public void testToUTCString_EndOfCentury() {
        Date endOfCentury = new Date(Long.parseLong("4102444800000"));
        timeStamp = new TimeStamp(endOfCentury);
        String result = timeStamp.toUTCString();
        String expected = utcFormatter.format(endOfCentury);
        assertEquals("Mismatch for end of century UTC formatted string", expected, result);
    }
}
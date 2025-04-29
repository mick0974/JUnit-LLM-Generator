// TimeStamp_getDateTest.java
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
 * TimeStamp#public getDate() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getDateTest {

 private TimeStamp timeStamp;
    private DateFormat dateFormat;
    @Before
    public void setUp() {
        dateFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    @After
    public void tearDown() {
        timeStamp = null;
    }
    @Test
    public void testDateFromCurrentNtpTime() {
        Date now = new Date();
        timeStamp = new TimeStamp(now);
        Date expectedDate = now;
        Date result = timeStamp.getDate();
        assertEquals(expectedDate.getTime() / 1000, result.getTime() / 1000);
    }
    @Test
    public void testDateFromFutureNtpTimeAfter2036() {
        Date future = new Date(Long.parseLong("2208988800000"));
        timeStamp = new TimeStamp(future);
        Date result = timeStamp.getDate();
        assertEquals(future.getTime() / 1000, result.getTime() / 1000);
    }
    @Test
    public void testDateFromMaxLongNtpTime() {
        timeStamp = new TimeStamp(Long.MAX_VALUE);
        Date result = timeStamp.getDate();
        long msAfter1900 = result.getTime() - TimeStamp.msb1baseTime;
        assertTrue(msAfter1900 > 0);
    }
    @Test(expected = NumberFormatException.class)
    public void testDateFromInvalidHexString() {
        timeStamp = new TimeStamp("invalidHex");
    }
}
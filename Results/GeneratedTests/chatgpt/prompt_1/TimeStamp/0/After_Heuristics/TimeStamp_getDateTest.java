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
    public void testDateFromZeroNtpTime() {
        timeStamp = new TimeStamp(0L);
        Date expectedDate = new Date(TimeStamp.msb1baseTime);
        Date result = timeStamp.getDate();
        assertEquals(expectedDate, result);
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
    public void testDateFromPastNtpTimeBefore1970() {
        Date past = new Date(-Long.parseLong("1577923200000"));
        timeStamp = new TimeStamp(past);
        Date result = timeStamp.getDate();
        assertEquals(past.getTime() / 1000, result.getTime() / 1000);
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
    @Test
    public void testDateFromNegativeHexString() {
        timeStamp = new TimeStamp("-1.0");
        Date expectedDate = new Date(TimeStamp.msb1baseTime);
        Date result = timeStamp.getDate();
        assertEquals(expectedDate, result);
    }
    @Test
    public void testDateFromRandomHexString() {
        timeStamp = new TimeStamp("c1a089bd.fc904f6d");
        long expectedTime = Long.parseLong("1044849759986");
        Date result = timeStamp.getDate();
        assertEquals(expectedTime, result.getTime());
    }
    @Test
    public void testDateFromLargeNtpTime() {
        Date extremeFuture = new Date(Long.MAX_VALUE - 10000);
        timeStamp = new TimeStamp(extremeFuture);
        Date result = timeStamp.getDate();
        assertEquals(extremeFuture.getTime() / 1000, result.getTime() / 1000);
    }
    @Test
    public void testDateFromSmallNtpTime() {
        Date extremePast = new Date(Long.MIN_VALUE + 10000);
        timeStamp = new TimeStamp(extremePast);
        Date result = timeStamp.getDate();
        assertEquals(extremePast.getTime() / 1000, result.getTime() / 1000);
    }
    @Test(expected = NumberFormatException.class)
    public void testDateFromInvalidHexString() {
        timeStamp = new TimeStamp("invalidHex");
    }
}
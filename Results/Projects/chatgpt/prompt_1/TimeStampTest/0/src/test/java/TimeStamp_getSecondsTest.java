// TimeStamp_getSecondsTest.java
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
 * TimeStamp#public getSeconds() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getSecondsTest {

private static final long TEST_NTP_TIME_1 = 0xC1A09BD7L << 32 | 0xFC904F6DL; // Example NTP time
    private static final long TEST_NTP_TIME_2 = 0x80000000L << 32; // NTP time with MSB set
    private static final long TEST_NTP_TIME_3 = 0x7FFFFFFFL << 32; // NTP time with MSB not set, edge case

    private TimeStamp timeStamp1;
    private TimeStamp timeStamp2;
    private TimeStamp timeStamp3;

    @Before
    public void setUp() {
        timeStamp1 = new TimeStamp(TEST_NTP_TIME_1);
        timeStamp2 = new TimeStamp(TEST_NTP_TIME_2);
        timeStamp3 = new TimeStamp(TEST_NTP_TIME_3);
    }

    @After
    public void tearDown() {
        timeStamp1 = null;
        timeStamp2 = null;
        timeStamp3 = null;
    }

    @Test
    public void testGetSecondsTypicalValue() {
        long expectedSeconds = TEST_NTP_TIME_1 >>> 32;
        assertEquals(expectedSeconds, timeStamp1.getSeconds());
    }

    @Test
    public void testGetSecondsMsbSet() {
        long expectedSeconds = TEST_NTP_TIME_2 >>> 32;
        assertEquals(expectedSeconds, timeStamp2.getSeconds());
    }

    @Test
    public void testGetSecondsMsbNotSet() {
        long expectedSeconds = TEST_NTP_TIME_3 >>> 32;
        assertEquals(expectedSeconds, timeStamp3.getSeconds());
    }

    @Test
    public void testGetSecondsFromZeroTime() {
        TimeStamp zeroTimeStamp = new TimeStamp(0L);
        assertEquals(0L, zeroTimeStamp.getSeconds());
    }

    @Test
    public void testGetSecondsFromCurrentTime() {
        TimeStamp currentTimeStamp = TimeStamp.getCurrentTime();
        long seconds = currentTimeStamp.getSeconds();
        assertTrue("Seconds should be a positive value.", seconds > 0);
    }


    @Test
    public void testGetSecondsForNegativeTime() {
        long negativeNtpTime = 0xFFFFFFFFL << 32;
        TimeStamp negativeTimeStamp = new TimeStamp(negativeNtpTime);
        assertEquals(negativeNtpTime >>> 32, negativeTimeStamp.getSeconds());
    }

    @Test
    public void testGetSecondsForMaxLongTime() {
        long maxLongNtpTime = 0xFFFFFFFFFFFFFFFFL;
        TimeStamp maxLongTimeStamp = new TimeStamp(maxLongNtpTime);
        assertEquals(0xFFFFFFFFL, maxLongTimeStamp.getSeconds());
    }

    @Test
    public void testGetSecondsFromStringConstructor() {
        TimeStamp hexStringTimeStamp = new TimeStamp("c1a09bd7.fc904f6d");
        long expectedSeconds = 0xC1A09BD7L;
        assertEquals(expectedSeconds, hexStringTimeStamp.getSeconds());
    }

    @Test(expected = NumberFormatException.class)
    public void testGetSecondsFromInvalidHexStringConstructor() {
        new TimeStamp("invalidHexString");
    }
}
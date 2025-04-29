// TimeStamp_compareToTest.java
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
 * TimeStamp#public compareTo(TimeStamp anotherTimeStamp) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_compareToTest {

 private TimeStamp timeStamp1;
    private TimeStamp timeStamp2;
    @Before
    public void setUp() {
    }
    @After
    public void tearDown() {
    }
    @Test
    public void testCompareTo_SameTimestamp() {
        timeStamp1 = TimeStamp.getCurrentTime();
        timeStamp2 = new TimeStamp(timeStamp1.ntpValue());
        int result = timeStamp1.compareTo(timeStamp2);
        assertEquals("compareTo should return 0 for identical timestamps", 0, result);
    }
    @Test
    public void testCompareTo_EarlierTimestamp() {
        timeStamp1 = new TimeStamp(0);
        timeStamp2 = new TimeStamp(1);
        int result = timeStamp1.compareTo(timeStamp2);
        assertTrue("compareTo should return negative when this timestamp is earlier", result < 0);
    }
    @Test
    public void testCompareTo_LaterTimestamp() {
        timeStamp1 = new TimeStamp(1);
        timeStamp2 = new TimeStamp(0);
        int result = timeStamp1.compareTo(timeStamp2);
        assertTrue("compareTo should return positive when this timestamp is later", result > 0);
    }
    @Test
    public void testCompareTo_MaxLongTimestamp() {
        timeStamp1 = new TimeStamp(Long.MAX_VALUE);
        timeStamp2 = new TimeStamp(Long.MAX_VALUE - 1);
        int result = timeStamp1.compareTo(timeStamp2);
        assertTrue("compareTo should return positive when this timestamp is at max long value", result > 0);
    }
    @Test
    public void testCompareTo_MinLongTimestamp() {
        timeStamp1 = new TimeStamp(Long.MIN_VALUE);
        timeStamp2 = new TimeStamp(Long.MIN_VALUE + 1);
        int result = timeStamp1.compareTo(timeStamp2);
        assertTrue("compareTo should return negative when this timestamp is at min long value", result < 0);
    }
    @Test
    public void testCompareTo_SameMillisDifferentFraction() {
        long baseTime = Long.parseLong("1234567800000");
        timeStamp1 = TimeStamp.getNtpTime(baseTime);
        timeStamp2 = new TimeStamp(timeStamp1.ntpValue() | 0x00000001L);
        int result = timeStamp1.compareTo(timeStamp2);
        assertTrue("compareTo should respect fractional part differences", result < 0);
    }
    @Test
    public void testCompareTo_SlightlyLaterTimestamp() {
        timeStamp1 = new TimeStamp(500);
        timeStamp2 = new TimeStamp(501);
        int result = timeStamp1.compareTo(timeStamp2);
        assertTrue("compareTo should return negative when compared to a slightly later timestamp", result < 0);
    }
    @Test
    public void testCompareTo_SlightlyEarlierTimestamp() {
        timeStamp1 = new TimeStamp(501);
        timeStamp2 = new TimeStamp(500);
        int result = timeStamp1.compareTo(timeStamp2);
        assertTrue("compareTo should return positive when compared to a slightly earlier timestamp", result > 0);
    }
    @Test
    public void testCompareTo_FractionalExactMatch() {
        long baseTime = 12345L;
        timeStamp1 = new TimeStamp(baseTime << 32 | 0x00000000L);
        timeStamp2 = new TimeStamp(baseTime << 32 | 0x00000000L);
        int result = timeStamp1.compareTo(timeStamp2);
        assertEquals("compareTo should return 0 for exactly matching time with fractional zero", 0, result);
    }
    @Test(expected = NullPointerException.class)
    public void testCompareTo_NullTimestamp() {
        timeStamp1 = TimeStamp.getCurrentTime();
        timeStamp2 = null;
        timeStamp1.compareTo(timeStamp2);
    }
}
// TimeStamp_ntpValueTest.java
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
 * TimeStamp#public ntpValue() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_ntpValueTest {

 private TimeStamp timeStamp;
    @Before
    public void setUp() {
    }
    @After
    public void tearDown() {
    }
    @Test
    public void testNtpValueWithCurrentTime() {
        long currentTime = System.currentTimeMillis();
        timeStamp = TimeStamp.getCurrentTime();
        long ntpValue = timeStamp.ntpValue();
        assertTrue(ntpValue > 0);
    }
    @Test
    public void testNtpValueForSpecificTime() {
        long specificTime = Long.parseLong("1633072800000");
        timeStamp = TimeStamp.getNtpTime(specificTime);
        long ntpValue = timeStamp.ntpValue();
        assertEquals(ntpValue, timeStamp.ntpValue());
    }
    @Test
    public void testNtpValueWithMinTime() {
        timeStamp = new TimeStamp(0L);
        long ntpValue = timeStamp.ntpValue();
        assertEquals(0L, ntpValue);
    }
    @Test
    public void testNtpValueWithHexString() {
        String hexString = "c1a089bd.fc904f6d";
        timeStamp = new TimeStamp(hexString);
        long ntpValue = timeStamp.ntpValue();
        assertTrue(ntpValue > 0);
    }
    @Test
    public void testNtpValueWithInvalidHexString() {
        String hexString = "invalid_hex_string";
        try {
            new TimeStamp(hexString);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    @Test
    public void testNtpValueWithLeapYearDate() {
        Date leapYearDate = new Date(Long.parseLong("1583020800000"));
        timeStamp = new TimeStamp(leapYearDate);
        long ntpValue = timeStamp.ntpValue();
        assertTrue(ntpValue > 0);
    }
    @Test
    public void testNtpValueWithDateIn1990() {
        Date date1990 = new Date(Long.parseLong("631152000000"));
        timeStamp = new TimeStamp(date1990);
        long ntpValue = timeStamp.ntpValue();
        assertTrue(ntpValue > 0);
    }
    @Test
    public void testNtpValueWithFutureDate() {
        Date futureDate = new Date(Long.parseLong("3155673600000"));
        timeStamp = new TimeStamp(futureDate);
        long ntpValue = timeStamp.ntpValue();
        assertTrue(ntpValue > 0);
    }
    @Test
    public void testNtpValueWithBoundaryDate() {
        Date boundaryDate = new Date(-Long.parseLong("2208988800000"));
        timeStamp = new TimeStamp(boundaryDate);
        long ntpValue = timeStamp.ntpValue();
        assertTrue(ntpValue > 0);
    }
    @Test
    public void testEqualityOfNtpValues() {
        Date date = new Date(Long.parseLong("863991000000"));
        timeStamp = new TimeStamp(date);
        TimeStamp anotherTimeStamp = new TimeStamp(date);
        long ntpValue = timeStamp.ntpValue();
        long anotherNtpValue = anotherTimeStamp.ntpValue();
        assertEquals(ntpValue, anotherNtpValue);
    }
}
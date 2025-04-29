// TimeStamp_getNtpTimeTest.java
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
 * TimeStamp#public static getNtpTime(long date) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getNtpTimeTest {

private DateFormat formatter;

    @Before
    public void setUp() {
        formatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS z", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @After
    public void tearDown() {
        formatter = null;
    }

    @Test
    public void testGetNtpTime_Epoch() {
        long epochTime = 0L;
        TimeStamp ntpTimeStamp = TimeStamp.getNtpTime(epochTime);
        assertEquals("NTP time for epoch should be the same as epoch", epochTime, ntpTimeStamp.getTime());
    }

    @Test
    public void testGetNtpTime_2036Base() {
        long date = TimeStamp.msb0baseTime + 1000L; // 1 second after 7-Feb-2036 @ 06:28:16 UTC
        TimeStamp ntpTimeStamp = TimeStamp.getNtpTime(date);
        assertEquals("NTP time for date just after 7-Feb-2036 not correctly calculated", date, ntpTimeStamp.getTime());
    }

    @Test
    public void testGetNtpTime_Before2036Base() {
        long date = TimeStamp.msb0baseTime - 1000L; // 1 second before 7-Feb-2036 @ 06:28:16 UTC
        TimeStamp ntpTimeStamp = TimeStamp.getNtpTime(date);
        assertEquals("NTP timestamp should be calculated using msb1 base for dates before 2036 base time", date, ntpTimeStamp.getTime());
    }

    @Test
    public void testGetNtpTime_1900Base() {
        long date = TimeStamp.msb1baseTime + 1000L; // 1 second after 1-Jan-1900
        TimeStamp ntpTimeStamp = TimeStamp.getNtpTime(date);
        assertEquals("NTP time for date just after 1-Jan-1900 not correctly calculated", date, ntpTimeStamp.getTime());
    }

    @Test
    public void testGetNtpTime_CurrentTime() {
        long currentTime = System.currentTimeMillis();
        TimeStamp ntpTimeStamp = TimeStamp.getNtpTime(currentTime);
        assertEquals("NTP time for current time is incorrect", currentTime, ntpTimeStamp.getTime());
    }

    @Test
    public void testGetNtpTime_Mid1900s() {
        long dateMid1900s = TimeStamp.msb1baseTime + (50L * 365 * 24 * 60 * 60 * 1000); // Approx 1950
        TimeStamp ntpTimeStamp = TimeStamp.getNtpTime(dateMid1900s);
        assertEquals("NTP timestamp should be derived correctly for mid-20th century dates", dateMid1900s, ntpTimeStamp.getTime());
    }

    @Test
    public void testGetNtpTime_MaxLong() {
        long maxLongDate = Long.MAX_VALUE - 1; 
        TimeStamp ntpTimeStamp = TimeStamp.getNtpTime(maxLongDate);
        assertEquals("NTP timestamp should handle near Long.MAX_VALUE correctly", maxLongDate, ntpTimeStamp.getTime());
    }

    @Test
    public void testGetNtpTime_VeryOldDates() {
        long veryOldDate = TimeStamp.msb1baseTime / 2; // An arbitrary very old date
        TimeStamp ntpTimeStamp = TimeStamp.getNtpTime(veryOldDate);
        assertEquals("NTP timestamp should handle very old dates correctly", veryOldDate, ntpTimeStamp.getTime());
    }

    @Test
    public void testGetNtpTime_LeapYear() {
        String leapYearDateStr = "Sun, Feb 29 2004 12:00:00.000 GMT";
        try {
            Date date = formatter.parse(leapYearDateStr);
            TimeStamp ntpTimeStamp = TimeStamp.getNtpTime(date.getTime());
            assertEquals("NTP timestamp for a leap year day should be handled correctly", date.getTime(), ntpTimeStamp.getTime());
        } catch (Exception e) {
            fail("Exception occurred in parsing leap year date: " + e.getMessage());
        }
    }

    @Test
    public void testGetNtpTime_FutureDate() {
        String futureDateStr = "Fri, Jan 01 2100 00:00:00.000 GMT";
        try {
            Date date = formatter.parse(futureDateStr);
            TimeStamp ntpTimeStamp = TimeStamp.getNtpTime(date.getTime());
            assertEquals("NTP timestamp for a future date should be handled correctly", date.getTime(), ntpTimeStamp.getTime());
        } catch (Exception e) {
            fail("Exception occurred in parsing future date: " + e.getMessage());
        }
    }
}
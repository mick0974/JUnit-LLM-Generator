// TimeStamp_toDateStringTest.java
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
 * TimeStamp#public toDateString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_toDateStringTest {

 private TimeStamp timeStamp;
    private DateFormat simpleFormatter;
    @Before
    public void setUp() {
        simpleFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT, Locale.US);
        simpleFormatter.setTimeZone(TimeZone.getDefault());
    }
    @Test
    public void testToDateStringForEpoch() {
        timeStamp = new TimeStamp(new Date(0));
        String dateString = timeStamp.toDateString();
        assertEquals(simpleFormatter.format(new Date(0)), dateString);
    }
    @Test
    public void testToDateStringForCurrentTime() {
        Date currentDate = new Date();
        timeStamp = new TimeStamp(currentDate);
        String dateString = timeStamp.toDateString();
        assertEquals(simpleFormatter.format(currentDate), dateString);
    }
    @Test
    public void testToDateStringForSpecificDate() {
        long specificTime = Long.parseLong("1633046400000");
        timeStamp = new TimeStamp(new Date(specificTime));
        String dateString = timeStamp.toDateString();
        assertEquals(simpleFormatter.format(new Date(specificTime)), dateString);
    }
    @Test
    public void testToDateStringForNTPMaxLong() {
        long ntpTime = 0xFFFFFFFFFFFFFFFFL;
        timeStamp = new TimeStamp(ntpTime);
        String dateString = timeStamp.toDateString();
        long expectedJavaTime = TimeStamp.getTime(ntpTime);
        assertEquals(simpleFormatter.format(new Date(expectedJavaTime)), dateString);
    }
    @Test
    public void testToDateStringForDateBeforeEpoch() {
        long beforeEpochTime = -Long.parseLong("2208988800000");
        timeStamp = new TimeStamp(new Date(beforeEpochTime));
        String dateString = timeStamp.toDateString();
        assertEquals(simpleFormatter.format(new Date(beforeEpochTime)), dateString);
    }
    @Test
    public void testToDateStringForLeapYear() {
        long leapYearTime = Long.parseLong("951782400000");
        timeStamp = new TimeStamp(new Date(leapYearTime));
        String dateString = timeStamp.toDateString();
        assertEquals(simpleFormatter.format(new Date(leapYearTime)), dateString);
    }
    @Test
    public void testToDateStringForYear2038Problem() {
        long year2038Time = Long.parseLong("2147483647000");
        timeStamp = new TimeStamp(new Date(year2038Time));
        String dateString = timeStamp.toDateString();
        assertEquals(simpleFormatter.format(new Date(year2038Time)), dateString);
    }
    @Test
    public void testToDateStringForStartOfYear2000() {
        long year2000Time = Long.parseLong("946684800000");
        timeStamp = new TimeStamp(new Date(year2000Time));
        String dateString = timeStamp.toDateString();
        assertEquals(simpleFormatter.format(new Date(year2000Time)), dateString);
    }
    @Test
    public void testToDateStringForStartOfYear1970() {
        long year1970Time = 0L;
        timeStamp = new TimeStamp(new Date(year1970Time));
        String dateString = timeStamp.toDateString();
        assertEquals(simpleFormatter.format(new Date(year1970Time)), dateString);
    }
    @Test
    public void testToDateStringForEndOfYear1999() {
        long endOfYear1999Time = Long.parseLong("946684799000");
        timeStamp = new TimeStamp(new Date(endOfYear1999Time));
        String dateString = timeStamp.toDateString();
        assertEquals(simpleFormatter.format(new Date(endOfYear1999Time)), dateString);
    }
    @After
    public void tearDown() {
        timeStamp = null;
        simpleFormatter = null;
    }
}
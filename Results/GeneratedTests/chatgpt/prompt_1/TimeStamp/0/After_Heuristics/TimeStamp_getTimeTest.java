 // TimeStamp_getTimeTest.java
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
 * TimeStamp#public getTime() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getTimeTest {

    private static final long ONE_SECOND_NTP = 0x100000000L;
    private DateFormat simpleFormatter;
    private DateFormat utcFormatter;
    @Before
    public void setUp() {
        simpleFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT, Locale.US);
        simpleFormatter.setTimeZone(TimeZone.getDefault());
        utcFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT + " 'UTC'", Locale.US);
        utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    @After
    public void tearDown() {
        simpleFormatter = null;
        utcFormatter = null;
    }
    @Test
    public void testGetTime_WithZeroNTPValue() {
        TimeStamp timeStamp = new TimeStamp(0L);
        long expectedTime = TimeStamp.getTime(0L);
        assertEquals(expectedTime, timeStamp.getTime());
    }
    @Test
    public void testGetTime_WithCurrentTime() {
        TimeStamp currentTimeStamp = TimeStamp.getCurrentTime();
        long expectedTime = System.currentTimeMillis();
        long actualTime = currentTimeStamp.getTime();
        assertTrue(Math.abs(expectedTime - actualTime) < 1000);
    }
    @Test
    public void testGetTime_WithSpecificNTPValue() {
        long ntpValue = (Long.parseLong("2208988800") << 32);
        TimeStamp timeStamp = new TimeStamp(ntpValue);
        assertEquals(0L, timeStamp.getTime());
    }
    @Test
    public void testGetTime_UsingNTPOneSecondValue() {
        TimeStamp timeStamp = new TimeStamp(ONE_SECOND_NTP);
        long expectedTime = 1000L;
        assertEquals(expectedTime, timeStamp.getTime());
    }
    @Test
    public void testGetTime_UsingNTPFraction() {
        long ntpValue = 1L;
        TimeStamp timeStamp = new TimeStamp(ntpValue);
        long time = timeStamp.getTime();
        assertEquals(0L, time);
    }
    @Test
    public void testGetTime_BeforeMSB0BaseTime() {
        long ntpValue = (2085978495L << 32) - 500;
        TimeStamp timeStamp = new TimeStamp(ntpValue);
        long time = TimeStamp.getTime(ntpValue);
        assertEquals(time, timeStamp.getTime());
    }
    @Test
    public void testGetTime_AfterMSB0BaseTime() {
        long ntpValue = (2085978496L << 32);
        TimeStamp timeStamp = new TimeStamp(ntpValue);
        long time = TimeStamp.getTime(ntpValue);
        assertEquals(time, timeStamp.getTime());
    }
    @Test
    public void testGetTime_MaxNTPValue() {
        long maxNTPValue = 0xFFFFFFFFFFFFFFFFL;
        TimeStamp timeStamp = new TimeStamp(maxNTPValue);
        assertEquals(TimeStamp.getTime(maxNTPValue), timeStamp.getTime());
    }
    @Test
    public void testGetTime_MinNTPValue() {
        long minNTPValue = 0L;
        TimeStamp timeStamp = new TimeStamp(minNTPValue);
        assertEquals(TimeStamp.getTime(minNTPValue), timeStamp.getTime());
    }
    @Test
    public void testGetTime_MidNTPValue() {
        long midNTPValue = 0x8000000080000000L;
        TimeStamp timeStamp = new TimeStamp(midNTPValue);
        assertEquals(TimeStamp.getTime(midNTPValue), timeStamp.getTime());
    }
}
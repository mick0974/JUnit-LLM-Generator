 import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_toUTCStringTest {
    private static final TimeZone UTC_ZONE = TimeZone.getTimeZone("UTC");
    private static final String EXPECTED_DATE_FORMAT = "EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'";
    private DateFormat utcFormatter;
    @Before
    public void setUp() {
        utcFormatter = new SimpleDateFormat(EXPECTED_DATE_FORMAT, Locale.US);
        utcFormatter.setTimeZone(UTC_ZONE);
    }
    @After
    public void tearDown() {
        utcFormatter = null;
    }
    private String getExpectedUTCString(long millis) {
        return utcFormatter.format(new Date(millis));
    }
    @Test
    public void testUTCStringForKnownDatePre2036() {
        long timeMillis = Long.parseLong("946684800000");
        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        String expected = "Sat, Jan 01 2000 00:00:00.000 UTC";
        String actual = ts.toUTCString();
        assertEquals(expected, actual);
        assertEquals(timeMillis, ts.getTime());
    }
    @Test
    public void testUTCStringForJavaEpoch() {
        long timeMillis = 0L;
        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        String expected = "Thu, Jan 01 1970 00:00:00.000 UTC";
        String actual = ts.toUTCString();
        assertEquals(expected, actual);
        assertEquals(timeMillis, ts.getTime());
    }
    @Test
    public void testUTCStringForNTPBase0Epoch() {
        long ntpBase0Time = 0L;
        TimeStamp ts = new TimeStamp(ntpBase0Time);
        String expected = "Thu, Feb 07 2036 06:28:16.000 UTC";
        long expectedMillis = Long.parseLong("2085978496000");
        String actual = ts.toUTCString();
        assertEquals(expected, actual);
        assertEquals(expectedMillis, ts.getTime());
    }
    @Test
    public void testUTCStringJustBeforeRollover() {
        long timeMillis = TimeStamp.msb0baseTime - 1;
        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        String expected = "Thu, Feb 07 2036 06:28:15.999 UTC";
        String actual = ts.toUTCString();
        assertEquals(expected, actual);
        assertEquals(timeMillis, ts.getTime());
    }
    @Test
    public void testUTCStringAtRollover() {
        long timeMillis = TimeStamp.msb0baseTime;
        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        String expected = "Thu, Feb 07 2036 06:28:16.000 UTC";
        String actual = ts.toUTCString();
        assertEquals(expected, actual);
        assertEquals(timeMillis, ts.getTime());
        assertEquals(0L, ts.ntpValue());
    }
    @Test
    public void testUTCStringWithMilliseconds() {
        long timeMillis = Long.parseLong("1678886461234");
        TimeStamp ts = TimeStamp.getNtpTime(timeMillis);
        String expected = "Wed, Mar 15 2023 13:21:01.234 UTC";
        String actual = ts.toUTCString();
        assertEquals(expected, actual);
        long actualMillis = ts.getTime();
        assertEquals(timeMillis, actualMillis);
    }
    @Test
    public void testUTCStringForCurrentTime() {
        long nowMillis = System.currentTimeMillis();
        TimeStamp ts = TimeStamp.getCurrentTime();
        String expected = getExpectedUTCString(nowMillis);
        String actual = ts.toUTCString();
        String actualFromTsMillis = getExpectedUTCString(ts.getTime());
        assertEquals(actualFromTsMillis, actual);
        long timeDiff = Math.abs(ts.getTime() - nowMillis);
        assertTrue("Time difference (" + timeDiff + "ms) too large", timeDiff < 10);
    }
}
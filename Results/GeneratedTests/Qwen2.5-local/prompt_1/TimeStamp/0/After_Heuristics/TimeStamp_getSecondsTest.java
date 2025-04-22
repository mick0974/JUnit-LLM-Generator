
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_getSecondsTest {
    private TimeStamp ts;
    @Before
    public void setUp() {
        ts = new TimeStamp(0xc1a089bdL);
    }
    @After
    public void tearDown() {
    }
    @Test
    public void testGetSecondsTypicalCase() {
        long expectedSeconds = Long.parseLong("3232947619");
        long actualSeconds = ts.getSeconds();
        assertEquals(expectedSeconds, actualSeconds);
    }
    @Test
    public void testGetSecondsEdgeCaseMin() {
        long expectedSeconds = 0L;
        long actualSeconds = new TimeStamp(msb1baseTime).getSeconds();
        assertEquals(expectedSeconds, actualSeconds);
    }
    @Test
    public void testGetSecondsEdgeCaseMax() {
        long expectedSeconds = Long.parseLong("4294967295");
        long actualSeconds = new TimeStamp(msb0baseTime).getSeconds();
        assertEquals(expectedSeconds, actualSeconds);
    }
    @Test
    public void testGetSecondsNegativeValue() {
        long expectedSeconds = -1L;
        long actualSeconds = new TimeStamp(-1L).getSeconds();
        assertEquals(expectedSeconds, actualSeconds);
    }
    @Test
    public void testGetSecondsZeroValue() {
        long expectedSeconds = 0L;
        long actualSeconds = new TimeStamp(0L).getSeconds();
        assertEquals(expectedSeconds, actualSeconds);
    }
    @Test
    public void testGetSecondsLargeValue() {
        long expectedSeconds = 2147483647L;
        long actualSeconds = new TimeStamp(0x7fffffffL).getSeconds();
        assertEquals(expectedSeconds, actualSeconds);
    }
    @Test
    public void testGetSecondsSmallValue() {
        long expectedSeconds = -Long.parseLong("2147483648");
        long actualSeconds = new TimeStamp(-0x80000000L).getSeconds();
        assertEquals(expectedSeconds, actualSeconds);
    }
    @Test
    public void testGetSecondsNullValue() {
        TimeStamp ts = null;
        Exception ex = null;
        try {
            long actualSeconds = ts.getSeconds();
        } catch (NullPointerException e) {
            ex = e;
        }
        assertNotNull(ex);
    }
    @Test
    public void testGetSecondsDifferentTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
        long expectedSeconds = Long.parseLong("3232947619");
        long actualSeconds = ts.getSeconds();
        assertEquals(expectedSeconds, actualSeconds);
    }
}
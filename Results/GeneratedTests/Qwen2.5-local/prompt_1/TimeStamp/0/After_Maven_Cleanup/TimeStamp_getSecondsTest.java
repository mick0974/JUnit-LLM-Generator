
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
    public void testGetSecondsZeroValue() {
        long expectedSeconds = 0L;
        long actualSeconds = new TimeStamp(0L).getSeconds();
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
}
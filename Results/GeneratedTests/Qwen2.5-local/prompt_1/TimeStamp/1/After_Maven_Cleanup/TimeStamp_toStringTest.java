
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_toStringTest {
    private TimeStamp ts;
    private Date date;
    @Before
    public void setUp() {
        date = new Date(Long.parseLong("1609459200000"));
        ts = new TimeStamp(date);
    }
    @After
    public void tearDown() {
        ts = null;
        date = null;
    }
    @Test
    public void testToStringNegativeTimestamp() {
        ts = new TimeStamp(-1L);
        String expected = "ffffffff.ffffffff";
        String actual = ts.toString();
        assertEquals(expected, actual);
    }
    @Test
    public void testToStringMinLong() {
        ts = new TimeStamp(Long.MIN_VALUE);
        String expected = "80000000.00000000";
        String actual = ts.toString();
        assertEquals(expected, actual);
    }
    @Test
    public void testToStringNegativeOne() {
        ts = new TimeStamp(-1L);
        String expected = "ffffffff.ffffffff";
        String actual = ts.toString();
        assertEquals(expected, actual);
    }
}

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
    public void testToStringValid() {
        String expected = "c1a089bd.fc904f6d";
        String actual = ts.toString();
        assertEquals(expected, actual);
    }
    @Test
    public void testToStringEdgeCase() {
        ts = new TimeStamp(0L);
        String expected = "0.000";
        String actual = ts.toString();
        assertEquals(expected, actual);
    }
    @Test
    public void testToStringNegativeTimestamp() {
        ts = new TimeStamp(-1L);
        String expected = "ffffffff.ffffffff";
        String actual = ts.toString();
        assertEquals(expected, actual);
    }
    @Test
    public void testToStringMaxLong() {
        ts = new TimeStamp(Long.MAX_VALUE);
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
    public void testToStringNull() {
        ts = null;
        String expected = null;
        String actual = ts.toString();
        assertEquals(expected, actual);
    }
    @Test
    public void testToStringLargePositiveNumber() {
        ts = new TimeStamp(Long.parseLong("9223372036854775807"));
        String expected = "ffffffff.ffffffff";
        String actual = ts.toString();
        assertEquals(expected, actual);
    }
    @Test
    public void testToStringSmallPositiveNumber() {
        ts = new TimeStamp(1L);
        String expected = "0.00000001";
        String actual = ts.toString();
        assertEquals(expected, actual);
    }
    @Test
    public void testToStringZero() {
        ts = new TimeStamp(0L);
        String expected = "0.000";
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
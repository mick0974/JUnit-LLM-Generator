
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_getTimeTest {
    private TimeStamp time_stamp;
    @Before
    public void setUp() {
        time_stamp = new TimeStamp(0);
    }
    @After
    public void tearDown() {
        time_stamp = null;
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeInput() {
        TimeStamp.getTime(-1);
    }
}

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
 private TimeStamp timeStamp;
 private Date date;
 @Before
 public void setUp() {
  timeStamp = new TimeStamp(new Date().getTime());
  date = new Date();
 }
 @After
 public void tearDown() {
  timeStamp = null;
  date = null;
 }
 @Test
 public void testGetCurrentTime() {
  TimeStamp ts = TimeStamp.getCurrentTime();
  assertNotNull(ts);
  long now = System.currentTimeMillis();
  assertTrue(now - 1000 < ts.getTime() && ts.getTime() < now + 1000);
 }
}
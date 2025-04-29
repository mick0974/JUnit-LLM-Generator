
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_toUTCStringTest {
 private TimeStamp timeStamp;
 private DateFormat formatter;
 @Before
 public void setUp() {
  timeStamp = new TimeStamp(new Date());
  formatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'", Locale.US);
  formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
 }
 @After
 public void tearDown() {
  timeStamp = null;
  formatter = null;
 }
 @Test
 public void testToUTCString_nullTimestamp() {
  timeStamp = null;
  try {
   timeStamp.toUTCString();
   fail("Expected NullPointerException");
  } catch (NullPointerException e) {
  }
 }
}
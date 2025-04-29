
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_toDateStringTest {
 private TimeStamp timeStamp;
 @Before
 public void setUp() {
  timeStamp = new TimeStamp(new Date());
 }
 @After
 public void tearDown() {
  timeStamp = null;
 }
 @Test
 public void testToDateStringWithDefaultTimeZone() {
  DateFormat defaultFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS", Locale.US);
  defaultFormat.setTimeZone(TimeZone.getDefault());
  String expected = defaultFormat.format(timeStamp.getDate());
  String actual = timeStamp.toDateString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToDateStringWithInvalidHexTimestamp() {
  try {
   new TimeStamp("invalid_hex_string");
   fail("Expected NumberFormatException");
  } catch (NumberFormatException e) {
  }
 }
}
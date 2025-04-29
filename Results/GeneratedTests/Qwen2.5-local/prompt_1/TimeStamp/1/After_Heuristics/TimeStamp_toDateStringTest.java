
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
 public void testToDateStringWithCustomTimeZone() {
  TimeZone customTimeZone = TimeZone.getTimeZone("GMT+8");
  DateFormat customFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS", Locale.US);
  customFormat.setTimeZone(customTimeZone);
  String expected = customFormat.format(timeStamp.getDate());
  String actual = timeStamp.toDateString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToDateStringWithNoLeadingZeroes() {
  timeStamp = new TimeStamp(new Date(0));
  String expected = "Thu, Jan 01 1970 00:00:00.000";
  String actual = timeStamp.toDateString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToDateStringWithLeapSecond() {
  timeStamp = new TimeStamp(new Date(Long.parseLong("1262304000000")));
  String expected = "Mon, Jan 01 2009 00:00:00.000";
  String actual = timeStamp.toDateString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToDateStringWithNegativeTime() {
  timeStamp = new TimeStamp(new Date(-Long.parseLong("2208988800000")));
  String expected = "Fri, Jan 01 1900 01:00:00.000";
  String actual = timeStamp.toDateString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToDateStringWithMaxLong() {
  timeStamp = new TimeStamp(Long.MAX_VALUE);
  String expected = "Sat, Aug 17 292278994 18:14:07.281";
  String actual = timeStamp.toDateString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToDateStringWithMinLong() {
  timeStamp = new TimeStamp(Long.MIN_VALUE);
  String expected = "Sun, Thu 01700 AD 07:12:43.619";
  String actual = timeStamp.toDateString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToDateStringWithNullTimeStamp() {
  try {
   new TimeStamp(null);
   fail("Expected NullPointerException");
  } catch (NullPointerException e) {
  }
 }
 @Test
 public void testToDateStringWithInvalidHexTimestamp() {
  try {
   new TimeStamp("invalid_hex_string");
   fail("Expected NumberFormatException");
  } catch (NumberFormatException e) {
  }
 }
 @Test
 public void testToDateStringWithEmptyHexTimestamp() {
  try {
   new TimeStamp("");
   fail("Expected NumberFormatException");
  } catch (NumberFormatException e) {
  }
 }
}
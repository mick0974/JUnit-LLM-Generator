
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
 public void testToUTCString_validDateTime() {
  String expected = formatter.format(timeStamp.getDate()) + " UTC";
  String actual = timeStamp.toUTCString();
  assertEquals(expected, actual);
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
 @Test
 public void testToUTCString_emptyTimestamp() {
  timeStamp = new TimeStamp(0L);
  String expected = "Thu, Jan 01 1970 00:00:00.000 'UTC'";
  String actual = timeStamp.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToUTCString_maxTimestamp() {
  timeStamp = new TimeStamp(Long.MAX_VALUE);
  String expected = "Fri, Apr 23 292278994 10:36:31.471 'UTC'";
  String actual = timeStamp.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToUTCString_minTimestamp() {
  timeStamp = new TimeStamp(Long.MIN_VALUE);
  String expected = "Wed, Thu 111111111 00:00:00.000 'UTC'";
  String actual = timeStamp.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToUTCString_leapSecondTimestamp() {
  long leapSecond = Long.parseLong("1115686400000");
  timeStamp = new TimeStamp(leapSecond);
  String expected = "Tue, Jan 01 1980 00:00:00.000 'UTC'";
  String actual = timeStamp.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToUTCString_negativeLeapSecondTimestamp() {
  long negativeLeapSecond = -Long.parseLong("1115686400000");
  timeStamp = new TimeStamp(negativeLeapSecond);
  String expected = "Sun, Nov 25 1969 23:59:59.000 'UTC'";
  String actual = timeStamp.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToUTCString_fractionalSeconds() {
  long fractionalSeconds = Long.parseLong("1115686400000") + 1000000000L;
  timeStamp = new TimeStamp(fractionalSeconds);
  String expected = "Tue, Jan 01 1980 00:00:01.000 'UTC'";
  String actual = timeStamp.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToUTCString_zeroFractionalSeconds() {
  long zeroFractionalSeconds = Long.parseLong("1115686400000");
  timeStamp = new TimeStamp(zeroFractionalSeconds);
  String expected = "Tue, Jan 01 1980 00:00:00.000 'UTC'";
  String actual = timeStamp.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToUTCString_msb0baseTime() {
  long msb0baseTime = Long.parseLong("2085978496000");
  timeStamp = new TimeStamp(msb0baseTime);
  String expected = "Sat, Feb 07 2036 06:28:16.000 'UTC'";
  String actual = timeStamp.toUTCString();
  assertEquals(expected, actual);
 }
}

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_ntpValueTest {
 TimeStamp ts;
 @Before
 public void setUp() {
  ts = new TimeStamp(new Date().getTime());
 }
 @After
 public void tearDown() {
  ts = null;
 }
 @Test
 public void testValidInput() {
  long expected = new Date().getTime();
  long actual = ts.ntpValue();
  assertEquals(expected, actual);
 }
 @Test
 public void testNullInput() {
  long expected = 0;
  long actual = new TimeStamp(null).ntpValue();
  assertEquals(expected, actual);
 }
 @Test
 public void testMaxLong() {
  long maxLong = Long.MAX_VALUE;
  long expected = maxLong;
  long actual = new TimeStamp(maxLong).ntpValue();
  assertEquals(expected, actual);
 }
 @Test
 public void testMinLong() {
  long minLong = Long.MIN_VALUE;
  long expected = minLong;
  long actual = new TimeStamp(minLong).ntpValue();
  assertEquals(expected, actual);
 }
 @Test
 public void testLargePositiveNumber() {
  long largePositiveNumber = Long.parseLong("9223372036854775807");
  long expected = largePositiveNumber;
  long actual = new TimeStamp(largePositiveNumber).ntpValue();
  assertEquals(expected, actual);
 }
 @Test
 public void testLargeNegativeNumber() {
  long largeNegativeNumber = -Long.parseLong("9223372036854775808");
  long expected = largeNegativeNumber;
  long actual = new TimeStamp(largeNegativeNumber).ntpValue();
  assertEquals(expected, actual);
 }
 @Test
 public void testZero() {
  long zero = 0;
  long expected = zero;
  long actual = new TimeStamp(zero).ntpValue();
  assertEquals(expected, actual);
 }
 @Test
 public void testSmallPositiveNumber() {
  long smallPositiveNumber = 1;
  long expected = smallPositiveNumber;
  long actual = new TimeStamp(smallPositiveNumber).ntpValue();
  assertEquals(expected, actual);
 }
 @Test
 public void testSmallNegativeNumber() {
  long smallNegativeNumber = -1;
  long expected = smallNegativeNumber;
  long actual = new TimeStamp(smallNegativeNumber).ntpValue();
  assertEquals(expected, actual);
 }
}
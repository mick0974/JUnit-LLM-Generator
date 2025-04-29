
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_compareToTest {
 private TimeStamp ts1, ts2, ts3, ts4, ts5, ts6, ts7, ts8, ts9, ts10;
 private DateFormat formatter;
 @Before
 public void setUp() {
  formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
  formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
  try {
   ts1 = new TimeStamp(formatter.parse("2000-01-01 00:00:00.000").getTime());
   ts2 = new TimeStamp(formatter.parse("2000-01-01 00:00:00.001").getTime());
   ts3 = new TimeStamp(formatter.parse("2000-01-01 00:00:00.002").getTime());
   ts4 = new TimeStamp(formatter.parse("2000-01-01 00:00:00.003").getTime());
   ts5 = new TimeStamp(formatter.parse("2000-01-01 00:00:00.004").getTime());
   ts6 = new TimeStamp(formatter.parse("1999-12-31 23:59:59.999").getTime());
   ts7 = new TimeStamp(formatter.parse("2000-01-01 00:00:00.000").getTime());
   ts8 = new TimeStamp(formatter.parse("2000-01-01 00:00:00.000").getTime());
   ts9 = new TimeStamp(formatter.parse("2000-01-01 00:00:00.000").getTime());
   ts10 = new TimeStamp(formatter.parse("2000-01-01 00:00:00.000").getTime());
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
 @After
 public void tearDown() {
  formatter = null;
  ts1 = null;
  ts2 = null;
  ts3 = null;
  ts4 = null;
  ts5 = null;
  ts6 = null;
  ts7 = null;
  ts8 = null;
  ts9 = null;
  ts10 = null;
 }
 @Test
 public void testCompareToEqual() {
  assertEquals(ts1.compareTo(ts7), 0);
  assertEquals(ts7.compareTo(ts8), 0);
  assertEquals(ts8.compareTo(ts9), 0);
  assertEquals(ts9.compareTo(ts10), 0);
 }
 @Test
 public void testCompareToLessThan() {
  assertTrue(ts1.compareTo(ts2) < 0);
  assertTrue(ts2.compareTo(ts3) < 0);
  assertTrue(ts3.compareTo(ts4) < 0);
  assertTrue(ts4.compareTo(ts5) < 0);
 }
 @Test
 public void testCompareToGreaterThan() {
  assertTrue(ts5.compareTo(ts4) > 0);
  assertTrue(ts4.compareTo(ts3) > 0);
  assertTrue(ts3.compareTo(ts2) > 0);
  assertTrue(ts2.compareTo(ts1) > 0);
 }
 @Test
 public void testCompareToDifferentSign() {
  assertTrue(ts1.compareTo(ts6) < 0);
  assertTrue(ts6.compareTo(ts1) > 0);
 }
 @Test
 public void testCompareToNull() {
  assertThrows(NullPointerException.class, () -> ts1.compareTo(null));
 }
 @Test
 public void testCompareToSameObject() {
  assertEquals(ts1.compareTo(ts1), 0);
 }
 @Test
 public void testCompareToDifferentTimeZone() {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
  assertTrue(ts1.compareTo(ts7) > 0);
  TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
 }
 @Test
 public void testCompareToLargeValues() {
  long largeValue = Long.parseLong("9223372036854775807");
  long smallValue = -Long.parseLong("9223372036854775808");
  TimeStamp largeTs = new TimeStamp(largeValue);
  TimeStamp smallTs = new TimeStamp(smallValue);
  assertTrue(largeTs.compareTo(smallTs) > 0);
  assertTrue(smallTs.compareTo(largeTs) < 0);
 }
 @Test
 public void testCompareToSmallValues() {
  long largeValue = Long.parseLong("9223372036854775807");
  long smallValue = -Long.parseLong("9223372036854775808");
  TimeStamp largeTs = new TimeStamp(largeValue);
  TimeStamp smallTs = new TimeStamp(smallValue);
  assertTrue(largeTs.compareTo(smallTs) > 0);
  assertTrue(smallTs.compareTo(largeTs) < 0);
 }
}
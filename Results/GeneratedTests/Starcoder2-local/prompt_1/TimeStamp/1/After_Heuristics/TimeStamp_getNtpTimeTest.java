
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_getNtpTimeTest {
 private TimeStamp ts;
 private DateFormat simpleFormatter;
 private DateFormat utcFormatter;
 @Before
 public void setUp() {
  ts = new TimeStamp(0);
  simpleFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT, Locale.US);
  simpleFormatter.setTimeZone(TimeZone.getDefault());
  utcFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT + " 'UTC'", Locale.US);
  utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
 }
 @After
 public void tearDown() {
  ts = null;
  simpleFormatter = null;
  utcFormatter = null;
 }
 @Test
 public void test_getTime_01() {
  long time = ts.getTime();
  assertEquals(0, time);
 }
 @Test
 public void test_getTime_02() {
  long time = ts.getTime(0);
  assertEquals(0, time);
 }
 @Test
 public void test_getTime_03() {
  long time = ts.getTime(1);
  assertEquals(1, time);
 }
 @Test
 public void test_getTime_04() {
  long time = ts.getTime(1000);
  assertEquals(1000, time);
 }
 @Test
 public void test_getTime_05() {
  long time = ts.getTime(1000000);
  assertEquals(1000000, time);
 }
 @Test
 public void test_getTime_06() {
  long time = ts.getTime(1000000000);
  assertEquals(1000000000, time);
 }
 @Test
 public void test_getTime_07() {
  long time = ts.getTime(Long.parseLong("1000000000000"));
  assertEquals(Long.parseLong("1000000000000"), time);
 }
 @Test
 public void test_getTime_08() {
  long time = ts.getTime(Long.MAX_VALUE);
  assertEquals(Long.MAX_VALUE, time);
 }
 @Test
 public void test_getTime_09() {
  long time = ts.getTime(Long.MIN_VALUE);
  assertEquals(Long.MIN_VALUE, time);
 }
 @Test
 public void test_getTime_10() {
  long time = ts.getTime(Long.MAX_VALUE + 1);
  assertEquals(Long.MAX_VALUE + 1, time);
 }
 @Test
 public void test_getTime_11() {
  long time = ts.getTime(Long.MIN_VALUE - 1);
  assertEquals(Long.MIN_VALUE - 1, time);
 }
 @Test
 public void test_getTime_12() {
  long time = ts.getTime(Long.MAX_VALUE + 2);
  assertEquals(Long.MAX_VALUE + 2, time);
 }
 @Test
 public void test_getTime_13() {
  long time = ts.getTime(Long.MIN_VALUE - 2);
  assertEquals(Long.MIN_VALUE - 2, time);
 }
 @Test
 public void test_getTime_14() {
  long time = ts.getTime(Long.MAX_VALUE + 3);
  assertEquals(Long.MAX_VALUE + 3, time);
 }
 @Test
 public void test_getTime_15() {
  long time = ts.getTime(Long.MIN_VALUE - 3);
  assertEquals(Long.MIN_VALUE - 3, time);
 }
 @Test
 public void test_getTime_16() {
  long time = ts.getTime(Long.MAX_VALUE + 4);
  assertEquals(Long.MAX_VALUE + 4, time);
 }
 @Test
 public void test_getTime_17() {
  long time = ts.getTime(Long.MIN_VALUE - 4);
  assertEquals(Long.MIN_VALUE - 4, time);
 }
 @Test
 public void test_getTime_18() {
  long time = ts.getTime(Long.MAX_VALUE + 5);
  assertEquals(Long.MAX_VALUE + 5, time);
 }
 @Test
 public void test_getTime_19() {
  long time = ts.getTime(Long.MIN_VALUE - 5);
  assertEquals(Long.MIN_VALUE - 5, time);
 }
 @Test
 public void test_getTime_20() {
  long time = ts.getTime(Long.MAX_VALUE + 6);
  assertEquals(Long.MAX_VALUE + 6, time);
 }
 @Test
 public void test_getTime_21() {
  long time = ts.getTime(Long.MIN_VALUE - 6);
  assertEquals(Long.MIN_VALUE - 6, time);
 }
 @Test
 public void test_getTime_22() {
  long time = ts.getTime(Long.MAX_VALUE + 7);
  assertEquals(Long.MAX_VALUE + 7, time);
 }
 @Test
 public void test_getTime_23() {
  long time = ts.getTime(Long.MIN_VALUE - 7);
  assertEquals(Long.MIN_VALUE - 7, time);
 }
 @Test
 public void test_getTime_24() {
  long time = ts.getTime(Long.MAX_VALUE + 8);
  assertEquals(Long.MAX_VALUE + 8, time);
 }
 @Test
 public void test_getTime_25() {
  long time = ts.getTime(Long.MIN_VALUE - 8);
  assertEquals(Long.MIN_VALUE - 8, time);
 }
 @Test
 public void test_getTime_26() {
  long time = ts.getTime(Long.MAX_VALUE + 9);
  assertEquals(Long.MAX_VALUE + 9, time);
 }
 @Test
 public void test_getTime_27() {
  long time = ts.getTime(Long.MIN_VALUE - 9);
  assertEquals(Long.MIN_VALUE - 9, time);
 }
 @Test
 public void test_getTime_28() {
  long time = ts.getTime(Long.MAX_VALUE + 10);
  assertEquals(Long.MAX_VALUE + 10, time);
 }
 @Test
 public void test_getTime_29() {
  long time = ts.getTime(Long.MIN_VALUE - 10);
  assertEquals(Long.MIN_VALUE - 10, time);
 }
 @Test
 public void test_getTime_30() {
  long time = ts.getTime(Long.MAX_VALUE + 11);
  assertEquals(Long.MAX_VALUE + 11, time);
 }
 @Test
 public void test_getTime_31() {
  long time = ts.getTime(Long.MIN_VALUE - 11);
  assertEquals(Long.MIN_VALUE - 11, time);
 }
 @Test
 public void test_getTime_32() {
  long time = ts.getTime(Long.MAX_VALUE + 12);
  assertEquals(Long.MAX_VALUE + 12, time);
 }
 @Test
 public void test_getTime_33() {
  long time = ts.getTime(Long.MIN_VALUE - 12);
  assertEquals(Long.MIN_VALUE - 12, time);
 }
 @Test
 public void test_getTime_34() {
  long time = ts.getTime(Long.MAX_VALUE + 13);
  assertEquals(Long.MAX_VALUE + 13, time);
 }
 @Test
 public void test_getTime_35() {
  long time = ts.getTime(Long.MIN_VALUE - 13);
  assertEquals(Long.MIN_VALUE - 13, time);
 }
 @Test
 public void test_getTime_36() {
  long time = ts.getTime(Long.MAX_VALUE + 14);
  assertEquals(Long.MAX_VALUE + 14, time);
 }
 @Test
 public void test_getTime_37() {
  long time = ts.getTime(Long.MIN_VALUE - 14);
  assertEquals(Long.MIN_VALUE - 14, time);
 }
}
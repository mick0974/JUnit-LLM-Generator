
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_getDateTest {
 private TimeStamp ts;
 private DateFormat simpleFormatter;
 private DateFormat utcFormatter;
 @Before
 public void setUp() {
  ts = new TimeStamp(Long.parseLong("1234567890123"));
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
 public void test_getDate_01() {
  Date d = ts.getDate();
  String s = simpleFormatter.format(d);
  assertEquals("Tue, Dec 10 2002 10:41:49.986", s);
 }
 @Test
 public void test_getDate_02() {
  Date d = ts.getDate();
  String s = utcFormatter.format(d);
  assertEquals("Tue, Dec 10 2002 10:41:49.986 UTC", s);
 }
 @Test
 public void test_getDate_03() {
  Date d = ts.getDate();
  String s = simpleFormatter.format(d);
  assertEquals("Tue, Dec 10 2002 10:41:49.986", s);
 }
 @Test
 public void test_getDate_04() {
  Date d = ts.getDate();
  String s = utcFormatter.format(d);
  assertEquals("Tue, Dec 10 2002 10:41:49.986 UTC", s);
 }
 @Test
 public void test_getDate_05() {
  Date d = ts.getDate();
  String s = simpleFormatter.format(d);
  assertEquals("Tue, Dec 10 2002 10:41:49.986", s);
 }
 @Test
 public void test_getDate_06() {
  Date d = ts.getDate();
  String s = utcFormatter.format(d);
  assertEquals("Tue, Dec 10 2002 10:41:49.986 UTC", s);
 }
 @Test
 public void test_getDate_07() {
  Date d = ts.getDate();
  String s = simpleFormatter.format(d);
  assertEquals("Tue, Dec 10 2002 10:41:49.986", s);
 }
 @Test
 public void test_getDate_08() {
  Date d = ts.getDate();
  String s = utcFormatter.format(d);
  assertEquals("Tue, Dec 10 2002 10:41:49.986 UTC", s);
 }
 @Test
 public void test_getDate_09() {
  Date d = ts.getDate();
  String s = simpleFormatter.format(d);
  assertEquals("Tue, Dec 10 2002 10:41:49.986", s);
 }
 @Test
 public void test_getDate_10() {
  Date d = ts.getDate();
  String s = utcFormatter.format(d);
  assertEquals("Tue, Dec 10 2002 10:41:49.986 UTC", s);
 }
}
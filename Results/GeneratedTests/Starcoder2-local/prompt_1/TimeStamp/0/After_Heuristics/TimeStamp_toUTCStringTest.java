
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
 public void test_01_toUTCString_01() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 UTC";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_02_toUTCString_02() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 'UTC'";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_03_toUTCString_03() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 'UTC'";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_04_toUTCString_04() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 'UTC'";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_05_toUTCString_05() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 'UTC'";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_06_toUTCString_06() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 'UTC'";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_07_toUTCString_07() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 'UTC'";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_08_toUTCString_08() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 'UTC'";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_09_toUTCString_09() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 'UTC'";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_10_toUTCString_10() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 'UTC'";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
}
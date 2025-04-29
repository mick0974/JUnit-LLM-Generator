
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
 private TimeStamp ts;
 private DateFormat simpleFormatter;
 private DateFormat utcFormatter;
 @Before
 public void setUp() {
  ts = new TimeStamp(Long.parseLong("1234567890123"));
 }
 @After
 public void tearDown() {
  ts = null;
 }
 @Test
 public void test_01_toDateString_01() {
  String expected = "Tue, Dec 10 2002 10:41:49.986";
  String actual = ts.toDateString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_02_toDateString_02() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 UTC";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_03_toDateString_03() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 UTC";
  String actual = ts.toDateString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_04_toDateString_04() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 UTC";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_05_toDateString_05() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 UTC";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_06_toDateString_06() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 UTC";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_07_toDateString_07() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 UTC";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_08_toDateString_08() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 UTC";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_09_toDateString_09() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 UTC";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
 @Test
 public void test_10_toDateString_10() {
  String expected = "Tue, Dec 10 2002 10:41:49.986 UTC";
  String actual = ts.toUTCString();
  assertEquals(expected, actual);
 }
}
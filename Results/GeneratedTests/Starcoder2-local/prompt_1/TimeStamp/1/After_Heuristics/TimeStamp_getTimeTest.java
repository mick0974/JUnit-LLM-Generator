
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_getTimeTest {
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
 public void test_getTime_01() {
  long time = ts.getTime();
  assertEquals(Long.parseLong("1234567890123"), time);
 }
 @Test
 public void test_getTime_02() {
  long time = ts.getTime();
  assertEquals(Long.parseLong("1234567890123"), time);
 }
 @Test
 public void test_getTime_03() {
  long time = ts.getTime();
  assertEquals(Long.parseLong("1234567890123"), time);
 }
 @Test
 public void test_getTime_04() {
  long time = ts.getTime();
  assertEquals(Long.parseLong("1234567890123"), time);
 }
 @Test
 public void test_getTime_05() {
  long time = ts.getTime();
  assertEquals(Long.parseLong("1234567890123"), time);
 }
 @Test
 public void test_getTime_06() {
  long time = ts.getTime();
  assertEquals(Long.parseLong("1234567890123"), time);
 }
 @Test
 public void test_getTime_07() {
  long time = ts.getTime();
  assertEquals(Long.parseLong("1234567890123"), time);
 }
 @Test
 public void test_getTime_08() {
  long time = ts.getTime();
  assertEquals(Long.parseLong("1234567890123"), time);
 }
 @Test
 public void test_getTime_09() {
  long time = ts.getTime();
  assertEquals(Long.parseLong("1234567890123"), time);
 }
 @Test
 public void test_getTime_10() {
  long time = ts.getTime();
  assertEquals(Long.parseLong("1234567890123"), time);
 }
}
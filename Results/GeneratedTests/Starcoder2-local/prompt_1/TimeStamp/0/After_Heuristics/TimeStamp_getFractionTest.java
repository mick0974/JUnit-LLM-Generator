
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_getFractionTest {
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
 public void test_getFraction_01() {
  long fraction = ts.getFraction();
  assertEquals(Long.parseLong("1234567890123"), fraction);
 }
 @Test
 public void test_getFraction_02() {
  long fraction = ts.getFraction();
  assertEquals(Long.parseLong("1234567890123"), fraction);
 }
 @Test
 public void test_getFraction_03() {
  long fraction = ts.getFraction();
  assertEquals(Long.parseLong("1234567890123"), fraction);
 }
 @Test
 public void test_getFraction_04() {
  long fraction = ts.getFraction();
  assertEquals(Long.parseLong("1234567890123"), fraction);
 }
 @Test
 public void test_getFraction_05() {
  long fraction = ts.getFraction();
  assertEquals(Long.parseLong("1234567890123"), fraction);
 }
 @Test
 public void test_getFraction_06() {
  long fraction = ts.getFraction();
  assertEquals(Long.parseLong("1234567890123"), fraction);
 }
 @Test
 public void test_getFraction_07() {
  long fraction = ts.getFraction();
  assertEquals(Long.parseLong("1234567890123"), fraction);
 }
 @Test
 public void test_getFraction_08() {
  long fraction = ts.getFraction();
  assertEquals(Long.parseLong("1234567890123"), fraction);
 }
 @Test
 public void test_getFraction_09() {
  long fraction = ts.getFraction();
  assertEquals(Long.parseLong("1234567890123"), fraction);
 }
 @Test
 public void test_getFraction_10() {
  long fraction = ts.getFraction();
  assertEquals(Long.parseLong("1234567890123"), fraction);
 }
}
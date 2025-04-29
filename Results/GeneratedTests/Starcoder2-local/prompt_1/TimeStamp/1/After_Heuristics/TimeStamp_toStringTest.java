
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_toStringTest {
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
 public void testToString() {
  String expected = "c1a089bd.fc904f6d";
  String actual = ts.toString();
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_null() {
  String expected = "null";
  String actual = ts.toString(0L);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_zero() {
  String expected = "0";
  String actual = ts.toString(0L);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_negative() {
  String expected = "-1";
  String actual = ts.toString(-1L);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_positive() {
  String expected = "1";
  String actual = ts.toString(1L);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong() {
  String expected = "7fffffffffffffff";
  String actual = ts.toString(Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong() {
  String expected = "8000000000000000";
  String actual = ts.toString(Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_negative() {
  String expected = "-8000000000000000";
  String actual = ts.toString(-Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_negative() {
  String expected = "-7fffffffffffffff";
  String actual = ts.toString(-Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_positive() {
  String expected = "7fffffffffffffff";
  String actual = ts.toString(Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_positive() {
  String expected = "8000000000000000";
  String actual = ts.toString(Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_negative_positive() {
  String expected = "-8000000000000000";
  String actual = ts.toString(-Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_negative_positive() {
  String expected = "-7fffffffffffffff";
  String actual = ts.toString(-Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_positive_negative() {
  String expected = "7fffffffffffffff";
  String actual = ts.toString(Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_positive_negative() {
  String expected = "8000000000000000";
  String actual = ts.toString(Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_negative_negative() {
  String expected = "-7fffffffffffffff";
  String actual = ts.toString(-Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_negative_negative() {
  String expected = "-8000000000000000";
  String actual = ts.toString(-Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_positive_positive() {
  String expected = "7fffffffffffffff";
  String actual = ts.toString(Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_positive_positive() {
  String expected = "8000000000000000";
  String actual = ts.toString(Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_negative_positive_positive() {
  String expected = "-8000000000000000";
  String actual = ts.toString(-Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_negative_positive_positive() {
  String expected = "-7fffffffffffffff";
  String actual = ts.toString(-Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_positive_negative_positive() {
  String expected = "7fffffffffffffff";
  String actual = ts.toString(Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_positive_negative_positive() {
  String expected = "8000000000000000";
  String actual = ts.toString(Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_negative_negative_positive() {
  String expected = "-7fffffffffffffff";
  String actual = ts.toString(-Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_negative_negative_positive() {
  String expected = "-8000000000000000";
  String actual = ts.toString(-Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_positive_positive_negative() {
  String expected = "7fffffffffffffff";
  String actual = ts.toString(Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_positive_positive_negative() {
  String expected = "8000000000000000";
  String actual = ts.toString(Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_negative_positive_negative() {
  String expected = "-8000000000000000";
  String actual = ts.toString(-Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_negative_positive_negative() {
  String expected = "-7fffffffffffffff";
  String actual = ts.toString(-Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_positive_negative_negative() {
  String expected = "7fffffffffffffff";
  String actual = ts.toString(Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_positive_negative_negative() {
  String expected = "8000000000000000";
  String actual = ts.toString(Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_maxLong_negative_negative_negative() {
  String expected = "-7fffffffffffffff";
  String actual = ts.toString(-Long.MAX_VALUE);
  assertEquals(expected, actual);
 }
 @Test
 public void testToString_minLong_negative_negative_negative() {
  String expected = "-8000000000000000";
  String actual = ts.toString(-Long.MIN_VALUE);
  assertEquals(expected, actual);
 }
}
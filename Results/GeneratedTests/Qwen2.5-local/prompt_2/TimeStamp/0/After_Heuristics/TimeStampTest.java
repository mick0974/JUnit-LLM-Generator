 import org.junit.Test;
import static org.junit.Assert.*;
public class TimeStampTest {
 @Test
 public void testConstructorLong() {
  long ntpTime = 0x0000000000000000L;
  TimeStamp ts = new TimeStamp(ntpTime);
  assertEquals(ntpTime, ts.ntpValue());
 }
 @Test(expected = NumberFormatException.class)
 public void testConstructorStringNull() {
  new TimeStamp((String) null);
 }
 @Test
 public void testConstructorStringValidHex() {
  String hexStamp = "c1a089bd.fc904f6d";
  TimeStamp ts = new TimeStamp(hexStamp);
  long expectedNtpTime = 0xc1a089bdfc904f6dL;
  assertEquals(expectedNtpTime, ts.ntpValue());
 }
 @Test
 public void testConstructorDate() {
  Date date = new Date(0);
  TimeStamp ts = new TimeStamp(date);
  long expectedNtpTime = Long.parseLong("2208988800000");
  assertEquals(expectedNtpTime, ts.ntpValue());
 }
 @Test
 public void testNtpValue() {
  long ntpTime = 0x0000000000000000L;
  TimeStamp ts = new TimeStamp(ntpTime);
  assertEquals(ntpTime, ts.ntpValue());
 }
 @Test
 public void testGetSeconds() {
  long ntpTime = 0x0000000000000000L;
  TimeStamp ts = new TimeStamp(ntpTime);
  assertEquals(0, ts.getSeconds());
 }
 @Test
 public void testGetFraction() {
  long ntpTime = 0x0000000000000000L;
  TimeStamp ts = new TimeStamp(ntpTime);
  assertEquals(0, ts.getFraction());
 }
 @Test
 public void testGetTime() {
  long ntpTime = 0x0000000000000000L;
  TimeStamp ts = new TimeStamp(ntpTime);
  long expectedJavaTime = Long.parseLong("2208988800000");
  assertEquals(expectedJavaTime, ts.getTime());
 }
 @Test
 public void testGetDate() {
  long ntpTime = 0x0000000000000000L;
  TimeStamp ts = new TimeStamp(ntpTime);
  long expectedJavaTime = Long.parseLong("2208988800000");
  Date expectedDate = new Date(expectedJavaTime);
  assertEquals(expectedDate, ts.getDate());
 }
 @Test
 public void testGetCurrentTime() {
  TimeStamp ts = TimeStamp.getCurrentTime();
  assertNotNull(ts);
 }
 @Test
 public void testParseNtpString() {
  String hexStamp = "c1a089bd.fc904f6d";
  TimeStamp ts = TimeStamp.parseNtpString(hexStamp);
  long expectedNtpTime = 0xc1a089bdfc904f6dL;
  assertEquals(expectedNtpTime, ts.ntpValue());
 }
 @Test
 public void testToNtpTime() {
  long javaTime = 0L;
  long expectedNtpTime = Long.parseLong("2208988800000");
  long actualNtpTime = TimeStamp.toNtpTime(javaTime);
  assertEquals(expectedNtpTime, actualNtpTime);
 }
 @Test
 public void testToString() {
  long ntpTime = 0x0000000000000000L;
  TimeStamp ts = new TimeStamp(ntpTime);
  String expectedString = "00000000.00000000";
  assertEquals(expectedString, ts.toString());
 }
 @Test
 public void testToDateString() {
  long ntpTime = 0x0000000000000000L;
  TimeStamp ts = new TimeStamp(ntpTime);
  String expectedString = "Thu, Jan 01 1970 00:00:00.000";
  assertEquals(expectedString, ts.toDateString());
 }
 @Test
 public void testToUTCString() {
  long ntpTime = 0x0000000000000000L;
  TimeStamp ts = new TimeStamp(ntpTime);
  String expectedString = "Thu, Jan 01 1970 00:00:00.000 UTC";
  assertEquals(expectedString, ts.toUTCString());
 }
 @Test
 public void testCompareToEqual() {
  TimeStamp ts1 = new TimeStamp(0L);
  TimeStamp ts2 = new TimeStamp(0L);
  assertEquals(0, ts1.compareTo(ts2));
 }
 @Test
 public void testCompareToLessThan() {
  TimeStamp ts1 = new TimeStamp(0L);
  TimeStamp ts2 = new TimeStamp(1L);
  assertEquals(-1, ts1.compareTo(ts2));
 }
 @Test
 public void testCompareToGreaterThan() {
  TimeStamp ts1 = new TimeStamp(1L);
  TimeStamp ts2 = new TimeStamp(0L);
  assertEquals(1, ts1.compareTo(ts2));
 }
 @Test
 public void testHashCode() {
  TimeStamp ts1 = new TimeStamp(0L);
  TimeStamp ts2 = new TimeStamp(0L);
  assertEquals(ts1.hashCode(), ts2.hashCode());
 }
 @Test
 public void testEqualsTrue() {
  TimeStamp ts1 = new TimeStamp(0L);
  TimeStamp ts2 = new TimeStamp(0L);
  assertTrue(ts1.equals(ts2));
 }
 @Test
 public void testEqualsFalse() {
  TimeStamp ts1 = new TimeStamp(0L);
  TimeStamp ts2 = new TimeStamp(1L);
  assertFalse(ts1.equals(ts2));
 }
}
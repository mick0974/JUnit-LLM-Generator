
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
 private TimeStamp timeStamp;
 private Date date;
 @Before
 public void setUp() {
  timeStamp = new TimeStamp(new Date().getTime());
  date = new Date();
 }
 @After
 public void tearDown() {
  timeStamp = null;
  date = null;
 }
 @Test
 public void testGetCurrentTime() {
  TimeStamp ts = TimeStamp.getCurrentTime();
  assertNotNull(ts);
  long now = System.currentTimeMillis();
  assertTrue(now - 1000 < ts.getTime() && ts.getTime() < now + 1000);
 }
 @Test
 public void testGetTimeWithValidNtpTime() {
  long ntpTime = 0x4000000000000000L;
  long expectedJavaTime = Long.parseLong("1139018896000");
  long actualJavaTime = TimeStamp.getTime(ntpTime);
  assertEquals(expectedJavaTime, actualJavaTime);
 }
 @Test
 public void testGetTimeWithInvalidNtpTime() {
  long ntpTime = -1L;
  long actualJavaTime = TimeStamp.getTime(ntpTime);
  assertEquals(-Long.parseLong("2208988800000"), actualJavaTime);
 }
 @Test
 public void testGetTimeWithMaxLong() {
  long ntpTime = Long.MAX_VALUE;
  long actualJavaTime = TimeStamp.getTime(ntpTime);
  assertEquals(Long.parseLong("2262447448000"), actualJavaTime);
 }
 @Test
 public void testGetTimeWithMinLong() {
  long ntpTime = Long.MIN_VALUE;
  long actualJavaTime = TimeStamp.getTime(ntpTime);
  assertEquals(-Long.parseLong("2262447448000"), actualJavaTime);
 }
 @Test
 public void testGetTimeWithZero() {
  long ntpTime = 0L;
  long actualJavaTime = TimeStamp.getTime(ntpTime);
  assertEquals(-Long.parseLong("2208988800000"), actualJavaTime);
 }
 @Test
 public void testGetTimeWithNegativeNtpTime() {
  long ntpTime = -1000000000L;
  long actualJavaTime = TimeStamp.getTime(ntpTime);
  assertEquals(-Long.parseLong("2208988800000"), actualJavaTime);
 }
 @Test
 public void testGetTimeWithPositiveNtpTime() {
  long ntpTime = 1000000000L;
  long actualJavaTime = TimeStamp.getTime(ntpTime);
  assertEquals(-Long.parseLong("2208988800000") + 1000000000L, actualJavaTime);
 }
 @Test
 public void testGetTimeWithNtpTimeOnEdgeOf1900And2036() {
  long ntpTime = 0x8000000000000000L;
  long actualJavaTime = TimeStamp.getTime(ntpTime);
  assertEquals(-Long.parseLong("2208988800000"), actualJavaTime);
 }
 @Test
 public void testGetTimeWithNtpTimeOnEdgeOf2036And2104() {
  long ntpTime = 0xc000000000000000L;
  long actualJavaTime = TimeStamp.getTime(ntpTime);
  assertEquals(Long.parseLong("1139018896000"), actualJavaTime);
 }
}
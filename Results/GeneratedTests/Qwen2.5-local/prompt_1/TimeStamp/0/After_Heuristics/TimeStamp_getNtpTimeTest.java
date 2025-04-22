
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
 private DateFormat formatter;
 private TimeZone tz;
 @Before
 public void setUp() {
  ts = new TimeStamp(System.currentTimeMillis());
  formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",
    Locale.US);
  tz = TimeZone.getTimeZone("UTC");
  formatter.setTimeZone(tz);
 }
 @After
 public void tearDown() {
  ts = null;
  formatter = null;
  tz = null;
 }
 @Test
 public void testGetNtpTime_valid() {
  long currentTimeMillis = System.currentTimeMillis();
  TimeStamp ntpTs = TimeStamp.getNtpTime(currentTimeMillis);
  assertNotEquals(0, ntpTs.ntpValue());
 }
 @Test(expected = IllegalArgumentException.class)
 public void testGetNtpTime_negative() {
  TimeStamp.getNtpTime(-1);
 }
 @Test(expected = NullPointerException.class)
 public void testGetNtpTime_null() {
  TimeStamp.getNtpTime(null);
 }
 @Test
 public void testGetNtpTime_min() {
  long min = 0;
  TimeStamp ntpTs = TimeStamp.getNtpTime(min);
  assertEquals(0, ntpTs.ntpValue());
 }
 @Test
 public void testGetNtpTime_max() {
  long max = Long.MAX_VALUE;
  TimeStamp ntpTs = TimeStamp.getNtpTime(max);
  assertNotEquals(0, ntpTs.ntpValue());
 }
 @Test
 public void testGetNtpTime_1970_01_01_00_00_00() {
  long date = formatter.parse("1970-01-01T00:00:00.000+0000").getTime();
  TimeStamp ntpTs = TimeStamp.getNtpTime(date);
  assertEquals(Long.parseLong("2208988800000"), ntpTs.ntpValue());
 }
 @Test
 public void testGetNtpTime_2036_02_07_06_28_16() {
  long date = formatter.parse("2036-02-07T06:28:16.000+0000").getTime();
  TimeStamp ntpTs = TimeStamp.getNtpTime(date);
  assertEquals(Long.parseLong("2085978496000"), ntpTs.ntpValue());
 }
 @Test
 public void testGetNtpTime_2000_01_01_00_00_00() {
  long date = formatter.parse("2000-01-01T00:00:00.000+0000").getTime();
  TimeStamp ntpTs = TimeStamp.getNtpTime(date);
  assertNotEquals(0, ntpTs.ntpValue());
 }
 @Test
 public void testGetNtpTime_1900_01_01_01_00_00() {
  long date = formatter.parse("1900-01-01T01:00:00.000+0000").getTime();
  TimeStamp ntpTs = TimeStamp.getNtpTime(date);
  assertNotEquals(0, ntpTs.ntpValue());
 }
 @Test
 public void testGetNtpTime_2036_02_07_06_28_16_utc() {
  long date = formatter.parse("2036-02-07T06:28:16.000Z").getTime();
  TimeStamp ntpTs = TimeStamp.getNtpTime(date);
  assertEquals(Long.parseLong("2085978496000"), ntpTs.ntpValue());
 }
}
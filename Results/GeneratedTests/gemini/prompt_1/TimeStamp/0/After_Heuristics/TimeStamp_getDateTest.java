 import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_getDateTest {
 private TimeZone defaultTimeZone;
 private DateFormat utcDateFormat;
 private static final long MSB1_BASE_TIME_MILLIS = -Long.parseLong("2208988800000");
 private static final long MSB0_BASE_TIME_MILLIS = Long.parseLong("2085978496000");
 @Before
 public void setUp() {
  defaultTimeZone = TimeZone.getDefault();
  TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  utcDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
  utcDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
 }
 @After
 public void tearDown() {
  TimeZone.setDefault(defaultTimeZone);
 }
 @Test
 public void testGetDateForZeroNtpTime() {
  long ntpTimeValue = 0L;
  long ntpTimeFor1900Base = TimeStamp.toNtpTime(MSB1_BASE_TIME_MILLIS);
  TimeStamp ts1900 = new TimeStamp(ntpTimeFor1900Base);
  Date expectedDate1900 = new Date(MSB1_BASE_TIME_MILLIS);
  long ntpTimeFor2036Base = 0L;
  TimeStamp ts2036 = new TimeStamp(ntpTimeFor2036Base);
  Date expectedDate2036 = new Date(MSB0_BASE_TIME_MILLIS);
  Date actualDate1900 = ts1900.getDate();
  assertEquals("Timestamp for 1900 base time should yield correct Date",
      expectedDate1900.getTime(), actualDate1900.getTime());
  Date actualDate2036 = ts2036.getDate();
  assertEquals("Timestamp 0L should yield MSB0 base Date (2036)",
      expectedDate2036.getTime(), actualDate2036.getTime());
 }
 @Test
 public void testGetDateForJavaEpoch() {
  long javaEpochMillis = 0L;
  TimeStamp ts = TimeStamp.getNtpTime(javaEpochMillis);
  Date expectedDate = new Date(javaEpochMillis);
  Date actualDate = ts.getDate();
  assertEquals("Timestamp for Java epoch should yield correct Date",
      expectedDate.getTime(), actualDate.getTime());
 }
 @Test
 public void testGetDatePreJavaEpoch() throws ParseException {
  String dateString = "1950-06-15 10:30:00.000";
  Date expectedDate = utcDateFormat.parse(dateString);
  TimeStamp ts = TimeStamp.getNtpTime(expectedDate.getTime());
  Date actualDate = ts.getDate();
  assertEquals("Timestamp for 1950 date should yield correct Date",
      expectedDate.getTime(), actualDate.getTime());
  assertTrue("NTP seconds part should have MSB set for pre-2036 date",
       (ts.getSeconds() & 0x80000000L) != 0);
 }
 @Test
 public void testGetDatePostJavaEpochPre2036() throws ParseException {
  String dateString = "2023-10-27 14:00:00.000";
  Date expectedDate = utcDateFormat.parse(dateString);
  TimeStamp ts = TimeStamp.getNtpTime(expectedDate.getTime());
  Date actualDate = ts.getDate();
  assertEquals("Timestamp for 2023 date should yield correct Date",
      expectedDate.getTime(), actualDate.getTime());
  assertTrue("NTP seconds part should have MSB set for pre-2036 date",
       (ts.getSeconds() & 0x80000000L) != 0);
 }
 @Test
 public void testGetDatePostJavaEpochPre2036WithMillis() throws ParseException {
  String dateString = "2023-10-27 14:00:00.123";
  Date expectedDate = utcDateFormat.parse(dateString);
  TimeStamp ts = TimeStamp.getNtpTime(expectedDate.getTime());
  Date actualDate = ts.getDate();
  assertEquals("Timestamp for 2023 date with millis should yield correct Date",
      expectedDate.getTime(), actualDate.getTime());
  assertTrue("NTP seconds part should have MSB set for pre-2036 date",
       (ts.getSeconds() & 0x80000000L) != 0);
 }
 @Test
 public void testGetDateAtBoundary2036() {
  long boundaryMillis = MSB0_BASE_TIME_MILLIS;
  Date expectedDate = new Date(boundaryMillis);
  TimeStamp ts = TimeStamp.getNtpTime(boundaryMillis);
  Date actualDate = ts.getDate();
  assertEquals("Timestamp for 2036 boundary time should yield correct Date",
      expectedDate.getTime(), actualDate.getTime());
  assertFalse("NTP seconds part should have MSB clear for 2036 boundary date",
     (ts.getSeconds() & 0x80000000L) != 0);
  assertEquals("NTP seconds part should be 0 for the 2036 boundary", 0L, ts.getSeconds());
  assertEquals("NTP fraction part should be 0 for the 2036 boundary", 0L, ts.getFraction());
 }
 @Test
 public void testGetDateJustAfterBoundary2036() throws ParseException {
  String dateString = "2036-02-07 06:28:17.000";
  Date expectedDate = utcDateFormat.parse(dateString);
  TimeStamp ts = TimeStamp.getNtpTime(expectedDate.getTime());
  Date actualDate = ts.getDate();
  assertEquals("Timestamp just after 2036 boundary should yield correct Date",
      expectedDate.getTime(), actualDate.getTime());
  assertFalse("NTP seconds part should have MSB clear for post-2036 date",
     (ts.getSeconds() & 0x80000000L) != 0);
  assertEquals("NTP seconds part should be 1", 1L, ts.getSeconds());
 }
 @Test
 public void testGetDatePost2036() throws ParseException {
  String dateString = "2040-01-01 00:00:00.000";
  Date expectedDate = utcDateFormat.parse(dateString);
  TimeStamp ts = TimeStamp.getNtpTime(expectedDate.getTime());
  Date actualDate = ts.getDate();
  assertEquals("Timestamp for 2040 date should yield correct Date",
      expectedDate.getTime(), actualDate.getTime());
  assertFalse("NTP seconds part should have MSB clear for post-2036 date",
     (ts.getSeconds() & 0x80000000L) != 0);
 }
 @Test
 public void testGetDatePost2036WithMillis() throws ParseException {
  String dateString = "2040-01-01 00:00:00.500";
  Date expectedDate = utcDateFormat.parse(dateString);
  TimeStamp ts = TimeStamp.getNtpTime(expectedDate.getTime());
  Date actualDate = ts.getDate();
  assertEquals("Timestamp for 2040 date with millis should yield correct Date",
      expectedDate.getTime(), actualDate.getTime());
  assertFalse("NTP seconds part should have MSB clear for post-2036 date",
     (ts.getSeconds() & 0x80000000L) != 0);
  assertEquals("NTP fraction part should be half", 0x80000000L, ts.getFraction());
 }
 @Test
 public void testGetDateFromKnownHexString() throws ParseException {
  String ntpHexString = "c1a9ae1c.cf5c28f5";
  TimeStamp ts = new TimeStamp(ntpHexString);
  Date expectedDate = utcDateFormat.parse("2002-12-17 14:07:24.810");
  Date actualDate = ts.getDate();
  assertEquals("Timestamp from known hex string should yield correct Date",
      expectedDate.getTime(), actualDate.getTime());
 }
}
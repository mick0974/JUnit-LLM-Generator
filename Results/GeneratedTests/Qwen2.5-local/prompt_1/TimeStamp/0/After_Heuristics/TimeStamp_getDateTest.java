
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
 private TimeStamp timeStamp;
 @Before
 public void setUp() {
  timeStamp = new TimeStamp(new Date());
 }
 @After
 public void tearDown() {
  timeStamp = null;
 }
 @Test(expected = NullPointerException.class)
 public void testGetDateWithNullArgument() {
  new TimeStamp(null).getDate();
 }
 @Test
 public void testGetDateWithCurrentDateTime() {
  Date currentDate = new Date();
  Date timeStampDate = timeStamp.getDate();
  assertEquals(currentDate.getYear(), timeStampDate.getYear());
  assertEquals(currentDate.getMonth(), timeStampDate.getMonth());
  assertEquals(currentDate.getDay(), timeStampDate.getDay());
  assertEquals(currentDate.getHours(), timeStampDate.getHours());
  assertEquals(currentDate.getMinutes(), timeStampDate.getMinutes());
  assertEquals(currentDate.getSeconds(), timeStampDate.getSeconds());
 }
 @Test
 public void testGetDateWithPastDateTime() {
  long pastTime = System.currentTimeMillis() - 86400000;
  TimeStamp pastTimeStamp = new TimeStamp(pastTime);
  Date pastTimeStampDate = pastTimeStamp.getDate();
  assertEquals(pastTimeStampDate.getYear(), new Date(pastTime).getYear());
  assertEquals(pastTimeStampDate.getMonth(), new Date(pastTime).getMonth());
  assertEquals(pastTimeStampDate.getDay(), new Date(pastTime).getDay());
  assertEquals(pastTimeStampDate.getHours(), new Date(pastTime).getHours());
  assertEquals(pastTimeStampDate.getMinutes(), new Date(pastTime).getMinutes());
  assertEquals(pastTimeStampDate.getSeconds(), new Date(pastTime).getSeconds());
 }
 @Test
 public void testGetDateWithFutureDateTime() {
  long futureTime = System.currentTimeMillis() + 86400000;
  TimeStamp futureTimeStamp = new TimeStamp(futureTime);
  Date futureTimeStampDate = futureTimeStamp.getDate();
  assertEquals(futureTimeStampDate.getYear(), new Date(futureTime).getYear());
  assertEquals(futureTimeStampDate.getMonth(), new Date(futureTime).getMonth());
  assertEquals(futureTimeStampDate.getDay(), new Date(futureTime).getDay());
  assertEquals(futureTimeStampDate.getHours(), new Date(futureTime).getHours());
  assertEquals(futureTimeStampDate.getMinutes(), new Date(futureTime).getMinutes());
  assertEquals(futureTimeStampDate.getSeconds(), new Date(futureTime).getSeconds());
 }
 @Test
 public void testGetDateWithMinDateTime() {
  long minTime = 0;
  TimeStamp minTimeStamp = new TimeStamp(minTime);
  Date minTimeStampDate = minTimeStamp.getDate();
  assertEquals(minTimeStampDate.getYear(), new Date(minTime).getYear());
  assertEquals(minTimeStampDate.getMonth(), new Date(minTime).getMonth());
  assertEquals(minTimeStampDate.getDay(), new Date(minTime).getDay());
  assertEquals(minTimeStampDate.getHours(), new Date(minTime).getHours());
  assertEquals(minTimeStampDate.getMinutes(), new Date(minTime).getMinutes());
  assertEquals(minTimeStampDate.getSeconds(), new Date(minTime).getSeconds());
 }
 @Test
 public void testGetDateWithMaxDateTime() {
  long maxTime = Long.parseLong("9223372036854775807");
  TimeStamp maxTimeStamp = new TimeStamp(maxTime);
  Date maxTimeStampDate = maxTimeStamp.getDate();
  assertEquals(maxTimeStampDate.getYear(), new Date(maxTime).getYear());
  assertEquals(maxTimeStampDate.getMonth(), new Date(maxTime).getMonth());
  assertEquals(maxTimeStampDate.getDay(), new Date(maxTime).getDay());
  assertEquals(maxTimeStampDate.getHours(), new Date(maxTime).getHours());
  assertEquals(maxTimeStampDate.getMinutes(), new Date(maxTime).getMinutes());
  assertEquals(maxTimeStampDate.getSeconds(), new Date(maxTime).getSeconds());
 }
 @Test
 public void testGetDateWithTimeZoneChange() {
  long time = System.currentTimeMillis();
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
  Date gmtPlusOneDate = new Date(time);
  TimeZone.setDefault(TimeZone.getTimeZone("GMT-1"));
  Date gmtMinusOneDate = new Date(time);
  TimeStamp gmtPlusOneTimeStamp = new TimeStamp(gmtPlusOneDate);
  TimeStamp gmtMinusOneTimeStamp = new TimeStamp(gmtMinusOneDate);
  assertEquals(gmtPlusOneTimeStamp.getDate().getHours(),
    gmtPlusOneDate.getHours());
  assertEquals(gmtPlusOneTimeStamp.getDate().getMinutes(),
    gmtPlusOneDate.getMinutes());
  assertEquals(gmtPlusOneTimeStamp.getDate().getSeconds(),
    gmtPlusOneDate.getSeconds());
  assertEquals(gmtMinusOneTimeStamp.getDate().getHours(),
    gmtMinusOneDate.getHours());
  assertEquals(gmtMinusOneTimeStamp.getDate().getMinutes(),
    gmtMinusOneDate.getMinutes());
  assertEquals(gmtMinusOneTimeStamp.getDate().getSeconds(),
    gmtMinusOneDate.getSeconds());
 }
 @Test
 public void testGetDateWithLeapSecond() {
  long leapSecondTime = Long.parseLong("1164447360000");
  TimeStamp leapSecondTimeStamp = new TimeStamp(leapSecondTime);
  Date leapSecondTimeStampDate = leapSecondTimeStamp.getDate();
  assertEquals(leapSecondTimeStampDate.getYear(), new Date(leapSecondTime).getYear());
  assertEquals(leapSecondTimeStampDate.getMonth(), new Date(leapSecondTime).getMonth());
  assertEquals(leapSecondTimeStampDate.getDay(), new Date(leapSecondTime).getDay());
  assertEquals(leapSecondTimeStampDate.getHours(), new Date(leapSecondTime).getHours());
  assertEquals(leapSecondTimeStampDate.getMinutes(), new Date(leapSecondTime).getMinutes());
  assertEquals(leapSecondTimeStampDate.getSeconds(), new Date(leapSecondTime).getSeconds());
 }
 @Test
 public void testGetDateWithNTPTimestamp() {
  long ntpTime = 0x4000000000000000L;
  TimeStamp ntpTimeStamp = new TimeStamp(ntpTime);
  Date ntpTimeStampDate = ntpTimeStamp.getDate();
  assertEquals(ntpTimeStampDate.getYear(), new Date(ntpTime).getYear());
  assertEquals(ntpTimeStampDate.getMonth(), new Date(ntpTime).getMonth());
  assertEquals(ntpTimeStampDate.getDay(), new Date(ntpTime).getDay());
  assertEquals(ntpTimeStampDate.getHours(), new Date(ntpTime).getHours());
  assertEquals(ntpTimeStampDate.getMinutes(), new Date(ntpTime).getMinutes());
  assertEquals(ntpTimeStampDate.getSeconds(), new Date(ntpTime).getSeconds());
 }
 @Test
 public void testGetDateWithNTPTimestampWithFractionalSeconds() {
  long ntpTime = 0x4000000000000000L + 0x80000000L;
  TimeStamp ntpTimeStamp = new TimeStamp(ntpTime);
  Date ntpTimeStampDate = ntpTimeStamp.getDate();
  assertEquals(ntpTimeStampDate.getYear(), new Date(ntpTime).getYear());
  assertEquals(ntpTimeStampDate.getMonth(), new Date(ntpTime).getMonth());
  assertEquals(ntpTimeStampDate.getDay(), new Date(ntpTime).getDay());
  assertEquals(ntpTimeStampDate.getHours(), new Date(ntpTime).getHours());
  assertEquals(ntpTimeStampDate.getMinutes(), new Date(ntpTime).getMinutes());
  assertEquals(ntpTimeStampDate.getSeconds(), new Date(ntpTime).getSeconds());
 }
}
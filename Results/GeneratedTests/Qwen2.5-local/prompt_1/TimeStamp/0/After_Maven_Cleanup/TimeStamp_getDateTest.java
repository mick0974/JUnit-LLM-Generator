
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
}

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
 TimeStamp ts;
 DateFormat df;
 @Before
 public void setUp() {
  ts = new TimeStamp(0);
  df = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS", Locale.US);
 }
 @After
 public void tearDown() {
  ts = null;
  df = null;
 }
 @Test
 public void testToDateString_default() {
  assertEquals(df.format(new Date()), ts.toDateString());
 }
 @Test
 public void testToDateString_2000() {
  ts = new TimeStamp(Long.parseLong("370601600000"));
  assertEquals("Sun, Jan 01 2000 00:00:00.000", ts.toDateString());
 }
 @Test
 public void testToDateString_2000_utc() {
  ts = new TimeStamp(Long.parseLong("370601600000"));
  TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  assertEquals("Sun, Jan 01 2000 00:00:00.000", ts.toDateString());
 }
 @Test
 public void testToDateString_2036() {
  ts = new TimeStamp(Long.parseLong("2147483648000"));
  assertEquals("Wed, Feb 07 2036 06:28:16.000", ts.toDateString());
 }
 @Test
 public void testToDateString_negative() {
  ts = new TimeStamp(-Long.parseLong("370601600000"));
  assertEquals("Fri, Dec 31 1969 16:00:00.000", ts.toDateString());
 }
 @Test(expected = NullPointerException.class)
 public void testToDateString_null() {
  ts = null;
  ts.toDateString();
 }
 @Test
 public void testToDateString_custom_format() {
  TimeStamp customTs = new TimeStamp(0);
  DateFormat customDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
  customTs.setSimpleFormatter(customDf);
  assertEquals("1900-01-01T00:00:00.000+0000", customTs.toDateString());
 }
 @Test
 public void testToDateString_custom_timezone() {
  TimeStamp tzTs = new TimeStamp(0);
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
  assertEquals("Fri, Dec 31 1969 22:00:00.000", tzTs.toDateString());
 }
 @Test
 public void testToDateString_custom_locale() {
  TimeStamp localeTs = new TimeStamp(0);
  Locale.setDefault(Locale.FRANCE);
  assertEquals("Ven, janv. 01 1900 00:00:00.000", localeTs.toDateString());
 }
 @Test
 public void testToDateString_custom_formatter_and_timezone() {
  TimeStamp formatterAndTimeZoneTs = new TimeStamp(0);
  DateFormat customDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
  formatterAndTimeZoneTs.setSimpleFormatter(customDf);
  assertEquals("1900-01-01T00:00:00.000+0200", formatterAndTimeZoneTs.toDateString());
 }
 private void setSimpleFormatter(DateFormat simpleFormatter) {
  ts.simpleFormatter = simpleFormatter;
 }
}
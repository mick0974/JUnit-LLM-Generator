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
public class TimeStamp_toDateStringTest {
 private TimeZone originalDefaultTimeZone;
 private SimpleDateFormat expectedFormat;
 @Before
 public void setUp() {
  originalDefaultTimeZone = TimeZone.getDefault();
  TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  expectedFormat = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT, Locale.US);
  expectedFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
 }
 @After
 public void tearDown() {
  TimeZone.setDefault(originalDefaultTimeZone);
 }
 private String getExpectedDateString(Date date) {
  return expectedFormat.format(date);
 }
 private Date createUtcDate(String dateString) throws ParseException {
  SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
  parser.setTimeZone(TimeZone.getTimeZone("UTC"));
  return parser.parse(dateString);
 }
 @Test
 public void testDateStringBefore2036() throws ParseException {
  String hexStamp = "c1a089bd.fc904f6d";
  TimeStamp ts = new TimeStamp(hexStamp);
  Date expectedDate = createUtcDate("2002-12-10T10:41:49.986Z");
  String expectedString = getExpectedDateString(expectedDate);
  expectedString = "Tue, Dec 10 2002 10:41:49.986";
  String actualString = ts.toDateString();
  assertEquals(expectedString, actualString);
 }
 @Test
 public void testDateStringAfter2036() throws ParseException {
  Date date = createUtcDate("2040-03-01T12:00:00.000Z");
  TimeStamp ts = new TimeStamp(date);
  String expectedString = getExpectedDateString(date);
  String actualString = ts.toDateString();
  assertEquals(expectedString, actualString);
 }
 @Test
 public void testDateStringJavaEpoch() throws ParseException {
  Date date = new Date(0);
  TimeStamp ts = new TimeStamp(date);
  String expectedString = getExpectedDateString(date);
  String actualString = ts.toDateString();
  assertEquals(expectedString, actualString);
 }
 @Test
 public void testDateStringNtpEpoch1900() throws ParseException {
  long ntpBase1TimeMillis = -Long.parseLong("2208988800000");
  Date date = new Date(ntpBase1TimeMillis);
  TimeStamp ts = new TimeStamp(date);
        assertEquals(0x80000000L, (ts.ntpValue() >>> 32) & 0x80000000L);
        assertEquals(0x8000000000000000L, ts.ntpValue());
  String expectedString = getExpectedDateString(date);
  String actualString = ts.toDateString();
  assertEquals(expectedString, actualString);
 }
 @Test
 public void testDateStringNtpEpoch2036() throws ParseException {
  long ntpBase0TimeMillis = Long.parseLong("2085978496000");
  Date date = new Date(ntpBase0TimeMillis);
  TimeStamp ts = new TimeStamp(date);
        assertEquals(0L, (ts.ntpValue() >>> 32) & 0x80000000L);
        assertEquals(0L, ts.ntpValue());
  String expectedString = getExpectedDateString(date);
  String actualString = ts.toDateString();
  assertEquals(expectedString, actualString);
 }
 @Test
 public void testDateStringFromZeroNtpTime() throws ParseException {
  TimeStamp ts = new TimeStamp(0L);
  long ntpBase0TimeMillis = Long.parseLong("2085978496000");
  Date expectedDate = new Date(ntpBase0TimeMillis);
  String expectedString = getExpectedDateString(expectedDate);
  String actualString = ts.toDateString();
  assertEquals(expectedString, actualString);
  assertEquals(ntpBase0TimeMillis, ts.getTime());
 }
 @Test
 public void testDateStringFromNullDate() throws ParseException {
  TimeStamp ts = new TimeStamp((Date) null);
  long ntpBase0TimeMillis = Long.parseLong("2085978496000");
  Date expectedDate = new Date(ntpBase0TimeMillis);
  String expectedString = getExpectedDateString(expectedDate);
  String actualString = ts.toDateString();
  assertEquals(0L, ts.ntpValue());
  assertEquals(expectedString, actualString);
 }
 @Test
 public void testDateStringJustBefore2036Boundary() throws ParseException {
  long ntpBase0TimeMillis = Long.parseLong("2085978496000");
  Date date = new Date(ntpBase0TimeMillis - 1);
  TimeStamp ts = new TimeStamp(date);
  String expectedString = getExpectedDateString(date);
        assertTrue("Timestamp before 2036 boundary should use base 1", (ts.ntpValue() >>> 63) == 1);
  String actualString = ts.toDateString();
  assertEquals(expectedString, actualString);
 }
 @Test
 public void testDateStringJustAfter2036Boundary() throws ParseException {
  long ntpBase0TimeMillis = Long.parseLong("2085978496000");
  Date date = new Date(ntpBase0TimeMillis + 1);
  TimeStamp ts = new TimeStamp(date);
  String expectedString = getExpectedDateString(date);
        assertTrue("Timestamp after 2036 boundary should use base 0", (ts.ntpValue() >>> 63) == 0);
  String actualString = ts.toDateString();
  assertEquals(expectedString, actualString);
 }
 @Test
 public void testDateStringKnownHexValue() throws ParseException {
  String hexStamp = "c1a089bd.fc904f6d";
  TimeStamp ts = TimeStamp.parseNtpString(hexStamp);
        Date expectedDate = createUtcDate("2002-12-10T10:41:49.986Z");
        String expectedString = getExpectedDateString(expectedDate);
  String actualString = ts.toDateString();
  assertEquals(expectedString, actualString);
 }
}
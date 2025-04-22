 import org.junit.Test;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import static org.junit.Assert.*;
public class TimeStampTest {
 private static final long MSB0_BASE_TIME_JAVA = Long.parseLong("2085978496000");
 private static final long MSB1_BASE_TIME_JAVA = -Long.parseLong("2208988800000");
 private static final long JAVA_TIME_1970 = 0L;
 private static final long NTP_TIME_1970 = 0x83aa7e8000000000L;
 private static final String NTP_HEX_1970 = "83aa7e80.00000000";
 private static final long JAVA_TIME_2040 = Long.parseLong("2208988800000");
 private static final long NTP_TIME_2040 = 0x0758ab0000000000L;
 private static final String NTP_HEX_2040 = "0758ab00.00000000";
 private static final long JAVA_TIME_2002_FRAC = Long.parseLong("1039534909987");
 private static final long NTP_TIME_2002_FRAC = 0xc1a089bdfc904f6dL;
 private static final String NTP_HEX_2002_FRAC = "c1a089bd.fc904f6d";
 private static final long JAVA_TIME_2002_FRAC_INPUT = Long.parseLong("1039534909986");
 private static final long NTP_TIME_2002_FRAC_OUTPUT = 0xc1a089bdfc904f6eL;
 private static final long NTP_TIME_ZERO = 0L;
 private static final long JAVA_TIME_NTP_ZERO = MSB0_BASE_TIME_JAVA;
 private static final String NTP_HEX_ZERO = "00000000.00000000";
 private static final long NTP_TIME_PADDING = 0x000123450006789AL;
 private static final String NTP_HEX_PADDING = "00012345.0006789a";
 @Test
 public void ConstructorLongNominalTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
  assertEquals(NTP_TIME_2002_FRAC, ts.ntpValue());
 }
 @Test
 public void ConstructorLongZeroTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_ZERO);
  assertEquals(NTP_TIME_ZERO, ts.ntpValue());
 }
 @Test
 public void ConstructorStringValidWithDotTest() {
  TimeStamp ts = new TimeStamp(NTP_HEX_2002_FRAC);
  assertEquals(NTP_TIME_2002_FRAC, ts.ntpValue());
 }
 @Test
 public void ConstructorStringValidNoDotTest() {
  String hexNoDot = "c1a089bd";
  long expectedNtp = 0xc1a089bd00000000L;
  TimeStamp ts = new TimeStamp(hexNoDot);
  assertEquals(expectedNtp, ts.ntpValue());
 }
 @Test
 public void ConstructorStringValidZeroTest() {
  TimeStamp ts = new TimeStamp("0");
  assertEquals(0L, ts.ntpValue());
 }
 @Test
 public void ConstructorStringValidZeroWithDotTest() {
  TimeStamp ts = new TimeStamp("0.0");
  assertEquals(0L, ts.ntpValue());
 }
 @Test
 public void ConstructorStringValidEmptyTest() {
  TimeStamp ts = new TimeStamp("");
  assertEquals(0L, ts.ntpValue());
 }
 @Test(expected = NumberFormatException.class)
 public void ConstructorStringInvalidHexTest() {
  new TimeStamp("invalid.hex");
 }
 @Test(expected = NumberFormatException.class)
 public void ConstructorStringInvalidFormatJustDotTest() {
  new TimeStamp(".");
 }
 @Test(expected = NumberFormatException.class)
 public void ConstructorStringInvalidFormatLeadingDotTest() {
  new TimeStamp(".fc904f6d");
 }
 @Test(expected = NumberFormatException.class)
 public void ConstructorStringInvalidFormatTrailingDotTest() {
  new TimeStamp("c1a089bd.");
 }
 @Test
 public void ConstructorDateNullTest() {
  TimeStamp ts = new TimeStamp((Date) null);
  assertEquals(0L, ts.ntpValue());
 }
 @Test
 public void ConstructorDateEpochTest() {
  Date date = new Date(JAVA_TIME_1970);
  TimeStamp ts = new TimeStamp(date);
  assertEquals(NTP_TIME_1970, ts.ntpValue());
 }
 @Test
 public void ConstructorDateBefore2036Test() {
  Date date = new Date(JAVA_TIME_1970);
  TimeStamp ts = new TimeStamp(date);
  assertEquals(NTP_TIME_1970, ts.ntpValue());
  assertTrue("MSB should be 1 for dates < 2036", (ts.ntpValue() & 0x8000000000000000L) != 0);
 }
 @Test
 public void NtpValueReturnInternalValueTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
  assertEquals(NTP_TIME_2002_FRAC, ts.ntpValue());
 }
 @Test
 public void GetSecondsReturnsCorrectValueTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
  long expectedSeconds = (NTP_TIME_2002_FRAC >>> 32) & 0xffffffffL;
  assertEquals(expectedSeconds, ts.getSeconds());
 }
 @Test
 public void GetFractionReturnsCorrectValueTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
  long expectedFraction = NTP_TIME_2002_FRAC & 0xffffffffL;
  assertEquals(expectedFraction, ts.getFraction());
 }
 @Test
 public void GetTimeMsb1BaseTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_1970);
  assertEquals(JAVA_TIME_1970, ts.getTime());
 }
 @Test
 public void GetTimeFractionRoundingTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
  assertEquals(JAVA_TIME_2002_FRAC, ts.getTime());
 }
 @Test
 public void GetTimeZeroTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_ZERO);
  assertEquals(JAVA_TIME_NTP_ZERO, ts.getTime());
 }
 @Test
 public void GetDateReturnsCorrectDateTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_1970);
  Date date = ts.getDate();
  assertNotNull(date);
  assertEquals(JAVA_TIME_1970, date.getTime());
  TimeStamp ts2 = new TimeStamp(NTP_TIME_2002_FRAC);
  Date date2 = ts2.getDate();
  assertNotNull(date2);
  assertEquals(JAVA_TIME_2002_FRAC, date2.getTime());
 }
 @Test
 public void GetTimeStaticMsb1BaseTest() {
  assertEquals(JAVA_TIME_1970, TimeStamp.getTime(NTP_TIME_1970));
 }
 @Test
 public void GetTimeStaticZeroTest() {
  assertEquals(JAVA_TIME_NTP_ZERO, TimeStamp.getTime(NTP_TIME_ZERO));
 }
 @Test
 public void GetNtpTimeStaticBefore2036Test() {
  TimeStamp ts = TimeStamp.getNtpTime(JAVA_TIME_1970);
  assertEquals(NTP_TIME_1970, ts.ntpValue());
 }
 @Test
 public void GetCurrentTimeReturnsValidTimeStampTest() {
  long javaTimeBefore = System.currentTimeMillis();
  TimeStamp ts = TimeStamp.getCurrentTime();
  long javaTimeAfter = System.currentTimeMillis();
  assertNotNull(ts);
  long tsJavaTime = ts.getTime();
  assertTrue("Timestamp should be close to current time", tsJavaTime >= javaTimeBefore && tsJavaTime <= javaTimeAfter + 1);
 }
 @Test
 public void DecodeNtpHexStringValidWithDotTest() {
  assertEquals(NTP_TIME_2002_FRAC, TimeStamp.decodeNtpHexString(NTP_HEX_2002_FRAC));
 }
 @Test
 public void DecodeNtpHexStringValidNoDotTest() {
  String hexNoDot = "c1a089bd";
  long expectedNtp = 0xc1a089bd00000000L;
  assertEquals(expectedNtp, TimeStamp.decodeNtpHexString(hexNoDot));
 }
 @Test
 public void DecodeNtpHexStringValidZeroTest() {
  assertEquals(0L, TimeStamp.decodeNtpHexString("0"));
 }
 @Test
 public void DecodeNtpHexStringValidZeroWithDotTest() {
  assertEquals(0L, TimeStamp.decodeNtpHexString("0.0"));
 }
 @Test
 public void DecodeNtpHexStringValidEmptyTest() {
  assertEquals(0L, TimeStamp.decodeNtpHexString(""));
 }
 @Test(expected = NumberFormatException.class)
 public void DecodeNtpHexStringNullTest() {
  TimeStamp.decodeNtpHexString(null);
 }
 @Test(expected = NumberFormatException.class)
 public void DecodeNtpHexStringInvalidHexTest() {
  TimeStamp.decodeNtpHexString("invalid.hex");
 }
 @Test(expected = NumberFormatException.class)
 public void DecodeNtpHexStringInvalidCharsTest() {
  TimeStamp.decodeNtpHexString("c1a0-89bd.fc904f6d");
 }
 @Test(expected = NumberFormatException.class)
 public void DecodeNtpHexStringEmptySecondsTest() {
  TimeStamp.decodeNtpHexString(".fc904f6d");
 }
 @Test(expected = NumberFormatException.class)
 public void DecodeNtpHexStringEmptyFractionTest() {
  TimeStamp.decodeNtpHexString("c1a089bd.");
 }
 @Test
 public void ParseNtpStringValidTest() {
  TimeStamp ts = TimeStamp.parseNtpString(NTP_HEX_2002_FRAC);
  assertEquals(NTP_TIME_2002_FRAC, ts.ntpValue());
 }
 @Test(expected = NumberFormatException.class)
 public void ParseNtpStringInvalidTest() {
  TimeStamp.parseNtpString("invalid-string");
 }
 @Test(expected = NumberFormatException.class)
 public void ParseNtpStringNullTest() {
  TimeStamp.parseNtpString(null);
 }
 @Test
 public void ToNtpTimeBefore2036Test() {
  assertEquals(NTP_TIME_1970, TimeStamp.toNtpTime(JAVA_TIME_1970));
 }
 @Test
 public void HashCodeConsistentTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
  int hc1 = ts.hashCode();
  int hc2 = ts.hashCode();
  assertEquals(hc1, hc2);
 }
 @Test
 public void HashCodeEqualForEqualObjectsTest() {
  TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
  TimeStamp ts2 = new TimeStamp(NTP_TIME_1970);
  assertEquals(ts1.hashCode(), ts2.hashCode());
 }
 @Test
 public void HashCodeDifferentForDifferentObjectsTest() {
  TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
  TimeStamp ts2 = new TimeStamp(NTP_TIME_2040);
  assertNotEquals(ts1.hashCode(), ts2.hashCode());
 }
 @Test
 public void EqualsSameObjectTest() {
  TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
  assertTrue(ts1.equals(ts1));
 }
 @Test
 public void EqualsEqualObjectTest() {
  TimeStamp ts1 = new TimeStamp(NTP_TIME_2002_FRAC);
  TimeStamp ts2 = new TimeStamp(NTP_TIME_2002_FRAC);
  assertTrue(ts1.equals(ts2));
  assertTrue(ts2.equals(ts1));
 }
 @Test
 public void EqualsDifferentObjectTest() {
  TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
  TimeStamp ts2 = new TimeStamp(NTP_TIME_2040);
  assertFalse(ts1.equals(ts2));
  assertFalse(ts2.equals(ts1));
 }
 @Test
 public void EqualsNullTest() {
  TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
  assertFalse(ts1.equals(null));
 }
 @Test
 public void EqualsDifferentTypeTest() {
  TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
  assertFalse(ts1.equals(new Object()));
  assertFalse(ts1.equals(Long.valueOf(NTP_TIME_1970)));
 }
 @Test
 public void ToStringReturnsCorrectFormatTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
  assertEquals(NTP_HEX_2002_FRAC, ts.toString());
  TimeStamp tsZero = new TimeStamp(NTP_TIME_ZERO);
  assertEquals(NTP_HEX_ZERO, tsZero.toString());
 }
 @Test
 public void ToStringPaddingTest() {
  TimeStamp ts = new TimeStamp(NTP_TIME_PADDING);
  assertEquals(NTP_HEX_PADDING, ts.toString());
 }
 @Test
 public void ToStringStaticReturnsCorrectFormatTest() {
  assertEquals(NTP_HEX_2002_FRAC, TimeStamp.toString(NTP_TIME_2002_FRAC));
  assertEquals(NTP_HEX_ZERO, TimeStamp.toString(NTP_TIME_ZERO));
 }
 @Test
 public void ToStringStaticPaddingTest() {
  assertEquals(NTP_HEX_PADDING, TimeStamp.toString(NTP_TIME_PADDING));
  long ntpSecPadding = 0x0000000100000000L;
  assertEquals("00000001.00000000", TimeStamp.toString(ntpSecPadding));
  long ntpFracPadding = 0x8000000000000001L;
  assertEquals("80000000.00000001", TimeStamp.toString(ntpFracPadding));
 }
 @Test
 public void CompareToEqualTest() {
  TimeStamp ts1 = new TimeStamp(NTP_TIME_2002_FRAC);
  TimeStamp ts2 = new TimeStamp(NTP_TIME_2002_FRAC);
  assertEquals(0, ts1.compareTo(ts2));
  assertEquals(0, ts2.compareTo(ts1));
 }
 @Test
 public void CompareToLessThanTest() {
  TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
  TimeStamp ts2 = new TimeStamp(NTP_TIME_2002_FRAC);
  assertTrue("ts1 should be less than ts2", ts1.compareTo(ts2) < 0);
 }
 @Test
 public void CompareToGreaterThanTest() {
  TimeStamp ts1 = new TimeStamp(NTP_TIME_2040);
  TimeStamp ts2 = new TimeStamp(NTP_TIME_2002_FRAC);
  assertTrue("ts1 should be greater than ts2", ts1.compareTo(ts2) > 0);
 }
}
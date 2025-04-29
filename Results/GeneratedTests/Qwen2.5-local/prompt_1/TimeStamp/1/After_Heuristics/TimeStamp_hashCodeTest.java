
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_hashCodeTest {
 private TimeStamp ts1;
 private TimeStamp ts2;
 private TimeStamp ts3;
 private TimeStamp ts4;
 @Before
 public void setUp() {
  ts1 = new TimeStamp(0x00000000L);
  ts2 = new TimeStamp(0xFFFFFFFFFFFFFFFFL);
  ts3 = new TimeStamp(-1L);
  ts4 = new TimeStamp(0x00000001L);
 }
 @After
 public void tearDown() {
 }
 @Test
 public void testHashCode_zeroTime() {
  assertEquals(0, ts1.hashCode());
 }
 @Test
 public void testHashCode_maxTime() {
  long expected = (int)((ts2.ntpTime ^ (ts2.ntpTime >>> 32)));
  assertEquals(expected, ts2.hashCode());
 }
 @Test
 public void testHashCode_negativeTime() {
  long expected = (int)((ts3.ntpTime ^ (ts3.ntpTime >>> 32)));
  assertEquals(expected, ts3.hashCode());
 }
 @Test
 public void testHashCode_oneMoreThanZero() {
  long expected = (int)((ts4.ntpTime ^ (ts4.ntpTime >>> 32)));
  assertEquals(expected, ts4.hashCode());
 }
 @Test
 public void testHashCode_equalObjects() {
  TimeStamp ts = new TimeStamp(0x00000000L);
  assertEquals(ts1.hashCode(), ts.hashCode());
 }
 @Test
 public void testHashCode_notEqualObjects() {
  TimeStamp ts = new TimeStamp(0x00000001L);
  assertNotEquals(ts1.hashCode(), ts.hashCode());
 }
 @Test
 public void testHashCode_sameObject() {
  assertSame(ts1.hashCode(), ts1.hashCode());
 }
 @Test
 public void testHashCode_nullObject() {
  assertThrows(NullPointerException.class, () -> {
   new TimeStamp(null);
  });
 }
 @Test
 public void testHashCode_largeNumber() {
  TimeStamp ts = new TimeStamp(Long.MAX_VALUE);
  long expected = (int)((ts.ntpTime ^ (ts.ntpTime >>> 32)));
  assertEquals(expected, ts.hashCode());
 }
}
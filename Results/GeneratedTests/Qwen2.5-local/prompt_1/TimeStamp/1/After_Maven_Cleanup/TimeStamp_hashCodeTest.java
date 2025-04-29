
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
}
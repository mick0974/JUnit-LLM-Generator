
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_getSecondsTest {
 private TimeStamp ts;
 @Before
 public void setUp() {
  ts = new TimeStamp(System.currentTimeMillis());
 }
 @After
 public void tearDown() {
  ts = null;
 }
 @Test
 public void testGetSecondsEdgeCaseZero() {
  long zero = 0;
  ts = new TimeStamp(zero);
  long expectedSeconds = zero / 1000;
  long actualSeconds = ts.getSeconds();
  assertEquals(expectedSeconds, actualSeconds);
 }
 @Test
 public void testGetSecondsNullObject() {
  try {
   TimeStamp ts = null;
   long actualSeconds = ts.getSeconds();
   fail("Expected NullPointerException");
  } catch (NullPointerException e) {
  }
 }
}
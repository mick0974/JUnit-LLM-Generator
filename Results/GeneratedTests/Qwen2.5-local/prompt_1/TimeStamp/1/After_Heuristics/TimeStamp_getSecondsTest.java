
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
 public void testGetSecondsTypicalCase() {
  long expectedSeconds = System.currentTimeMillis() / 1000;
  long actualSeconds = ts.getSeconds();
  assertEquals(expectedSeconds, actualSeconds);
 }
 @Test
 public void testGetSecondsEdgeCaseMinLong() {
  long minLong = Long.MIN_VALUE;
  ts = new TimeStamp(minLong);
  long expectedSeconds = minLong / 1000;
  long actualSeconds = ts.getSeconds();
  assertEquals(expectedSeconds, actualSeconds);
 }
 @Test
 public void testGetSecondsEdgeCaseMaxLong() {
  long maxLong = Long.MAX_VALUE;
  ts = new TimeStamp(maxLong);
  long expectedSeconds = maxLong / 1000;
  long actualSeconds = ts.getSeconds();
  assertEquals(expectedSeconds, actualSeconds);
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
 public void testGetSecondsNegativeNumber() {
  long negativeNumber = -123456789L;
  ts = new TimeStamp(negativeNumber);
  long expectedSeconds = negativeNumber / 1000;
  long actualSeconds = ts.getSeconds();
  assertEquals(expectedSeconds, actualSeconds);
 }
 @Test
 public void testGetSecondsPositiveNumber() {
  long positiveNumber = 123456789L;
  ts = new TimeStamp(positiveNumber);
  long expectedSeconds = positiveNumber / 1000;
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
 @Test
 public void testGetSecondsLargeNumber() {
  long largeNumber = Long.parseLong("1234567890123456789");
  ts = new TimeStamp(largeNumber);
  long expectedSeconds = largeNumber / 1000;
  long actualSeconds = ts.getSeconds();
  assertEquals(expectedSeconds, actualSeconds);
 }
 @Test
 public void testGetSecondsSmallNumber() {
  long smallNumber = -Long.parseLong("1234567890123456789");
  ts = new TimeStamp(smallNumber);
  long expectedSeconds = smallNumber / 1000;
  long actualSeconds = ts.getSeconds();
  assertEquals(expectedSeconds, actualSeconds);
 }
 @Test
 public void testGetSecondsBoundaryCondition() {
  long boundaryCondition = 1234567890L;
  ts = new TimeStamp(boundaryCondition);
  long expectedSeconds = boundaryCondition / 1000;
  long actualSeconds = ts.getSeconds();
  assertEquals(expectedSeconds, actualSeconds);
 }
}
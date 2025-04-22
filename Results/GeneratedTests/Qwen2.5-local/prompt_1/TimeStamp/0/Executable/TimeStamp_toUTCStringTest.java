// TimeStamp_toUTCStringTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Test class of TimeStamp.
 * It contains 10 unit test cases for the
 * TimeStamp#public toUTCString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_toUTCStringTest {
	private TimeStamp ts;
	private DateFormat formatter;

	@Before
	public void setUp() {
		formatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'", Locale.US);
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	@After
	public void tearDown() {
		formatter = null;
	}

	@Test
	public void testToUTCString_validTimestamp() {
		ts = new TimeStamp(1234567890L); // Thu Jan 01 00:00:00 UTC 1970
		assertEquals(formatter.format(ts.getDate()), ts.toUTCString());
	}

	@Test
	public void testToUTCString_negativeTimestamp() {
		ts = new TimeStamp(-1234567890L); // Wed Dec 31 16:23:21 UTC 1969
		assertEquals(formatter.format(ts.getDate()), ts.toUTCString());
	}

	@Test
	public void testToUTCString_maxLongTimestamp() {
		ts = new TimeStamp(Long.MAX_VALUE); // Mon Oct 15 21:31:59 UTC 292278994
		assertEquals(formatter.format(ts.getDate()), ts.toUTCString());
	}

	@Test
	public void testToUTCString_minLongTimestamp() {
		ts = new TimeStamp(Long.MIN_VALUE); // Sun Jul 21 02:47:14 UTC -292278994
		assertEquals(formatter.format(ts.getDate()), ts.toUTCString());
	}

	@Test
	public void testToUTCString_zeroTimestamp() {
		ts = new TimeStamp(0L); // Thu Jan 01 00:00:00 UTC 1970
		assertEquals(formatter.format(ts.getDate()), ts.toUTCString());
	}

	@Test
	public void testToUTCString_msb0baseTimestamp() {
		long msb0baseTime = 2085978496000L; // Tue Feb 07 06:28:16 UTC 2036
		ts = new TimeStamp(msb0baseTime);
		assertEquals(formatter.format(ts.getDate()), ts.toUTCString());
	}

	@Test
	public void testToUTCString_msb1baseTimestamp() {
		long msb1baseTime = -2208988800000L; // Thu Jan 01 01:00:00 UTC 1900
		ts = new TimeStamp(msb1baseTime);
		assertEquals(formatter.format(ts.getDate()), ts.toUTCString());
	}

	@Test(expected = NullPointerException.class)
	public void testToUTCString_nullTimestamp() {
		TimeStamp ts = null;
		ts.toUTCString();
	}

	@Test
	public void testToUTCString_largePositiveTimestamp() {
		long largePos = 9223372036854775807L; // Thu Nov 16 21:31:59 UTC 292278994
		ts = new TimeStamp(largePos);
		assertEquals(formatter.format(ts.getDate()), ts.toUTCString());
	}

	@Test
	public void testToUTCString_smallNegativeTimestamp() {
		long smallNeg = -9223372036854775807L; // Sun Jul 21 02:47:14 UTC -292278994
		ts = new TimeStamp(smallNeg);
		assertEquals(formatter.format(ts.getDate()), ts.toUTCString());
	}
}
// TimeStamp_getNtpTimeTest.java
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
 * TimeStamp#public static getNtpTime(long date) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getNtpTimeTest {
	private TimeStamp ts;
	private DateFormat formatter;

	@Before
	public void setUp() {
		ts = new TimeStamp(new Date().getTime());
		formatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS", Locale.US);
		formatter.setTimeZone(TimeZone.getDefault());
	}

	@After
	public void tearDown() {
		ts = null;
		formatter = null;
	}

	@Test
	public void testValidNtpTime() {
		long now = System.currentTimeMillis();
		TimeStamp ntptimestamp = TimeStamp.getNtpTime(now);
		assertNotEquals(ntptimestamp, null);
		assertEquals(formatter.format(ntptimestamp.getDate()), formatter.format(new Date(now)));
	}

	@Test(expected = NullPointerException.class)
	public void testNullInput() {
		TimeStamp.getNtpTime(null);
	}

	@Test
	public void testMinJavaTime() {
		long minJavaTime = -9223372036854775808L; // -Long.MAX_VALUE
		TimeStamp ntptimestamp = TimeStamp.getNtpTime(minJavaTime);
		assertNotEquals(ntptimestamp, null);
		assertEquals(formatter.format(ntptimestamp.getDate()), formatter.format(new Date(minJavaTime)));
	}

	@Test
	public void testMaxJavaTime() {
		long maxJavaTime = 9223372036854775807L; // Long.MAX_VALUE
		TimeStamp ntptimestamp = TimeStamp.getNtpTime(maxJavaTime);
		assertNotEquals(ntptimestamp, null);
		assertEquals(formatter.format(ntptimestamp.getDate()), formatter.format(new Date(maxJavaTime)));
	}

	@Test
	public void testNegativeJavaTime() {
		long negativeJavaTime = -1L;
		TimeStamp ntptimestamp = TimeStamp.getNtpTime(negativeJavaTime);
		assertNotEquals(ntptimestamp, null);
		assertEquals(formatter.format(ntptimestamp.getDate()), formatter.format(new Date(negativeJavaTime)));
	}

	@Test
	public void testZeroJavaTime() {
		long zeroJavaTime = 0L;
		TimeStamp ntptimestamp = TimeStamp.getNtpTime(zeroJavaTime);
		assertNotEquals(ntptimestamp, null);
		assertEquals(formatter.format(ntptimestamp.getDate()), formatter.format(new Date(zeroJavaTime)));
	}

	@Test
	public void testFutureJavaTime() {
		long futureJavaTime = 253402300799999L; // 292278994 at 23:59:59.999
		TimeStamp ntptimestamp = TimeStamp.getNtpTime(futureJavaTime);
		assertNotEquals(ntptimestamp, null);
		assertEquals(formatter.format(ntptimestamp.getDate()), formatter.format(new Date(futureJavaTime)));
	}

	@Test
	public void testPastJavaTime() {
		long pastJavaTime = -2208988800000L; // 1900-01-01 01:00:00
		TimeStamp ntptimestamp = TimeStamp.getNtpTime(pastJavaTime);
		assertNotEquals(ntptimestamp, null);
		assertEquals(formatter.format(ntptimestamp.getDate()), formatter.format(new Date(pastJavaTime)));
	}

	@Test
	public void testLeapSecondJavaTime() {
		long leapSecondJavaTime = 1262304000000L; // 2010-01-01 00:00:00 (leap second)
		TimeStamp ntptimestamp = TimeStamp.getNtpTime(leapSecondJavaTime);
		assertNotEquals(ntptimestamp, null);
		assertEquals(formatter.format(ntptimestamp.getDate()), formatter.format(new Date(leapSecondJavaTime)));
	}

	@Test
	public void testInvalidJavaTime() {
		long invalidJavaTime = 9223372036854775808L; // Out of range
		try {
			TimeStamp.getNtpTime(invalidJavaTime);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
}
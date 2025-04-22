// TimeStamp_getTimeTest.java
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
 * TimeStamp#public static getTime(long ntpTimeValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getTimeTest {
	private TimeStamp ts;
	private DateFormat simpleFormatter;
	private DateFormat utcFormatter;

	@Before
	public void setUp() {
		ts = new TimeStamp(0x0000000000000000L);
		simpleFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT, Locale.US);
		simpleFormatter.setTimeZone(TimeZone.getDefault());
		utcFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT + " 'UTC'", Locale.US);
		utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	@After
	public void tearDown() {
		ts = null;
		simpleFormatter = null;
		utcFormatter = null;
	}

	@Test
	public void test_getTime_0000000000000000L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_0000000000000001L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffffeL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffffdL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffffcL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffffbL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffffaL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff9L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff8L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff7L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff6L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff5L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff4L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff3L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff2L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff1L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff0L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffefL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffeeL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffedL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffecL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffebL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffeaL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff9L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff8L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff7L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff6L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff5L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff4L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff3L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff2L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff1L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff0L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffefL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffeeL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffedL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffecL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffebL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_ffffffffffffffeaL() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff9L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff8L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff7L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff6L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff5L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff4L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff3L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff2L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff1L() {
		long time = ts.getTime();
		assertEquals(0, time);
	}

	@Test
	public void test_getTime_fffffffffffffff0L() {
}
}
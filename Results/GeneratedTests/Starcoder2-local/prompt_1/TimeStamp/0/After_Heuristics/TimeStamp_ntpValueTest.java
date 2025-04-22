// TimeStamp_ntpValueTest.java
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
 * TimeStamp#public ntpValue() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_ntpValueTest {
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
	public void testNtpValue_0000000000000000L() {
		long ntpTime = 0x0000000000000000L;
		long expected = 0x0000000000000000L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_0000000000000001L() {
		long ntpTime = 0x0000000000000001L;
		long expected = 0x0000000000000001L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffeffL() {
		long ntpTime = 0xfffffffffffffeffL;
		long expected = 0xfffffffffffffeffL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffffeL() {
		long ntpTime = 0xfffffffffffffffeL;
		long expected = 0xfffffffffffffffeL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffffdL() {
		long ntpTime = 0xfffffffffffffffdL;
		long expected = 0xfffffffffffffffdL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffffcL() {
		long ntpTime = 0xfffffffffffffffcL;
		long expected = 0xfffffffffffffffcL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffffbL() {
		long ntpTime = 0xfffffffffffffffbL;
		long expected = 0xfffffffffffffffbL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffffaL() {
		long ntpTime = 0xfffffffffffffffaL;
		long expected = 0xfffffffffffffffaL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffff9L() {
		long ntpTime = 0xfffffffffffffff9L;
		long expected = 0xfffffffffffffff9L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffff8L() {
		long ntpTime = 0xfffffffffffffff8L;
		long expected = 0xfffffffffffffff8L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffff7L() {
		long ntpTime = 0xfffffffffffffff7L;
		long expected = 0xfffffffffffffff7L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffff6L() {
		long ntpTime = 0xfffffffffffffff6L;
		long expected = 0xfffffffffffffff6L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffff5L() {
		long ntpTime = 0xfffffffffffffff5L;
		long expected = 0xfffffffffffffff5L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffff4L() {
		long ntpTime = 0xfffffffffffffff4L;
		long expected = 0xfffffffffffffff4L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffff3L() {
		long ntpTime = 0xfffffffffffffff3L;
		long expected = 0xfffffffffffffff3L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffff2L() {
		long ntpTime = 0xfffffffffffffff2L;
		long expected = 0xfffffffffffffff2L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffff1L() {
		long ntpTime = 0xfffffffffffffff1L;
		long expected = 0xfffffffffffffff1L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_fffffffffffffff0L() {
		long ntpTime = 0xfffffffffffffff0L;
		long expected = 0xfffffffffffffff0L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_ffffffffffffffefL() {
		long ntpTime = 0xffffffffffffffefL;
		long expected = 0xffffffffffffffefL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_ffffffffffffffeeL() {
		long ntpTime = 0xffffffffffffffeeL;
		long expected = 0xffffffffffffffeeL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_ffffffffffffffedL() {
		long ntpTime = 0xffffffffffffffedL;
		long expected = 0xffffffffffffffedL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_ffffffffffffffecL() {
		long ntpTime = 0xffffffffffffffecL;
		long expected = 0xffffffffffffffecL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_ffffffffffffffebL() {
		long ntpTime = 0xffffffffffffffebL;
		long expected = 0xffffffffffffffebL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_ffffffffffffffeaL() {
		long ntpTime = 0xffffffffffffffeaL;
		long expected = 0xffffffffffffffeaL;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_ffffffffffffffe9L() {
		long ntpTime = 0xffffffffffffffe9L;
		long expected = 0xffffffffffffffe9L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_ffffffffffffffe8L() {
		long ntpTime = 0xffffffffffffffe8L;
		long expected = 0xffffffffffffffe8L;
		long actual = ts.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue_ffffffffffffffe7L() {
		long ntpTime = 0xffffffffffffffe7L;
}
}
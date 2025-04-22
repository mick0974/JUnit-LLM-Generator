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
	private TimeStamp ts1;
	private TimeStamp ts2;
	private TimeStamp ts3;
	private TimeStamp ts4;
	private TimeStamp ts5;
	private TimeStamp ts6;
	private TimeStamp ts7;
	private TimeStamp ts8;
	private TimeStamp ts9;
	private TimeStamp ts10;

	@Before
	public void setUp() {
		ts1 = new TimeStamp(0x00000000_00000000L); // 1900-01-01 00:00:00
		ts2 = new TimeStamp(0x00000000_00000001L); // 1900-01-01 00:00:00.001
		ts3 = new TimeStamp(0xFFFFFFFF_FFFFFFFFL); // 1900-01-01 23:59:59.999
		ts4 = new TimeStamp(0x00000001_00000000L); // 1900-01-02 00:00:00
		ts5 = new TimeStamp(0x00000001_00000001L); // 1900-01-02 00:00:00.001
		ts6 = new TimeStamp(0x7FFFFFFF_FFFFFFFFL); // 2036-02-07 06:28:15.999
		ts7 = new TimeStamp(0x7FFFFFFF_FFFFFFFCL); // 2036-02-07 06:28:15.998
		ts8 = new TimeStamp(0x80000000_00000000L); // 2036-02-07 06:28:16
		ts9 = new TimeStamp(0x80000000_00000001L); // 2036-02-07 06:28:16.001
		ts10 = new TimeStamp(0xFFFFFFFF_FFFFFFFEL); // 2036-02-07 06:28:15.998
	}

	@After
	public void tearDown() {
		ts1 = null;
		ts2 = null;
		ts3 = null;
		ts4 = null;
		ts5 = null;
		ts6 = null;
		ts7 = null;
		ts8 = null;
		ts9 = null;
		ts10 = null;
	}

	@Test
	public void testNtpValue1() {
		long expected = 0x00000000_00000000L;
		long actual = ts1.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue2() {
		long expected = 0x00000000_00000001L;
		long actual = ts2.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue3() {
		long expected = 0xFFFFFFFF_FFFFFFFFL;
		long actual = ts3.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue4() {
		long expected = 0x00000001_00000000L;
		long actual = ts4.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue5() {
		long expected = 0x00000001_00000001L;
		long actual = ts5.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue6() {
		long expected = 0x7FFFFFFF_FFFFFFFFL;
		long actual = ts6.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue7() {
		long expected = 0x7FFFFFFF_FFFFFFFCL;
		long actual = ts7.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue8() {
		long expected = 0x80000000_00000000L;
		long actual = ts8.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue9() {
		long expected = 0x80000000_00000001L;
		long actual = ts9.ntpValue();
		assertEquals(expected, actual);
	}

	@Test
	public void testNtpValue10() {
		long expected = 0xFFFFFFFF_FFFFFFFEL;
		long actual = ts10.ntpValue();
		assertEquals(expected, actual);
	}
}
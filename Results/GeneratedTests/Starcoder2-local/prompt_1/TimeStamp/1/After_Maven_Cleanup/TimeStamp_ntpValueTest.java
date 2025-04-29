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
	public void testNtpValue_ffffffffffffffe7L() {
		long ntpTime = 0xffffffffffffffe7L;
}
}
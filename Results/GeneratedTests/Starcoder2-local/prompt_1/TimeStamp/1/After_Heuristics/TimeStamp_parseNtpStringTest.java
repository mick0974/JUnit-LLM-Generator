// TimeStamp_parseNtpStringTest.java
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
 * TimeStamp#public static parseNtpString(String s) throws NumberFormatException  method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_parseNtpStringTest {
	private TimeStamp ts;
	private DateFormat simpleFormatter;
	private DateFormat utcFormatter;

	@Before
	public void setUp() throws Exception {
		simpleFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT, Locale.US);
		simpleFormatter.setTimeZone(TimeZone.getDefault());
		utcFormatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT + " 'UTC'", Locale.US);
		utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	@After
	public void tearDown() throws Exception {
		simpleFormatter = null;
		utcFormatter = null;
	}

	@Test
	public void testParseNtpString() {
		String s = "c1a089bd.fc904f6d";
		ts = TimeStamp.parseNtpString(s);
		assertEquals(s, ts.toString());
		assertEquals(s, TimeStamp.toString(ts.ntpValue()));
		assertEquals(s, ts.toDateString());
		assertEquals(s, ts.toUTCString());
		assertEquals(s, ts.toHexString());
		assertEquals(s, ts.toNtpString());
		assertEquals(s, ts.toNtpHexString());
		assertEquals(s, ts.toNtpHexString(true));
		assertEquals(s, ts.toNtpHexString(false));
		assertEquals(s, ts.toNtpHexString(true, true));
		assertEquals(s, ts.toNtpHexString(false, true));
		assertEquals(s, ts.toNtpHexString(true, false));
		assertEquals(s, ts.toNtpHexString(false, false));
		assertEquals(s, ts.toNtpHexString(true, true, true));
		assertEquals(s, ts.toNtpHexString(false, true, true));
		assertEquals(s, ts.toNtpHexString(true, false, true));
		assertEquals(s, ts.toNtpHexString(false, false, true));
		assertEquals(s, ts.toNtpHexString(true, true, false));
		assertEquals(s, ts.toNtpHexString(false, true, false));
		assertEquals(s, ts.toNtpHexString(true, false, false));
		assertEquals(s, ts.toNtpHexString(false, false, false));
		assertEquals(s, ts.toNtpHexString(true, true, true, true));
		assertEquals(s, ts.toNtpHexString(false, true, true, true));
		assertEquals(s, ts.toNtpHexString(true, false, true, true));
		assertEquals(s, ts.toNtpHexString(false, false, true, true));
		assertEquals(s, ts.toNtpHexString(true, true, false, true));
		assertEquals(s, ts.toNtpHexString(false, true, false, true));
		assertEquals(s, ts.toNtpHexString(true, false, false, true));
		assertEquals(s, ts.toNtpHexString(false, false, false, true));
		assertEquals(s, ts.toNtpHexString(true, true, true, false));
		assertEquals(s, ts.toNtpHexString(false, true, true, false));
		assertEquals(s, ts.toNtpHexString(true, false, true, false));
		assertEquals(s, ts.toNtpHexString(false, false, true, false));
		assertEquals(s, ts.toNtpHexString(true, true, false, false));
		assertEquals(s, ts.toNtpHexString(false, true, false, false));
		assertEquals(s, ts.toNtpHexString(true, false, false, false));
		assertEquals(s, ts.toNtpHexString(false, false, false, false));
		assertEquals(s, ts.toNtpHexString(true, true, true, true, true));
		assertEquals(s, ts.toNtpHexString(false, true, true, true, true));
		assertEquals(s, ts.toNtpHexString(true, false, true, true, true));
		assertEquals(s, ts.toNtpHexString(false, false, true, true, true));
		assertEquals(s, ts.toNtpHexString(true, true, false, true, true));
		assertEquals(s, ts.toNtpHexString(false, true, false, true, true));
		assertEquals(s, ts.toNtpHexString(true, false, false, true, true));
		assertEquals(s, ts.toNtpHexString(false, false, false, true, true));
		assertEquals(s, ts.toNtpHexString(true, true, true, false, true));
		assertEquals(s, ts.toNtpHexString(false, true, true, false, true));
		assertEquals(s, ts.toNtpHexString(true, false, true, false, true));
		assertEquals(s, ts.toNtpHexString(false, false, true, false, true));
		assertEquals(s, ts.toNtpHexString(true, true, false, false, true));
		assertEquals(s, ts.toNtpHexString(false, true, false, false, true));
		assertEquals(s, ts.toNtpHexString(true, false, false, false, true));
		assertEquals(s, ts.toNtpHexString(false, false, false, false, true));
		assertEquals(s, ts.toNtpHexString(true, true, true, true, false));
		assertEquals(s, ts.toNtpHexString(false, true, true, true, false));
		assertEquals(s, ts.toNtpHexString(true, false, true, true, false));
		assertEquals(s, ts.toNtpHexString(false, false, true, true, false));
		assertEquals(s, ts.toNtpHexString(true, true, false, true, false));
		assertEquals(s, ts.toNtpHexString(false, true, false, true, false));
		assertEquals(s, ts.toNtpHexString(true, false, false, true, false));
		assertEquals(s, ts.toNtpHexString(false, false, false, true, false));
		assertEquals(s, ts.toNtpHexString(true, true, true, false, false));
		assertEquals(s, ts.toNtpHexString(false, true, true, false, false));
		assertEquals(s, ts.toNtpHexString(true, false, true, false, false));
		assertEquals(s, ts.toNtpHexString(false, false, true, false, false));
		assertEquals(s, ts.toNtpHexString(true, true, false, false, false));
		assertEquals(s, ts.toNtpHexString(false, true, false, false, false));
		assertEquals(s, ts.toNtpHexString(true, false, false, false, false));
		assertEquals(s, ts.toNtpHexString(false, false, false, false, false));
		assertEquals(s, ts.toNtpHexString(true, true, true, true, true, true));
		assertEquals(s, ts.toNtpHexString(false, true, true, true, true, true));
		assertEquals(s, ts.toNtpHexString(true, false, true, true, true, true));
		assertEquals(s, ts.toNtpHexString(false, false, true, true, true, true));
		assertEquals(s, ts.toNtpHexString(true, true, false, true, true, true));
		assertEquals(s, ts.toNtpHexString(false, true, false, true, true, true));
		assertEquals(s, ts.toNtpHexString(true, false, false, true, true, true));
		assertEquals(s, ts.toNtpHexString(false, false, false, true, true, true));
		assertEquals(s, ts.toNtpHexString(true, true, true, false, true, true));
		assertEquals(s, ts.toNtpHexString(false, true, true, false, true, true));
		assertEquals(s, ts.toNtpHexString(true, false, true, false, true, true));
		assertEquals(s, ts.toNtpHexString(false, false, true, false, true, true));
		assertEquals(s, ts.toNtpHexString(true, true, false, false, true, true));
		assertEquals(s, ts.toNtpHexString(false, true, false, false, true, true));
		assertEquals(s, ts.toNtpHexString(true, false, false, false, true, true));
		assertEquals(s, ts.toNtpHexString(false, false, false, false, true, true));
		assertEquals(s, ts.toNtpHexString(true, true, true, true, false, true));
}
}
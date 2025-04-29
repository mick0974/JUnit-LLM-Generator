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

	@Before
	public void setUp() throws Exception {
		ts = new TimeStamp(new Date().getTime()); // create an instance of TimeStamp
	}

	@After
	public void tearDown() throws Exception {
		ts = null; // release resources
	}

	@Test(expected = NumberFormatException.class)
	public void testParseNtpStringWithNullInput() {
		TimeStamp.parseNtpString(null); // should throw NullPointerException
	}

	@Test(expected = NumberFormatException.class)
	public void testParseNtpStringWithEmptyInput() {
		TimeStamp.parseNtpString(""); // should throw IllegalArgumentException
	}

	@Test(expected = NumberFormatException.class)
	public void testParseNtpStringWithInvalidHexInput() {
		TimeStamp.parseNtpString("abc"); // should throw NumberFormatException
	}

	@Test(expected = NumberFormatException.class)
	public void testParseNtpStringWithIncorrectFormat() {
		TimeStamp.parseNtpString("c1a089bd-fc904f6d"); // should throw NumberFormatException
	}

	@Test
	public void testParseNtpStringWithValidInput() {
		String ntpHex = "c1a089bd.fc904f6d";
		TimeStamp parsedTs = TimeStamp.parseNtpString(ntpHex);
		long expectedTime = ts.getTime();
		long actualTime = parsedTs.getTime();
		assertEquals(expectedTime, actualTime);
	}

	@Test
	public void testParseNtpStringWithLeadingZeros() {
		String ntpHex = "00000000.00000000"; // leading zeros
		TimeStamp parsedTs = TimeStamp.parseNtpString(ntpHex);
		long expectedTime = 0;
		long actualTime = parsedTs.getTime();
		assertEquals(expectedTime, actualTime);
	}

	@Test
	public void testParseNtpStringWithTrailingZeros() {
		String ntpHex = "ffffffff.ffffffff"; // trailing zeros
		TimeStamp parsedTs = TimeStamp.parseNtpString(ntpHex);
		long expectedTime = Long.MAX_VALUE;
		long actualTime = parsedTs.getTime();
		assertEquals(expectedTime, actualTime);
	}

	@Test
	public void testParseNtpStringWithMixedCase() {
		String ntpHex = "C1A089BD.FC904F6D"; // mixed case
		TimeStamp parsedTs = TimeStamp.parseNtpString(ntpHex);
		long expectedTime = ts.getTime();
		long actualTime = parsedTs.getTime();
		assertEquals(expectedTime, actualTime);
	}

	@Test
	public void testParseNtpStringWithBoundaryValues() {
		String ntpHexMin = "00000000.00000000"; // boundary min
		String ntpHexMax = "ffffffff.ffffffff"; // boundary max
		TimeStamp parsedTsMin = TimeStamp.parseNtpString(ntpHexMin);
		TimeStamp parsedTsMax = TimeStamp.parseNtpString(ntpHexMax);
		long expectedTimeMin = 0;
		long expectedTimeMax = Long.MAX_VALUE;
		long actualTimeMin = parsedTsMin.getTime();
		long actualTimeMax = parsedTsMax.getTime();
	 assertEquals(expectedTimeMin, actualTimeMin);
	 assertEquals(expectedTimeMax, actualTimeMax);
	}

	@Test
	public void testParseNtpStringWithLargePositiveValues() {
		String ntpHex = "FFFFFFFF.FFFFFFFF"; // large positive values
		TimeStamp parsedTs = TimeStamp.parseNtpString(ntpHex);
		long expectedTime = Long.MAX_VALUE;
		long actualTime = parsedTs.getTime();
		assertEquals(expectedTime, actualTime);
	}
}
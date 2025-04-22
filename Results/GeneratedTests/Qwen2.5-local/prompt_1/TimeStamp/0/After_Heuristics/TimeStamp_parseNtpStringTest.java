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
	private TimeStamp timeStamp;

	@Before
	public void setUp() {
		timeStamp = new TimeStamp();
	}

	@After
	public void tearDown() {
		timeStamp = null;
	}

	@Test(expected = NumberFormatException.class)
	public void testParseNtpString_InvalidInput() throws NumberFormatException {
		timeStamp.parseNtpString(null);
	}

	@Test
	public void testParseNtpString_ValidInput() throws NumberFormatException {
		TimeStamp ts = timeStamp.parseNtpString("c1a089bd.fc904f6d");
		assertEquals(ts.getNtpTime().getTime(), 1038361309810L);
	}

	@Test
	public void testParseNtpString_EmptyInput() throws NumberFormatException {
		TimeStamp ts = timeStamp.parseNtpString("");
		assertEquals(ts.getNtpTime().getTime(), 0L);
	}

	@Test
	public void testParseNtpString_NoDecimalPoint() throws NumberFormatException {
		TimeStamp ts = timeStamp.parseNtpString("c1a089bd");
		assertEquals(ts.getNtpTime().getTime(), 1038361309L);
	}

	@Test
	public void testParseNtpString_FractionalPartZero() throws NumberFormatException {
		TimeStamp ts = timeStamp.parseNtpString("c1a089bd.00000000");
		assertEquals(ts.getNtpTime().getTime(), 1038361309000L);
	}

	@Test
	public void testParseNtpString_MaximumValue() throws NumberFormatException {
		TimeStamp ts = timeStamp.parseNtpString("ffffffff.ffffffff");
		assertEquals(ts.getNtpTime().getTime(), 9223372036854775807L);
	}

	@Test
	public void testParseNtpString_MinimumValue() throws NumberFormatException {
		TimeStamp ts = timeStamp.parseNtpString("00000000.00000000");
		assertEquals(ts.getNtpTime().getTime(), 0L);
	}

	@Test
	public void testParseNtpString_Base1900() throws NumberFormatException {
		TimeStamp ts = timeStamp.parseNtpString("00000000.00000001");
	 assertEquals(ts.getNtpTime().getTime(), 1000L);
	}

	@Test
	public void testParseNtpString_Base2036() throws NumberFormatException {
		TimeStamp ts = timeStamp.parseNtpString("80000000.00000001");
		assertEquals(ts.getNtpTime().getTime(), 1164447360000L);
	}

	@Test
	public void testParseNtpString_NegativeTime() throws NumberFormatException {
		TimeStamp ts = timeStamp.parseNtpString("-1.00000000");
	 assertEquals(ts.getNtpTime().getTime(), -1000L);
	}
}
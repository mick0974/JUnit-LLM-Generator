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






}
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

	@After
	public void tearDown() {
		timeStamp = null;
	}

	@Test(expected = NumberFormatException.class)
	public void testParseNtpString_InvalidInput() throws NumberFormatException {
		timeStamp.parseNtpString(null);
	}









}
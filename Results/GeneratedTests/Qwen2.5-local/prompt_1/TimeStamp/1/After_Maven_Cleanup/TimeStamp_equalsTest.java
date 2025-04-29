// TimeStamp_equalsTest.java
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
 * TimeStamp#public equals(Object obj) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_equalsTest {
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
		ts1 = new TimeStamp(0L); // 0
		ts2 = new TimeStamp(1L); // 1
		ts3 = new TimeStamp(-1L); // -1
		ts4 = new TimeStamp(Long.MAX_VALUE); // MaxLong
		ts5 = new TimeStamp(Long.MIN_VALUE); // MinLong
		ts6 = new TimeStamp(new Date().getTime()); // Current Date
		ts7 = new TimeStamp("c1a089bd.fc904f6d"); // Hex String
		ts8 = new TimeStamp("c1a089bd.fc904f6e"); // Hex String + 1
		ts9 = new TimeStamp("c1a089bd.fc904f6c"); // Hex String - 1
		ts10 = new TimeStamp("ffffffff.ffffffff"); // All F's
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
	public void testEqualsNull() {
		assertFalse(ts1.equals(null)); // ts1!= null
	}

	@Test
	public void testEqualsDifferentClass() {
		assertFalse(ts1.equals(new Object())); // Different Class
	}

	@Test
	public void testEqualsSameObject() {
		assertTrue(ts1.equals(ts1)); // Same Object
	}


	@Test
	public void testEqualsDifferentValues() {
		assertFalse(ts1.equals(ts3)); // Different Value
	}

	@Test
	public void testEqualsMaxLong() {
		assertFalse(ts4.equals(ts1)); // MaxLong!= 0
	}

	@Test
	public void testEqualsMinLong() {
		assertFalse(ts5.equals(ts1)); // MinLong!= 0
	}

	@Test
	public void testEqualsCurrentDate() {
	 assertFalse(ts6.equals(ts1)); // CurrentDate!= 0
	}


	@Test
	public void testEqualsAdjacentHexStrings() {
	 assertFalse(ts7.equals(ts8)); // HexString + 1!= HexString
	 assertFalse(ts7.equals(ts9)); // HexString - 1!= HexString
	}
}
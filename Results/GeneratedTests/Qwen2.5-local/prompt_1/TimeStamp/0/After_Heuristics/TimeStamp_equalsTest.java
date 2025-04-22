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
		ts1 = new TimeStamp(0);
		ts2 = new TimeStamp(1);
		ts3 = new TimeStamp(new Date().getTime());
		ts4 = new TimeStamp(ts3);
		ts5 = new TimeStamp(Long.MAX_VALUE);
		ts6 = new TimeStamp(Long.MIN_VALUE);
		ts7 = new TimeStamp(Long.MAX_VALUE - 1);
		ts8 = new TimeStamp(Long.MIN_VALUE + 1);
		ts9 = new TimeStamp("c1a089bd.fc904f6d"); // 2002-12-10T10:41:49.986Z
		ts10 = new TimeStamp("c1a089bd.fc904f6d"); // 2002-12-10T10:41:49.986Z
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testEquals_sameObject() {
		assertTrue(ts1.equals(ts1));
	}

	@Test
	public void testEquals_differentObjects() {
		assertFalse(ts1.equals(ts2));
	}

	@Test
	public void testEquals_nullObject() {
		assertFalse(ts1.equals(null));
	}

	@Test
	public void testEquals_anotherClass() {
		assertFalse(ts1.equals(new Object()));
	}

	@Test
	public void testEquals_identicalValues() {
		assertTrue(ts3.equals(ts4));
	}

	@Test
	public void testEquals_maxValue() {
		assertTrue(ts5.equals(ts5));
	}

	@Test
	public void testEquals_minValue() {
		assertTrue(ts6.equals(ts6));
	}

	@Test
	public void testEquals_nearMaxValue() {
		assertTrue(ts7.equals(ts7));
	}

	@Test
	public void testEquals_nearMinValue() {
		assertTrue(ts8.equals(ts8));
	}

	@Test
	public void testEquals_equivalentStrings() {
		assertTrue(ts9.equals(ts10));
	}
}
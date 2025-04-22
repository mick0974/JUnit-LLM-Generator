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
		ts2 = new TimeStamp(0);
		ts3 = new TimeStamp(0);
		ts4 = new TimeStamp(0);
		ts5 = new TimeStamp(0);
		ts6 = new TimeStamp(0);
		ts7 = new TimeStamp(0);
		ts8 = new TimeStamp(0);
		ts9 = new TimeStamp(0);
		ts10 = new TimeStamp(0);
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
	public void test_equals_01() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(null);

		// Assert
		assertFalse(result);
	}

	@Test
	public void test_equals_02() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(new Object());

		// Assert
		assertFalse(result);
	}

	@Test
	public void test_equals_03() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_04() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts1);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_05() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts2);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_06() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts3);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_07() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts4);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_08() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts5);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_09() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts6);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_10() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts7);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_11() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts8);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_12() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts9);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_13() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts10);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_14() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts1);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_15() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts2);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_16() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts3);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_17() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts4);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_18() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts5);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_19() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
		boolean result = ts.equals(ts6);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_equals_20() {
		// Arrange
		long ntpTime = 0x0000000000000000L;
		TimeStamp ts = new TimeStamp(ntpTime);

		// Act
}
}
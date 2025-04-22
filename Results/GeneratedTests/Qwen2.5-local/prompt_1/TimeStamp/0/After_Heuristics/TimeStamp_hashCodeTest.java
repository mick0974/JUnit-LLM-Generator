// TimeStamp_hashCodeTest.java
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
 * TimeStamp#public hashCode() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_hashCodeTest {
	
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
		ts1 = new TimeStamp(123456789L);
		ts2 = new TimeStamp(987654321L);
		ts3 = new TimeStamp(ts1); // same content as ts1
		ts4 = new TimeStamp(ts1.getTime()); // same content as ts1
		ts5 = new TimeStamp(ts1.getDate()); // same content as ts1
		ts6 = new TimeStamp(0L);
		ts7 = new TimeStamp(Long.MAX_VALUE);
		ts8 = new TimeStamp(-Long.MAX_VALUE);
		ts9 = new TimeStamp(Long.MIN_VALUE);
		ts10 = new TimeStamp(-1L);
	}

	@After
	public void tearDown() {
		// Cleanup here if necessary
	}

	@Test
	public void testHashCode_sameObject() {
		// Arrange
		// Act
		int result = ts1.hashCode();

		// Assert
		assertEquals(result, ts1.hashCode());
	}

	@Test
	public void testHashCode_differentObjects() {
		// Arrange
		// Act
		int result = ts1.hashCode();

		// Assert
		assertNotEquals(result, ts2.hashCode());
	}

	@Test
	public void testHashCode_equalObjects() {
		// Arrange
		// Act
		int result = ts1.hashCode();

		// Assert
	 assertEquals(result, ts3.hashCode());
	 assertEquals(result, ts4.hashCode());
	 assertEquals(result, ts5.hashCode());
	}

	@Test
	public void testHashCode_zero() {
		// Arrange
		// Act
		int result = ts6.hashCode();

		// Assert
	 assertEquals(0, result);
	}

	@Test
	public void testHashCode_maxValue() {
		// Arrange
		// Act
		int result = ts7.hashCode();

		// Assert
	 assertNotEquals(0, result);
	}

	@Test
	public void testHashCode_minValue() {
		// Arrange
		// Act
		int result = ts9.hashCode();

		// Assert
	 assertNotEquals(0, result);
	}

	@Test
	public void testHashCode_negativeValue() {
		// Arrange
		// Act
		int result = ts10.hashCode();

		// Assert
	 assertNotEquals(0, result);
	}

	@Test
	public void testHashCode_largePositiveValue() {
		// Arrange
		// Act
		int result = ts7.hashCode();

		// Assert
	 assertNotEquals(0, result);
	}

	@Test
	public void testHashCode_largeNegativeValue() {
		// Arrange
		// Act
		int result = ts8.hashCode();

		// Assert
	 assertNotEquals(0, result);
	}
}
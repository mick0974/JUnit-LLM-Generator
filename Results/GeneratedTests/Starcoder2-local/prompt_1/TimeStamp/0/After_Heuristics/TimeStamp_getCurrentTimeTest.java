// TimeStamp_getCurrentTimeTest.java
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
 * TimeStamp#public static getCurrentTime() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getCurrentTimeTest {
	private TimeStamp ts;
	private DateFormat simpleFormatter;
	private DateFormat utcFormatter;

	@Before
	public void setUp() {
		ts = TimeStamp.getCurrentTime();
	}

	@After
	public void tearDown() {
		ts = null;
	}

	@Test
	public void test_getCurrentTime_01() {
		// Arrange
		long expected = System.currentTimeMillis();

		// Act
		long actual = ts.getTime();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void test_getCurrentTime_02() {
		// Arrange
		long expected = System.currentTimeMillis();

		// Act
		long actual = ts.getTime();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void test_getCurrentTime_03() {
		// Arrange
		long expected = System.currentTimeMillis();

		// Act
		long actual = ts.getTime();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void test_getCurrentTime_04() {
		// Arrange
		long expected = System.currentTimeMillis();

		// Act
		long actual = ts.getTime();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void test_getCurrentTime_05() {
		// Arrange
		long expected = System.currentTimeMillis();

		// Act
		long actual = ts.getTime();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void test_getCurrentTime_06() {
		// Arrange
		long expected = System.currentTimeMillis();

		// Act
		long actual = ts.getTime();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void test_getCurrentTime_07() {
		// Arrange
		long expected = System.currentTimeMillis();

		// Act
		long actual = ts.getTime();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void test_getCurrentTime_08() {
		// Arrange
		long expected = System.currentTimeMillis();

		// Act
		long actual = ts.getTime();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void test_getCurrentTime_09() {
		// Arrange
		long expected = System.currentTimeMillis();

		// Act
		long actual = ts.getTime();

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void test_getCurrentTime_10() {
		// Arrange
		long expected = System.currentTimeMillis();

		// Act
		long actual = ts.getTime();

		// Assert
		assertEquals(expected, actual);
	}
}
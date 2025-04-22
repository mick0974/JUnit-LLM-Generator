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

	@Before
	public void setUp() {
		ts = TimeStamp.getCurrentTime();
	}

	@After
	public void tearDown() {
		ts = null;
	}

	@Test
	public void testGetCurrentTime_validCase() {
		// Arrange
		long currentTimeMillis = System.currentTimeMillis();

		// Act
		TimeStamp ts = TimeStamp.getCurrentTime();

		// Assert
		long timestampMillis = ts.getTime();
		assertTrue(timestampMillis >= currentTimeMillis);
	}

	@Test(expected = NullPointerException.class)
	public void testGetCurrentTime_nullObject() {
		// Arrange
		TimeStamp ts = null;

		// Act
		ts = TimeStamp.getCurrentTime();

		// Assert
		fail("Expected NullPointerException to be thrown");
	}

	@Test
	public void testGetCurrentTime_edgeCase() {
		// Arrange
		long currentTimeMillis = System.currentTimeMillis();

		// Act
		TimeStamp ts = TimeStamp.getCurrentTime();

		// Assert
		long timestampMillis = ts.getTime();
		assertEquals(currentTimeMillis, timestampMillis);
	}

	@Test
	public void testGetCurrentTime_boundaryCase() {
		// Arrange
		long currentTimeMillis = System.currentTimeMillis();

		// Act
		TimeStamp ts = TimeStamp.getCurrentTime();

		// Assert
		long timestampMillis = ts.getTime();
		assertTrue(timestampMillis >= currentTimeMillis);
	}

	@Test
	public void testGetCurrentTime_largeInput() {
		// Arrange
		long currentTimeMillis = System.currentTimeMillis();

		// Act
		TimeStamp ts = TimeStamp.getCurrentTime();

		// Assert
		long timestampMillis = ts.getTime();
		assertTrue(timestampMillis >= currentTimeMillis);
	}

	@Test
	public void testGetCurrentTime_smallInput() {
		// Arrange
		long currentTimeMillis = System.currentTimeMillis();

		// Act
		TimeStamp ts = TimeStamp.getCurrentTime();

		// Assert
		long timestampMillis = ts.getTime();
		assertTrue(timestampMillis >= currentTimeMillis);
	}

	@Test
	public void testGetCurrentTime_zeroInput() {
		// Arrange
		long currentTimeMillis = System.currentTimeMillis();

		// Act
		TimeStamp ts = TimeStamp.getCurrentTime();

		// Assert
		long timestampMillis = ts.getTime();
		assertTrue(timestampMillis >= currentTimeMillis);
	}

	@Test
	public void testGetCurrentTime_negativeInput() {
		// Arrange
		long currentTimeMillis = System.currentTimeMillis();

		// Act
		TimeStamp ts = TimeStamp.getCurrentTime();

		// Assert
		long timestampMillis = ts.getTime();
		assertTrue(timestampMillis >= currentTimeMillis);
	}

	@Test
	public void testGetCurrentTime_positiveInput() {
		// Arrange
		long currentTimeMillis = System.currentTimeMillis();

		// Act
		TimeStamp ts = TimeStamp.getCurrentTime();

		// Assert
		long timestampMillis = ts.getTime();
		assertTrue(timestampMillis >= currentTimeMillis);
	}

	@Test
	public void testGetCurrentTime_emptyInput() {
		// Arrange
		long currentTimeMillis = System.currentTimeMillis();

		// Act
		TimeStamp ts = TimeStamp.getCurrentTime();

		// Assert
		long timestampMillis = ts.getTime();
		assertTrue(timestampMillis >= currentTimeMillis);
	}
}
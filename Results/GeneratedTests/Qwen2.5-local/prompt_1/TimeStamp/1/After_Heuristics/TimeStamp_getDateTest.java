// TimeStamp_getDateTest.java
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
 * TimeStamp#public getDate() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getDateTest {
	private TimeStamp timeStamp;
	private Date expectedDate;
	private DateFormat formatter;

	@Before
	public void setUp() {
		expectedDate = new Date();
		timeStamp = new TimeStamp(expectedDate);
		formatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS zzz",
				Locale.US);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	@After
	public void tearDown() {
		expectedDate = null;
		timeStamp = null;
		formatter = null;
	}

	@Test
	public void testGetDate_validDate() {
		// Arrange
		Date actualDate = timeStamp.getDate();

		// Act
		String expectedFormattedDate = formatter.format(expectedDate);
		String actualFormattedDate = formatter.format(actualDate);

		// Assert
		assertEquals(expectedFormattedDate, actualFormattedDate);
	}

	@Test
	public void testGetDate_nullDate() {
		// Arrange
		timeStamp = new TimeStamp(null);

		// Act
		Date actualDate = timeStamp.getDate();

		// Assert
	 assertNull(actualDate);
	}

	@Test
	public void testGetDate_pastDate() {
		// Arrange
		expectedDate.setYear(1990); // Set year to 1990
		timeStamp = new TimeStamp(expectedDate);

		// Act
		actualDate = timeStamp.getDate();

		// Assert
		String expectedFormattedDate = formatter.format(expectedDate);
		String actualFormattedDate = formatter.format(actualDate);
		assertEquals(expectedFormattedDate, actualFormattedDate);
	}

	@Test
	public void testGetDate_futureDate() {
		// Arrange
		expectedDate.setYear(2025); // Set year to 2025
		timeStamp = new TimeStamp(expectedDate);

		// Act
		actualDate = timeStamp.getDate();

		// Assert
		String expectedFormattedDate = formatter.format(expectedDate);
		String actualFormattedDate = formatter.format(actualDate);
		assertEquals(expectedFormattedDate, actualFormattedDate);
	}

	@Test
	public void testGetDate_maxLongDate() {
		// Arrange
		expectedDate.setTime(Long.MAX_VALUE);
		timeStamp = new TimeStamp(expectedDate);

		// Act
		actualDate = timeStamp.getDate();

		// Assert
		String expectedFormattedDate = formatter.format(expectedDate);
		String actualFormattedDate = formatter.format(actualDate);
		assertEquals(expectedFormattedDate, actualFormattedDate);
	}

	@Test
	public void testGetDate_minLongDate() {
		// Arrange
		expectedDate.setTime(Long.MIN_VALUE);
		timeStamp = new TimeStamp(expectedDate);

		// Act
		actualDate = timeStamp.getDate();

		// Assert
		String expectedFormattedDate = formatter.format(expectedDate);
		String actualFormattedDate = formatter.format(actualDate);
		assertEquals(expectedFormattedDate, actualFormattedDate);
	}

	@Test
	public void testGetDate_sameAsCurrentDate() {
		// Arrange
		expectedDate.setTime(System.currentTimeMillis());
		timeStamp = new TimeStamp(expectedDate);

		// Act
		actualDate = timeStamp.getDate();

		// Assert
		String expectedFormattedDate = formatter.format(expectedDate);
		String actualFormattedDate = formatter.format(actualDate);
		assertEquals(expectedFormattedDate, actualFormattedDate);
	}

	@Test
	public void testGetDate_negativeDate() {
		// Arrange
		expectedDate.setTime(-1000); // Set time to negative 1000 milliseconds
		timeStamp = new TimeStamp(expectedDate);

		// Act
		actualDate = timeStamp.getDate();

		// Assert
		String expectedFormattedDate = formatter.format(expectedDate);
		String actualFormattedDate = formatter.format(actualDate);
		assertEquals(expectedFormattedDate, actualFormattedDate);
	}

	@Test
	public void testGetDate_largePositiveDate() {
		// Arrange
		expectedDate.setTime(1000000000000L); // Set time to large positive value
		timeStamp = new TimeStamp(expectedDate);

		// Act
		actualDate = timeStamp.getDate();

		// Assert
		String expectedFormattedDate = formatter.format(expectedDate);
		String actualFormattedDate = formatter.format(actualDate);
		assertEquals(expectedFormattedDate, actualFormattedDate);
	}

	@Test
	public void testGetDate_zeroDate() {
		// Arrange
		expectedDate.setTime(0); // Set time to zero
		timeStamp = new TimeStamp(expectedDate);

		// Act
		actualDate = timeStamp.getDate();

		// Assert
		String expectedFormattedDate = formatter.format(expectedDate);
		String actualFormattedDate = formatter.format(actualDate);
		assertEquals(expectedFormattedDate, actualFormattedDate);
	}
}
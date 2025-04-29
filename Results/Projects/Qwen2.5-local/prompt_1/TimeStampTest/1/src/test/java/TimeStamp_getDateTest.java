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









}
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
	private DateFormat simpleFormatter;
	private DateFormat utcFormatter;

	@Before
	public void setUp() {
		simpleFormatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS",
				Locale.US);
		simpleFormatter.setTimeZone(TimeZone.getDefault());

		utcFormatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'",
				Locale.US);
		utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	@After
	public void tearDown() {
		simpleFormatter = null;
		utcFormatter = null;
	}

	@Test
	public void testGetCurrentTime_valid() {
		TimeStamp currentTime = TimeStamp.getCurrentTime();
		assertNotNull(currentTime);
	}

	@Test(expected = NullPointerException.class)
	public void testGetCurrentTime_null() {
		TimeStamp.setCurrentTime(null);
	}

	@Test
	public void testGetCurrentTime_formatSimple() {
		TimeStamp currentTime = TimeStamp.getCurrentTime();
		String formattedTime = simpleFormatter.format(new Date());
		assertEquals(formattedTime, currentTime.toDateString());
	}

	@Test
	public void testGetCurrentTime_formatUtc() {
		TimeStamp currentTime = TimeStamp.getCurrentTime();
		String formattedTime = utcFormatter.format(new Date());
		assertEquals(formattedTime, currentTime.toUTCString());
	}

	@Test
	public void testGetCurrentTime_compareTo() {
		TimeStamp currentTime = TimeStamp.getCurrentTime();
		TimeStamp otherTime = TimeStamp.getCurrentTime();

		assertTrue(currentTime.compareTo(otherTime) == 0);
	}

	@Test
	public void testGetCurrentTime_setAndGet() {
		TimeStamp originalTime = TimeStamp.getCurrentTime();
		TimeStamp setTime = TimeStamp.getNtpTime(originalTime.getTime());
		TimeStamp retrievedTime = TimeStamp.getCurrentTime();

	 assertEquals(setTime, retrievedTime);
	}

	@Test
	public void testGetCurrentTime_negativeTime() {
		TimeStamp negativeTime = TimeStamp.getNtpTime(-1L);
		assertNotEquals(negativeTime, TimeStamp.getCurrentTime());
	}

	@Test
	public void testGetCurrentTime_maxLongTime() {
		TimeStamp maxLongTime = TimeStamp.getNtpTime(Long.MAX_VALUE);
		assertNotEquals(maxLongTime, TimeStamp.getCurrentTime());
	}

	@Test
	public void testGetCurrentTime_minLongTime() {
		TimeStamp minLongTime = TimeStamp.getNtpTime(Long.MIN_VALUE);
		assertNotEquals(minLongTime, TimeStamp.getCurrentTime());
	}
}
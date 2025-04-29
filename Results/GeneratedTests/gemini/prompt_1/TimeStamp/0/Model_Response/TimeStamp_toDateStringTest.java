```java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Test class for TimeStamp#toDateString().
 *
 * This class tests the formatting of TimeStamp objects into a date string
 * using the default time zone. To ensure consistent results regardless of the
 * execution environment, the default time zone is temporarily set to UTC
 * during test execution.
 *
 * It contains 10 unit test cases covering typical and edge scenarios.
 * Follows the Arrange-Act-Assert pattern.
 */
public class TimeStamp_toDateStringTest {

	private TimeZone originalDefaultTimeZone;
	private SimpleDateFormat expectedFormat;

	/**
	 * Sets up the test environment before each test.
	 * Stores the original default time zone and sets the default time zone to UTC
	 * for predictable test results. Initializes the expected date format.
	 */
	@Before
	public void setUp() {
		originalDefaultTimeZone = TimeZone.getDefault();
		// Set default timezone to UTC for consistent test results
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		// Define the expected format, matching TimeStamp.NTP_DATE_FORMAT but in UTC
		expectedFormat = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT, Locale.US);
		expectedFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Explicitly UTC
	}

	/**
	 * Tears down the test environment after each test.
	 * Restores the original default time zone.
	 */
	@After
	public void tearDown() {
		TimeZone.setDefault(originalDefaultTimeZone);
	}

	/**
	 * Helper method to get the expected date string for a given Date object.
	 * @param date The date to format.
	 * @return The formatted date string according to NTP_DATE_FORMAT in UTC.
	 */
	private String getExpectedDateString(Date date) {
		return expectedFormat.format(date);
	}

	/**
	 * Helper method to create a Date object from a UTC date string.
	 * @param dateString The UTC date string (e.g., "2002-12-10T10:41:49.986Z").
	 * @return The corresponding Date object.
	 * @throws ParseException if the date string is invalid.
	 */
	private Date createUtcDate(String dateString) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		parser.setTimeZone(TimeZone.getTimeZone("UTC"));
		return parser.parse(dateString);
	}

	// --- Test Cases ---

	/**
	 * Test case for a date before the 2036 wrap-around (MSB=1).
	 * Uses a specific date: Tue, Dec 10 2002 10:41:49.986 UTC.
	 * Corresponds to NTP hex string c1a089bd.fc904f6d.
	 */
	@Test
	public void testDateStringBefore2036() throws ParseException {
		// Arrange
		String hexStamp = "c1a089bd.fc904f6d";
		TimeStamp ts = new TimeStamp(hexStamp);
		Date expectedDate = createUtcDate("2002-12-10T10:41:49.986Z"); // Known equivalent
		String expectedString = getExpectedDateString(expectedDate);
		// Note: Minor rounding differences might occur between direct Date creation
		// and NTP conversion. Let's derive expected string from the TimeStamp itself.
		expectedString = "Tue, Dec 10 2002 10:41:49.986";


		// Act
		String actualString = ts.toDateString();

		// Assert
		assertEquals(expectedString, actualString);
	}

	/**
	 * Test case for a date after the 2036 wrap-around (MSB=0).
	 * Uses date: Fri, Mar 01 2040 12:00:00.000 UTC
	 */
	@Test
	public void testDateStringAfter2036() throws ParseException {
		// Arrange
		Date date = createUtcDate("2040-03-01T12:00:00.000Z");
		TimeStamp ts = new TimeStamp(date);
		String expectedString = getExpectedDateString(date); // "Fri, Mar 01 2040 12:00:00.000"

		// Act
		String actualString = ts.toDateString();

		// Assert
		assertEquals(expectedString, actualString);
	}

	/**
	 * Test case for the Java epoch (Jan 1, 1970 00:00:00.000 UTC).
	 */
	@Test
	public void testDateStringJavaEpoch() throws ParseException {
		// Arrange
		Date date = new Date(0); // Java epoch
		TimeStamp ts = new TimeStamp(date);
		String expectedString = getExpectedDateString(date); // "Thu, Jan 01 1970 00:00:00.000"

		// Act
		String actualString = ts.toDateString();

		// Assert
		assertEquals(expectedString, actualString);
	}

	/**
	 * Test case for the NTP MSB=1 base time (Jan 1, 1900 00:00:00.000 UTC).
	 * Corresponds to Java time -2208988800000L.
	 */
	@Test
	public void testDateStringNtpEpoch1900() throws ParseException {
		// Arrange
		long ntpBase1TimeMillis = -2208988800000L; // Corresponds to 1 Jan 1900 00:00:00 UTC
		Date date = new Date(ntpBase1TimeMillis);
		TimeStamp ts = new TimeStamp(date);
		// Verify internal NTP value has MSB set
        assertEquals(0x80000000L, (ts.ntpValue() >>> 32) & 0x80000000L);
        assertEquals(0x8000000000000000L, ts.ntpValue()); // Seconds=0x80000000, Fraction=0
		String expectedString = getExpectedDateString(date); // "Mon, Jan 01 1900 00:00:00.000"

		// Act
		String actualString = ts.toDateString();

		// Assert
		assertEquals(expectedString, actualString);
	}

	/**
	 * Test case for the NTP MSB=0 base time (Feb 7, 2036 06:28:16.000 UTC).
	 * Corresponds to NTP timestamp 0L and Java time 2085978496000L.
	 */
	@Test
	public void testDateStringNtpEpoch2036() throws ParseException {
		// Arrange
		long ntpBase0TimeMillis = 2085978496000L; // Corresponds to 7 Feb 2036 06:28:16 UTC
		Date date = new Date(ntpBase0TimeMillis);
		TimeStamp ts = new TimeStamp(date);
		// Verify internal NTP value is 0 with MSB clear
        assertEquals(0L, (ts.ntpValue() >>> 32) & 0x80000000L);
        assertEquals(0L, ts.ntpValue()); // Seconds=0, Fraction=0
		String expectedString = getExpectedDateString(date); // "Thu, Feb 07 2036 06:28:16.000"

		// Act
		String actualString = ts.toDateString();

		// Assert
		assertEquals(expectedString, actualString);
	}

	/**
	 * Test case for a TimeStamp created with NTP value 0L.
	 * This should represent the MSB=0 base time (Feb 7, 2036 06:28:16.000 UTC).
	 */
	@Test
	public void testDateStringFromZeroNtpTime() throws ParseException {
		// Arrange
		TimeStamp ts = new TimeStamp(0L);
		long ntpBase0TimeMillis = 2085978496000L;
		Date expectedDate = new Date(ntpBase0TimeMillis);
		String expectedString = getExpectedDateString(expectedDate); // "Thu, Feb 07 2036 06:28:16.000"

		// Act
		String actualString = ts.toDateString();

		// Assert
		assertEquals(expectedString, actualString);
		assertEquals(ntpBase0TimeMillis, ts.getTime());
	}

	/**
	 * Test case for a TimeStamp created from a null Date object.
	 * This should result in an NTP value of 0L, representing the MSB=0 base time.
	 */
	@Test
	public void testDateStringFromNullDate() throws ParseException {
		// Arrange
		TimeStamp ts = new TimeStamp((Date) null);
		long ntpBase0TimeMillis = 2085978496000L;
		Date expectedDate = new Date(ntpBase0TimeMillis);
		String expectedString = getExpectedDateString(expectedDate); // "Thu, Feb 07 2036 06:28:16.000"

		// Act
		String actualString = ts.toDateString();

		// Assert
		assertEquals(0L, ts.ntpValue());
		assertEquals(expectedString, actualString);
	}

	/**
	 * Test case for a time just before the 2036 boundary.
	 * One millisecond before Feb 7, 2036 06:28:16.000 UTC.
	 */
	@Test
	public void testDateStringJustBefore2036Boundary() throws ParseException {
		// Arrange
		long ntpBase0TimeMillis = 2085978496000L;
		Date date = new Date(ntpBase0TimeMillis - 1); // One ms before boundary
		TimeStamp ts = new TimeStamp(date);
		String expectedString = getExpectedDateString(date); // "Thu, Feb 07 2036 06:28:15.999"
		// Check that MSB is set
        assertTrue("Timestamp before 2036 boundary should use base 1", (ts.ntpValue() >>> 63) == 1);

		// Act
		String actualString = ts.toDateString();

		// Assert
		assertEquals(expectedString, actualString);
	}

    /**
	 * Test case for a time just after the 2036 boundary.
	 * One millisecond after Feb 7, 2036 06:28:16.000 UTC.
	 */
	@Test
	public void testDateStringJustAfter2036Boundary() throws ParseException {
		// Arrange
		long ntpBase0TimeMillis = 2085978496000L;
		Date date = new Date(ntpBase0TimeMillis + 1); // One ms after boundary start
		TimeStamp ts = new TimeStamp(date);
		String expectedString = getExpectedDateString(date); // "Thu, Feb 07 2036 06:28:16.001"
		// Check that MSB is clear
        assertTrue("Timestamp after 2036 boundary should use base 0", (ts.ntpValue() >>> 63) == 0);

		// Act
		String actualString = ts.toDateString();

		// Assert
		assertEquals(expectedString, actualString);
	}


	/**
	 * Test case using a known timestamp from RFC-1305 example (adjusted).
	 * Original example is slightly different, using a known valid hex string.
	 * Use "c1a089bd.fc904f6d" again to focus on formatting consistency.
	 */
	@Test
	public void testDateStringKnownHexValue() throws ParseException {
		// Arrange
		String hexStamp = "c1a089bd.fc904f6d"; // Tue, Dec 10 2002 10:41:49.986 UTC
		TimeStamp ts = TimeStamp.parseNtpString(hexStamp);
        Date expectedDate = createUtcDate("2002-12-10T10:41:49.986Z"); // Known equivalent
        String expectedString = getExpectedDateString(expectedDate); // "Tue, Dec 10 2002 10:41:49.986"

		// Act
		String actualString = ts.toDateString();

		// Assert
		assertEquals(expectedString, actualString);
	}
}
```
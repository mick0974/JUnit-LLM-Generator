// TimeStamp_compareToTest.java
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
 * TimeStamp#public compareTo(TimeStamp anotherTimeStamp) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_compareToTest {
	private TimeStamp ts1;
	private TimeStamp ts2;
	private TimeStamp ts3;

	@Before
	public void setUp() {
		ts1 = new TimeStamp("c1a089bd.fc904f6d"); // Tue, Dec 10 2002 10:41:49.986
		ts2 = new TimeStamp("c1a089bd.fc904f6e"); // Tue, Dec 10 2002 10:41:49.987
		ts3 = new TimeStamp("c1a089bd.fc904f6c"); // Tue, Dec 10 2002 10:41:49.985
	}

	@After
	public void tearDown() {
		ts1 = null;
		ts2 = null;
		ts3 = null;
	}

	@Test
	public void testCompareToEqual() {
		assertEquals(0, ts1.compareTo(ts1)); // Compare to itself
	}

	@Test
	public void testCompareToLessThan() {
		assertTrue(ts1.compareTo(ts3) < 0); // ts1 should be less than ts3
	}

	@Test
	public void testCompareToGreaterThan() {
		assertTrue(ts2.compareTo(ts1) > 0); // ts2 should be greater than ts1
	}

	@Test
	public void testCompareToNull() {
		assertThrows(NullPointerException.class, () -> {
			ts1.compareTo(null); // Should throw NullPointerException
		});
	}

	@Test
	public void testCompareToDifferentTimeZone() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",
				Locale.US);
	(formatter.setTimeZone(TimeZone.getTimeZone("UTC")));
	formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));

		TimeStamp tsUTC = new TimeStamp(formatter.parse("2022-10-01T12:00:00.000+0000").getTime());
		TimeStamp tsNYC = new TimeStamp(formatter.parse("2022-10-01T08:00:00.000-0400").getTime());

		assertTrue(tsUTC.compareTo(tsNYC) > 0); // UTC should be greater than NYC
	}

	@Test
	public void testCompareToSameSecondButDifferentFraction() {
		TimeStamp ts1 = new TimeStamp("c1a089bd.fc904f6d"); // Tue, Dec 10 2002 10:41:49.986
		TimeStamp ts2 = new TimeStamp("c1a089bd.fd000000"); // Tue, Dec 10 2002 10:41:50.000

		assertTrue(ts1.compareTo(ts2) < 0); // ts1 should be less than ts2
	}

	@Test
	public void testCompareToSameFractionButDifferentSecond() {
		TimeStamp ts1 = new TimeStamp("c1a089bd.fc904f6d"); // Tue, Dec 10 2002 10:41:49.986
		TimeStamp ts2 = new TimeStamp("c1a089be.fc904f6d"); // Tue, Dec 10 2002 10:42:00.000

		assertTrue(ts1.compareTo(ts2) < 0); // ts1 should be less than ts2
	}

	@Test
	public void testCompareToMaxLong() {
		TimeStamp maxTs = new TimeStamp(Long.MAX_VALUE);
		TimeStamp minTs = new TimeStamp(Long.MIN_VALUE);

		assertTrue(maxTs.compareTo(minTs) > 0); // MaxLong should be greater than MinLong
	}

	@Test
	public void testCompareToMinLong() {
		TimeStamp maxTs = new TimeStamp(Long.MAX_VALUE);
		TimeStamp minTs = new TimeStamp(Long.MIN_VALUE);

		assertTrue(maxTs.compareTo(minTs) > 0); // MaxLong should be greater than MinLong
	}
}
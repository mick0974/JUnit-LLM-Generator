// TimeStamp_toStringTest.java
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
 * TimeStamp#public static toString(long ntpTime) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_toStringTest {
	private TimeStamp timeStamp;

	@Before
	public void setUp() {
		timeStamp = new TimeStamp(0L);
	}

	@After
	public void tearDown() {
		timeStamp = null;
	}





	@Test
	public void testToStringWithHighOrderBitsSet() {
		long ntpTime = 0xc1a089bdL << 32 | 0xfc904f6dL;
		String result = TimeStamp.toString(ntpTime);
		assertEquals("c1a089bd.fc904f6d", result);
	}

	@Test
	public void testToStringWithLowOrderBitsSet() {
		long ntpTime = 0x00000000L << 32 | 0x00000001L;
		String result = TimeStamp.toString(ntpTime);
		assertEquals("00000000.00000001", result);
	}

	@Test
	public void testToStringWithZero() {
		long ntpTime = 0L;
		String result = TimeStamp.toString(ntpTime);
		assertEquals("00000000.00000000", result);
	}

	@Test
	public void testToStringWithNegative() {
		long ntpTime = -1L;
		String result = TimeStamp.toString(ntpTime);
		assertEquals("ffffffff.ffffffff", result);
	}

	@Test
	public void testToStringWithMaxLong() {
		long ntpTime = Long.MAX_VALUE;
		String result = TimeStamp.toString(ntpTime);
		assertEquals("7fffffff.ffffffff", result);
	}

	@Test
	public void testToStringWithMinLong() {
		long ntpTime = Long.MIN_VALUE;
		String result = TimeStamp.toString(ntpTime);
		assertEquals("80000000.00000000", result);
	}
}
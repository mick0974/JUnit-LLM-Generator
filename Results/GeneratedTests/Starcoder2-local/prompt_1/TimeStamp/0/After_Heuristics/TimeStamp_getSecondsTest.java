// TimeStamp_getSecondsTest.java
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
 * TimeStamp#public getSeconds() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getSecondsTest {
	private TimeStamp ts;
	private DateFormat simpleFormatter;
	private DateFormat utcFormatter;

	@Before
	public void setUp() {
		ts = new TimeStamp(1234567890123L);
	}

	@After
	public void tearDown() {
		ts = null;
	}

	@Test
	public void test_getSeconds_01() {
		long seconds = ts.getSeconds();
		assertEquals(1234567890L, seconds);
	}

	@Test
	public void test_getSeconds_02() {
		long seconds = ts.getSeconds();
		assertEquals(1234567890L, seconds);
	}

	@Test
	public void test_getSeconds_03() {
		long seconds = ts.getSeconds();
		assertEquals(1234567890L, seconds);
	}

	@Test
	public void test_getSeconds_04() {
		long seconds = ts.getSeconds();
		assertEquals(1234567890L, seconds);
	}

	@Test
	public void test_getSeconds_05() {
		long seconds = ts.getSeconds();
		assertEquals(1234567890L, seconds);
	}

	@Test
	public void test_getSeconds_06() {
		long seconds = ts.getSeconds();
		assertEquals(1234567890L, seconds);
	}

	@Test
	public void test_getSeconds_07() {
		long seconds = ts.getSeconds();
		assertEquals(1234567890L, seconds);
	}

	@Test
	public void test_getSeconds_08() {
		long seconds = ts.getSeconds();
		assertEquals(1234567890L, seconds);
	}

	@Test
	public void test_getSeconds_09() {
		long seconds = ts.getSeconds();
		assertEquals(1234567890L, seconds);
	}

	@Test
	public void test_getSeconds_10() {
		long seconds = ts.getSeconds();
		assertEquals(1234567890L, seconds);
	}

}
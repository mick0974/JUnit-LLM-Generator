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
	private TimeStamp ts4;
	private TimeStamp ts5;
	private TimeStamp ts6;
	private TimeStamp ts7;
	private TimeStamp ts8;
	private TimeStamp ts9;
	private TimeStamp ts10;

	@Before
	public void setUp() throws Exception {
		ts1 = new TimeStamp(0);
		ts2 = new TimeStamp(1);
		ts3 = new TimeStamp(2);
		ts4 = new TimeStamp(3);
		ts5 = new TimeStamp(4);
		ts6 = new TimeStamp(5);
		ts7 = new TimeStamp(6);
		ts8 = new TimeStamp(7);
		ts9 = new TimeStamp(8);
		ts10 = new TimeStamp(9);
	}

	@After
	public void tearDown() throws Exception {
		ts1 = null;
		ts2 = null;
		ts3 = null;
		ts4 = null;
		ts5 = null;
		ts6 = null;
		ts7 = null;
		ts8 = null;
		ts9 = null;
		ts10 = null;
	}

	@Test
	public void testCompareTo_01() {
		assertEquals(0, ts1.compareTo(ts1));
	}

	@Test
	public void testCompareTo_02() {
		assertEquals(0, ts2.compareTo(ts2));
	}

	@Test
	public void testCompareTo_03() {
		assertEquals(0, ts3.compareTo(ts3));
	}

	@Test
	public void testCompareTo_04() {
		assertEquals(0, ts4.compareTo(ts4));
	}

	@Test
	public void testCompareTo_05() {
		assertEquals(0, ts5.compareTo(ts5));
	}

	@Test
	public void testCompareTo_06() {
		assertEquals(0, ts6.compareTo(ts6));
	}

	@Test
	public void testCompareTo_07() {
		assertEquals(0, ts7.compareTo(ts7));
	}

	@Test
	public void testCompareTo_08() {
		assertEquals(0, ts8.compareTo(ts8));
	}

	@Test
	public void testCompareTo_09() {
		assertEquals(0, ts9.compareTo(ts9));
	}

	@Test
	public void testCompareTo_10() {
		assertEquals(0, ts10.compareTo(ts10));
	}

	@Test
	public void testCompareTo_11() {
		assertEquals(-1, ts1.compareTo(ts2));
	}

	@Test
	public void testCompareTo_12() {
		assertEquals(-1, ts2.compareTo(ts3));
	}

	@Test
	public void testCompareTo_13() {
		assertEquals(-1, ts3.compareTo(ts4));
	}

	@Test
	public void testCompareTo_14() {
		assertEquals(-1, ts4.compareTo(ts5));
	}

	@Test
	public void testCompareTo_15() {
		assertEquals(-1, ts5.compareTo(ts6));
	}

	@Test
	public void testCompareTo_16() {
		assertEquals(-1, ts6.compareTo(ts7));
	}

	@Test
	public void testCompareTo_17() {
		assertEquals(-1, ts7.compareTo(ts8));
	}

	@Test
	public void testCompareTo_18() {
		assertEquals(-1, ts8.compareTo(ts9));
	}

	@Test
	public void testCompareTo_19() {
		assertEquals(-1, ts9.compareTo(ts10));
	}

	@Test
	public void testCompareTo_20() {
		assertEquals(1, ts1.compareTo(ts10));
	}

	@Test
	public void testCompareTo_21() {
		assertEquals(1, ts2.compareTo(ts9));
	}

	@Test
	public void testCompareTo_22() {
		assertEquals(1, ts3.compareTo(ts8));
	}

	@Test
	public void testCompareTo_23() {
		assertEquals(1, ts4.compareTo(ts7));
	}

	@Test
	public void testCompareTo_24() {
		assertEquals(1, ts5.compareTo(ts6));
	}

	@Test
	public void testCompareTo_25() {
		assertEquals(1, ts6.compareTo(ts5));
	}

	@Test
	public void testCompareTo_26() {
		assertEquals(1, ts7.compareTo(ts4));
	}

	@Test
	public void testCompareTo_27() {
		assertEquals(1, ts8.compareTo(ts3));
	}

	@Test
	public void testCompareTo_28() {
		assertEquals(1, ts9.compareTo(ts2));
	}

	@Test
	public void testCompareTo_29() {
		assertEquals(1, ts10.compareTo(ts1));
	}

	@Test
	public void testCompareTo_30() {
		assertEquals(1, ts1.compareTo(ts1));
	}

	@Test
	public void testCompareTo_31() {
		assertEquals(1, ts2.compareTo(ts2));
	}

	@Test
	public void testCompareTo_32() {
		assertEquals(1, ts3.compareTo(ts3));
	}

	@Test
	public void testCompareTo_33() {
		assertEquals(1, ts4.compareTo(ts4));
	}

	@Test
	public void testCompareTo_34() {
		assertEquals(1, ts5.compareTo(ts5));
	}

	@Test
	public void testCompareTo_35() {
		assertEquals(1, ts6.compareTo(ts6));
	}

	@Test
	public void testCompareTo_36() {
		assertEquals(1, ts7.compareTo(ts7));
	}

	@Test
	public void testCompareTo_37() {
		assertEquals(1, ts8.compareTo(ts8));
	}

	@Test
	public void testCompareTo_38() {
		assertEquals(1, ts9.compareTo(ts9));
	}

	@Test
	public void testCompareTo_39() {
		assertEquals(1, ts10.compareTo(ts10));
	}

	@Test
	public void testCompareTo_40() {
		assertEquals(-1, ts1.compareTo(ts10));
	}

	@Test
	public void testCompareTo_41() {
		assertEquals(-1, ts2.compareTo(ts9));
	}

	@Test
	public void testCompareTo_42() {
		assertEquals(-1, ts3.compareTo(ts8));
	}

	@Test
	public void testCompareTo_43() {
		assertEquals(-1, ts4.compareTo(ts7));
	}

	@Test
	public void testCompareTo_44() {
		assertEquals(-1, ts5.compareTo(ts6));
	}

	@Test
	public void testCompareTo_45() {
		assertEquals(-1, ts6.compareTo(ts5));
	}

	@Test
	public void testCompareTo_46() {
		assertEquals(-1, ts7.compareTo(ts4));
	}

	@Test
	public void testCompareTo_47() {
		assertEquals(-1, ts8.compareTo(ts3));
	}

	@Test
	public void testCompareTo_48() {
		assertEquals(-1, ts9.compareTo(ts2));
	}

	@Test
	public void testCompareTo_49() {
		assertEquals(-1, ts10.compareTo(ts1));
	}

	@Test
	public void testCompareTo_50() {
		assertEquals(1, ts1.compareTo(ts10));
	}

	@Test
	public void testCompareTo_51() {
		assertEquals(1, ts2.compareTo(ts9));
	}

	@Test
	public void testCompareTo_52() {
		assertEquals(1, ts3.compareTo(ts8));
	}

	@Test
	public void testCompareTo_53() {
		assertEquals(1, ts4.compareTo(ts7));
	}

	@Test
	public void testCompareTo_54() {
		assertEquals(1, ts5.compareTo(ts6));
	}

	@Test
	public void testCompareTo_55() {
	
}
}
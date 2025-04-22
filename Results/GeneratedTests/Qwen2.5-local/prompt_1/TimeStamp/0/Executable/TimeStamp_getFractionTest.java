// TimeStamp_getFractionTest.java
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
 * TimeStamp#public getFraction() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getFractionTest {
	private TimeStamp ts;
	private DateFormat formatter;
	
	@Before
	public void setUp() {
		ts = new TimeStamp(0); // Initialize TimeStamp with default value
		formatter = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);
		formatter.setTimeZone(TimeZone.getTimeZone("UTC")); // Set timezone to UTC
	}
	
	@After
	public void tearDown() {
		ts = null;
		formatter = null;
	}
	
	@Test
	public void testGetFraction_withDefaultConstructor() {
		// Arrange
		long expected = 0L;
		
		// Act
		long actual = ts.getFraction();
		
		// Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetFraction_withValidNtpTime() {
		// Arrange
		long ntpTime = 0x00000000_00000000L;
		ts = new TimeStamp(ntpTime);
		long expected = 0L;
		
		// Act
		long actual = ts.getFraction();
		
		// Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetFraction_withHighOrderBitsSet() {
		// Arrange
		long ntpTime = 0xFFFFFFFF_00000000L;
		ts = new TimeStamp(ntpTime);
		long expected = 0L;
		
		// Act
		long actual = ts.getFraction();
		
		// Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetFraction_withLowOrderBitsSet() {
		// Arrange
		long ntpTime = 0x00000000_FFFFFFFFL;
		ts = new TimeStamp(ntpTime);
		long expected = 0xFFFFFFFFL;
		
		// Act
		long actual = ts.getFraction();
		
		// Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetFraction_withMixedBitsSet() {
		// Arrange
		long ntpTime = 0x00000000_80000000L;
		ts = new TimeStamp(ntpTime);
		long expected = 0x80000000L;
		
		// Act
		long actual = ts.getFraction();
		
		// Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetFraction_withNegativeNtpTime() {
		// Arrange
		long ntpTime = -1L;
		ts = new TimeStamp(ntpTime);
		long expected = 0xFFFFFFFFL;
		
		// Act
		long actual = ts.getFraction();
		
		// Assert
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testGetFraction_withZeroNtpTime() {
		// Arrange
		long ntpTime = 0L;
		ts = new TimeStamp(ntpTime);
		long expected = 0L;
		
		// Act
		long actual = ts.getFraction();
		
		// Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetFraction_withMaxLongNtpTime() {
		// Arrange
		long ntpTime = Long.MAX_VALUE;
		ts = new TimeStamp(ntpTime);
		long expected = 0xFFFFFFFFL;
		
		// Act
		long actual = ts.getFraction();
		
		// Assert
		assertEquals(expected, actual);
	}
}
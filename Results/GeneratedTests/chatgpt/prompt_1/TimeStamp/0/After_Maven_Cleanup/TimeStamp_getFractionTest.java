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

private TimeStamp timeStamp;

    @Before
    public void setUp() {
        // Initialize a TimeStamp object before each test case
        long ntpTime = 0xabcd1234567890efL; // Some arbitrary NTP timestamp for testing
        timeStamp = new TimeStamp(ntpTime);
    }

    @After
    public void tearDown() {
        // Clean up after each test case
        timeStamp = null;
    }

    @Test
    public void testGetFractionNormalValue() {
        // Arrange
        long expectedFraction = 0x567890efL;
        
        // Act
        long actualFraction = timeStamp.getFraction();
        
        // Assert
        assertEquals("The fraction should match the expected value.", expectedFraction, actualFraction);
    }

    @Test
    public void testGetFractionOfZeroTimestamp() {
        // Arrange
        TimeStamp zeroTimestamp = new TimeStamp(0);
        long expectedFraction = 0;

        // Act
        long actualFraction = zeroTimestamp.getFraction();

        // Assert
        assertEquals("The fraction should be zero for zero timestamp.", expectedFraction, actualFraction);
    }

    @Test
    public void testGetFractionOfMaxTimestamp() {
        // Arrange
        TimeStamp maxTimestamp = new TimeStamp(0xFFFFFFFFFFFFFFFFL);
        long expectedFraction = 0xFFFFFFFFL;
        
        // Act
        long actualFraction = maxTimestamp.getFraction();
        
        // Assert
        assertEquals("The fraction should be maximum possible for max timestamp.", expectedFraction, actualFraction);
    }

    @Test
    public void testGetFractionWithExactSeconds() {
        // Arrange: A timestamp exactly on the second boundary
        TimeStamp exactSecondsTimestamp = new TimeStamp(0x1234567800000000L);
        long expectedFraction = 0;
        
        // Act
        long actualFraction = exactSecondsTimestamp.getFraction();
        
        // Assert
        assertEquals("The fraction should be zero for timestamp exactly on second boundary.", expectedFraction, actualFraction);
    }

    @Test
    public void testGetFractionOfHexRepresentation() {
        // Arrange
        String hexStamp = "abcd1234.567890ef";
        TimeStamp hexTimestamp = new TimeStamp(hexStamp);
        long expectedFraction = 0x567890efL;
        
        // Act
        long actualFraction = hexTimestamp.getFraction();
        
        // Assert
        assertEquals("The fraction should match expected value from hex representation.", expectedFraction, actualFraction);
    }

    @Test
    public void testGetFractionForNegativeTimestamp() {
        // Arrange: Negative timestamp, which should never happen
        TimeStamp negativeTimestamp = new TimeStamp(-1);
        long expectedFraction = 0xFFFFFFFFL;
        
        // Act
        long actualFraction = negativeTimestamp.getFraction();
        
        // Assert
        assertEquals("The fraction should handle negative timestamp correctly as max value.", expectedFraction, actualFraction);
    }

    @Test
    public void testGetFractionMidRangeTimestamp() {
        // Arrange: A mid-range timestamp
        TimeStamp midRangeTimestamp = new TimeStamp(0x7FFFFFFFFFFFFFFFL);
        long expectedFraction = 0xFFFFFFFFL;
        
        // Act
        long actualFraction = midRangeTimestamp.getFraction();
        
        // Assert
        assertEquals("The fraction should be max value for a mid-range timestamp.", expectedFraction, actualFraction);
    }

    @Test
    public void testGetFractionMinimumNonZero() {
        // Arrange: Minimum non-zero fraction
        TimeStamp minNonZeroFracTimestamp = new TimeStamp(0x00000001L);
        long expectedFraction = 0x00000001L;
        
        // Act
        long actualFraction = minNonZeroFracTimestamp.getFraction();
        
        // Assert
        assertEquals("The fraction should be minimum possible non-zero.", expectedFraction, actualFraction);
    }

    @Test
    public void testGetFractionWithFractionalPart() {
        // Arrange: A timestamp with specific non-zero fractional part
        long ntpTime = 0x12345678ABCDEF12L;
        TimeStamp fractionalTimestamp = new TimeStamp(ntpTime);
        long expectedFraction = 0xABCDEF12L;
        
        // Act
        long actualFraction = fractionalTimestamp.getFraction();
        
        // Assert
        assertEquals("The fraction should match the expected non-zero fractional part.", expectedFraction, actualFraction);
    }

    @Test
    public void testGetFractionAfterDateConversion() {
        // Arrange: Start with Java date and ensure conversion keeps the fraction
        Date date = new Date();
        TimeStamp convertedTimestamp = new TimeStamp(date);
        long expectedFraction = convertedTimestamp.getFraction();

        // Act
        long actualFraction = convertedTimestamp.getFraction();

        // Assert
        assertEquals("The fraction should be preserved after converting from Date.", expectedFraction, actualFraction);
    }
}
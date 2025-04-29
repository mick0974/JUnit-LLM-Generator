// TimeStamp_equalsTest.java
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
 * TimeStamp#public equals(Object obj) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_equalsTest {

private TimeStamp timeStamp1;
    private TimeStamp timeStamp2;
    private TimeStamp timeStamp3;
    private TimeStamp timeStamp4;

    @Before
    public void setUp() {
        // Set up TimeStamp objects for testing
        timeStamp1 = new TimeStamp(0L); // Zero timestamp
        timeStamp2 = new TimeStamp(0xFFFFFFFFFFFFFFFFL); // Max timestamp
        timeStamp3 = new TimeStamp(1L << 32); // One second timestamp
        timeStamp4 = new TimeStamp(System.currentTimeMillis()); // Current time
    }

    @After
    public void tearDown() {
        // Cleanup
        timeStamp1 = null;
        timeStamp2 = null;
        timeStamp3 = null;
        timeStamp4 = null;
    }

    @Test
    public void testEqualsSameObject() {
        // Arrange
        // Already done in setup

        // Act & Assert
        assertTrue("A TimeStamp object should equal itself", timeStamp1.equals(timeStamp1));
    }

    @Test
    public void testEqualsEquivalentObject() {
        // Arrange
        TimeStamp equivalentTimeStamp = new TimeStamp(0L);

        // Act & Assert
        assertTrue("Two TimeStamp objects with the same value should be equal",
                timeStamp1.equals(equivalentTimeStamp));
    }

    @Test
    public void testEqualsDifferentObjectSameValue() {
        // Arrange
        TimeStamp differentReferenceSameValue = new TimeStamp(0xFFFFFFFFFFFFFFFFL);

        // Act & Assert
        assertTrue("Different reference but same value should be equal",
                timeStamp2.equals(differentReferenceSameValue));
    }

    @Test
    public void testEqualsDifferentValues() {
        // Act & Assert
        assertFalse("TimeStamps with different values should not be equal", timeStamp1.equals(timeStamp2));
    }

    @Test
    public void testEqualsNullObject() {
        // Act & Assert
        assertFalse("A TimeStamp should not be equal to null", timeStamp1.equals(null));
    }

    @Test
    public void testEqualsDifferentType() {
        // Arrange
        String differentTypeObject = "Not a TimeStamp";

        // Act & Assert
        assertFalse("A TimeStamp should not be equal to an object of a different type",
                timeStamp1.equals(differentTypeObject));
    }

    @Test
    public void testEqualsObjectWithLessPrecision() {
        // Arrange
        TimeStamp lessPrecision = new TimeStamp(1L << 32); // Equals one second

        // Act & Assert
        assertTrue("A TimeStamp should match another with lesser precision if they represent the same time",
                timeStamp3.equals(lessPrecision));
    }



    @Test
    public void testEqualsReflexivity() {
        // Act & Assert
        assertTrue("Equals should be reflexive", timeStamp4.equals(timeStamp4));
    }
}
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
    private long[] fractions = {0, 1, 1000, 1000000, 0xFFFFFFFFL};

    @Before
    public void setUp() {
        ts = new TimeStamp(0); // initialize with zero timestamp
    }

    @After
    public void tearDown() {
        ts = null;
    }

    @Test
    public void testGetFraction_zero() {
        assertEquals(fractions[0], ts.getFraction());
    }

    @Test
    public void testGetFraction_one() {
        ts = new TimeStamp(1); // set timestamp to one
        assertEquals(fractions[1], ts.getFraction());
    }

    @Test
    public void testGetFraction_1000() {
        ts = new TimeStamp(1000); // set timestamp to 1000
        assertEquals(fractions[2], ts.getFraction());
    }

    @Test
    public void testGetFraction_1000000() {
        ts = new TimeStamp(1000000); // set timestamp to 1000000
        assertEquals(fractions[3], ts.getFraction());
    }

    @Test
    public void testGetFraction_maxInt() {
        ts = new TimeStamp(0xFFFFFFFFL); // set timestamp to max integer
        assertEquals(fractions[4], ts.getFraction());
    }
}
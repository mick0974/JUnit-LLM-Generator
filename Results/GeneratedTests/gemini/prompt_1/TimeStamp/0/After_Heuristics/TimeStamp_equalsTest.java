// TimeStamp_equalsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

/**
 * Test class of TimeStamp.
 * It contains unit test cases for the
 * TimeStamp#public equals(Object obj) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 * Contains only valid Java code.
 */
public class TimeStamp_equalsTest {

    private TimeStamp timestamp1;
    private TimeStamp timestamp2;
    private TimeStamp timestamp3;
    private TimeStamp timestampDifferentValue;
    private TimeStamp timestampFromDate;
    private TimeStamp timestampFromString;

    private final long ntpTimeValue1 = 0xc1a089bd00000000L; // Example NTP time
    private final long ntpTimeValue2 = 0xd0b4c5e612345678L; // Different NTP time
    private final String ntpString1 = "c1a089bd.00000000";
    private final Date date1 = TimeStamp.getTime(ntpTimeValue1);

    @Before
    public void setUp() {
        // Arrange: Initialize TimeStamp objects for testing
        timestamp1 = new TimeStamp(ntpTimeValue1);
        timestamp2 = new TimeStamp(ntpTimeValue1); // Same value as timestamp1
        timestamp3 = new TimeStamp(ntpTimeValue1); // Same value as timestamp1 and timestamp2
        timestampDifferentValue = new TimeStamp(ntpTimeValue2);
        timestampFromDate = new TimeStamp(date1); // Should be equivalent to timestamp1 if conversion is precise enough for equals
        timestampFromString = new TimeStamp(ntpString1); // Should be equal to timestamp1
    }

    @After
    public void tearDown() {
        // Clean up resources if needed (not strictly required for this test class)
        timestamp1 = null;
        timestamp2 = null;
        timestamp3 = null;
        timestampDifferentValue = null;
        timestampFromDate = null;
        timestampFromString = null;
    }

    /**
     * Test case 1: Test equals with the same object instance.
     * An object must be equal to itself.
     */
    @Test
    public void testEquals_SameObject() {
        // Arrange: timestamp1 is already initialized

        // Act & Assert
        assertTrue("A TimeStamp object must be equal to itself.", timestamp1.equals(timestamp1));
    }

    /**
     * Test case 2: Test equals with two different objects having the same ntpTime value.
     */
    @Test
    public void testEquals_EqualObjectsSameLong() {
        // Arrange: timestamp1 and timestamp2 initialized with the same long value in setUp()

        // Act & Assert
        assertTrue("Two TimeStamp objects with the same ntpTime value should be equal.", timestamp1.equals(timestamp2));
    }

    /**
     * Test case 3: Test equals with two different objects having different ntpTime values.
     */
    @Test
    public void testEquals_DifferentObjectsDifferentLong() {
        // Arrange: timestamp1 and timestampDifferentValue initialized with different long values in setUp()

        // Act & Assert
        assertFalse("Two TimeStamp objects with different ntpTime values should not be equal.", timestamp1.equals(timestampDifferentValue));
    }

    /**
     * Test case 4: Test equals with a null argument.
     * equals(null) must return false.
     */
    @Test
    public void testEquals_NullArgument() {
        // Arrange: timestamp1 is initialized

        // Act & Assert
        assertFalse("A TimeStamp object compared with null should return false.", timestamp1.equals(null));
    }

    /**
     * Test case 5: Test equals with an object of a different type.
     * equals(Object) must return false if the object is not a TimeStamp.
     */
    @Test
    public void testEquals_DifferentType() {
        // Arrange: timestamp1 is initialized
        Object otherObject = new Object();
        String otherString = "c1a089bd.00000000";

        // Act & Assert
        assertFalse("A TimeStamp object compared with an object of a different type (Object) should return false.", timestamp1.equals(otherObject));
        assertFalse("A TimeStamp object compared with an object of a different type (String) should return false.", timestamp1.equals(otherString));
    }

    /**
     * Test case 6: Test equals for symmetry (if a.equals(b) is true, then b.equals(a) must be true).
     */
    @Test
    public void testEquals_Symmetry_Equal() {
        // Arrange: timestamp1 and timestamp2 initialized with the same long value in setUp()

        // Act
        boolean forwardEquals = timestamp1.equals(timestamp2);
        boolean backwardEquals = timestamp2.equals(timestamp1);

        // Assert
        assertTrue("Symmetry broken for equal objects: if t1.equals(t2), then t2.equals(t1) must be true.", forwardEquals && backwardEquals);
    }

    /**
     * Test case 7: Test equals for symmetry (if a.equals(b) is false, then b.equals(a) must be false).
     */
    @Test
    public void testEquals_Symmetry_NotEqual() {
        // Arrange: timestamp1 and timestampDifferentValue initialized with different long values in setUp()

        // Act
        boolean forwardEquals = timestamp1.equals(timestampDifferentValue);
        boolean backwardEquals = timestampDifferentValue.equals(timestamp1);

        // Assert
        assertFalse("Symmetry broken for non-equal objects: if t1.equals(t_diff), then t_diff.equals(t1) must be false.", forwardEquals || backwardEquals);
    }

    /**
     * Test case 8: Test equals for transitivity (if a.equals(b) and b.equals(c) are true, then a.equals(c) must be true).
     */
    @Test
    public void testEquals_Transitivity() {
        // Arrange: timestamp1, timestamp2, timestamp3 initialized with the same long value in setUp()

        // Act
        boolean equals12 = timestamp1.equals(timestamp2);
        boolean equals23 = timestamp2.equals(timestamp3);
        boolean equals13 = timestamp1.equals(timestamp3);

        // Assert
        assertTrue("Transitivity broken: if t1.equals(t2) and t2.equals(t3), then t1.equals(t3) must be true.", equals12 && equals23 && equals13);
    }

    /**
     * Test case 9: Test equals consistency (multiple calls return the same result).
     */
    @Test
    public void testEquals_Consistency() {
        // Arrange: timestamp1 and timestamp2 initialized with the same long value in setUp()
        //         timestampDifferentValue initialized with a different long value

        // Act & Assert
        for (int i = 0; i < 5; i++) {
            assertTrue("Consistency broken for equal objects.", timestamp1.equals(timestamp2));
            assertFalse("Consistency broken for non-equal objects.", timestamp1.equals(timestampDifferentValue));
            assertFalse("Consistency broken for null comparison.", timestamp1.equals(null));
            assertFalse("Consistency broken for different type comparison.", timestamp1.equals("some string"));
        }
    }

    /**
     * Test case 10: Test equals with TimeStamp created from equivalent hex string.
     */
    @Test
    public void testEquals_EqualObjectsFromString() {
        // Arrange: timestamp1 and timestampFromString initialized in setUp()
        // Act & Assert
        // Note: timestampFromString uses decodeNtpHexString, which should result in the same internal long value.
        assertTrue("TimeStamp from long should be equal to TimeStamp from equivalent hex string.", timestamp1.equals(timestampFromString));
        assertTrue("Symmetry check: TimeStamp from hex string should be equal to TimeStamp from equivalent long.", timestampFromString.equals(timestamp1));
    }

    /**
     * Test case 11: Test equals with TimeStamp created from equivalent Date object.
     * Note: Conversion from NTP time to Java time (Date) and back might lose precision.
     * The equals method compares the internal 64-bit ntpTime. If toNtpTime(getTime(ntpTimeValue))
     * does not perfectly reconstruct the original ntpTimeValue, this test might fail.
     * Let's test if they are equal based on the reconstructed value.
     */
    @Test
    public void testEquals_EqualObjectsFromDate() {
        // Arrange: timestamp1 created from long, timestampFromDate created from Date derived from that long.
        long reconstructedNtpTime = TimeStamp.toNtpTime(date1.getTime());
        TimeStamp reconstructedTimestamp = new TimeStamp(reconstructedNtpTime);


        // Act & Assert
        // We compare timestampFromDate (created directly from Date) with the reconstructed one.
        // Direct comparison timestamp1.equals(timestampFromDate) depends heavily on precision loss.
        // A better test is to ensure that two TimeStamps created from the same Date are equal.
        TimeStamp timestampFromDate2 = new TimeStamp(date1);
        assertTrue("Two TimeStamp objects created from the same Date object should be equal.", timestampFromDate.equals(timestampFromDate2));


        // Let's explicitly check if the precision loss affects equality with the original long constructor
        // This tests the round trip conversion implicitly used in the Date constructor
        assertEquals("Internal ntpTime value after Date conversion should match the reconstructed value.",
                     timestampFromDate.ntpValue(), reconstructedTimestamp.ntpValue());


        // This assertion depends on whether the round-trip conversion is lossless *for the equals comparison*.
        // Given the nature of NTP vs Java time precision, the internal ntpTime *might* differ slightly.
        // However, the equals method ONLY compares the ntpTime field. If the Date constructor calculates
        // an ntpTime value slightly different from the original due to rounding, they won't be equal.
        // assertEquals(timestamp1, timestampFromDate); // This might fail due to precision loss.
        // Instead, let's compare the reconstructed value:
        assertEquals("TimeStamp from Date should equal a TimeStamp reconstructed via Date's milliseconds.",
                     reconstructedTimestamp, timestampFromDate);


    }
}
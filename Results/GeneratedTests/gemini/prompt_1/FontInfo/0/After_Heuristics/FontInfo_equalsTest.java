import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;

/**
 * Test class for FontInfo#equals(Object obj).
 * Contains 10 unit test cases focusing on the equals method.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 */
public class FontInfo_equalsTest {

    private FontInfo fontInfo1;
    private FontInfo fontInfo2;

    // Default values for easy comparison setup
    private static final String DEFAULT_FAMILY = "Arial";
    private static final int DEFAULT_SIZE = 14;
    private static final boolean DEFAULT_BOLD = false;
    private static final boolean DEFAULT_ITALIC = false;

    @Before
    public void setUp() {
        // Arrange: Create two default FontInfo objects before each test
        fontInfo1 = new FontInfo();
        fontInfo1.setFamily(DEFAULT_FAMILY);
        fontInfo1.setSize(DEFAULT_SIZE);
        fontInfo1.setIsBold(DEFAULT_BOLD);
        fontInfo1.setIsItalic(DEFAULT_ITALIC);

        fontInfo2 = new FontInfo();
        fontInfo2.setFamily(DEFAULT_FAMILY);
        fontInfo2.setSize(DEFAULT_SIZE);
        fontInfo2.setIsBold(DEFAULT_BOLD);
        fontInfo2.setIsItalic(DEFAULT_ITALIC);
    }

    /**
     * Test case 1: Reflexivity - An object must be equal to itself.
     */
    @Test
    public void testEquals_SameInstance() {
        // Arrange: fontInfo1 is already set up

        // Act & Assert: Check if fontInfo1 is equal to itself
        assertTrue("An object must be equal to itself.", fontInfo1.equals(fontInfo1));
        assertEquals("Hash code must be consistent for equal objects.", fontInfo1.hashCode(), fontInfo1.hashCode());
    }

    /**
     * Test case 2: Symmetry - If x.equals(y) is true, then y.equals(x) must be true.
     * Also tests basic equality for identical independent objects.
     */
    @Test
    public void testEquals_IdenticalObjects() {
        // Arrange: fontInfo1 and fontInfo2 are identical but different instances

        // Act & Assert: Check if fontInfo1 equals fontInfo2 and vice versa
        assertTrue("Identical objects should be equal (fontInfo1.equals(fontInfo2)).", fontInfo1.equals(fontInfo2));
        assertTrue("Identical objects should be equal (fontInfo2.equals(fontInfo1)).", fontInfo2.equals(fontInfo1));
        assertEquals("Hash codes must be equal for equal objects.", fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    /**
     * Test case 3: Comparison with null - x.equals(null) must return false.
     */
    @Test
    public void testEquals_NullObject() {
        // Arrange: fontInfo1 is already set up

        // Act & Assert: Check if fontInfo1 equals null
        assertFalse("Comparison with null must return false.", fontInfo1.equals(null));
    }

    /**
     * Test case 4: Comparison with different type - x.equals(objectOfDifferentType) must return false.
     */
    @Test
    public void testEquals_DifferentType() {
        // Arrange: fontInfo1 is already set up
        Object otherObject = new String("Not a FontInfo");

        // Act & Assert: Check if fontInfo1 equals an object of a different type
        assertFalse("Comparison with an object of a different type must return false.", fontInfo1.equals(otherObject));
    }

    /**
     * Test case 5: Inequality due to different family name.
     */
    @Test
    public void testEquals_DifferentFamily() {
        // Arrange: Modify fontInfo2's family name
        fontInfo2.setFamily("Times New Roman");

        // Act & Assert: Check inequality
        assertFalse("Objects with different family names should not be equal.", fontInfo1.equals(fontInfo2));
        // Note: Hash codes are not guaranteed to be different, but likely are.
    }

    /**
     * Test case 6: Inequality due to different bold status.
     */
    @Test
    public void testEquals_DifferentBold() {
        // Arrange: Modify fontInfo2's bold status
        fontInfo2.setIsBold(true); // fontInfo1 is false

        // Act & Assert: Check inequality
        assertFalse("Objects with different bold status should not be equal.", fontInfo1.equals(fontInfo2));
    }

    /**
     * Test case 7: Inequality due to different italic status.
     */
    @Test
    public void testEquals_DifferentItalic() {
        // Arrange: Modify fontInfo2's italic status
        fontInfo2.setIsItalic(true); // fontInfo1 is false

        // Act & Assert: Check inequality
        assertFalse("Objects with different italic status should not be equal.", fontInfo1.equals(fontInfo2));
    }

    /**
     * Test case 8: Inequality due to different size.
     */
    @Test
    public void testEquals_DifferentSize() {
        // Arrange: Modify fontInfo2's size
        fontInfo2.setSize(DEFAULT_SIZE + 2);

        // Act & Assert: Check inequality
        assertFalse("Objects with different sizes should not be equal.", fontInfo1.equals(fontInfo2));
    }

    /**
     * Test case 9: Equality when both family names are null (edge case).
     */
    @Test
    public void testEquals_BothFamilyNull() {
        // Arrange: Set both family names to null
        fontInfo1.setFamily(null);
        fontInfo2.setFamily(null);
         // Ensure other properties are still the same
        fontInfo1.setSize(DEFAULT_SIZE);
        fontInfo2.setSize(DEFAULT_SIZE);
        fontInfo1.setIsBold(DEFAULT_BOLD);
        fontInfo2.setIsBold(DEFAULT_BOLD);
        fontInfo1.setIsItalic(DEFAULT_ITALIC);
        fontInfo2.setIsItalic(DEFAULT_ITALIC);

        // Act & Assert: Check equality
        assertTrue("Objects with null family names and otherwise identical properties should be equal.", fontInfo1.equals(fontInfo2));
        assertEquals("Hash codes must be equal for equal objects (with null family).", fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    /**
     * Test case 10: Inequality when one family name is null and the other is not.
     */
    @Test
    public void testEquals_OneFamilyNull() {
        // Arrange: Set fontInfo1's family name to null, fontInfo2 has the default non-null name
        fontInfo1.setFamily(null);
        // fontInfo2 keeps DEFAULT_FAMILY

        // Act & Assert: Check inequality
        assertFalse("Objects should not be equal if one family name is null and the other is not (obj1.equals(obj2)).", fontInfo1.equals(fontInfo2));
        assertFalse("Objects should not be equal if one family name is null and the other is not (obj2.equals(obj1)).", fontInfo2.equals(fontInfo1));
    }
}
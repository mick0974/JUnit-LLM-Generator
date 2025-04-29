```java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class for the hashCode() method of FontInfo.
 * It contains 10 unit test cases following the Arrange-Act-Assert pattern,
 * covering typical and edge cases to ensure the hash code contract is upheld.
 */
public class FontInfo_hashCodeTest {

    private FontInfo fontInfo1;
    private FontInfo fontInfo2;

    // No specific setup needed before each test for hashCode,
    // as each test creates its own specific instances.
    // @Before
    // public void setUp() {
    // }

    // No specific cleanup needed after each test.
    // @After
    // public void tearDown() {
    // }

    /**
     * Test case 1: Two equal FontInfo objects must have the same hash code.
     * Covers the fundamental requirement of the hashCode contract.
     */
    @Test
    public void testHashCode_EqualObjects() {
        // Arrange
        fontInfo1 = new FontInfo();
        fontInfo1.setFamily("Arial");
        fontInfo1.setSize(14);
        fontInfo1.setIsBold(true);
        fontInfo1.setIsItalic(false);

        fontInfo2 = new FontInfo();
        fontInfo2.setFamily("Arial");
        fontInfo2.setSize(14);
        fontInfo2.setIsBold(true);
        fontInfo2.setIsItalic(false);

        // Act
        int hashCode1 = fontInfo1.hashCode();
        int hashCode2 = fontInfo2.hashCode();

        // Assert
        assertTrue("Objects should be equal", fontInfo1.equals(fontInfo2));
        assertEquals("Equal objects must have the same hash code", hashCode1, hashCode2);
    }

    /**
     * Test case 2: Two FontInfo objects differing only by family name should ideally have different hash codes.
     */
    @Test
    public void testHashCode_DifferentFamilyName() {
        // Arrange
        fontInfo1 = new FontInfo();
        fontInfo1.setFamily("Arial");
        fontInfo1.setSize(12);
        fontInfo1.setIsBold(false);
        fontInfo1.setIsItalic(false);

        fontInfo2 = new FontInfo();
        fontInfo2.setFamily("Times New Roman"); // Different family
        fontInfo2.setSize(12);
        fontInfo2.setIsBold(false);
        fontInfo2.setIsItalic(false);

        // Act
        int hashCode1 = fontInfo1.hashCode();
        int hashCode2 = fontInfo2.hashCode();

        // Assert
        assertFalse("Objects should not be equal", fontInfo1.equals(fontInfo2));
        // Note: Hash collisions are possible but unlikely for this simple change.
        assertNotEquals("Objects with different family names should ideally have different hash codes", hashCode1, hashCode2);
    }

    /**
     * Test case 3: Two FontInfo objects differing only by bold status should have different hash codes.
     */
    @Test
    public void testHashCode_DifferentBoldStatus() {
        // Arrange
        fontInfo1 = new FontInfo();
        fontInfo1.setFamily("Courier New");
        fontInfo1.setSize(10);
        fontInfo1.setIsBold(true); // Bold true
        fontInfo1.setIsItalic(false);

        fontInfo2 = new FontInfo();
        fontInfo2.setFamily("Courier New");
        fontInfo2.setSize(10);
        fontInfo2.setIsBold(false); // Bold false
        fontInfo2.setIsItalic(false);

        // Act
        int hashCode1 = fontInfo1.hashCode();
        int hashCode2 = fontInfo2.hashCode();

        // Assert
        assertFalse("Objects should not be equal", fontInfo1.equals(fontInfo2));
        assertNotEquals("Objects with different bold status must have different hash codes", hashCode1, hashCode2);
    }

    /**
     * Test case 4: Two FontInfo objects differing only by italic status should have different hash codes.
     */
    @Test
    public void testHashCode_DifferentItalicStatus() {
        // Arrange
        fontInfo1 = new FontInfo();
        fontInfo1.setFamily("Verdana");
        fontInfo1.setSize(16);
        fontInfo1.setIsBold(false);
        fontInfo1.setIsItalic(true); // Italic true

        fontInfo2 = new FontInfo();
        fontInfo2.setFamily("Verdana");
        fontInfo2.setSize(16);
        fontInfo2.setIsBold(false);
        fontInfo2.setIsItalic(false); // Italic false

        // Act
        int hashCode1 = fontInfo1.hashCode();
        int hashCode2 = fontInfo2.hashCode();

        // Assert
        assertFalse("Objects should not be equal", fontInfo1.equals(fontInfo2));
        assertNotEquals("Objects with different italic status must have different hash codes", hashCode1, hashCode2);
    }

    /**
     * Test case 5: Two FontInfo objects differing only by size should have different hash codes.
     */
    @Test
    public void testHashCode_DifferentSize() {
        // Arrange
        fontInfo1 = new FontInfo();
        fontInfo1.setFamily("Helvetica");
        fontInfo1.setSize(11); // Size 11
        fontInfo1.setIsBold(true);
        fontInfo1.setIsItalic(true);

        fontInfo2 = new FontInfo();
        fontInfo2.setFamily("Helvetica");
        fontInfo2.setSize(12); // Size 12
        fontInfo2.setIsBold(true);
        fontInfo2.setIsItalic(true);

        // Act
        int hashCode1 = fontInfo1.hashCode();
        int hashCode2 = fontInfo2.hashCode();

        // Assert
        assertFalse("Objects should not be equal", fontInfo1.equals(fontInfo2));
        assertNotEquals("Objects with different sizes must have different hash codes", hashCode1, hashCode2);
    }

    /**
     * Test case 6: Test hash code consistency. Calling hashCode multiple times on the same object
     * should return the same value.
     */
    @Test
    public void testHashCode_Consistency() {
        // Arrange
        fontInfo1 = new FontInfo();
        fontInfo1.setFamily("Dialog");
        fontInfo1.setSize(10);
        fontInfo1.setIsBold(false);
        fontInfo1.setIsItalic(true);

        // Act
        int hashCode1 = fontInfo1.hashCode();
        int hashCode2 = fontInfo1.hashCode();
        int hashCode3 = fontInfo1.hashCode();

        // Assert
        assertEquals("Consecutive hashCode calls must return the same value", hashCode1, hashCode2);
        assertEquals("Consecutive hashCode calls must return the same value", hashCode1, hashCode3);
    }

    /**
     * Test case 7: Two default FontInfo objects (created with the default constructor)
     * must have the same hash code.
     */
    @Test
    public void testHashCode_DefaultObjects() {
        // Arrange
        fontInfo1 = new FontInfo(); // Uses DEFAULT_FAMILY, size 12, plain
        fontInfo2 = new FontInfo(); // Uses DEFAULT_FAMILY, size 12, plain

        // Act
        int hashCode1 = fontInfo1.hashCode();
        int hashCode2 = fontInfo2.hashCode();

        // Assert
        assertTrue("Default objects should be equal", fontInfo1.equals(fontInfo2));
        assertEquals("Default objects must have the same hash code", hashCode1, hashCode2);
    }

    /**
     * Test case 8: Test hash code with the default family name explicitly set vs default constructor.
     */
    @Test
    public void testHashCode_DefaultFamilyExplicitVsDefaultConstructor() {
        // Arrange
        fontInfo1 = new FontInfo(); // Uses DEFAULT_FAMILY ("Monospaced"), size 12, plain

        fontInfo2 = new FontInfo();
        fontInfo2.setFamily("Monospaced"); // Explicitly set default family
        fontInfo2.setSize(12);             // Explicitly set default size
        fontInfo2.setIsBold(false);        // Explicitly set default style
        fontInfo2.setIsItalic(false);      // Explicitly set default style


        // Act
        int hashCode1 = fontInfo1.hashCode();
        int hashCode2 = fontInfo2.hashCode();

        // Assert
        assertTrue("Objects should be equal", fontInfo1.equals(fontInfo2));
        assertEquals("Objects with default values (implicit vs explicit) must have the same hash code", hashCode1, hashCode2);
    }

    /**
     * Test case 9: Test hash code with zero size.
     */
    @Test
    public void testHashCode_ZeroSize() {
         // Arrange
        fontInfo1 = new FontInfo();
        fontInfo1.setFamily("Arial");
        fontInfo1.setSize(0); // Zero size
        fontInfo1.setIsBold(false);
        fontInfo1.setIsItalic(false);

        fontInfo2 = new FontInfo();
        fontInfo2.setFamily("Arial");
        fontInfo2.setSize(0); // Zero size
        fontInfo2.setIsBold(false);
        fontInfo2.setIsItalic(false);

        FontInfo fontInfo3 = new FontInfo();
        fontInfo3.setFamily("Arial");
        fontInfo3.setSize(1); // Non-zero size
        fontInfo3.setIsBold(false);
        fontInfo3.setIsItalic(false);

        // Act
        int hashCode1 = fontInfo1.hashCode();
        int hashCode2 = fontInfo2.hashCode();
        int hashCode3 = fontInfo3.hashCode();

        // Assert
        assertTrue("Objects with zero size should be equal", fontInfo1.equals(fontInfo2));
        assertEquals("Equal objects with zero size must have the same hash code", hashCode1, hashCode2);
        assertNotEquals("Objects with zero vs non-zero size must have different hash codes", hashCode1, hashCode3);
    }

     /**
     * Test case 10: Test hash code with different combinations of bold and italic.
     */
    @Test
    public void testHashCode_BoldItalicCombinations() {
        // Arrange
        FontInfo plain = new FontInfo("Arial", 12, false, false);
        FontInfo bold = new FontInfo("Arial", 12, true, false);
        FontInfo italic = new FontInfo("Arial", 12, false, true);
        FontInfo boldItalic = new FontInfo("Arial", 12, true, true);

        // Act
        int hashPlain = plain.hashCode();
        int hashBold = bold.hashCode();
        int hashItalic = italic.hashCode();
        int hashBoldItalic = boldItalic.hashCode();

        // Assert
        // Assert they are all different from each other
        assertNotEquals("Plain and Bold should have different hash codes", hashPlain, hashBold);
        assertNotEquals("Plain and Italic should have different hash codes", hashPlain, hashItalic);
        assertNotEquals("Plain and BoldItalic should have different hash codes", hashPlain, hashBoldItalic);
        assertNotEquals("Bold and Italic should have different hash codes", hashBold, hashItalic);
        assertNotEquals("Bold and BoldItalic should have different hash codes", hashBold, hashBoldItalic);
        assertNotEquals("Italic and BoldItalic should have different hash codes", hashItalic, hashBoldItalic);

        // Sanity check consistency
        FontInfo plain2 = new FontInfo("Arial", 12, false, false);
        assertEquals("Equal plain objects must have same hash code", hashPlain, plain2.hashCode());
    }

    // Helper constructor for test case 10 for easier setup
    private static class FontInfo extends com.example.FontInfo { // Replace com.example with the actual package if needed
        public FontInfo(String family, int size, boolean bold, boolean italic) {
            super();
            this.setFamily(family);
            this.setSize(size);
            this.setIsBold(bold);
            this.setIsItalic(italic);
        }
        // Need default constructor for other tests
        public FontInfo() {
            super();
        }
    }
}
```
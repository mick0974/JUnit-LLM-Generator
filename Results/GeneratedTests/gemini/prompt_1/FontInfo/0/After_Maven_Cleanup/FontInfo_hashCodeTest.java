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

    /**
     * Test case 1: Two equal FontInfo objects must have the same hash code.
     * Covers the fundamental requirement of the hashCode contract.
     */

    /**
     * Test case 2: Two FontInfo objects differing only by family name should ideally have different hash codes.
     */

    /**
     * Test case 3: Two FontInfo objects differing only by bold status should have different hash codes.
     */

    /**
     * Test case 4: Two FontInfo objects differing only by italic status should have different hash codes.
     */

    /**
     * Test case 5: Two FontInfo objects differing only by size should have different hash codes.
     */

    /**
     * Test case 6: Test hash code consistency. Calling hashCode multiple times on the same object
     * should return the same value.
     */

    /**
     * Test case 7: Two default FontInfo objects (created with the default constructor)
     * must have the same hash code.
     */

    /**
     * Test case 8: Test hash code with the default family name explicitly set vs default constructor.
     */

    /**
     * Test case 9: Test hash code with zero size.
     */

     /**
     * Test case 10: Test hash code with different combinations of bold and italic.
     */

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
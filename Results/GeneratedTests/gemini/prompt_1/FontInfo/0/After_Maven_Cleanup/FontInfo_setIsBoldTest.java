import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class for the FontInfo#setIsBold(boolean value) method.
 * 
 * This class focuses specifically on testing the behavior of setting the bold property.
 * It follows the Arrange-Act-Assert pattern for each test case.
 * 
 * @see FontInfo
 */
public class FontInfo_setIsBoldTest {

    private FontInfo fontInfo;
    private static final String TEST_FAMILY = "Arial";
    private static final int TEST_SIZE = 14;

    /**
     * Sets up a default FontInfo object before each test.
     * The default FontInfo has isBold = false.
     */
    @Before
    public void setUp() {
        // Arrange: Create a default FontInfo instance
        fontInfo = new FontInfo();
        // Set known values different from defaults for side-effect testing
        fontInfo.setFamily(TEST_FAMILY);
        fontInfo.setSize(TEST_SIZE);
        fontInfo.setIsItalic(false); // Ensure italic is initially false
    }

    /**
     * Cleans up resources after each test (optional but good practice).
     */
    @After
    public void tearDown() {
        fontInfo = null;
    }

    /**
     * Test case 1: Set bold to true when initially false (default state).
     * Verifies the state change and that other properties remain unaffected.
     */
    @Test
    public void testSetIsBold_ToTrue_FromDefaultFalse() {
        // Arrange: fontInfo is initialized in setUp() with isBold = false

        // Act: Set isBold to true
        fontInfo.setIsBold(true);

        // Assert: Verify isBold is now true
        assertTrue("isBold should be true after setting to true", fontInfo.isBold());
        // Assert: Verify other properties are unchanged
        assertEquals("Family should remain unchanged", TEST_FAMILY, fontInfo.getFamily());
        assertEquals("Size should remain unchanged", TEST_SIZE, fontInfo.getSize());
        assertFalse("isItalic should remain unchanged", fontInfo.isItalic());
    }

    /**
     * Test case 2: Set bold to false when initially false (default state).
     * Verifies the state remains false and other properties are unaffected.
     */
    @Test
    public void testSetIsBold_ToFalse_FromDefaultFalse() {
        // Arrange: fontInfo is initialized in setUp() with isBold = false

        // Act: Set isBold to false (no change expected)
        fontInfo.setIsBold(false);

        // Assert: Verify isBold remains false
        assertFalse("isBold should remain false after setting to false", fontInfo.isBold());
        // Assert: Verify other properties are unchanged
        assertEquals("Family should remain unchanged", TEST_FAMILY, fontInfo.getFamily());
        assertEquals("Size should remain unchanged", TEST_SIZE, fontInfo.getSize());
        assertFalse("isItalic should remain unchanged", fontInfo.isItalic());
    }

    /**
     * Test case 3: Set bold to false when initially true.
     * Verifies the state change from true to false.
     */
    @Test
    public void testSetIsBold_ToFalse_FromExplicitlyTrue() {
        // Arrange: Set initial state to bold = true
        fontInfo.setIsBold(true);
        assertTrue("Precondition failed: isBold should be true initially", fontInfo.isBold());

        // Act: Set isBold to false
        fontInfo.setIsBold(false);

        // Assert: Verify isBold is now false
        assertFalse("isBold should be false after setting to false from true", fontInfo.isBold());
    }

    /**
     * Test case 4: Set bold to true when initially true.
     * Verifies the state remains true.
     */
    @Test
    public void testSetIsBold_ToTrue_FromExplicitlyTrue() {
        // Arrange: Set initial state to bold = true
        fontInfo.setIsBold(true);
        assertTrue("Precondition failed: isBold should be true initially", fontInfo.isBold());

        // Act: Set isBold to true (no change expected)
        fontInfo.setIsBold(true);

        // Assert: Verify isBold remains true
        assertTrue("isBold should remain true after setting to true from true", fontInfo.isBold());
    }

    /**
     * Test case 5: Set bold to true when italic is also true.
     * Verifies that setting bold does not affect the italic state.
     */
    @Test
    public void testSetIsBold_ToTrue_WhenItalicIsTrue() {
        // Arrange: Set italic state to true, bold is initially false
        fontInfo.setIsItalic(true);
        assertTrue("Precondition failed: isItalic should be true", fontInfo.isItalic());
        assertFalse("Precondition failed: isBold should be false initially", fontInfo.isBold());

        // Act: Set isBold to true
        fontInfo.setIsBold(true);

        // Assert: Verify isBold is now true and isItalic remains true
        assertTrue("isBold should be true after setting to true", fontInfo.isBold());
        assertTrue("isItalic should remain true after setting bold", fontInfo.isItalic());
    }

    /**
     * Test case 6: Set bold to false when italic is also true.
     * Verifies that setting bold does not affect the italic state.
     */
    @Test
    public void testSetIsBold_ToFalse_WhenItalicIsTrueAndBoldIsTrue() {
        // Arrange: Set both bold and italic state to true
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        assertTrue("Precondition failed: isBold should be true initially", fontInfo.isBold());
        assertTrue("Precondition failed: isItalic should be true", fontInfo.isItalic());

        // Act: Set isBold to false
        fontInfo.setIsBold(false);

        // Assert: Verify isBold is now false and isItalic remains true
        assertFalse("isBold should be false after setting to false", fontInfo.isBold());
        assertTrue("isItalic should remain true after unsetting bold", fontInfo.isItalic());
    }
    
    /**
     * Test case 7: Verify generateStyle reflects setting bold to true (from plain).
     */
    @Test
    public void testGenerateStyle_AfterSetBoldTrueFromPlain() {
        // Arrange: fontInfo is default (plain style)
        assertEquals("Initial style should be PLAIN", Font.PLAIN, fontInfo.generateStyle());
        
        // Act: Set bold to true
        fontInfo.setIsBold(true);
        
        // Assert: Verify style is now BOLD
        assertEquals("Style should be BOLD after setting bold to true", Font.BOLD, fontInfo.generateStyle());
    }

    /**
     * Test case 8: Verify generateStyle reflects setting bold to false (from bold).
     */
    @Test
    public void testGenerateStyle_AfterSetBoldFalseFromBold() {
        // Arrange: Set fontInfo to be bold initially
        fontInfo.setIsBold(true);
        assertEquals("Initial style should be BOLD", Font.BOLD, fontInfo.generateStyle());
        
        // Act: Set bold to false
        fontInfo.setIsBold(false);
        
        // Assert: Verify style is now PLAIN
        assertEquals("Style should be PLAIN after setting bold to false", Font.PLAIN, fontInfo.generateStyle());
    }
    
    /**
     * Test case 9: Verify generateStyle reflects setting bold to true (when italic is true).
     */
    @Test
    public void testGenerateStyle_AfterSetBoldTrueWhenItalic() {
        // Arrange: Set fontInfo to be italic initially
        fontInfo.setIsItalic(true);
        assertEquals("Initial style should be ITALIC", Font.ITALIC, fontInfo.generateStyle());
        
        // Act: Set bold to true
        fontInfo.setIsBold(true);
        
        // Assert: Verify style is now BOLD | ITALIC
        assertEquals("Style should be BOLD | ITALIC after setting bold to true", 
                     Font.BOLD | Font.ITALIC, fontInfo.generateStyle());
    }

    /**
     * Test case 10: Verify generateStyle reflects setting bold to false (when italic is true).
     */
    @Test
    public void testGenerateStyle_AfterSetBoldFalseWhenBoldItalic() {
        // Arrange: Set fontInfo to be bold and italic initially
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        assertEquals("Initial style should be BOLD | ITALIC", 
                     Font.BOLD | Font.ITALIC, fontInfo.generateStyle());
        
        // Act: Set bold to false
        fontInfo.setIsBold(false);
        
        // Assert: Verify style is now ITALIC
        assertEquals("Style should be ITALIC after setting bold to false", 
                     Font.ITALIC, fontInfo.generateStyle());
    }
}
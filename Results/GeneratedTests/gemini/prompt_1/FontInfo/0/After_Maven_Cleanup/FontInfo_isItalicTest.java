import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public isItalic() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 * Contains only valid Java code.
 */
public class FontInfo_isItalicTest {

    private FontInfo fontInfo;
    private static final String TEST_FAMILY = "Arial";
    private static final int TEST_SIZE = 14;

    // No common setup needed for these specific tests,
    // as each test creates its specific scenario.
    // @Before
    // public void setUp() throws Exception {
    // }

    // No cleanup needed.
    // @After
    // public void tearDown() throws Exception {
    // }

    /**
     * Test case 1: Default constructor state.
     * Expects isItalic() to return false as the default state is not italic.
     */
    @Test
    public void testIsItalic_DefaultConstructor_ShouldBeFalse() {
        // Arrange
        fontInfo = new FontInfo();

        // Act
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertFalse("Default FontInfo should not be italic", isItalic);
    }

    /**
     * Test case 2: Explicitly set italic to true using setIsItalic.
     * Expects isItalic() to return true.
     */
    @Test
    public void testIsItalic_SetToTrue_ShouldBeTrue() {
        // Arrange
        fontInfo = new FontInfo();
        fontInfo.setIsItalic(true);

        // Act
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertTrue("FontInfo should be italic after setIsItalic(true)", isItalic);
    }

    /**
     * Test case 3: Explicitly set italic to false using setIsItalic.
     * Expects isItalic() to return false.
     */
    @Test
    public void testIsItalic_SetToFalse_ShouldBeFalse() {
        // Arrange
        fontInfo = new FontInfo();
        // Ensure initial state is different if needed (optional here)
        // fontInfo.setIsItalic(true);
        fontInfo.setIsItalic(false);

        // Act
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertFalse("FontInfo should not be italic after setIsItalic(false)", isItalic);
    }

    /**
     * Test case 4: Constructor with a non-italic Font object.
     * Expects isItalic() to return false.
     */
    @Test
    public void testIsItalic_ConstructorWithPlainFont_ShouldBeFalse() {
        // Arrange
        Font plainFont = new Font(TEST_FAMILY, Font.PLAIN, TEST_SIZE);
        fontInfo = new FontInfo(plainFont);

        // Act
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertFalse("FontInfo created with a plain Font should not be italic", isItalic);
    }

    /**
     * Test case 5: Constructor with an italic Font object.
     * Expects isItalic() to return true.
     */
    @Test
    public void testIsItalic_ConstructorWithItalicFont_ShouldBeTrue() {
        // Arrange
        Font italicFont = new Font(TEST_FAMILY, Font.ITALIC, TEST_SIZE);
        fontInfo = new FontInfo(italicFont);

        // Act
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertTrue("FontInfo created with an italic Font should be italic", isItalic);
    }

    /**
     * Test case 6: Constructor with a bold and italic Font object.
     * Expects isItalic() to return true.
     */
    @Test
    public void testIsItalic_ConstructorWithBoldItalicFont_ShouldBeTrue() {
        // Arrange
        Font boldItalicFont = new Font(TEST_FAMILY, Font.BOLD | Font.ITALIC, TEST_SIZE);
        fontInfo = new FontInfo(boldItalicFont);

        // Act
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertTrue("FontInfo created with a bold italic Font should be italic", isItalic);
    }

    /**
     * Test case 7: Using setFont with a non-italic Font object.
     * Expects isItalic() to return false after the call.
     */
    @Test
    public void testIsItalic_SetFontWithPlainFont_ShouldBeFalse() {
        // Arrange
        fontInfo = new FontInfo();
        fontInfo.setIsItalic(true); // Start with italic true
        Font plainFont = new Font(TEST_FAMILY, Font.PLAIN, TEST_SIZE);

        // Act
        fontInfo.setFont(plainFont);
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertFalse("FontInfo should not be italic after setFont with plain Font", isItalic);
    }

    /**
     * Test case 8: Using setFont with an italic Font object.
     * Expects isItalic() to return true after the call.
     */
    @Test
    public void testIsItalic_SetFontWithItalicFont_ShouldBeTrue() {
        // Arrange
        fontInfo = new FontInfo(); // Starts with italic false
        Font italicFont = new Font(TEST_FAMILY, Font.ITALIC, TEST_SIZE);

        // Act
        fontInfo.setFont(italicFont);
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertTrue("FontInfo should be italic after setFont with italic Font", isItalic);
    }

    /**
     * Test case 9: Using setFont with a bold and italic Font object.
     * Expects isItalic() to return true after the call.
     */
    @Test
    public void testIsItalic_SetFontWithBoldItalicFont_ShouldBeTrue() {
        // Arrange
        fontInfo = new FontInfo(); // Starts with italic false
        Font boldItalicFont = new Font(TEST_FAMILY, Font.BOLD | Font.ITALIC, TEST_SIZE);

        // Act
        fontInfo.setFont(boldItalicFont);
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertTrue("FontInfo should be italic after setFont with bold italic Font", isItalic);
    }

    /**
     * Test case 10: Check italic status after cloning an italic FontInfo object.
     * Expects the clone's isItalic() to return true.
     */
    @Test
    public void testIsItalic_AfterCloningItalicInstance_ShouldBeTrue() {
        // Arrange
        fontInfo = new FontInfo();
        fontInfo.setIsItalic(true);
        FontInfo clonedFontInfo = (FontInfo) fontInfo.clone();

        // Act
        boolean isItalic = clonedFontInfo.isItalic();

        // Assert
        assertTrue("Cloned FontInfo (originally italic) should be italic", isItalic);
        assertNotSame("Clone should be a different object", fontInfo, clonedFontInfo);
        assertEquals("Clone should be equal in value", fontInfo, clonedFontInfo);
    }
}
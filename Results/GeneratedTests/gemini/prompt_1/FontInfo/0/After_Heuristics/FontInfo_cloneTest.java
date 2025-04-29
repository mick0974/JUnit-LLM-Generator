import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class for FontInfo#clone().
 * It contains 10 unit test cases focusing on the cloning behavior.
 * Tests follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 * Uses JUnit 4 annotations.
 */
public class FontInfo_cloneTest {

    private FontInfo originalFontInfo;

    // Test constants
    private static final String TEST_FAMILY = "Arial";
    private static final int TEST_SIZE = 14;
    private static final String DEFAULT_FAMILY = "Monospaced";
    private static final int DEFAULT_SIZE = 12;


    @Before
    public void setUp() {
        // Optional: Initialize a common object if needed,
        // but individual tests often prefer their own specific setup (Arrange).
        // For clone tests, creating the 'original' in each test is clearer.
        originalFontInfo = null;
    }

    @After
    public void tearDown() {
        // Clean up resources if any were allocated in setUp
        originalFontInfo = null;
    }

    /**
     * Test cloning a FontInfo created with the default constructor.
     * Verifies the clone is a distinct object but equals the original.
     */
    @Test
    public void testClone_DefaultConstructor_ReturnsEqualButNotSameObject() {
        // Arrange
        originalFontInfo = new FontInfo();

        // Act
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();

        // Assert
        assertNotSame("Clone should be a different object instance", originalFontInfo, clonedFontInfo);
        assertEquals("Clone should be equal to the original", originalFontInfo, clonedFontInfo);
        assertEquals("Clone family should match default", DEFAULT_FAMILY, clonedFontInfo.getFamily());
        assertEquals("Clone size should match default", DEFAULT_SIZE, clonedFontInfo.getSize());
        assertFalse("Clone should not be bold by default", clonedFontInfo.isBold());
        assertFalse("Clone should not be italic by default", clonedFontInfo.isItalic());
    }

    /**
     * Test cloning a FontInfo after setting custom properties.
     * Verifies the clone has the same custom properties.
     */
    @Test
    public void testClone_CustomProperties_ReturnsEqualClone() {
        // Arrange
        originalFontInfo = new FontInfo();
        originalFontInfo.setFamily(TEST_FAMILY);
        originalFontInfo.setSize(TEST_SIZE);
        originalFontInfo.setIsBold(true);
        originalFontInfo.setIsItalic(true);

        // Act
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();

        // Assert
        assertNotSame("Clone should be a different object instance", originalFontInfo, clonedFontInfo);
        assertEquals("Clone should be equal to the original with custom properties", originalFontInfo, clonedFontInfo);
        assertEquals("Clone family should match custom value", TEST_FAMILY, clonedFontInfo.getFamily());
        assertEquals("Clone size should match custom value", TEST_SIZE, clonedFontInfo.getSize());
        assertTrue("Clone bold state should match custom value", clonedFontInfo.isBold());
        assertTrue("Clone italic state should match custom value", clonedFontInfo.isItalic());
    }

    /**
     * Test cloning a FontInfo initialized from a Font object.
     * Verifies the clone correctly reflects the Font's properties.
     */
    @Test
    public void testClone_InitializedWithFont_ReturnsEqualClone() {
        // Arrange
        Font sourceFont = new Font(TEST_FAMILY, Font.BOLD | Font.ITALIC, TEST_SIZE);
        originalFontInfo = new FontInfo(sourceFont);

        // Act
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();

        // Assert
        assertNotSame("Clone should be a different object instance", originalFontInfo, clonedFontInfo);
        assertEquals("Clone should be equal to the original initialized from Font", originalFontInfo, clonedFontInfo);
        assertEquals("Clone family should match Font's family", TEST_FAMILY, clonedFontInfo.getFamily());
        assertEquals("Clone size should match Font's size", TEST_SIZE, clonedFontInfo.getSize());
        assertTrue("Clone bold state should match Font's bold state", clonedFontInfo.isBold());
        assertTrue("Clone italic state should match Font's italic state", clonedFontInfo.isItalic());
    }

    /**
     * Test that modifying the clone does not affect the original object.
     */
    @Test
    public void testClone_ModifyClone_OriginalUnchanged() {
        // Arrange
        originalFontInfo = new FontInfo();
        originalFontInfo.setFamily(TEST_FAMILY);
        originalFontInfo.setSize(TEST_SIZE);
        originalFontInfo.setIsBold(false);
        originalFontInfo.setIsItalic(false);

        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();

        // Act: Modify the clone
        clonedFontInfo.setFamily("Verdana");
        clonedFontInfo.setSize(10);
        clonedFontInfo.setIsBold(true);
        clonedFontInfo.setIsItalic(true);

        // Assert: Original remains unchanged
        assertEquals("Original family should remain unchanged after modifying clone", TEST_FAMILY, originalFontInfo.getFamily());
        assertEquals("Original size should remain unchanged after modifying clone", TEST_SIZE, originalFontInfo.getSize());
        assertFalse("Original bold state should remain unchanged after modifying clone", originalFontInfo.isBold());
        assertFalse("Original italic state should remain unchanged after modifying clone", originalFontInfo.isItalic());
        assertNotEquals("Original and modified clone should no longer be equal", originalFontInfo, clonedFontInfo);
    }

    /**
     * Test that modifying the original object after cloning does not affect the clone.
     */
    @Test
    public void testClone_ModifyOriginal_CloneUnchanged() {
        // Arrange
        originalFontInfo = new FontInfo();
        originalFontInfo.setFamily(TEST_FAMILY);
        originalFontInfo.setSize(TEST_SIZE);
        originalFontInfo.setIsBold(false);
        originalFontInfo.setIsItalic(false);

        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();
        // Capture clone's initial state (although assertEquals covers this)
        String initialCloneFamily = clonedFontInfo.getFamily();
        int initialCloneSize = clonedFontInfo.getSize();
        boolean initialCloneBold = clonedFontInfo.isBold();
        boolean initialCloneItalic = clonedFontInfo.isItalic();


        // Act: Modify the original
        originalFontInfo.setFamily("Verdana");
        originalFontInfo.setSize(10);
        originalFontInfo.setIsBold(true);
        originalFontInfo.setIsItalic(true);

        // Assert: Clone remains unchanged
        assertEquals("Clone family should remain unchanged after modifying original", initialCloneFamily, clonedFontInfo.getFamily());
        assertEquals("Clone size should remain unchanged after modifying original", initialCloneSize, clonedFontInfo.getSize());
        assertEquals("Clone bold state should remain unchanged after modifying original", initialCloneBold, clonedFontInfo.isBold());
        assertEquals("Clone italic state should remain unchanged after modifying original", initialCloneItalic, clonedFontInfo.isItalic());
        assertNotEquals("Modified original and clone should no longer be equal", originalFontInfo, clonedFontInfo);
    }

    /**
     * Test cloning when only the bold property is true.
     */
    @Test
    public void testClone_OnlyBoldIsTrue_ReturnsEqualClone() {
        // Arrange
        originalFontInfo = new FontInfo();
        originalFontInfo.setFamily(TEST_FAMILY);
        originalFontInfo.setSize(TEST_SIZE);
        originalFontInfo.setIsBold(true);
        originalFontInfo.setIsItalic(false);

        // Act
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();

        // Assert
        assertNotSame("Clone should be a different object instance", originalFontInfo, clonedFontInfo);
        assertEquals("Clone should be equal to the original", originalFontInfo, clonedFontInfo);
        assertTrue("Clone bold state should be true", clonedFontInfo.isBold());
        assertFalse("Clone italic state should be false", clonedFontInfo.isItalic());
    }

    /**
     * Test cloning when only the italic property is true.
     */
    @Test
    public void testClone_OnlyItalicIsTrue_ReturnsEqualClone() {
        // Arrange
        originalFontInfo = new FontInfo();
        originalFontInfo.setFamily(TEST_FAMILY);
        originalFontInfo.setSize(TEST_SIZE);
        originalFontInfo.setIsBold(false);
        originalFontInfo.setIsItalic(true);

        // Act
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();

        // Assert
        assertNotSame("Clone should be a different object instance", originalFontInfo, clonedFontInfo);
        assertEquals("Clone should be equal to the original", originalFontInfo, clonedFontInfo);
        assertFalse("Clone bold state should be false", clonedFontInfo.isBold());
        assertTrue("Clone italic state should be true", clonedFontInfo.isItalic());
    }

    /**
     * Test cloning when both bold and italic properties are false (plain style).
     */
    @Test
    public void testClone_BothBoldItalicFalse_ReturnsEqualClone() {
        // Arrange
        originalFontInfo = new FontInfo();
        originalFontInfo.setFamily(TEST_FAMILY);
        originalFontInfo.setSize(TEST_SIZE);
        originalFontInfo.setIsBold(false);
        originalFontInfo.setIsItalic(false); // Default state, but explicit for test clarity

        // Act
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();

        // Assert
        assertNotSame("Clone should be a different object instance", originalFontInfo, clonedFontInfo);
        assertEquals("Clone should be equal to the original", originalFontInfo, clonedFontInfo);
        assertFalse("Clone bold state should be false", clonedFontInfo.isBold());
        assertFalse("Clone italic state should be false", clonedFontInfo.isItalic());
    }

    /**
     * Test cloning when the family name was explicitly set to null
     * (which should result in the default family name being used).
     */
    @Test
    public void testClone_FamilySetToNull_CloneHasDefaultFamily() {
        // Arrange
        originalFontInfo = new FontInfo();
        originalFontInfo.setFamily(null); // Should set to DEFAULT_FAMILY internally

        // Act
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();

        // Assert
        assertNotSame("Clone should be a different object instance", originalFontInfo, clonedFontInfo);
        assertEquals("Original family should be default after setting null", DEFAULT_FAMILY, originalFontInfo.getFamily());
        assertEquals("Clone should be equal to the original", originalFontInfo, clonedFontInfo);
        assertEquals("Clone family should also be the default family", DEFAULT_FAMILY, clonedFontInfo.getFamily());
    }

    /**
     * Test cloning preserves the exact hash code.
     * While covered by assertEquals, this explicitly tests the hash code contract
     * which is related to cloning and equality.
     */
    @Test
    public void testClone_HashCode_IsSameAsOriginal() {
        // Arrange
        originalFontInfo = new FontInfo();
        originalFontInfo.setFamily(TEST_FAMILY);
        originalFontInfo.setSize(TEST_SIZE);
        originalFontInfo.setIsBold(true);
        originalFontInfo.setIsItalic(false);

        // Act
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();

        // Assert
        assertNotSame(originalFontInfo, clonedFontInfo); // Sanity check: different objects
        assertEquals("Clone hash code should match original hash code", originalFontInfo.hashCode(), clonedFontInfo.hashCode());
    }
}
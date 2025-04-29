// FontInfo_setIsItalicTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setIsItalic(boolean value) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setIsItalicTest {

private FontInfo fontInfo;

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test
    public void testSetIsItalicTrue() {
        // Arrange
        fontInfo.setIsItalic(false);

        // Act
        fontInfo.setIsItalic(true);

        // Assert
        assertTrue("Expected isItalic to be true", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalicFalse() {
        // Arrange
        fontInfo.setIsItalic(true);

        // Act
        fontInfo.setIsItalic(false);

        // Assert
        assertFalse("Expected isItalic to be false", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalicAlreadyTrue() {
        // Arrange
        fontInfo.setIsItalic(true);

        // Act
        fontInfo.setIsItalic(true);

        // Assert
        assertTrue("Expected isItalic to remain true", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalicAlreadyFalse() {
        // Arrange
        fontInfo.setIsItalic(false);

        // Act
        fontInfo.setIsItalic(false);

        // Assert
        assertFalse("Expected isItalic to remain false", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalicWhenInitialized() {
        // Act & Assert
        assertFalse("Expected isItalic to initially be false", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalicOnClonedObject() throws CloneNotSupportedException {
        // Arrange
        fontInfo.setIsItalic(false);
        FontInfo clonedFontInfo = (FontInfo) fontInfo.clone();

        // Act
        clonedFontInfo.setIsItalic(true);

        // Assert
        assertTrue("Expected the cloned object's isItalic to be true", clonedFontInfo.isItalic());
        assertFalse("Expected the original object's isItalic to remain false", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalicEffectOnGeneratedFont() {
        // Arrange
        fontInfo.setFamily("Arial");
        fontInfo.setSize(14);
        fontInfo.setIsBold(true);

        // Act
        fontInfo.setIsItalic(true);
        Font generatedFont = fontInfo.createFont();

        // Assert
        assertEquals("Expected Font's style to be BOLD + ITALIC", Font.BOLD + Font.ITALIC, generatedFont.getStyle());
    }

    @Test
    public void testSetIsItalicNoEffectOnFamilyName() {
        // Arrange
        String initialFamilyName = fontInfo.getFamily();

        // Act
        fontInfo.setIsItalic(true);

        // Assert
        assertEquals("Expected family name to remain unchanged", initialFamilyName, fontInfo.getFamily());
    }

    @Test
    public void testSetIsItalicNoEffectOnSize() {
        // Arrange
        int initialSize = fontInfo.getSize();

        // Act
        fontInfo.setIsItalic(true);

        // Assert
        assertEquals("Expected size to remain unchanged", initialSize, fontInfo.getSize());
    }

    @Test
    public void testSetIsItalicInSerializedObject() throws Exception {
        // Arrange
        fontInfo.setIsItalic(true);
        SerializableFontInfo serializedFontInfo = new SerializableFontInfo(fontInfo);

        // Act
        FontInfo deserializedFontInfo = serializedFontInfo.deserialize();

        // Assert
        assertTrue("Expected deserialized object to retain isItalic state", deserializedFontInfo.isItalic());
    }
}
// FontInfo_getFamilyTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public getFamily() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_getFamilyTest {


    private FontInfo fontInfoDefault;
    private FontInfo fontInfoWithFont;

    private static final String CUSTOM_FAMILY = "Arial";
    private static final int CUSTOM_SIZE = 14;

    @Before
    public void setUp() {
        fontInfoDefault = new FontInfo();
        fontInfoWithFont = new FontInfo(new Font(CUSTOM_FAMILY, Font.PLAIN, CUSTOM_SIZE));
    }

    @After
    public void tearDown() {
        fontInfoDefault = null;
        fontInfoWithFont = null;
    }

    @Test
    public void testGetFamily_DefaultConstructor() {
        // Arrange
        // Act
        String family = fontInfoDefault.getFamily();
        // Assert
        assertEquals("Default family should be Monospaced", "Monospaced", family);
    }


    @Test
    public void testSetFamily_NullValue() {
        // Arrange
        fontInfoDefault.setFamily(null);
        // Act
        String family = fontInfoDefault.getFamily();
        // Assert
        assertEquals("Family should fallback to default if null is set", "Monospaced", family);
    }

    @Test
    public void testSetFamily_EmptyString() {
        // Arrange
        fontInfoDefault.setFamily("");
        // Act
        String family = fontInfoDefault.getFamily();
        // Assert
        assertEquals("Family should accept empty string", "", family);
    }

    @Test
    public void testSetFamily_CustomValue() {
        // Arrange
        fontInfoDefault.setFamily(CUSTOM_FAMILY);
        // Act
        String family = fontInfoDefault.getFamily();
        // Assert
        assertEquals("Family should be set to Arial", CUSTOM_FAMILY, family);
    }

    @Test
    public void testChangeFamilyAfterCreation() {
        // Arrange
        fontInfoWithFont.setFamily("Times New Roman");
        // Act
        String family = fontInfoWithFont.getFamily();
        // Assert
        assertEquals("Family should be updated to Times New Roman", "Times New Roman", family);
    }


    @Test
    public void testGetFamily_AfterSetBold() {
        // Arrange
        fontInfoDefault.setIsBold(true);
        // Act
        String family = fontInfoDefault.getFamily();
        // Assert
        assertEquals("Bold setting should not affect family", "Monospaced", family);
    }

    @Test
    public void testGetFamily_WithItalicAndBold() {
        // Arrange
        fontInfoDefault.setIsItalic(true);
        fontInfoDefault.setIsBold(true);
        // Act
        String family = fontInfoDefault.getFamily();
        // Assert
        assertEquals("Italic and bold should not affect family", "Monospaced", family);
    }

}
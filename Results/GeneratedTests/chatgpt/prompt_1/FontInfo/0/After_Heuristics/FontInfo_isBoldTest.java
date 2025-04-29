// FontInfo_isBoldTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public isBold() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_isBoldTest {

private FontInfo fontInfo;
    private static final String DEFAULT_FAMILY = "Monospaced";
    private static final int DEFAULT_SIZE = 12;

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test
    public void testDefaultConstructorSetsBoldToFalse() {
        assertFalse("Default constructor should set isBold to false", fontInfo.isBold());
    }

    @Test
    public void testSetBoldTrue() {
        fontInfo.setIsBold(true);
        assertTrue("setIsBold(true) should set isBold to true", fontInfo.isBold());
    }

    @Test
    public void testSetBoldFalse() {
        fontInfo.setIsBold(false);
        assertFalse("setIsBold(false) should keep isBold as false", fontInfo.isBold());
    }

    @Test
    public void testFontConstructorSetsBoldCorrectly() {
        Font boldFont = new Font(DEFAULT_FAMILY, Font.BOLD, DEFAULT_SIZE);
        FontInfo newFontInfo = new FontInfo(boldFont);
        assertTrue("FontInfo constructed with a bold Font should return true for isBold", newFontInfo.isBold());
    }

    @Test
    public void testFontConstructorSetsNonBoldCorrectly() {
        Font plainFont = new Font(DEFAULT_FAMILY, Font.PLAIN, DEFAULT_SIZE);
        FontInfo newFontInfo = new FontInfo(plainFont);
        assertFalse("FontInfo constructed with a non-bold Font should return false for isBold", newFontInfo.isBold());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionOnNullFont() {
        new FontInfo(null);
    }

    @Test
    public void testCloneMaintainsBoldStateTrue() throws CloneNotSupportedException {
        fontInfo.setIsBold(true);
        FontInfo cloned = (FontInfo) fontInfo.clone();
        assertTrue("Cloned FontInfo should maintain the bold state", cloned.isBold());
    }

    @Test
    public void testCloneMaintainsBoldStateFalse() throws CloneNotSupportedException {
        fontInfo.setIsBold(false);
        FontInfo cloned = (FontInfo) fontInfo.clone();
        assertFalse("Cloned FontInfo should maintain the non-bold state", cloned.isBold());
    }

    @Test
    public void testChangeBoldDoesNotAffectClone() throws CloneNotSupportedException {
        fontInfo.setIsBold(true);
        FontInfo cloned = (FontInfo) fontInfo.clone();
        fontInfo.setIsBold(false);
        assertTrue("Changes to original FontInfo should not affect clone", cloned.isBold());
    }

    @Test
    public void testToStringReflectsBoldState() {
        fontInfo.setIsBold(true);
        assertTrue("toString should reflect bold state", fontInfo.toString().contains("bold"));
    }
}
// FontInfo_cloneTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public clone() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_cloneTest {

private FontInfo originalFontInfo;

    @Before
    public void setUp() {
        originalFontInfo = new FontInfo();
        originalFontInfo.setFamily("Arial");
        originalFontInfo.setIsBold(true);
        originalFontInfo.setIsItalic(false);
        originalFontInfo.setSize(14);
    }

    @After
    public void tearDown() {
        originalFontInfo = null;
    }

    @Test
    public void testClone_NotNull() {
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();
        assertNotNull("Cloned object is null", clonedFontInfo);
    }

    @Test
    public void testClone_DifferentInstance() {
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();
        assertNotSame("Cloned object is the same instance as the original", originalFontInfo, clonedFontInfo);
    }

    @Test
    public void testClone_Equality() {
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();
        assertEquals("Cloned object is not equal to the original", originalFontInfo, clonedFontInfo);
    }

    @Test
    public void testClone_FamilyName() {
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();
        assertEquals("Family name not cloned correctly", originalFontInfo.getFamily(), clonedFontInfo.getFamily());
    }

    @Test
    public void testClone_IsBold() {
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();
        assertEquals("Bold property not cloned correctly", originalFontInfo.isBold(), clonedFontInfo.isBold());
    }

    @Test
    public void testClone_IsItalic() {
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();
        assertEquals("Italic property not cloned correctly", originalFontInfo.isItalic(), clonedFontInfo.isItalic());
    }

    @Test
    public void testClone_Size() {
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();
        assertEquals("Size not cloned correctly", originalFontInfo.getSize(), clonedFontInfo.getSize());
    }

    @Test
    public void testClone_ModifyOriginal_DoesNotAffectClone() {
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();
        originalFontInfo.setFamily("Times New Roman");
        assertNotEquals("Modifying original affects the clone", originalFontInfo.getFamily(), clonedFontInfo.getFamily());
    }

    @Test
    public void testClone_ModifyClone_DoesNotAffectOriginal() {
        FontInfo clonedFontInfo = (FontInfo) originalFontInfo.clone();
        clonedFontInfo.setIsBold(false);
        assertNotEquals("Modifying clone affects the original", originalFontInfo.isBold(), clonedFontInfo.isBold());
    }

    @Test
    public void testClone_Serializable() {
        assertTrue("FontInfo is not serializable", originalFontInfo instanceof Serializable);
    }
}
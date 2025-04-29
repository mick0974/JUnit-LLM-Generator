// FontInfo_hashCodeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public hashCode() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_hashCodeTest {

private FontInfo fontInfo1;
    private FontInfo fontInfo2;

    @Before
    public void setUp() {
        fontInfo1 = new FontInfo();
        fontInfo2 = new FontInfo();
    }

    @After
    public void tearDown() {
        fontInfo1 = null;
        fontInfo2 = null;
    }

    @Test
    public void testHashCode_TwoIdenticalObjects_ShouldBeEqual() {
        assertEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_DifferentFamily_ShouldNotBeEqual() {
        fontInfo1.setFamily("Serif");
        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_DifferentBoldSetting_ShouldNotBeEqual() {
        fontInfo1.setIsBold(true);
        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_DifferentItalicSetting_ShouldNotBeEqual() {
        fontInfo1.setIsItalic(true);
        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_DifferentSize_ShouldNotBeEqual() {
        fontInfo1.setSize(14);
        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_SameAttributes_ShouldBeEqual() {
        fontInfo1.setFamily("Serif");
        fontInfo1.setIsBold(true);
        fontInfo1.setIsItalic(true);
        fontInfo1.setSize(14);
        
        fontInfo2.setFamily("Serif");
        fontInfo2.setIsBold(true);
        fontInfo2.setIsItalic(true);
        fontInfo2.setSize(14);

        assertEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_FontInfoWithNullFamily_ShouldBeEqualToDefaultFamily() {
        fontInfo1.setFamily(null);
        assertEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_DifferentCombinations_ShouldBeUnique() {
        fontInfo1.setFamily("SansSerif");
        fontInfo1.setIsBold(true);
        fontInfo1.setIsItalic(true);
        fontInfo1.setSize(10);

        fontInfo2.setFamily("Serif");
        fontInfo2.setIsBold(false);
        fontInfo2.setIsItalic(false);
        fontInfo2.setSize(12);

        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_SameFontDifferentInstances_ShouldBeEqual() {
        Font font = new Font("Serif", Font.BOLD | Font.ITALIC, 16);
        FontInfo fontInfo1 = new FontInfo(font);
        FontInfo fontInfo2 = new FontInfo(font);

        assertEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_CloneShouldHaveSameHashCode() {
        FontInfo clone = (FontInfo) fontInfo1.clone();
        assertEquals(fontInfo1.hashCode(), clone.hashCode());
    }
}
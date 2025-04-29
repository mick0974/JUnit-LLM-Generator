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
    private FontInfo original;
    private FontInfo cloned;

    @Before
    public void setUp() {
        original = new FontInfo();
        original.setFamily("Serif");
        original.setIsBold(true);
        original.setIsItalic(false);
        original.setSize(14);

        cloned = (FontInfo) original.clone();
    }

    @After
    public void tearDown() {
        original = null;
        cloned = null;
    }

    @Test
    public void testClone_sameValues() {
        assertEquals(original.getFamily(), cloned.getFamily());
        assertEquals(original.isBold(), cloned.isBold());
        assertEquals(original.isItalic(), cloned.isItalic());
        assertEquals(original.getSize(), cloned.getSize());
    }

    @Test
    public void testClone_differentFamily() {
        original.setFamily("SansSerif");
        assertNotEquals(original.getFamily(), cloned.getFamily());
    }

    @Test
    public void testClone_differentSize() {
        original.setSize(16);
        assertNotEquals(original.getSize(), cloned.getSize());
    }

    @Test
    public void testClone_differentBoldness() {
        original.setIsBold(false);
        assertNotEquals(original.isBold(), cloned.isBold());
    }

    @Test
    public void testClone_differentItalics() {
        original.setIsItalic(true);
        assertNotEquals(original.isItalic(), cloned.isItalic());
    }





}
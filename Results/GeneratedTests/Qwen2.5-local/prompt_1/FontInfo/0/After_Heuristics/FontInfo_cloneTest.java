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
    private FontInfo cloneObj;

    @Before
    public void setUp() {
        original = new FontInfo(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
    }

    @After
    public void tearDown() {
        original = null;
        cloneObj = null;
    }

    @Test
    public void testCloneDifferentObject() {
        cloneObj = (FontInfo) original.clone();

        assertTrue(original!= cloneObj); // Check that they are different objects

        assertEquals(original.getFamily(), cloneObj.getFamily());
        assertEquals(original.isBold(), cloneObj.isBold());
        assertEquals(original.isItalic(), cloneObj.isItalic());
        assertEquals(original.getSize(), cloneObj.getSize());
    }

    @Test
    public void testCloneNullFont() {
        try {
            original.setFont(null);
        } catch (IllegalArgumentException e) {
            fail("Exception was thrown: " + e.getMessage());
        }
    }

    @Test
    public void testCloneSameFont() {
        cloneObj = (FontInfo) original.clone();

        assertTrue(original!= cloneObj); // Check that they are different objects

        assertEquals(original.getFamily(), cloneObj.getFamily());
        assertEquals(original.isBold(), cloneObj.isBold());
        assertEquals(original.isItalic(), cloneObj.isItalic());
        assertEquals(original.getSize(), cloneObj.getSize());
    }

    @Test
    public void testCloneDifferentFamily() {
        original.setFamily("Times New Roman");

        cloneObj = (FontInfo) original.clone();

        assertTrue(original!= cloneObj); // Check that they are different objects

        assertEquals(original.getFamily(), cloneObj.getFamily());
        assertEquals(original.isBold(), cloneObj.isBold());
        assertEquals(original.isItalic(), cloneObj.isItalic());
        assertEquals(original.getSize(), cloneObj.getSize());
    }

    @Test
    public void testCloneDifferentSize() {
        original.setSize(16);

        cloneObj = (FontInfo) original.clone();

        assertTrue(original!= cloneObj); // Check that they are different objects

        assertEquals(original.getFamily(), cloneObj.getFamily());
        assertEquals(original.isBold(), cloneObj.isBold());
        assertEquals(original.isItalic(), cloneObj.isItalic());
        assertEquals(original.getSize(), cloneObj.getSize());
    }

    @Test
    public void testCloneDifferentStyle() {
        original.setIsBold(false);
        original.setIsItalic(true);

        cloneObj = (FontInfo) original.clone();

        assertTrue(original!= cloneObj); // Check that they are different objects

        assertEquals(original.getFamily(), cloneObj.getFamily());
        assertEquals(original.isBold(), cloneObj.isBold());
        assertEquals(original.isItalic(), cloneObj.isItalic());
        assertEquals(original.getSize(), cloneObj.getSize());
    }

    @Test
    public void testCloneDefaultFamily() {
        original.setFamily(null);

        cloneObj = (FontInfo) original.clone();

        assertTrue(original!= cloneObj); // Check that they are different objects

        assertEquals(original.getFamily(), cloneObj.getFamily());
        assertEquals(original.isBold(), cloneObj.isBold());
        assertEquals(original.isItalic(), cloneObj.isItalic());
        assertEquals(original.getSize(), cloneObj.getSize());
    }

    @Test
    public void testCloneDefaultSize() {
        original.setSize(0);

        cloneObj = (FontInfo) original.clone();

        assertTrue(original!= cloneObj); // Check that they are different objects

        assertEquals(original.getFamily(), cloneObj.getFamily());
        assertEquals(original.isBold(), cloneObj.isBold());
        assertEquals(original.isItalic(), cloneObj.isItalic());
        assertEquals(original.getSize(), cloneObj.getSize());
    }

    @Test
    public void testCloneDefaultStyle() {
        original.setIsBold(false);
        original.setIsItalic(false);

        cloneObj = (FontInfo) original.clone();

        assertTrue(original!= cloneObj); // Check that they are different objects

        assertEquals(original.getFamily(), cloneObj.getFamily());
        assertEquals(original.isBold(), cloneObj.isBold());
        assertEquals(original.isItalic(), cloneObj.isItalic());
        assertEquals(original.getSize(), cloneObj.getSize());
    }

    @Test
    public void testCloneSerializable() {
        try {
            cloneObj = (FontInfo) SerializationUtils.clone(original);
        } catch (Exception e) {
            fail("Exception was thrown: " + e.getMessage());
        }

        assertTrue(original!= cloneObj); // Check that they are different objects

        assertEquals(original.getFamily(), cloneObj.getFamily());
        assertEquals(original.isBold(), cloneObj.isBold());
        assertEquals(original.isItalic(), cloneObj.isItalic());
        assertEquals(original.getSize(), cloneObj.getSize());
    }
}
// FontInfo_getSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public getSize() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_getSizeTest {

private FontInfo fontInfo;

    @Before
    public void setUp() {
        // This method will be executed before each test
        fontInfo = new FontInfo();
    }

    @After
    public void tearDown() {
        // This method will be executed after each test
        fontInfo = null;
    }

    @Test
    public void testDefaultSize() {
        // Arrange - Initialization done in setUp()
        // Act
        int size = fontInfo.getSize();
        // Assert
        assertEquals("Default font size should be 12", 12, size);
    }

    @Test
    public void testSetSize() {
        // Arrange
        int expectedSize = 16;
        fontInfo.setSize(expectedSize);
        // Act
        int actualSize = fontInfo.getSize();
        // Assert
        assertEquals("Setting font size should set to 16", expectedSize, actualSize);
    }

    @Test
    public void testSetSizeMinimum() {
        // Arrange
        int expectedSize = 1;
        fontInfo.setSize(expectedSize);
        // Act
        int actualSize = fontInfo.getSize();
        // Assert
        assertEquals("Setting font size to minimum of 1 should be successful", expectedSize, actualSize);
    }

    @Test
    public void testSetSizeMaximum() {
        // Arrange
        int expectedSize = 100;
        fontInfo.setSize(expectedSize);
        // Act
        int actualSize = fontInfo.getSize();
        // Assert
        assertEquals("Setting font size to a large number such as 100 should be successful", expectedSize, actualSize);
    }

    @Test
    public void testSetSizeToNegativeValue() {
        // Arrange
        int negativeSize = -5;
        fontInfo.setSize(negativeSize);
        // Act
        int actualSize = fontInfo.getSize();
        // Assert
        assertEquals("Setting font size to a negative value should not affect the size", negativeSize, actualSize);
    }

    @Test
    public void testSetSizeToZero() {
        // Arrange
        int zeroSize = 0;
        fontInfo.setSize(zeroSize);
        // Act
        int actualSize = fontInfo.getSize();
        // Assert
        assertEquals("Setting font size to zero should set the size to 0", zeroSize, actualSize);
    }

    @Test
    public void testFontSizeConsistency() {
        // Arrange
        int initialSize = fontInfo.getSize();
        // Act
        fontInfo.setSize(initialSize);
        int actualSize = fontInfo.getSize();
        // Assert
        assertEquals("Font size should remain consistent when set to its current value", initialSize, actualSize);
    }

    @Test
    public void testSetSizeWithLargeInteger() {
        // Arrange
        int largeSize = Integer.MAX_VALUE;
        fontInfo.setSize(largeSize);
        // Act
        int actualSize = fontInfo.getSize();
        // Assert
        assertEquals("Setting font size to a large integer should be handled correctly", largeSize, actualSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSizeWithNullFont() {
        // Arrange
        Font nullFont = null;
        // Act
        fontInfo.setFont(nullFont);
    }

    @Test
    public void testSizeAfterValidFontSet() {
        // Arrange
        Font font = new Font("Arial", Font.PLAIN, 14);
        fontInfo.setFont(font);
        // Act
        int actualSize = fontInfo.getSize();
        // Assert
        assertEquals("Font size should match the size of a valid set Font", font.getSize(), actualSize);
    }
}
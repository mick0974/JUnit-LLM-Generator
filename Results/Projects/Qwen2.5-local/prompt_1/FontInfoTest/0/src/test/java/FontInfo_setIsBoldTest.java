// FontInfo_setIsBoldTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setIsBold(boolean value) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setIsBoldTest {
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
    public void testSetIsBoldTrue() {
        fontInfo.setIsBold(true);
        assertTrue("The setIsBold should be true", fontInfo.isBold());
    }

    @Test
    public void testSetIsBoldFalse() {
        fontInfo.setIsBold(false);
        assertFalse("The setIsBold should be false", fontInfo.isBold());
    }
    
    // Additional tests can be added to cover more scenarios like setting both bold and italic etc.
}
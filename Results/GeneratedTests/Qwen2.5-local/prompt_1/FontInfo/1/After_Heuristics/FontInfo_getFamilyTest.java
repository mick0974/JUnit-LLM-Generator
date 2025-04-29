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
    public void testGetFamily_DefaultFamily() {
        assertEquals(FontInfo.DEFAULT_FAMILY, fontInfo.getFamily());
    }

    @Test
    public void testGetFamily_CustomFamily() {
        String customFamily = "Arial";
        fontInfo.setFamily(customFamily);
        assertEquals(customFamily, fontInfo.getFamily());
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetFamily_NullFamily() {
        fontInfo.setFamily(null);
        fontInfo.getFamily(); // Should throw NullPointerException
    }
    
    @Test
    public void testGetFamily_EmptyStringFamily() {
        fontInfo.setFamily("");
        assertEquals(FontInfo.DEFAULT_FAMILY, fontInfo.getFamily());
    }
    
    @Test
    public void testGetFamily_MixedCaseFamily() {
        String mixedCaseFamily = "Times New Roman";
        fontInfo.setFamily(mixedCaseFamily);
        assertEquals(mixedCaseFamily.toLowerCase(), fontInfo.getFamily());
    }
    
    @Test
    public void testGetFamily_LowercaseFamily() {
        String lowercaseFamily = "verdana";
        fontInfo.setFamily(lowercaseFamily);
        assertEquals(lowercaseFamily, fontInfo.getFamily());
    }
    
    @Test
    public void testGetFamily_UppercaseFamily() {
        String uppercaseFamily = "HELVETICA";
        fontInfo.setFamily(uppercaseFamily);
        assertEquals(uppercaseFamily.toLowerCase(), fontInfo.getFamily());
    }
    
    @Test
    public void testGetFamily_SpecialCharactersFamily() {
        String specialCharsFamily = "My$Family!@#";
        fontInfo.setFamily(specialCharsFamily);
        assertEquals(specialCharsFamily.replaceAll("[^a-zA-Z0-9]", ""), fontInfo.getFamily());
    }
    
    @Test
    public void testGetFamily_BlankSpacesFamily() {
        String blankSpacesFamily = "   ";
        fontInfo.setFamily(blankSpacesFamily);
        assertEquals(FontInfo.DEFAULT_FAMILY, fontInfo.getFamily());
    }
    
    @Test
    public void testGetFamily_SingleCharacterFamily() {
        String singleCharFamily = "C";
        fontInfo.setFamily(singleCharFamily);
        assertEquals(singleCharFamily, fontInfo.getFamily());
    }
}
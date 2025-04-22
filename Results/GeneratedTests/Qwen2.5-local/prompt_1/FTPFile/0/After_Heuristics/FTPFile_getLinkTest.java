// FTPFile_getLinkTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

/**
 * Test class of FTPFile.
 * It contains 10 unit test cases for the
 * FTPFile#public getLink() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getLinkTest {
    private FTPFile ftpFile;

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void testGetLink_defaultValue() {
        assertNull(ftpFile.getLink());
    }

    @Test
    public void testGetLink_afterSettingNull() {
        ftpFile.setLink(null);
        assertNull(ftpFile.getLink());
    }

    @Test
    public void testGetLink_afterSettingEmptyString() {
        ftpFile.setLink("");
        assertEquals("", ftpFile.getLink());
    }

    @Test
    public void testGetLink_afterSettingValidString() {
        String expected = "validLink";
        ftpFile.setLink(expected);
        assertEquals(expected, ftpFile.getLink());
    }
    
    @Test
    public void testGetLink_afterParsingFailedEntry() {
    	String rawListing = "invalid entry";
    	FTPFile ftpFile = new FTPFile(rawListing);
    	assertNull(ftpFile.getLink());
    }
    
    @Test
    public void testGetLink_afterSettingValidStringWithSpaces() {
        String expected = "valid Link";
        ftpFile.setLink(expected);
        assertEquals(expected, ftpFile.getLink());
    }
    
    @Test
    public void testGetLink_afterSettingValidStringWithSpecialCharacters() {
        String expected = "valid!@#$%^&*()_+";
        ftpFile.setLink(expected);
        assertEquals(expected, ftpFile.getLink());
    }
    
    @Test
    public void testGetLink_afterSettingValidStringWithUnicode() {
        String expected = "valid\u00A0string"; // Unicode space character
        ftpFile.setLink(expected);
        assertEquals(expected, ftpFile.getLink());
    }
    
    @Test
    public void testGetLink_afterSettingValidStringWithLongLength() {
        StringBuilder expectedBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            expectedBuilder.append('a');
        }
        String expected = expectedBuilder.toString();
        ftpFile.setLink(expected);
        assertEquals(expected, ftpFile.getLink());
    }
    
    @Test
    public void testGetLink_afterSettingValidStringWithTrailingWhitespace() {
        String expected = "validLink   ";
        ftpFile.setLink(expected);
        assertEquals(expected.trim(), ftpFile.getLink()); // trim to match getLink behavior
    }
}
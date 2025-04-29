// FTPFile_getRawListingTest.java
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
 * FTPFile#public getRawListing() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getRawListingTest {
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
    public void testGetRawListing_NullInput() {
        ftpFile.setRawListing(null);
        assertNull(ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListing_EmptyInput() {
        ftpFile.setRawListing("");
        assertEquals("", ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListing_SingleWhitespaceInput() {
        ftpFile.setRawListing(" ");
        assertEquals(" ", ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListing_MultiWhitespaceInput() {
        ftpFile.setRawListing("   ");
        assertEquals("   ", ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListing_AlphanumericInput() {
        ftpFile.setRawListing("ABC123");
        assertEquals("ABC123", ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListing_SpecialCharacterInput() {
        ftpFile.setRawListing("!@#$%^&*()");
        assertEquals("!@#$%^&*()", ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListing_UnicodeInput() {
        ftpFile.setRawListing("\u00E9\u00F1\u00FC"); // Spanish characters
        assertEquals("\u00E9\u00F1\u00FC", ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListing_LargeInput() {
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeInput.append("X");
        }
        ftpFile.setRawListing(largeInput.toString());
        assertEquals(largeInput.toString(), ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListing_MaximumLengthInput() {
        StringBuilder maxInput = new StringBuilder();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            maxInput.append("X");
        }
        ftpFile.setRawListing(maxInput.toString());
        assertEquals(maxInput.toString(), ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListing_MinimumLengthInput() {
        ftpFile.setRawListing("");
        assertEquals("", ftpFile.getRawListing());
    }
}
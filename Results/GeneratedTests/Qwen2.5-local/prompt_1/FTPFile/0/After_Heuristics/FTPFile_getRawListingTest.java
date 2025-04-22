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
    public void testGetRawListingInitiallyNull() {
        assertNull(ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListingAfterSetting() {
        String expected = "test";
        ftpFile.setRawListing(expected);
        assertEquals(expected, ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListingWithSpaces() {
        String expected = "test file";
        ftpFile.setRawListing(expected);
        assertEquals(expected, ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListingWithSpecialCharacters() {
        String expected = "test!@#$%^&*()_+";
        ftpFile.setRawListing(expected);
        assertEquals(expected, ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListingWithNewLine() {
        String expected = "test\nfile";
        ftpFile.setRawListing(expected);
        assertEquals(expected, ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListingWithCarriageReturn() {
        String expected = "test\rfile";
        ftpFile.setRawListing(expected);
        assertEquals(expected, ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListingWithTab() {
        String expected = "test\tfile";
        ftpFile.setRawListing(expected);
        assertEquals(expected, ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListingWithBackslash() {
        String expected = "test\\file";
        ftpFile.setRawListing(expected);
        assertEquals(expected, ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListingWithForwardSlash() {
        String expected = "test/file";
        ftpFile.setRawListing(expected);
        assertEquals(expected, ftpFile.getRawListing());
    }

    @Test
    public void testGetRawListingWithUnicode() {
        String expected = "\u00E4\u00F6\u00FC"; // äöü
        ftpFile.setRawListing(expected);
        assertEquals(expected, ftpFile.getRawListing());
    }
}
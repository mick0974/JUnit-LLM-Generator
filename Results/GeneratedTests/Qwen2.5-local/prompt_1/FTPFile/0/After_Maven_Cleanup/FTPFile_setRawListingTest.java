// FTPFile_setRawListingTest.java
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
 * FTPFile#public setRawListing(String rawListing) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setRawListingTest {
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
    public void testSetRawListing_validInput() {
        String rawListing = "drwxr-xr-x 2 user group 4096 Jan 1 12:34 dir";
        ftpFile.setRawListing(rawListing);

        assertEquals(rawListing, ftpFile.getRawListing());
    }

    @Test(expected = NullPointerException.class)
    public void testSetRawListing_nullInput() {
        ftpFile.setRawListing(null);
    }

    @Test
    public void testSetRawListing_emptyInput() {
        String rawListing = "";
        ftpFile.setRawListing(rawListing);

        assertEquals(rawListing, ftpFile.getRawListing());
    }

    @Test
    public void testSetRawListing_whitespaceInput() {
        String rawListing = "      ";
        ftpFile.setRawListing(rawListing);

        assertEquals(rawListing, ftpFile.getRawListing());
    }

    @Test
    public void testSetRawListing_withSpacesInFilename() {
        String rawListing = "-rw-r--r-- 1 user group 1234 Jan 1 12:34 file with spaces.txt";
        ftpFile.setRawListing(rawListing);

        assertEquals(rawListing, ftpFile.getRawListing());
    }

    @Test
    public void testSetRawListing_withTabsInFilename() {
        String rawListing = "-rw-r--r-- 1 user group 1234 Jan 1 12:34\tfile\twith\ttabs.txt";
        ftpFile.setRawListing(rawListing);

        assertEquals(rawListing, ftpFile.getRawListing());
    }

    @Test
    public void testSetRawListing_withNewlineInFilename() {
        String rawListing = "-rw-r--r-- 1 user group 1234 Jan 1 12:34\nfile\nwith\nnewlines.txt";
        ftpFile.setRawListing(rawListing);

        assertEquals(rawListing, ftpFile.getRawListing());
    }

    @Test
    public void testSetRawListing_withCarriageReturnInFilename() {
        String rawListing = "-rw-r--r-- 1 user group 1234 Jan 1 12:34\rfile\rwith\rcarriage return.txt";
        ftpFile.setRawListing(rawListing);

        assertEquals(rawListing, ftpFile.getRawListing());
    }

    @Test
    public void testSetRawListing_withMixedWhitespaceAndCharactersInFilename() {
        String rawListing = "-rw-r--r-- 1 user group 1234 Jan 1 12:34   file     with   characters.txt";
        ftpFile.setRawListing(rawListing);

        assertEquals(rawListing, ftpFile.getRawListing());
    }
}
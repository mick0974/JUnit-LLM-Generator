// FTPFile_isFileTest.java
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
 * FTPFile#public isFile() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isFileTest {
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
    public void testIsFileWhenFileType() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertTrue(ftpFile.isFile());
    }

    @Test
    public void testIsFileWhenDirectoryType() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertFalse(ftpFile.isFile());
    }

    @Test
    public void testIsFileWhenSymbolicLinkType() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertFalse(ftpFile.isFile());
    }

    @Test
    public void testIsFileWhenUnknownType() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        assertFalse(ftpFile.isFile());
    }

    @Test
    public void testIsFileWithNullType() {
        ftpFile._type = FTPFile.UNKNOWN_TYPE; // Setting directly for testing
        assertFalse(ftpFile.isFile());
    }

    @Test
    public void testIsFileWithValidEntry() {
        ftpFile.setRawListing("-rw-r--r-- 1 user group 1024 Jan 1 12:00 filename.txt");
        assertTrue(ftpFile.isFile());
    }

    @Test
    public void testIsFileWithInvalidEntry() {
        ftpFile.setRawListing("[Invalid: could not parse file entry]");
        assertFalse(ftpFile.isFile());
    }

    @Test
    public void testIsFileWithEmptyRawListing() {
        ftpFile.setRawListing("");
        assertFalse(ftpFile.isFile());
    }

    @Test
    public void testIsFileWithWhitespaceOnlyRawListing() {
        ftpFile.setRawListing(" ");
        assertFalse(ftpFile.isFile());
    }
    
    @Test
    public void testIsFileWithNullRawListing() {
        ftpFile.setRawListing(null);
        assertFalse(ftpFile.isFile());
    }
}
// FTPFile_isSymbolicLinkTest.java
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
 * FTPFile#public isSymbolicLink() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isSymbolicLinkTest {
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
    public void testIsSymbolicLink_WhenFileType() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertFalse(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLink_WhenDirectoryType() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertFalse(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLink_WhenSymbolicLinkType() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertTrue(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLink_WhenUnknownType() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        assertFalse(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLink_WithNullPermissions() {
        ftpFile.setRawListing("invalid entry");
        assertFalse(ftpFile.isSymbolicLink());
    }
    
    @Test
    public void testIsSymbolicLink_WithValidEntry() {
        ftpFile.setRawListing("-rw-r--r-- 1 user group 1024 Jan 1 00:00 link -> target");
        assertTrue(ftpFile.isSymbolicLink());
    }
    
    @Test
    public void testIsSymbolicLink_WithInvalidEntry() {
        ftpFile.setRawListing("invalid entry");
        assertFalse(ftpFile.isSymbolicLink());
    }
    
    @Test
    public void testIsSymbolicLink_WithNoLink() {
        ftpFile.setRawListing("-rw-r--r-- 1 user group 1024 Jan 1 00:00 filename");
        assertFalse(ftpFile.isSymbolicLink());
    }
    
    @Test
    public void testIsSymbolicLink_WithEmptyLink() {
        ftpFile.setRawListing("-rw-r--r-- 1 user group 1024 Jan 1 00:00 link -> ");
        assertFalse(ftpFile.isSymbolicLink());
    }
    
    @Test
    public void testIsSymbolicLink_WithWhitespaceLink() {
        ftpFile.setRawListing("-rw-r--r-- 1 user group 1024 Jan 1 00:00 link ->   ");
        assertFalse(ftpFile.isSymbolicLink());
    }
}
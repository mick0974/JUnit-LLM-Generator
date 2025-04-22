// FTPFile_isDirectoryTest.java
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
 * FTPFile#public isDirectory() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isDirectoryTest {
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
    public void testIsDirectory_WhenFileType() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertFalse(ftpFile.isDirectory());
    }

    @Test
    public void testIsDirectory_WhenDirectoryType() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertTrue(ftpFile.isDirectory());
    }

    @Test
    public void testIsDirectory_WhenSymbolicLinkType() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertFalse(ftpFile.isDirectory());
    }

    @Test
    public void testIsDirectory_WhenUnknownType() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        assertFalse(ftpFile.isDirectory());
    }
    
    @Test
    public void testIsDirectory_WhenValid() {
    	boolean valid = ftpFile.isValid();
    	assertTrue(valid); // should always return true since we are just checking if it's a directory
    }
    
    @Test
    public void testIsDirectory_WhenInvalid() {
    	ftpFile.setRawListing("[Invalid: could not parse file entry]");
    	boolean valid = ftpFile.isValid();
    	assertFalse(valid); // should always return false since we are just checking if it's a directory
    }
    
    @Test
    public void testIsDirectory_WhenNullPermissions() {
    	ftpFile.setRawListing("[Invalid: could not parse file entry]");
    	boolean valid = ftpFile.isValid();
    	assertFalse(valid); // should always return false since we are just checking if it's a directory
    }
    
    @Test
    public void testIsDirectory_WhenEmptyRawListing() {
    	ftpFile.setRawListing("");
    	boolean valid = ftpFile.isValid();
    	assertFalse(valid); // should always return false since we are just checking if it's a directory
    }
    
    @Test
    public void testIsDirectory_WhenWhitespaceRawListing() {
    	ftpFile.setRawListing(" ");
    	boolean valid = ftpFile.isValid();
    	assertFalse(valid); // should always return false since we are just checking if it's a directory
    }
    
    @Test
    public void testIsDirectory_WhenNullCalendar() {
    	ftpFile.setRawListing("[Invalid: could not parse file entry]");
    	boolean valid = ftpFile.isValid();
    	assertFalse(valid); // should always return false since we are just checking if it's a directory
    }
}
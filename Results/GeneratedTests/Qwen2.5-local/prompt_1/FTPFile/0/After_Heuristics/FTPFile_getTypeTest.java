// FTPFile_getTypeTest.java
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
 * FTPFile#public getType() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getTypeTest {
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
    public void testGetType_default() {
        assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
    }

    @Test
    public void testGetType_directory() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertEquals(FTPFile.DIRECTORY_TYPE, ftpFile.getType());
    }

    @Test
    public void testGetType_file() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertEquals(FTPFile.FILE_TYPE, ftpFile.getType());
    }

    @Test
    public void testGetType_symbolicLink() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, ftpFile.getType());
    }
    
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testGetType_outOfBounds() {
        ftpFile.setType(4); // Invalid type
    }

    @Test
    public void testGetType_afterSettingInvalid() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setRawListing("[Invalid: could not parse file entry]");
        assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
    }
    
    @Test
    public void testGetType_afterResetting() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
    }
    
    @Test
    public void testGetType_afterParsingFailed() {
        ftpFile.setRawListing("[Invalid: could not parse file entry]");
        assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
    }
    
    @Test
    public void testGetType_afterParsingSuccess() {
        ftpFile.setRawListing("-rw-r--r-- 1 user group 1234 Jan 1 12:34 file.txt");
        assertEquals(FTPFile.FILE_TYPE, ftpFile.getType());
    }
    
    @Test
    public void testGetType_afterParsingSymbolicLink() {
        ftpFile.setRawListing("lrwxrwxrwx 1 user group 1234 Jan 1 12:34 file.txt -> target.txt");
        assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, ftpFile.getType());
    }
}
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
        assertTrue("Should return true when type is FILE_TYPE", ftpFile.isFile());
    }

    @Test
    public void testIsFileWhenDirectoryType() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertFalse("Should return false when type is DIRECTORY_TYPE", ftpFile.isFile());
    }

    @Test
    public void testIsFileWhenSymbolicLinkType() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertFalse("Should return false when type is SYMBOLIC_LINK_TYPE", ftpFile.isFile());
    }

    @Test
    public void testIsFileWhenUnknownType() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        assertFalse("Should return false when type is UNKNOWN_TYPE", ftpFile.isFile());
    }
    
    @Test
    public void testIsFileWithNullType() {
        ftpFile.setType(-1); // Assuming -1 represents an unknown/unset type
        assertFalse("Should return false when type is unknown/unset", ftpFile.isFile());
    }
    
    @Test
    public void testIsFileWithValidPermissions() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, false);
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertTrue("Should return true when type is FILE_TYPE and permissions are valid", ftpFile.isFile());
    }
    
    @Test
    public void testIsFileWithEmptyName() {
        ftpFile.setName("");
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertTrue("Should return true when type is FILE_TYPE and name is empty", ftpFile.isFile());
    }
    
    @Test
    public void testIsFileWithLargeFileSize() {
        ftpFile.setSize(Long.MAX_VALUE);
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertTrue("Should return true when type is FILE_TYPE and file size is large", ftpFile.isFile());
    }
    
    @Test
    public void testIsFileWithNegativeFileSize() {
        ftpFile.setSize(-1);
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertTrue("Should return true when type is FILE_TYPE and file size is negative", ftpFile.isFile());
    }
    
    @Test
    public void testIsFileWithNonZeroHardLinkCount() {
        ftpFile.setHardLinkCount(5);
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertTrue("Should return true when type is FILE_TYPE and hard link count is non-zero", ftpFile.isFile());
    }
}
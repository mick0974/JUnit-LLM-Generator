// FTPFile_toStringTest.java
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
 * FTPFile#public toString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_toStringTest {
    private FTPFile ftpFile;

    @Before
    public void setUp() throws Exception {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() throws Exception {
        ftpFile = null;
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testSetPermissionWithNegativeAccess() {
        ftpFile.setPermission(-1, FTPFile.READ_PERMISSION, true);
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testSetPermissionWithNegativePermission() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, -1, true);
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testSetPermissionWithLargeAccess() {
        ftpFile.setPermission(4, FTPFile.READ_PERMISSION, true);
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testSetPermissionWithLargePermission() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, 3, true);
    }

    @Test
    public void testGetPermission() {
        assertFalse(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }


    @Test
    public void testSetAndGetName() {
        assertNull(ftpFile.getName());
        ftpFile.setName("test.txt");
        assertEquals("test.txt", ftpFile.getName());
    }

    @Test
    public void testSetAndGetHardLinkCount() {
        assertEquals(0, ftpFile.getHardLinkCount());
        ftpFile.setHardLinkCount(5);
        assertEquals(5, ftpFile.getHardLinkCount());
    }

}
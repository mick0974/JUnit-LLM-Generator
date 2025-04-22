// FTPFile_hasPermissionTest.java
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
 * FTPFile#public hasPermission(int access, int permission) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_hasPermissionTest {
    private FTPFile ftpFile;

    @Before
    public void setUp() throws Exception {
        ftpFile = new FTPFile();
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("testUser");
        ftpFile.setGroup("testGroup");
        ftpFile.setSize(1024L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);
        ftpFile.setName("testFile.txt");
    }

    @After
    public void tearDown() throws Exception {
        ftpFile = null;
    }

    @Test
    public void testHasPermission_User_Read() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void testHasPermission_Group_Write() {
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    @Test
    public void testHasPermission_World_Exec() {
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    @Test
    public void testHasPermission_User_NoRead() {
        assertFalse(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void testHasPermission_Group_NoWrite() {
        assertFalse(ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    @Test
    public void testHasPermission_World_NoExec() {
        assertFalse(ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    @Test
    public void testHasPermission_Invalid_Access() {
        try {
            ftpFile.hasPermission(-1, FTPFile.READ_PERMISSION);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected exception
        }
    }

    @Test
    public void testHasPermission_Invalid_Permission() {
        try {
            ftpFile.hasPermission(FTPFile.USER_ACCESS, -1);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected exception
        }
    }

    @Test
    public void testHasPermission_NotValidEntry() {
        FTPFile invalidFtpFile = new FTPFile("invalidEntry");
        assertFalse(invalidFtpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void testHasPermission_DefaultPermissions() {
        assertFalse(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
        assertFalse(ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
        assertFalse(ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }
}
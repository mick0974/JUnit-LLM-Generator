// FTPFile_setPermissionTest.java
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
 * FTPFile#public setPermission(int access, int permission, boolean value) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setPermissionTest {
    private FTPFile ftpFile;

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setName("test.txt");
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void testSetPermission_ReadUserTrue() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void testSetPermission_WriteUserFalse() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, false);
        assertFalse(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    @Test
    public void testSetPermission_ExecuteUserTrue() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    @Test
    public void testSetPermission_ReadGroupTrue() {
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void testSetPermission_WriteGroupFalse() {
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, false);
        assertFalse(ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    @Test
    public void testSetPermission_ExecuteGroupTrue() {
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    @Test
    public void testSetPermission_ReadWorldTrue() {
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void testSetPermission_WriteWorldFalse() {
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, false);
        assertFalse(ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    @Test
    public void testSetPermission_ExecuteWorldTrue() {
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }
}
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
    public void setUp() {
        ftpFile = new FTPFile();
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);

        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, false);
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);

        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, false);

        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, false);
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, false);
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void testHasPermission_User_Read() {
        assertTrue(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void testHasPermission_User_Write() {
        assertFalse(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    @Test
    public void testHasPermission_User_Execute() {
        assertTrue(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    @Test
    public void testHasPermission_Group_Read() {
        assertTrue(ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void testHasPermission_Group_Write() {
        assertTrue(ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    @Test
    public void testHasPermission_Group_Execute() {
        assertFalse(ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    @Test
    public void testHasPermission_World_Read() {
        assertFalse(ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void testHasPermission_World_Write() {
        assertFalse(ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    @Test
    public void testHasPermission_World_Execute() {
        assertTrue(ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }
}
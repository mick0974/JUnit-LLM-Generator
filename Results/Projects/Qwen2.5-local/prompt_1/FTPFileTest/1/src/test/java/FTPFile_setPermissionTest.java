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
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void test_setPermission_validUserAccessReadPermission_true() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void test_setPermission_validUserAccessWritePermission_false() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, false);
        assertFalse(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    @Test
    public void test_setPermission_validUserAccessExecutePermission_true() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    @Test
    public void test_setPermission_validGroupAccessReadPermission_true() {
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test
    public void test_setPermission_validWorldAccessReadPermission_false() {
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, false);
        assertFalse(ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test_setPermission_invalidAccess_readPermission() {
        ftpFile.setPermission(-1, FTPFile.READ_PERMISSION, true);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test_setPermission_validAccess_invalidPermission() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, -1, true);
    }


    @Test
    public void test_setPermission_noChange() {
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        assertTrue(ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }
}
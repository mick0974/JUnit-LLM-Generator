// FTPFile_toFormattedStringTest.java
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
 * FTPFile#public toFormattedString(final String timezone) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_toFormattedStringTest {
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
    public void testToFormattedStringWithValidEntry() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, FTPFile.READ_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(false, FTPFile.WRITE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(true, FTPFile.EXECUTE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);

        assertEquals("-rw-r--r--   1 user     group      1024 Jan 01 12:00:00 2021 [file]", ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedStringWithDirectoryEntry() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        ftpFile.setPermissions(true, FTPFile.READ_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(false, FTPFile.WRITE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(true, FTPFile.EXECUTE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(0L); // Directory size is typically zero
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);

        assertEquals("drwxr-xr-x   1 user     group           0 Jan 01 12:00:00 2021 [directory]", ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedStringWithSymlinkEntry() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setPermissions(true, FTPFile.READ_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(false, FTPFile.WRITE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(true, FTPFile.EXECUTE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setLink("target");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);

        assertEquals("lrwxrwxrwx   1 user     group           5 Jan 01 12:00:00 2021 -> target [symlink]", ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedStringWithNoTimezone() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, FTPFile.READ_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(false, FTPFile.WRITE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(true, FTPFile.EXECUTE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);

        assertEquals("-rw-r--r--   1 user     group      1024 Jan 01 12:00:00 2021 [file]", ftpFile.toFormattedString(null));
    }

    @Test
    public void testToFormattedStringWithDifferentTimezone() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, FTPFile.READ_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(false, FTPFile.WRITE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(true, FTPFile.EXECUTE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1, 12, 0, 0);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        ftpFile.setTimestamp(calendar);

        assertEquals("-rw-r--r--   1 user     group      1024 Jan 01 12:00 UTC [file]", ftpFile.toFormattedString("UTC"));
    }

    @Test
    public void testToFormattedStringWithNullEntry() {
        ftpFile = new FTPFile();

        assertEquals("[Invalid: could not parse file entry]", ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedStringWithEmptyName() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, FTPFile.READ_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(false, FTPFile.WRITE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(true, FTPFile.EXECUTE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);
        ftpFile.setName("");

        assertEquals("-rw-r--r--   1 user     group      1024 Jan 01 12:00:00 2021 [file]", ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedStringWithNegativeSize() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, FTPFile.READ_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(false, FTPFile.WRITE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(true, FTPFile.EXECUTE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(-1L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);

        assertEquals("-rw-r--r--   1 user     group           -1 Jan 01 12:00:00 2021 [file]", ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedStringWithNullUser() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, FTPFile.READ_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(false, FTPFile.WRITE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(true, FTPFile.EXECUTE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser(null);
        ftpFile.setGroup("group");
        ftpFile.setSize(1024L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);

        assertEquals("-rw-r--r--   1?        group      1024 Jan 01 12:00:00 2021 [file]", ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedStringWithNullGroup() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, FTPFile.READ_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(false, FTPFile.WRITE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setPermissions(true, FTPFile.EXECUTE_PERMISSION, FTPFile.USER_ACCESS);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup(null);
        ftpFile.setSize(1024L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);

        assertEquals("-rw-r--r--   1 user    ?          1024 Jan 01 12:00:00 2021 [file]", ftpFile.toFormattedString());
    }
}
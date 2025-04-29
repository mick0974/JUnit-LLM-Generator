import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class FTPFileTest {

    @Test
    public void testConstructorCreatesEmptyFTPFile() {
        FTPFile ftpFile = new FTPFile();
        assertEquals(UNKNOWN_TYPE, ftpFile.getType());
        assertTrue(ftpFile.getPermissions()[USER_ACCESS][READ_PERMISSION]);
        assertNull(ftpFile.getRawListing());
        assertEquals("", ftpFile.getUser());
        assertEquals("", ftpFile.getGroup());
        assertNull(ftpFile.getTimestamp());
        assertNull(ftpFile.getName());
    }

    @Test
    public void testConstructorWithRawListingCreatesInvalidFTPFile() {
        FTPFile ftpFile = new FTPFile("invalid listing");
        assertFalse(ftpFile.isValid());
        assertEquals("invalid listing", ftpFile.getRawListing());
    }

    @Test
    public void testSetAndGetRawListing() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setRawListing("valid listing");
        assertEquals("valid listing", ftpFile.getRawListing());
    }

    @Test
    public void testIsFileType() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(FILE_TYPE);
        assertTrue(ftpFile.isFile());
        assertFalse(ftpFile.isDirectory());
        assertFalse(ftpFile.isSymbolicLink());
        assertFalse(ftpFile.isUnknown());
    }

    @Test
    public void testIsDirectoryType() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(DIRECTORY_TYPE);
        assertFalse(ftpFile.isFile());
        assertTrue(ftpFile.isDirectory());
        assertFalse(ftpFile.isSymbolicLink());
        assertFalse(ftpFile.isUnknown());
    }

    @Test
    public void testIsSymbolicLinkType() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(SYMBOLIC_LINK_TYPE);
        assertFalse(ftpFile.isFile());
        assertFalse(ftpFile.isDirectory());
        assertTrue(ftpFile.isSymbolicLink());
        assertFalse(ftpFile.isUnknown());
    }

    @Test
    public void testIsUnknownType() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(UNKNOWN_TYPE);
        assertFalse(ftpFile.isFile());
        assertFalse(ftpFile.isDirectory());
        assertFalse(ftpFile.isSymbolicLink());
        assertTrue(ftpFile.isUnknown());
    }

    @Test
    public void testIsValid() {
        FTPFile ftpFile = new FTPFile();
        assertFalse(ftpFile.isValid());

        ftpFile.setRawListing("valid listing");
        assertTrue(ftpFile.isValid());
    }

    @Test
    public void testSetAndGetName() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setName("test.txt");
        assertEquals("test.txt", ftpFile.getName());
    }

    @Test
    public void testSetAndGetUser() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setUser("user1");
        assertEquals("user1", ftpFile.getUser());
    }

    @Test
    public void testSetAndGetGroup() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setGroup("group1");
        assertEquals("group1", ftpFile.getGroup());
    }

    @Test
    public void testSetAndGetLink() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setLink("target.txt");
        assertEquals("target.txt", ftpFile.getLink());
    }

    @Test
    public void testSetAndGetHardLinkCount() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setHardLinkCount(5);
        assertEquals(5, ftpFile.getHardLinkCount());
    }

    @Test
    public void testSetAndGetFileSize() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setSize(1024);
        assertEquals(1024, ftpFile.getSize());
    }

    @Test
    public void testSetAndGetTimestamp() {
        FTPFile ftpFile = new FTPFile();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        ftpFile.setTimestamp(calendar);
        assertEquals(calendar, ftpFile.getTimestamp());
    }

    @Test
    public void testSetAndGetPermissions() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setPermission(USER_ACCESS, READ_PERMISSION, true);
        ftpFile.setPermission(GROUP_ACCESS, WRITE_PERMISSION, true);
        ftpFile.setPermission(WORLD_ACCESS, EXECUTE_PERMISSION, true);

        assertTrue(ftpFile.hasPermission(USER_ACCESS, READ_PERMISSION));
        assertTrue(ftpFile.hasPermission(GROUP_ACCESS, WRITE_PERMISSION));
        assertTrue(ftpFile.hasPermission(WORLD_ACCESS, EXECUTE_PERMISSION));

        ftpFile.setPermission(USER_ACCESS, READ_PERMISSION, false);
        assertFalse(ftpFile.hasPermission(USER_ACCESS, READ_PERMISSION));
    }

    @Test
    public void testToString() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setRawListing("valid listing");
        assertEquals("valid listing", ftpFile.toString());
    }

    @Test
    public void testToFormattedStringWithoutTimezone() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setRawListing("valid listing");
        ftpFile.setType(FILE_TYPE);
        ftpFile.setPermission(USER_ACCESS, READ_PERMISSION, true);
        ftpFile.setPermission(GROUP_ACCESS, WRITE_PERMISSION, true);
        ftpFile.setPermission(WORLD_ACCESS, EXECUTE_PERMISSION, true);
        ftpFile.setHardLinkCount(5);
        ftpFile.setUser("user1");
        ftpFile.setGroup("group1");
        ftpFile.setSize(1024);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        ftpFile.setTimestamp(calendar);
        ftpFile.setName("test.txt");

        String formattedString = ftpFile.toFormattedString();
        assertNotNull(formattedString);
        assertTrue(formattedString.contains("valid listing"));
        assertTrue(formattedString.contains("-rw-r--r--"));
        assertTrue(formattedString.contains("   5"));
        assertTrue(formattedString.contains("user1"));
        assertTrue(formattedString.contains("group1"));
        assertTrue(formattedString.contains("     1024"));
        assertTrue(formattedString.contains("2022-01-01"));
        assertTrue(formattedString.contains("test.txt"));
    }

    @Test
    public void testToFormattedStringWithTimezone() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setRawListing("valid listing");
        ftpFile.setType(FILE_TYPE);
        ftpFile.setPermission(USER_ACCESS, READ_PERMISSION, true);
        ftpFile.setPermission(GROUP_ACCESS, WRITE_PERMISSION, true);
        ftpFile.setPermission(WORLD_ACCESS, EXECUTE_PERMISSION, true);
        ftpFile.setHardLinkCount(5);
        ftpFile.setUser("user1");
        ftpFile.setGroup("group1");
        ftpFile.setSize(1024);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        ftpFile.setTimestamp(calendar);
        ftpFile.setName("test.txt");

        String formattedString = ftpFile.toFormattedString("UTC");
        assertNotNull(formattedString);
        assertTrue(formattedString.contains("valid listing"));
        assertTrue(formattedString.contains("-rw-r--r--"));
        assertTrue(formattedString.contains("   5"));
        assertTrue(formattedString.contains("user1"));
        assertTrue(formattedString.contains("group1"));
        assertTrue(formattedString.contains("     1024"));
        assertTrue(formattedString.contains("2022-01-01"));
        assertTrue(formattedString.contains("test.txt"));
    }
}
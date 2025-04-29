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
 * FTPFile#public toFormattedString() method.
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
    public void testToFormattedString_validFileEntry() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setPermissions(false, true, true); // -wx
        ftpFile.setPermissions(false, false, true); // --x
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(calendar);

        String expectedOutput = "-rwxr-x--- 1 user group     1024 Jan  1 12:00 file";
        assertEquals(expectedOutput, ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedString_directoryEntry() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(2);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(4096L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.FEBRUARY, 15, 14, 30, 0);
        ftpFile.setTimestamp(calendar);

        String expectedOutput = "drwxr-x--- 2 user group     4096 Feb 15 14:30 dir";
        assertEquals(expectedOutput, ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedString_symbolicLinkEntry() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setLink("target_file");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.MARCH, 20, 16, 45, 0);
        ftpFile.setTimestamp(calendar);

        String expectedOutput = "lrwxrwxrwx 1 user group       12 Mar 20 16:45 sym -> target_file";
        assertEquals(expectedOutput, ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedString_noPermissions() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(false, false, false); // ---
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(512L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.APRIL, 25, 18, 0, 0);
        ftpFile.setTimestamp(calendar);

        String expectedOutput = "--- --- --- 1 user group      512 Apr 25 18:00 file";
        assertEquals(expectedOutput, ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedString_emptyUserAndGroup() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("");
        ftpFile.setGroup("");
        ftpFile.setSize(2048L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.MAY, 30, 20, 15, 0);
        ftpFile.setTimestamp(calendar);

        String expectedOutput = "-rwxrwxrwx 1   \t   \t 2048 May 30 20:15 file";
        assertEquals(expectedOutput, ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedString_invalidTimeZone() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(3072L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JUNE, 2, 22, 30, 0);
        ftpFile.setTimestamp(calendar);

        String expectedOutput = "-rwxrwxrwx 1 user group     3072 Jun  2 22:30 file";
        assertEquals(expectedOutput, ftpFile.toFormattedString("invalid_timezone"));
    }

    @Test
    public void testToFormattedString_nullTimeZone() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(4096L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JULY, 7, 24, 45, 0);
        ftpFile.setTimestamp(calendar);

        String expectedOutput = "-rwxrwxrwx 1 user group     4096 Jul  7 24:45 file";
        assertEquals(expectedOutput, ftpFile.toFormattedString((String) null));
    }

    @Test
    public void testToFormattedString_emptyName() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setName("");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 12, 0, 0, 0);
        ftpFile.setTimestamp(calendar);

        String expectedOutput = "-rwxrwxrwx 1 user group          0 Aug 12 00:00 ";
        assertEquals(expectedOutput, ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedString_nullCalendar() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(5120L);
        ftpFile.setTimestamp(null);

        String expectedOutput = "-rwxrwxrwx 1 user group     5120????-??-?????:??:?? file";
        assertEquals(expectedOutput, ftpFile.toFormattedString());
    }

    @Test
    public void testToFormattedString_unknownFileType() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(6144L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.SEPTEMBER, 17, 2, 15, 0);
        ftpFile.setTimestamp(calendar);

        String expectedOutput = "?rwxrwxrwx 1 user group     6144 Sep 17 02:15 file";
        assertEquals(expectedOutput, ftpFile.toFormattedString());
    }
}
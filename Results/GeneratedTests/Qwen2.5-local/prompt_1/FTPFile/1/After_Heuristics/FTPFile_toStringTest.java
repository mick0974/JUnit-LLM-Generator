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

    @Test
    public void testToString_withValidFile() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setPermissions(false, true, false); // -wx
        ftpFile.setPermissions(true, false, false); // --x
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024L);
        ftpFile.setTimestamp(createCalendar(2023, Calendar.OCTOBER, 5, 14, 30));

        assertEquals("-rwxr-x--- 1 user group   1024 Oct  5 14:30 file.txt", ftpFile.toString());
    }

    @Test
    public void testToString_withValidDirectory() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setPermissions(false, true, false); // -wx
        ftpFile.setPermissions(true, false, false); // --x
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(0L);
        ftpFile.setTimestamp(createCalendar(2023, Calendar.OCTOBER, 5, 14, 30));

        assertEquals("drwxr-x--- 1 user group      0 Oct  5 14:30 dir/", ftpFile.toString());
    }

    @Test
    public void testToString_withValidSymbolicLink() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setPermissions(false, true, false); // -wx
        ftpFile.setPermissions(true, false, false); // --x
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(0L);
        ftpFile.setTimestamp(createCalendar(2023, Calendar.OCTOBER, 5, 14, 30));
        ftpFile.setLink("target");

        assertEquals("lrwxr-x--- 1 user group      0 Oct  5 14:30 -> target", ftpFile.toString());
    }

    @Test
    public void testToString_withInvalidFile() {
        FTPFile invalidFtpFile = new FTPFile("invalid entry");
        assertEquals("[Invalid: could not parse file entry]", invalidFtpFile.toString());
    }

    @Test
    public void testToString_withNullTimestamp() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024L);
        ftpFile.setTimestamp(null);

        assertEquals("-rw-r--r-- 1 user group   1024          file.txt", ftpFile.toString());
    }

    @Test
    public void testToString_withEmptyUser() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024L);
        ftpFile.setTimestamp(createCalendar(2023, Calendar.OCTOBER, 5, 14, 30));

        assertEquals("-rwxr-x--- 1        group   1024 Oct  5 14:30 file.txt", ftpFile.toString());
    }

    @Test
    public void testToString_withEmptyGroup() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("");
        ftpFile.setSize(1024L);
        ftpFile.setTimestamp(createCalendar(2023, Calendar.OCTOBER, 5, 14, 30));

        assertEquals("-rwxr-x--- 1 user           1024 Oct  5 14:30 file.txt", ftpFile.toString());
    }

    @Test
    public void testToString_withNegativeSize() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(-1L);
        ftpFile.setTimestamp(createCalendar(2023, Calendar.OCTOBER, 5, 14, 30));

        assertEquals("-rwxr-x--- 1 user group       -1 Oct  5 14:30 file.txt", ftpFile.toString());
    }

    @Test
    public void testToString_withLargeSize() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(Long.MAX_VALUE);
        ftpFile.setTimestamp(createCalendar(2023, Calendar.OCTOBER, 5, 14, 30));

        assertEquals("-rwxr-x--- 1 user group 9223372036854775807 Oct  5 14:30 file.txt", ftpFile.toString());
    }

    @Test
    public void testToString_withNonStandardTimeFormat() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setPermissions(true, true, true); // rwx
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setSize(1024L);
        ftpFile.setTimestamp(createCalendar(2023, Calendar.OCTOBER, 5, 14, 30));
        ftpFile.setRawListing("file.txt");

        assertEquals("[Invalid: could not parse file entry]", ftpFile.toString());
    }

    /**
     * Helper method to create a calendar instance with specific date/time values.
     * @param year Year component
     * @param month Month component
     * @param day Day component
     * @param hour Hour component
     * @param minute Minute component
     * @return Calendar instance initialized with specified values
     */
    private Calendar createCalendar(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, 0);
        return calendar;
    }
}
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class FTPFileTest {


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
    public void testToString() {
        FTPFile ftpFile = new FTPFile();
        ftpFile.setRawListing("valid listing");
        assertEquals("valid listing", ftpFile.toString());
    }


}
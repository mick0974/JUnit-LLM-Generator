// FTPFile_isValidTest.java
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
 * FTPFile#public isValid() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isValidTest {
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
    public void testIsValid_withValidEntry() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setName("test.txt");
        ftpFile.setSize(1024);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        ftpFile.setTimestamp(calendar);

        // Act & Assert
        assertTrue(ftpFile.isValid());
    }

    @Test
    public void testIsValid_withInvalidEntry() {
        // Arrange
        FTPFile ftpFileWithInvalidEntry = new FTPFile("invalid_entry");

        // Act & Assert
        assertFalse(ftpFileWithInvalidEntry.isValid());
    }
    
}
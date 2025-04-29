// FTPFile_getTimestampTest.java
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
 * FTPFile#public getTimestamp() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getTimestampTest {
    private FTPFile ftpFile;
    
    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }
    
    @After
    public void tearDown() {
        ftpFile = null;
    }
    
    @Test(expected=NullPointerException.class)
    public void testGetTimestamp_NullCalendar() {
        ftpFile.setTimestamp(null);
        ftpFile.getTimestamp(); // Should throw NullPointerException
    }

    @Test
    public void testGetTimestamp_SetCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        ftpFile.setTimestamp(calendar);
        Calendar result = ftpFile.getTimestamp();
        assertEquals(calendar, result);
    }
    
    @Test
    public void testGetTimestamp_UnsetCalendar() {
        Calendar result = ftpFile.getTimestamp();
        assertNull(result);
    }
}
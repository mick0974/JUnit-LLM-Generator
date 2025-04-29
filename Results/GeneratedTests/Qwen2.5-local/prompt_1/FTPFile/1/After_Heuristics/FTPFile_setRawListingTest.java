// FTPFile_setRawListingTest.java
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
 * FTPFile#public setRawListing(String rawListing) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setRawListingTest {
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
    public void testSetRawListing_validInput() {
        String input = "drwxr-xr-x 2 user group 4096 Jan 1 12:34:56 /path/to/file";
        ftpFile.setRawListing(input);
        assertEquals(input, ftpFile.getRawListing());
    }

    @Test(expected = NullPointerException.class)
    public void testSetRawListing_nullInput() {
        ftpFile.setRawListing(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRawListing_emptyInput() {
        ftpFile.setRawListing("");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetRawListing_spaceOnlyInput() {
        ftpFile.setRawListing(" ");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetRawListing_invalidFormatInput() {
        String input = "invalid format";
        ftpFile.setRawListing(input);
        assertNotEquals(input, ftpFile.getRawListing());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetRawListing_afterParsingFailed() {
        String input = "invalid format";
        ftpFile.setRawListing(input); // Parsing fails
        String anotherInput = "valid format";
        ftpFile.setRawListing(anotherInput); // Attempting to set again should throw exception
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetRawListing_afterSettingRawListingTwice() {
        String input1 = "valid format 1";
        ftpFile.setRawListing(input1);
        String input2 = "valid format 2";
        ftpFile.setRawListing(input2); // Setting again should throw exception
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetRawListing_afterCallingMethods() {
        String input = "valid format";
        ftpFile.setRawListing(input);
        
        // Call some methods
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setName("test.txt");
        ftpFile.setSize(1024);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        ftpFile.setTimestamp(calendar);

        // Try setting raw listing again
        String anotherInput = "another valid format";
        ftpFile.setRawListing(anotherInput); // Should throw exception
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetRawListing_afterSettingPermissions() {
        String input = "valid format";
        ftpFile.setRawListing(input);
        
        // Set permissions
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, false);
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);

        // Try setting raw listing again
        String anotherInput = "another valid format";
        ftpFile.setRawListing(anotherInput); // Should throw exception
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetRawListing_afterSettingAllFields() {
        String input = "valid format";
        ftpFile.setRawListing(input);
        
        // Set all fields
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setName("test.txt");
        ftpFile.setSize(1024);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        ftpFile.setTimestamp(calendar);
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, false);
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);

        // Try setting raw listing again
        String anotherInput = "another valid format";
        ftpFile.setRawListing(anotherInput); // Should throw exception
    }
}
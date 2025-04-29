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
    public void setUp() {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void testIsValid_WhenEntryIsNotParsedSuccessfully() {
        // Arrange
        FTPFile invalidFtpFile = new FTPFile("This is an invalid listing");

        // Act
        boolean result = invalidFtpFile.isValid();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsValid_WhenEntryIsParsedSuccessfully() {
        // Arrange
        FTPFile validFtpFile = new FTPFile();
        validFtpFile.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = validFtpFile.isValid();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsValid_WhenPermissionsIsNull() {
        // Arrange
        FTPFile ftpFileWithNullPermissions = new FTPFile();
        ftpFileWithNullPermissions._permissions = null;

        // Act
        boolean result = ftpFileWithNullPermissions.isValid();

        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testIsValid_WhenAllFieldsAreSet() {
        // Arrange
        FTPFile ftpFileWithAllFieldsSet = new FTPFile();
        ftpFileWithAllFieldsSet.setType(FTPFile.FILE_TYPE);
        ftpFileWithAllFieldsSet.setHardLinkCount(5);
        ftpFileWithAllFieldsSet.setSize(1024);
        ftpFileWithAllFieldsSet.setUser("user");
        ftpFileWithAllFieldsSet.setGroup("group");
        ftpFileWithAllFieldsSet.setName("file.txt");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFileWithAllFieldsSet.setTimestamp(calendar);

        // Act
        boolean result = ftpFileWithAllFieldsSet.isValid();

        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testIsValid_WhenOnlyTypeIsSet() {
        // Arrange
        FTPFile ftpFileWithTypeSet = new FTPFile();
        ftpFileWithTypeSet.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = ftpFileWithTypeSet.isValid();

        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testIsValid_WhenNoFieldsAreSet() {
        // Arrange
        FTPFile ftpFileWithNoFieldsSet = new FTPFile();

        // Act
        boolean result = ftpFileWithNoFieldsSet.isValid();

        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testIsValid_WhenDateIsSet() {
        // Arrange
        FTPFile ftpFileWithDateSet = new FTPFile();
        ftpFileWithDateSet.setType(FTPFile.FILE_TYPE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFileWithDateSet.setTimestamp(calendar);

        // Act
        boolean result = ftpFileWithDateSet.isValid();

        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testIsValid_WhenLinkIsSet() {
        // Arrange
        FTPFile ftpFileWithLinkSet = new FTPFile();
        ftpFileWithLinkSet.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFileWithLinkSet.setLink("target.txt");

        // Act
        boolean result = ftpFileWithLinkSet.isValid();

        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testIsValid_WhenGroupNameIsSet() {
        // Arrange
        FTPFile ftpFileWithGroupNameSet = new FTPFile();
        ftpFileWithGroupNameSet.setType(FTPFile.FILE_TYPE);
        ftpFileWithGroupNameSet.setGroup("group");

        // Act
        boolean result = ftpFileWithGroupNameSet.isValid();

        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testIsValid_WhenUserNameIsSet() {
        // Arrange
        FTPFile ftpFileWithUserNameSet = new FTPFile();
        ftpFileWithUserNameSet.setType(FTPFile.FILE_TYPE);
        ftpFileWithUserNameSet.setUser("user");

        // Act
        boolean result = ftpFileWithUserNameSet.isValid();

        // Assert
        assertTrue(result);
    }
}
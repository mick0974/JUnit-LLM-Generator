```java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for FTPFile.
 * Contains unit test cases specifically for the FTPFile#isFile() method.
 * Tests follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 */
public class FTPFile_isFileTest {

    private FTPFile ftpFile;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     * Initializes a new FTPFile instance.
     */
    @Before
    public void setUp() {
        ftpFile = new FTPFile(); // Creates an instance with default UNKNOWN_TYPE
    }

    /**
     * Test case 1: isFile() should return true when the type is explicitly set to FILE_TYPE.
     */
    @Test
    public void testIsFileWhenTypeIsFile() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertTrue("isFile() should return true when type is FILE_TYPE", result);
    }

    /**
     * Test case 2: isFile() should return false when the type is explicitly set to DIRECTORY_TYPE.
     */
    @Test
    public void testIsFileWhenTypeIsDirectory() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false when type is DIRECTORY_TYPE", result);
    }

    /**
     * Test case 3: isFile() should return false when the type is explicitly set to SYMBOLIC_LINK_TYPE.
     */
    @Test
    public void testIsFileWhenTypeIsSymbolicLink() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false when type is SYMBOLIC_LINK_TYPE", result);
    }

    /**
     * Test case 4: isFile() should return false when the type is the default UNKNOWN_TYPE
     * (as set by the default constructor).
     */
    @Test
    public void testIsFileWhenTypeIsUnknownDefault() {
        // Arrange (Default state from setUp)
        // ftpFile = new FTPFile(); // Type is UNKNOWN_TYPE by default

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false when type is the default UNKNOWN_TYPE", result);
        assertEquals("Default type should be UNKNOWN_TYPE", FTPFile.UNKNOWN_TYPE, ftpFile.getType());
    }

    /**
     * Test case 5: isFile() should return false when the type is explicitly set to UNKNOWN_TYPE.
     */
    @Test
    public void testIsFileWhenTypeIsUnknownExplicit() {
        // Arrange
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false when type is explicitly UNKNOWN_TYPE", result);
    }

    /**
     * Test case 6: isFile() should return false for an invalid FTPFile instance created
     * using the constructor for failed parses. The type defaults to UNKNOWN_TYPE.
     */
    @Test
    public void testIsFileWhenInstanceIsInvalid() {
        // Arrange
        FTPFile invalidFile = new FTPFile("This is a raw listing that failed parse");
        // Note: invalidFile.isValid() would be false, type is UNKNOWN_TYPE

        // Act
        boolean result = invalidFile.isFile();

        // Assert
        assertFalse("isFile() should return false for an invalid instance", result);
        assertEquals("Type should be UNKNOWN_TYPE for invalid instance", FTPFile.UNKNOWN_TYPE, invalidFile.getType());
    }

    /**
     * Test case 7: isFile() should return false when the type is set to a negative integer value.
     */
    @Test
    public void testIsFileWhenTypeIsNegativeValue() {
        // Arrange
        ftpFile.setType(-1); // Arbitrary negative value

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false when type is a negative value", result);
    }

    /**
     * Test case 8: isFile() should return false when the type is set to an integer value
     * different from FILE_TYPE and other defined constants.
     */
    @Test
    public void testIsFileWhenTypeIsOtherValue() {
        // Arrange
        ftpFile.setType(100); // Arbitrary value not matching defined types

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false when type is an arbitrary non-FILE_TYPE value", result);
    }

    /**
     * Test case 9: isFile() should return false after the type is changed from FILE_TYPE
     * to DIRECTORY_TYPE.
     */
    @Test
    public void testIsFileAfterChangingFromFileType() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertTrue("isFile() should initially be true", ftpFile.isFile());
        ftpFile.setType(FTPFile.DIRECTORY_TYPE); // Change type

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false after changing type from FILE_TYPE to DIRECTORY_TYPE", result);
    }

    /**
     * Test case 10: isFile() should return true after the type is changed from DIRECTORY_TYPE
     * to FILE_TYPE.
     */
    @Test
    public void testIsFileAfterChangingToFileType() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertFalse("isFile() should initially be false", ftpFile.isFile());
        ftpFile.setType(FTPFile.FILE_TYPE); // Change type

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertTrue("isFile() should return true after changing type from DIRECTORY_TYPE to FILE_TYPE", result);
    }
}
```
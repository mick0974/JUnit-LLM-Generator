import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the isFile() method of the FTPFile class.
 * It follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 * Uses JUnit 4 annotations.
 */
public class FTPFile_isFileTest {

    private FTPFile ftpFile;

    @Before
    public void setUp() {
        // Initialize a new FTPFile instance before each test
        ftpFile = new FTPFile();
    }

    /**
     * Test case 1: isFile() should return true when the type is explicitly set to FILE_TYPE.
     */
    @Test
    public void testIsFile_WhenTypeIsFile_ShouldReturnTrue() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertTrue("isFile() should return true for FILE_TYPE", result);
    }

    /**
     * Test case 2: isFile() should return false when the type is set to DIRECTORY_TYPE.
     */
    @Test
    public void testIsFile_WhenTypeIsDirectory_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false for DIRECTORY_TYPE", result);
    }

    /**
     * Test case 3: isFile() should return false when the type is set to SYMBOLIC_LINK_TYPE.
     */
    @Test
    public void testIsFile_WhenTypeIsSymbolicLink_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false for SYMBOLIC_LINK_TYPE", result);
    }

    /**
     * Test case 4: isFile() should return false when the type is set to UNKNOWN_TYPE (explicitly).
     */
    @Test
    public void testIsFile_WhenTypeIsUnknown_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false for UNKNOWN_TYPE", result);
    }

    /**
     * Test case 5: isFile() should return false for a default FTPFile object (type is UNKNOWN_TYPE by default).
     */
    @Test
    public void testIsFile_WhenDefaultConstructor_ShouldReturnFalse() {
        // Arrange: ftpFile is already created with default constructor in setUp()

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false for a default FTPFile instance", result);
    }

    /**
     * Test case 6: isFile() should return false when using the constructor for failed parses (invalid file).
     * The type defaults to UNKNOWN_TYPE in this constructor.
     */
    @Test
    public void testIsFile_WhenInvalidFileConstructor_ShouldReturnFalse() {
        // Arrange
        FTPFile invalidFile = new FTPFile("some raw listing that failed parse");

        // Act
        boolean result = invalidFile.isFile();

        // Assert
        assertFalse("isFile() should return false for an FTPFile created for a failed parse", result);
        assertEquals("Type should be UNKNOWN_TYPE for invalid file", FTPFile.UNKNOWN_TYPE, invalidFile.getType());
    }

    /**
     * Test case 7: isFile() should return true after changing type from default (UNKNOWN) to FILE_TYPE.
     */
    @Test
    public void testIsFile_WhenTypeChangedToFromFile_ShouldReturnTrue() {
        // Arrange: ftpFile starts as UNKNOWN_TYPE
        ftpFile.setType(FTPFile.FILE_TYPE); // Change type

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertTrue("isFile() should return true after changing type to FILE_TYPE", result);
    }

    /**
     * Test case 8: isFile() should return false after changing type from FILE_TYPE to DIRECTORY_TYPE.
     */
    @Test
    public void testIsFile_WhenTypeChangedFromFileToDirectory_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE); // Set initial type to FILE
        assertTrue("Precondition: isFile() should be true", ftpFile.isFile());
        ftpFile.setType(FTPFile.DIRECTORY_TYPE); // Change type

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false after changing type from FILE_TYPE to DIRECTORY_TYPE", result);
    }

    /**
     * Test case 9: isFile() should return false when type is set to an arbitrary integer value not matching any constant.
     */
    @Test
    public void testIsFile_WhenTypeIsArbitraryValue_ShouldReturnFalse() {
        // Arrange
        int arbitraryType = 99; // A value not equal to FILE_TYPE, DIRECTORY_TYPE, etc.
        ftpFile.setType(arbitraryType);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false for an arbitrary type value", result);
    }

     /**
     * Test case 10: isFile() should return false after setting type to FILE_TYPE then back to UNKNOWN_TYPE.
     */
    @Test
    public void testIsFile_WhenTypeChangedFromFileToUnknown_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE); // Set initial type to FILE
        assertTrue("Precondition: isFile() should be true", ftpFile.isFile());
        ftpFile.setType(FTPFile.UNKNOWN_TYPE); // Change type back to UNKNOWN

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("isFile() should return false after changing type from FILE_TYPE back to UNKNOWN_TYPE", result);
    }
}
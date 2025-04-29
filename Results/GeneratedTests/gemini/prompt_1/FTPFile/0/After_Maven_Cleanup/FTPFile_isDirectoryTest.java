import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for FTPFile#isDirectory().
 * Contains 10 unit test cases ensuring the method correctly identifies
 * if an FTPFile instance represents a directory based on its type field.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 * Contains only valid Java code using JUnit 4.
 */
public class FTPFile_isDirectoryTest {

    /**
     * Test case: isDirectory() should return true when the type is explicitly set to DIRECTORY_TYPE.
     */
    @Test
    public void testIsDirectoryWhenTypeIsDirectory() {
        // Arrange
        FTPFile file = new FTPFile();
        file.setType(FTPFile.DIRECTORY_TYPE);

        // Act
        boolean result = file.isDirectory();

        // Assert
        assertTrue("isDirectory() should return true for DIRECTORY_TYPE", result);
    }

    /**
     * Test case: isDirectory() should return false when the type is explicitly set to FILE_TYPE.
     */
    @Test
    public void testIsDirectoryWhenTypeIsFile() {
        // Arrange
        FTPFile file = new FTPFile();
        file.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = file.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false for FILE_TYPE", result);
    }

    /**
     * Test case: isDirectory() should return false when the type is explicitly set to SYMBOLIC_LINK_TYPE.
     */
    @Test
    public void testIsDirectoryWhenTypeIsSymbolicLink() {
        // Arrange
        FTPFile file = new FTPFile();
        file.setType(FTPFile.SYMBOLIC_LINK_TYPE);

        // Act
        boolean result = file.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false for SYMBOLIC_LINK_TYPE", result);
    }

    /**
     * Test case: isDirectory() should return false when the type is UNKNOWN_TYPE (default for default constructor).
     */
    @Test
    public void testIsDirectoryWhenTypeIsUnknownDefault() {
        // Arrange
        FTPFile file = new FTPFile(); // Default type is UNKNOWN_TYPE

        // Act
        boolean result = file.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false for default UNKNOWN_TYPE", result);
    }

    /**
     * Test case: isDirectory() should return false when the type is explicitly set to UNKNOWN_TYPE.
     */
    @Test
    public void testIsDirectoryWhenTypeIsUnknownExplicit() {
        // Arrange
        FTPFile file = new FTPFile();
        file.setType(FTPFile.UNKNOWN_TYPE);

        // Act
        boolean result = file.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false for explicitly set UNKNOWN_TYPE", result);
    }

    /**
     * Test case: isDirectory() should return false for an instance created due to a failed parse.
     * The internal type is set to UNKNOWN_TYPE in this case.
     */
    @Test
    public void testIsDirectoryForInvalidParseInstance() {
        // Arrange
        FTPFile file = new FTPFile("raw listing data that failed parsing"); // Type defaults to UNKNOWN_TYPE

        // Act
        boolean result = file.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false for an instance created from a raw listing (invalid parse)", result);
        assertFalse("Instance created from raw listing should be invalid", file.isValid());
    }


    /**
     * Test case: isDirectory() should return false when the type is set to an arbitrary negative value.
     */
    @Test
    public void testIsDirectoryWhenTypeIsNegativeValue() {
        // Arrange
        FTPFile file = new FTPFile();
        file.setType(-1); // An invalid type value

        // Act
        boolean result = file.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false for a negative type value", result);
    }

    /**
     * Test case: isDirectory() should return false when the type is set to an arbitrary positive value
     * different from the defined constants.
     */
    @Test
    public void testIsDirectoryWhenTypeIsArbitraryPositiveValue() {
        // Arrange
        FTPFile file = new FTPFile();
        // Use a value distinct from FILE_TYPE, DIRECTORY_TYPE, SYMBOLIC_LINK_TYPE, UNKNOWN_TYPE
        file.setType(10);

        // Act
        boolean result = file.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false for an arbitrary positive type value", result);
    }

    /**
     * Test case: isDirectory() should return false after the type is changed from DIRECTORY_TYPE to FILE_TYPE.
     */
    @Test
    public void testIsDirectoryAfterChangingTypeFromDirectoryToFile() {
        // Arrange
        FTPFile file = new FTPFile();
        file.setType(FTPFile.DIRECTORY_TYPE); // Set initial type
        assertTrue("Sanity check: should be directory initially", file.isDirectory());
        file.setType(FTPFile.FILE_TYPE);       // Change type

        // Act
        boolean result = file.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false after type changed from DIRECTORY to FILE", result);
    }

    /**
     * Test case: isDirectory() should return true after the type is changed from FILE_TYPE to DIRECTORY_TYPE.
     */
    @Test
    public void testIsDirectoryAfterChangingTypeFromFileToDirectory() {
        // Arrange
        FTPFile file = new FTPFile();
        file.setType(FTPFile.FILE_TYPE);       // Set initial type
        assertFalse("Sanity check: should not be directory initially", file.isDirectory());
        file.setType(FTPFile.DIRECTORY_TYPE); // Change type

        // Act
        boolean result = file.isDirectory();

        // Assert
        assertTrue("isDirectory() should return true after type changed from FILE to DIRECTORY", result);
    }
}
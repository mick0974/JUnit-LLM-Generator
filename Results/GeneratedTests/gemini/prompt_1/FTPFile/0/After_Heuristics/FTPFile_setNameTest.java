import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the FTPFile#setName(String name) method.
 * Contains unit tests covering typical and edge cases.
 * Uses JUnit 4 annotations and follows the Arrange-Act-Assert pattern.
 */
public class FTPFile_setNameTest {

    private FTPFile ftpFile;

    @Before
    public void setUp() {
        // Arrange: Initialize a new FTPFile object before each test
        ftpFile = new FTPFile();
    }

    /**
     * Test setting a typical file name.
     */
    @Test
    public void testSetTypicalName() {
        // Arrange
        String expectedName = "file.txt";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals("The name should be set to 'file.txt'", expectedName, ftpFile.getName());
    }

    /**
     * Test setting a file name containing spaces.
     */
    @Test
    public void testSetNameWithSpaces() {
        // Arrange
        String expectedName = "my important file.dat";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals("The name should handle spaces correctly", expectedName, ftpFile.getName());
    }

    /**
     * Test setting a file name with various special characters.
     */
    @Test
    public void testSetNameWithSpecialCharacters() {
        // Arrange
        String expectedName = "file_1-@#$%^&().log";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals("The name should allow special characters", expectedName, ftpFile.getName());
    }

    /**
     * Test setting an empty string as the file name.
     */
    @Test
    public void testSetEmptyName() {
        // Arrange
        String expectedName = "";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals("The name should be settable to an empty string", expectedName, ftpFile.getName());
    }

    /**
     * Test setting null as the file name.
     */
    @Test
    public void testSetNullName() {
        // Arrange
        String expectedName = null;

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertNull("The name should be settable to null", ftpFile.getName());
    }

    /**
     * Test setting a relatively long file name.
     */
    @Test
    public void testSetLongName() {
        // Arrange
        String expectedName = "a_very_long_file_name_that_goes_on_and_on_for_quite_some_time_to_test_length_limits_if_any_exist_in_the_implementation_or_underlying_systems.extension";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals("The name should handle long strings", expectedName, ftpFile.getName());
    }

    /**
     * Test setting a file name with leading and trailing spaces.
     * Note: While settable, file systems often trim these. The test confirms the value is stored as set.
     */
    @Test
    public void testSetNameWithLeadingTrailingSpaces() {
        // Arrange
        String expectedName = "  spaced name  ";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals("The name should preserve leading/trailing spaces", expectedName, ftpFile.getName());
    }

    /**
     * Test setting a file name containing numbers.
     */
    @Test
    public void testSetNameWithNumbers() {
        // Arrange
        String expectedName = "archive_20231027_v1.zip";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals("The name should handle numbers correctly", expectedName, ftpFile.getName());
    }

    /**
     * Test setting a file name with mixed case characters.
     */
    @Test
    public void testSetNameWithMixedCase() {
        // Arrange
        String expectedName = "DocumentFile.TXT";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals("The name should preserve mixed case", expectedName, ftpFile.getName());
    }

    /**
     * Test overwriting a previously set name.
     */
    @Test
    public void testOverwriteExistingName() {
        // Arrange
        String initialName = "old_name.txt";
        String expectedName = "new_name.log";
        ftpFile.setName(initialName); // Set an initial name

        // Act
        ftpFile.setName(expectedName); // Overwrite with the new name

        // Assert
        assertEquals("The name should be updated to the new value", expectedName, ftpFile.getName());
    }
}
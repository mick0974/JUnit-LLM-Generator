```java
import org.junit.Test;
import static org.junit.Assert.*;

// No @Before or @After needed as each test creates its own fresh FTPFile instance.

/**
 * Test class for FTPFile#getName().
 * Contains 10 unit test cases focusing on retrieving the file name.
 * Follows the Arrange-Act-Assert pattern.
 */
public class FTPFile_getNameTest {

    /**
     * Test case: getName() when the name is null (default constructor).
     */
    @Test
    public void testGetNameWhenNullInitially() {
        // Arrange
        FTPFile file = new FTPFile();

        // Act
        String name = file.getName();

        // Assert
        assertNull("getName() should return null when no name is set", name);
    }

    /**
     * Test case: getName() after setting the name to a typical file name.
     */
    @Test
    public void testGetNameWithSimpleFileName() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedName = "document.txt";
        file.setName(expectedName);

        // Act
        String actualName = file.getName();

        // Assert
        assertEquals("getName() should return the set simple file name", expectedName, actualName);
    }

    /**
     * Test case: getName() after setting the name to a typical directory name.
     */
    @Test
    public void testGetNameWithSimpleDirectoryName() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedName = "my_directory";
        file.setName(expectedName);

        // Act
        String actualName = file.getName();

        // Assert
        assertEquals("getName() should return the set simple directory name", expectedName, actualName);
    }

    /**
     * Test case: getName() after setting the name to an empty string.
     */
    @Test
    public void testGetNameWithEmptyString() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedName = "";
        file.setName(expectedName);

        // Act
        String actualName = file.getName();

        // Assert
        assertEquals("getName() should return an empty string when set to empty", expectedName, actualName);
    }

    /**
     * Test case: getName() after setting the name to a value containing spaces.
     */
    @Test
    public void testGetNameWithSpaces() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedName = "file with spaces.dat";
        file.setName(expectedName);

        // Act
        String actualName = file.getName();

        // Assert
        assertEquals("getName() should handle names with spaces correctly", expectedName, actualName);
    }

    /**
     * Test case: getName() after setting the name to a value containing special characters.
     */
    @Test
    public void testGetNameWithSpecialCharacters() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedName = "file-v1.0_@#$%^&().zip";
        file.setName(expectedName);

        // Act
        String actualName = file.getName();

        // Assert
        assertEquals("getName() should handle names with special characters", expectedName, actualName);
    }

    /**
     * Test case: getName() after setting the name to a very long string.
     */
    @Test
    public void testGetNameWithLongName() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedName = "a_very_long_file_name_that_goes_on_and_on_and_on_for_testing_purposes_to_see_how_it_handles_length.longextension";
        file.setName(expectedName);

        // Act
        String actualName = file.getName();

        // Assert
        assertEquals("getName() should handle long names correctly", expectedName, actualName);
    }

    /**
     * Test case: getName() after setting the name, then setting it to null.
     * Although setName(null) is possible, the typical use is setting a non-null name.
     * This tests if it correctly returns the last set value.
     */
    @Test
    public void testGetNameAfterSettingToNull() {
        // Arrange
        FTPFile file = new FTPFile();
        file.setName("initial_name.tmp"); // Set an initial name
        file.setName(null);               // Set name to null

        // Act
        String actualName = file.getName();

        // Assert
        assertNull("getName() should return null after the name is explicitly set to null", actualName);
    }

    /**
     * Test case: getName() after setting the name to a value with leading/trailing whitespace.
     */
    @Test
    public void testGetNameWithLeadingTrailingWhitespace() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedName = "  padded name  ";
        file.setName(expectedName);

        // Act
        String actualName = file.getName();

        // Assert
        assertEquals("getName() should preserve leading/trailing whitespace", expectedName, actualName);
    }

    /**
     * Test case: getName() when using the constructor for invalid entries.
     * Even for invalid entries, the name field might be set during parsing attempts
     * before failure, or remain null. Here we test the initial state from this constructor.
     */
    @Test
    public void testGetNameForInvalidEntryConstructor() {
         // Arrange
        FTPFile file = new FTPFile("some raw listing that failed parsing");
        // Note: The name field is initialized to null in this constructor

        // Act
        String name = file.getName();

        // Assert
        assertNull("getName() should return null for an invalid entry constructed this way initially", name);
        // We could also test setting the name on an invalid entry, but getName itself
        // just returns the field's value, regardless of validity flag.
    }
}
```
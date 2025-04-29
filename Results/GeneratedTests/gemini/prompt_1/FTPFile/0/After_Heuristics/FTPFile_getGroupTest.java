import org.junit.Test;
import static org.junit.Assert.*;

// FTPFile is assumed to be in the same package or correctly imported
// No other imports beyond JUnit are needed for this specific test class.

/**
 * Test class for the {@link FTPFile#getGroup()} method.
 * Contains 10 unit test cases covering typical and edge scenarios.
 * Tests follow the Arrange-Act-Assert pattern.
 */
public class FTPFile_getGroupTest {

    /**
     * Test case: Getting the group from a default-constructed FTPFile.
     * Expectation: Should return an empty string as per the constructor's initialization.
     */
    @Test
    public void testGetGroup_DefaultConstructor() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedGroup = "";

        // Act
        String actualGroup = file.getGroup();

        // Assert
        assertEquals("Default group should be an empty string", expectedGroup, actualGroup);
        assertTrue("File should be valid by default", file.isValid());
    }

    /**
     * Test case: Setting a typical group name and retrieving it.
     * Expectation: Should return the exact string that was set.
     */
    @Test
    public void testGetGroup_SetStandardName() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedGroup = "users";
        file.setGroup(expectedGroup);

        // Act
        String actualGroup = file.getGroup();

        // Assert
        assertEquals("Should return the set standard group name", expectedGroup, actualGroup);
    }

    /**
     * Test case: Setting a group name that looks like a number (common in Unix).
     * Expectation: Should return the exact numeric string that was set.
     */
    @Test
    public void testGetGroup_SetNumericName() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedGroup = "1001";
        file.setGroup(expectedGroup);

        // Act
        String actualGroup = file.getGroup();

        // Assert
        assertEquals("Should return the set numeric group name", expectedGroup, actualGroup);
    }

    /**
     * Test case: Setting the group name to an empty string.
     * Expectation: Should return an empty string.
     */
    @Test
    public void testGetGroup_SetEmptyString() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedGroup = "";
        file.setGroup(expectedGroup); // Explicitly set to empty

        // Act
        String actualGroup = file.getGroup();

        // Assert
        assertEquals("Should return an empty string when set to empty", expectedGroup, actualGroup);
    }

    /**
     * Test case: Setting the group name to null.
     * Expectation: Should return null.
     */
    @Test
    public void testGetGroup_SetNull() {
        // Arrange
        FTPFile file = new FTPFile();
        file.setGroup("initialGroup"); // Set to non-null first
        file.setGroup(null);          // Now set to null

        // Act
        String actualGroup = file.getGroup();

        // Assert
        assertNull("Should return null when group is set to null", actualGroup);
    }

    /**
     * Test case: Setting a group name with leading and trailing whitespace.
     * Expectation: Should return the string including the whitespace.
     */
    @Test
    public void testGetGroup_SetWhitespace() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedGroup = "  spaced group  ";
        file.setGroup(expectedGroup);

        // Act
        String actualGroup = file.getGroup();

        // Assert
        assertEquals("Should preserve leading/trailing whitespace", expectedGroup, actualGroup);
    }

    /**
     * Test case: Setting a group name containing special characters (hyphen, underscore).
     * Expectation: Should return the exact string with special characters.
     */
    @Test
    public void testGetGroup_SetSpecialChars() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedGroup = "dev-ops_team";
        file.setGroup(expectedGroup);

        // Act
        String actualGroup = file.getGroup();

        // Assert
        assertEquals("Should handle special characters correctly", expectedGroup, actualGroup);
    }

    /**
     * Test case: Setting a very long group name.
     * Expectation: Should return the complete long string.
     */
    @Test
    public void testGetGroup_SetLongName() {
        // Arrange
        FTPFile file = new FTPFile();
        String expectedGroup = "thisIsAVeryLongGroupNameThatTestsPotentialLengthLimitsWhichShouldNotExistForStrings";
        file.setGroup(expectedGroup);

        // Act
        String actualGroup = file.getGroup();

        // Assert
        assertEquals("Should handle very long group names", expectedGroup, actualGroup);
    }

    /**
     * Test case: Setting a group name, then changing it.
     * Expectation: Should return the most recently set group name.
     */
    @Test
    public void testGetGroup_ResetValue() {
        // Arrange
        FTPFile file = new FTPFile();
        file.setGroup("oldGroup");
        String expectedGroup = "newGroup";
        file.setGroup(expectedGroup); // Overwrite the group

        // Act
        String actualGroup = file.getGroup();

        // Assert
        assertEquals("Should return the latest set group value", expectedGroup, actualGroup);
    }

    /**
     * Test case: Getting the group from an "invalid" FTPFile instance (created via specific constructor).
     * Expectation: Should return an empty string as initialized by that constructor, even if isValid() is false.
     */
    @Test
    public void testGetGroup_InvalidFileInstance() {
        // Arrange
        FTPFile file = new FTPFile("drwxr-xr-x 1 user group 4096 Jan 1 10:00 invalid"); // Raw listing doesn't matter here
        String expectedGroup = ""; // As per the FTPFile(String) constructor

        // Act
        String actualGroup = file.getGroup();

        // Assert
        assertEquals("Group should be empty string for invalid file instance", expectedGroup, actualGroup);
        assertFalse("File instance should be invalid", file.isValid());
    }
}
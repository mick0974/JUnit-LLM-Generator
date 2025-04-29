```java
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the FTPFile#isDirectory() method.
 * It contains 10 unit test cases following the Arrange-Act-Assert pattern,
 * covering typical and edge cases for determining if an FTPFile represents a directory.
 */
public class FTPFile_isDirectoryTest {

    /**
     * Test case 1: File type is explicitly set to DIRECTORY_TYPE.
     * Expects isDirectory() to return true.
     */
    @Test
    public void testIsDirectory_WhenTypeIsDirectory_ShouldReturnTrue() {
        // Arrange
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertTrue("isDirectory() should return true when type is DIRECTORY_TYPE", result);
    }

    /**
     * Test case 2: File type is explicitly set to FILE_TYPE.
     * Expects isDirectory() to return false.
     */
    @Test
    public void testIsDirectory_WhenTypeIsFile_ShouldReturnFalse() {
        // Arrange
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false when type is FILE_TYPE", result);
    }

    /**
     * Test case 3: File type is explicitly set to SYMBOLIC_LINK_TYPE.
     * Expects isDirectory() to return false.
     */
    @Test
    public void testIsDirectory_WhenTypeIsSymbolicLink_ShouldReturnFalse() {
        // Arrange
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false when type is SYMBOLIC_LINK_TYPE", result);
    }

    /**
     * Test case 4: File type is the default UNKNOWN_TYPE (from the default constructor).
     * Expects isDirectory() to return false.
     */
    @Test
    public void testIsDirectory_WhenTypeIsUnknown_ShouldReturnFalse() {
        // Arrange
        FTPFile ftpFile = new FTPFile(); // Default type is UNKNOWN_TYPE

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false when type is UNKNOWN_TYPE", result);
    }

    /**
     * Test case 5: FTPFile created using the constructor for invalid entries.
     * The type defaults to UNKNOWN_TYPE.
     * Expects isDirectory() to return false.
     */
    @Test
    public void testIsDirectory_WhenEntryIsInvalid_ShouldReturnFalse() {
        // Arrange
        FTPFile ftpFile = new FTPFile("drwxr-xr-x 2 ftp ftp 4096 Dec 1 10:00 invalid-dir"); // Type remains UNKNOWN

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false for an invalid entry instance", result);
        // Although isValid() is false, isDirectory() still checks the type field.
        assertFalse("isValid() should be false", ftpFile.isValid());
        assertEquals("Type should be UNKNOWN", FTPFile.UNKNOWN_TYPE, ftpFile.getType());
    }

    /**
     * Test case 6: File type is initially FILE_TYPE, then changed to DIRECTORY_TYPE.
     * Expects isDirectory() to return true after the change.
     */
    @Test
    public void testIsDirectory_AfterChangingTypeToFileThenDirectory_ShouldReturnTrue() {
        // Arrange
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertFalse("Initially set to FILE_TYPE, isDirectory should be false", ftpFile.isDirectory());

        // Act
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        boolean result = ftpFile.isDirectory();

        // Assert
        assertTrue("After changing type to DIRECTORY_TYPE, isDirectory should return true", result);
    }

    /**
     * Test case 7: File type is initially DIRECTORY_TYPE, then changed to FILE_TYPE.
     * Expects isDirectory() to return false after the change.
     */
    @Test
    public void testIsDirectory_AfterChangingTypeToDirectoryThenFile_ShouldReturnFalse() {
        // Arrange
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertTrue("Initially set to DIRECTORY_TYPE, isDirectory should be true", ftpFile.isDirectory());

        // Act
        ftpFile.setType(FTPFile.FILE_TYPE);
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("After changing type to FILE_TYPE, isDirectory should return false", result);
    }

     /**
     * Test case 8: File type is set to DIRECTORY_TYPE on a fully populated FTPFile instance.
     * Expects isDirectory() to return true.
     */
    @Test
    public void testIsDirectory_WhenTypeIsDirectoryAndFullyPopulated_ShouldReturnTrue() {
        // Arrange
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        ftpFile.setName("mydir");
        ftpFile.setSize(4096);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setHardLinkCount(2);
        ftpFile.setTimestamp(Calendar.getInstance());
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setRawListing("drwxr-xr-x 2 user group 4096 Jan 1 00:00 mydir");

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertTrue("isDirectory() should return true for a fully populated directory entry", result);
    }

     /**
     * Test case 9: File type is set to an arbitrary integer value not matching any defined type.
     * Expects isDirectory() to return false.
     */
    @Test
    public void testIsDirectory_WhenTypeIsArbitraryValue_ShouldReturnFalse() {
        // Arrange
        FTPFile ftpFile = new FTPFile();
        ftpFile.setType(99); // Arbitrary value, not DIRECTORY_TYPE

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("isDirectory() should return false when type is an arbitrary non-directory value", result);
    }

    /**
     * Test case 10: File type is set to DIRECTORY_TYPE, but other fields remain default/null.
     * Expects isDirectory() to return true, as it only checks the type field.
     */
    @Test
    public void testIsDirectory_WhenTypeIsDirectoryAndMinimalSetup_ShouldReturnTrue() {
        // Arrange
        FTPFile ftpFile = new FTPFile(); // Defaults for most fields
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertTrue("isDirectory() should return true even with minimal setup if type is DIRECTORY_TYPE", result);
    }
}
```
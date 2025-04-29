import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the FTPFile#getType() method.
 * Focuses on verifying the correct return value after setting different file types.
 * Uses JUnit 4 framework.
 */
public class FTPFile_getTypeTest {

    private FTPFile ftpFile;

    @Before
    public void setUp() {
        // Arrange: Create a new FTPFile instance before each test
        ftpFile = new FTPFile();
    }

    /**
     * Test case 1: Verify the default type after using the default constructor.
     * The default type should be UNKNOWN_TYPE.
     */
    @Test
    public void testGetType_DefaultConstructor_ShouldReturnUnknown() {
        // Act
        int type = ftpFile.getType();

        // Assert
        assertEquals("Default type should be UNKNOWN_TYPE", FTPFile.UNKNOWN_TYPE, type);
    }

    /**
     * Test case 2: Verify the type after using the constructor for invalid listings.
     * The type should be UNKNOWN_TYPE.
     */
    @Test
    public void testGetType_InvalidListingConstructor_ShouldReturnUnknown() {
        // Arrange
        FTPFile invalidFile = new FTPFile("some raw listing that failed parsing");

        // Act
        int type = invalidFile.getType();

        // Assert
        assertEquals("Type for invalid listing constructor should be UNKNOWN_TYPE", FTPFile.UNKNOWN_TYPE, type);
    }


    /**
     * Test case 3: Verify getType() returns FILE_TYPE after setting the type to FILE_TYPE.
     */
    @Test
    public void testGetType_WhenSetToFileType_ShouldReturnFileType() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        int type = ftpFile.getType();

        // Assert
        assertEquals("Type should be FILE_TYPE", FTPFile.FILE_TYPE, type);
    }

    /**
     * Test case 4: Verify getType() returns DIRECTORY_TYPE after setting the type to DIRECTORY_TYPE.
     */
    @Test
    public void testGetType_WhenSetToDirectoryType_ShouldReturnDirectoryType() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Act
        int type = ftpFile.getType();

        // Assert
        assertEquals("Type should be DIRECTORY_TYPE", FTPFile.DIRECTORY_TYPE, type);
    }

    /**
     * Test case 5: Verify getType() returns SYMBOLIC_LINK_TYPE after setting the type to SYMBOLIC_LINK_TYPE.
     */
    @Test
    public void testGetType_WhenSetToSymbolicLinkType_ShouldReturnSymbolicLinkType() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);

        // Act
        int type = ftpFile.getType();

        // Assert
        assertEquals("Type should be SYMBOLIC_LINK_TYPE", FTPFile.SYMBOLIC_LINK_TYPE, type);
    }

    /**
     * Test case 6: Verify getType() returns UNKNOWN_TYPE after explicitly setting the type to UNKNOWN_TYPE.
     */
    @Test
    public void testGetType_WhenExplicitlySetToUnknownType_ShouldReturnUnknownType() {
        // Arrange
        // Set to something else first to ensure setType works
        ftpFile.setType(FTPFile.FILE_TYPE);
        // Now set explicitly to UNKNOWN_TYPE
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);


        // Act
        int type = ftpFile.getType();

        // Assert
        assertEquals("Type should be UNKNOWN_TYPE after explicitly setting it", FTPFile.UNKNOWN_TYPE, type);
    }

    /**
     * Test case 7: Verify getType() returns the latest type after changing it multiple times.
     * Change from default (UNKNOWN) -> FILE -> DIRECTORY.
     */
    @Test
    public void testGetType_AfterChangingTypeMultipleTimes_ShouldReturnLastSetType() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE); // Set to FILE
        ftpFile.setType(FTPFile.DIRECTORY_TYPE); // Change to DIRECTORY

        // Act
        int type = ftpFile.getType();

        // Assert
        assertEquals("Type should be the last set type (DIRECTORY_TYPE)", FTPFile.DIRECTORY_TYPE, type);
    }

    /**
     * Test case 8: Verify getType() returns the latest type after changing it multiple times.
     * Change from default (UNKNOWN) -> DIRECTORY -> SYMBOLIC_LINK.
     */
    @Test
    public void testGetType_AfterChangingTypeAgain_ShouldReturnLastSetType() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE); // Set to DIRECTORY
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE); // Change to SYMBOLIC_LINK

        // Act
        int type = ftpFile.getType();

        // Assert
        assertEquals("Type should be the last set type (SYMBOLIC_LINK_TYPE)", FTPFile.SYMBOLIC_LINK_TYPE, type);
    }

    /**
     * Test case 9: Test setting a type value outside the predefined constants (e.g., negative).
     * Although not typical, getType should return the value set.
     */
    @Test
    public void testGetType_WhenSetToNegativeValue_ShouldReturnNegativeValue() {
        // Arrange
        int negativeType = -1;
        ftpFile.setType(negativeType);

        // Act
        int type = ftpFile.getType();

        // Assert
        assertEquals("getType should return the set negative value", negativeType, type);
    }

    /**
     * Test case 10: Test setting a type value outside the predefined constants (e.g., large positive).
     * Although not typical, getType should return the value set.
     */
    @Test
    public void testGetType_WhenSetToLargeValue_ShouldReturnLargeValue() {
        // Arrange
        int largeType = 100;
        ftpFile.setType(largeType);

        // Act
        int type = ftpFile.getType();

        // Assert
        assertEquals("getType should return the set large value", largeType, type);
    }

}
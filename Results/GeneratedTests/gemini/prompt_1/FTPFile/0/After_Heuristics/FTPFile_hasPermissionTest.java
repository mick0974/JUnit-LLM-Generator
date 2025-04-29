import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar; // Keep related imports if needed for context, even if not directly used in this test file

/**
 * Test class for FTPFile#hasPermission(int access, int permission).
 * Contains 10 unit test cases covering various scenarios, including
 * default states, setting permissions, unsetting permissions, and
 * handling invalid FTPFile instances. Tests follow the Arrange-Act-Assert pattern.
 */
public class FTPFile_hasPermissionTest {

	private FTPFile ftpFile;

	/**
	 * Sets up a new valid FTPFile instance before each test.
	 * This instance has default permissions (all false).
	 */
	@Before
	public void setUp() {
		ftpFile = new FTPFile(); // Creates a valid instance with _permissions initialized
	}

	/**
	 * Cleans up the FTPFile instance after each test.
	 */
	@After
	public void tearDown() {
		ftpFile = null;
	}

	/**
	 * Test case 1: Check default user read permission (should be false).
	 */
	@Test
	public void testHasPermission_DefaultUserRead_ShouldBeFalse() {
		// Arrange: ftpFile is initialized in setUp with default permissions

		// Act
		boolean hasPermission = ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION);

		// Assert
		assertFalse("Default user read permission should be false", hasPermission);
	}

	/**
	 * Test case 2: Check default group write permission (should be false).
	 */
	@Test
	public void testHasPermission_DefaultGroupWrite_ShouldBeFalse() {
		// Arrange: ftpFile is initialized in setUp

		// Act
		boolean hasPermission = ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION);

		// Assert
		assertFalse("Default group write permission should be false", hasPermission);
	}

	/**
	 * Test case 3: Check default world execute permission (should be false).
	 */
	@Test
	public void testHasPermission_DefaultWorldExecute_ShouldBeFalse() {
		// Arrange: ftpFile is initialized in setUp

		// Act
		boolean hasPermission = ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION);

		// Assert
		assertFalse("Default world execute permission should be false", hasPermission);
	}

	/**
	 * Test case 4: Set user read permission to true and verify.
	 */
	@Test
	public void testHasPermission_SetUserReadTrue_ShouldBeTrue() {
		// Arrange
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);

		// Act
		boolean hasPermission = ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION);

		// Assert
		assertTrue("User read permission should be true after setting it", hasPermission);
	}

	/**
	 * Test case 5: Set group write permission to true and verify.
	 */
	@Test
	public void testHasPermission_SetGroupWriteTrue_ShouldBeTrue() {
		// Arrange
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);

		// Act
		boolean hasPermission = ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION);

		// Assert
		assertTrue("Group write permission should be true after setting it", hasPermission);
	}

	/**
	 * Test case 6: Set world execute permission to true and verify.
	 */
	@Test
	public void testHasPermission_SetWorldExecuteTrue_ShouldBeTrue() {
		// Arrange
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);

		// Act
		boolean hasPermission = ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION);

		// Assert
		assertTrue("World execute permission should be true after setting it", hasPermission);
	}

	/**
	 * Test case 7: Set user read permission to true, but check user write permission (should remain false).
	 */
	@Test
	public void testHasPermission_SetUserReadTrue_CheckUserWrite_ShouldBeFalse() {
		// Arrange
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);

		// Act
		boolean hasPermission = ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION);

		// Assert
		assertFalse("User write permission should remain false when only read is set", hasPermission);
	}

    /**
     * Test case 8: Set user read permission to true, then set it back to false and verify.
     */
    @Test
    public void testHasPermission_SetUserReadTrueThenFalse_ShouldBeFalse() {
        // Arrange
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true); // Set true
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, false); // Set false

        // Act
        boolean hasPermission = ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION);

        // Assert
        assertFalse("User read permission should be false after setting it back to false", hasPermission);
    }

    /**
     * Test case 9: Set multiple permissions for different access levels and verify one.
     */
    @Test
    public void testHasPermission_SetMultiplePermissions_CheckOneSpecific() {
        // Arrange
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
        ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);

        // Act
        boolean hasGroupWritePermission = ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION);
        boolean hasUserReadPermission = ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION);
        boolean hasWorldExecutePermission = ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION);
        boolean hasUserWritePermission = ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION); // Should be false

        // Assert
        assertTrue("Group write permission should be true when set among others", hasGroupWritePermission);
        assertTrue("User read permission should be true when set among others", hasUserReadPermission);
        assertTrue("World execute permission should be true when set among others", hasWorldExecutePermission);
        assertFalse("User write permission should be false as it wasn't set", hasUserWritePermission);
    }

    /**
     * Test case 10: Edge Case - Check permission on an invalid FTPFile instance (created with raw listing constructor).
     * An invalid instance has _permissions set to null. hasPermission should return false.
     */
    @Test
    public void testHasPermission_InvalidInstance_ShouldReturnFalse() {
        // Arrange
        FTPFile invalidFile = new FTPFile("drwxr-xr-x 1 user group 4096 Jan 1 10:00 invalid"); // Uses constructor that sets _permissions=null
        assertTrue("File should report as invalid", !invalidFile.isValid()); // Pre-condition check

        // Act
        boolean hasUserRead = invalidFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION);
        boolean hasGroupWrite = invalidFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION);
        boolean hasWorldExecute = invalidFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION);

        // Assert
        assertFalse("hasPermission should return false for user read on an invalid instance", hasUserRead);
        assertFalse("hasPermission should return false for group write on an invalid instance", hasGroupWrite);
        assertFalse("hasPermission should return false for world execute on an invalid instance", hasWorldExecute);
    }
}
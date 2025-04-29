```java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for FTPFile#setPermission(int, int, boolean).
 * It contains 10 unit test cases focusing on setting various permissions
 * and handling edge cases like invalid access or permission types.
 * Follows the Arrange-Act-Assert pattern.
 */
public class FTPFile_setPermissionTest {

	private FTPFile ftpFile;

	/**
	 * Sets up a new FTPFile instance before each test.
	 */
	@Before
	public void setUp() {
		ftpFile = new FTPFile();
		// Ensure the file object is valid (permissions array is initialized)
		assertTrue("FTPFile should be valid upon creation", ftpFile.isValid());
	}

	// --- Typical Cases ---

	/**
	 * Test setting USER_ACCESS READ_PERMISSION to true.
	 */
	@Test
	public void testSetUserReadPermissionTrue() {
		// Arrange
		int access = FTPFile.USER_ACCESS;
		int permission = FTPFile.READ_PERMISSION;
		boolean value = true;

		// Act
		ftpFile.setPermission(access, permission, value);

		// Assert
		assertTrue("User read permission should be set to true", ftpFile.hasPermission(access, permission));
		// Verify other permissions are not inadvertently changed (optional but good practice)
		assertFalse("User write permission should remain false", ftpFile.hasPermission(access, FTPFile.WRITE_PERMISSION));
		assertFalse("User execute permission should remain false", ftpFile.hasPermission(access, FTPFile.EXECUTE_PERMISSION));
	}

	/**
	 * Test setting USER_ACCESS WRITE_PERMISSION to true.
	 */
	@Test
	public void testSetUserWritePermissionTrue() {
		// Arrange
		int access = FTPFile.USER_ACCESS;
		int permission = FTPFile.WRITE_PERMISSION;
		boolean value = true;

		// Act
		ftpFile.setPermission(access, permission, value);

		// Assert
		assertTrue("User write permission should be set to true", ftpFile.hasPermission(access, permission));
		assertFalse("User read permission should remain false", ftpFile.hasPermission(access, FTPFile.READ_PERMISSION));
	}

	/**
	 * Test setting USER_ACCESS EXECUTE_PERMISSION to true.
	 */
	@Test
	public void testSetUserExecutePermissionTrue() {
		// Arrange
		int access = FTPFile.USER_ACCESS;
		int permission = FTPFile.EXECUTE_PERMISSION;
		boolean value = true;

		// Act
		ftpFile.setPermission(access, permission, value);

		// Assert
		assertTrue("User execute permission should be set to true", ftpFile.hasPermission(access, permission));
		assertFalse("User read permission should remain false", ftpFile.hasPermission(access, FTPFile.READ_PERMISSION));
	}

	/**
	 * Test setting GROUP_ACCESS WRITE_PERMISSION to true.
	 */
	@Test
	public void testSetGroupWritePermissionTrue() {
		// Arrange
		int access = FTPFile.GROUP_ACCESS;
		int permission = FTPFile.WRITE_PERMISSION;
		boolean value = true;

		// Act
		ftpFile.setPermission(access, permission, value);

		// Assert
		assertTrue("Group write permission should be set to true", ftpFile.hasPermission(access, permission));
		assertFalse("Group read permission should remain false", ftpFile.hasPermission(access, FTPFile.READ_PERMISSION));
		assertFalse("User write permission should remain false", ftpFile.hasPermission(FTPFile.USER_ACCESS, permission));
	}

	/**
	 * Test setting WORLD_ACCESS EXECUTE_PERMISSION to true.
	 */
	@Test
	public void testSetWorldExecutePermissionTrue() {
		// Arrange
		int access = FTPFile.WORLD_ACCESS;
		int permission = FTPFile.EXECUTE_PERMISSION;
		boolean value = true;

		// Act
		ftpFile.setPermission(access, permission, value);

		// Assert
		assertTrue("World execute permission should be set to true", ftpFile.hasPermission(access, permission));
		assertFalse("World read permission should remain false", ftpFile.hasPermission(access, FTPFile.READ_PERMISSION));
		assertFalse("Group execute permission should remain false", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, permission));
	}

	/**
	 * Test setting USER_ACCESS READ_PERMISSION to false after initially being true.
	 */
	@Test
	public void testSetUserReadPermissionFalse() {
		// Arrange
		int access = FTPFile.USER_ACCESS;
		int permission = FTPFile.READ_PERMISSION;
		ftpFile.setPermission(access, permission, true); // Set initial value
		assertTrue("Precondition failed: User read permission should be true", ftpFile.hasPermission(access, permission));
		boolean newValue = false;

		// Act
		ftpFile.setPermission(access, permission, newValue);

		// Assert
		assertFalse("User read permission should now be set to false", ftpFile.hasPermission(access, permission));
	}

	/**
	 * Test setting multiple permissions for USER_ACCESS to true.
	 */
	@Test
	public void testSetMultiplePermissionsUser() {
		// Arrange
		int access = FTPFile.USER_ACCESS;

		// Act
		ftpFile.setPermission(access, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(access, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(access, FTPFile.EXECUTE_PERMISSION, true);

		// Assert
		assertTrue("User read permission should be true", ftpFile.hasPermission(access, FTPFile.READ_PERMISSION));
		assertTrue("User write permission should be true", ftpFile.hasPermission(access, FTPFile.WRITE_PERMISSION));
		assertTrue("User execute permission should be true", ftpFile.hasPermission(access, FTPFile.EXECUTE_PERMISSION));
		// Verify other access types remain unchanged
		assertFalse("Group read permission should remain false", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION));
	}

	// --- Edge Cases (Invalid Indices) ---

	/**
	 * Test setting permission with an invalid negative access index.
	 * Expects ArrayIndexOutOfBoundsException.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetPermissionInvalidAccessNegative() {
		// Arrange
		int invalidAccess = -1;
		int permission = FTPFile.READ_PERMISSION;
		boolean value = true;

		// Act
		ftpFile.setPermission(invalidAccess, permission, value);

		// Assert (handled by expected exception)
	}

	/**
	 * Test setting permission with an invalid positive access index (out of bounds).
	 * Expects ArrayIndexOutOfBoundsException.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetPermissionInvalidAccessPositive() {
		// Arrange
		int invalidAccess = 3; // Valid indices are 0, 1, 2
		int permission = FTPFile.READ_PERMISSION;
		boolean value = true;

		// Act
		ftpFile.setPermission(invalidAccess, permission, value);

		// Assert (handled by expected exception)
	}

	/**
	 * Test setting permission with an invalid positive permission index (out of bounds).
	 * Expects ArrayIndexOutOfBoundsException.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetPermissionInvalidPermissionPositive() {
		// Arrange
		int access = FTPFile.USER_ACCESS;
		int invalidPermission = 3; // Valid indices are 0, 1, 2
		boolean value = true;

		// Act
		ftpFile.setPermission(access, invalidPermission, value);

		// Assert (handled by expected exception)
	}

    /* Optional: Test with invalid negative permission index */
	/*
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetPermissionInvalidPermissionNegative() {
		// Arrange
		int access = FTPFile.USER_ACCESS;
		int invalidPermission = -1;
		boolean value = true;

		// Act
		ftpFile.setPermission(access, invalidPermission, value);

		// Assert (handled by expected exception)
	}
	*/
}
```
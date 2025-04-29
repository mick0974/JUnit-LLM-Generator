```java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for FTPFile#setPermission(int, int, boolean).
 * Focuses on testing the setting and subsequent checking of permissions.
 */
public class FTPFile_setPermissionTest {

	private FTPFile ftpFile;

	@Before
	public void setUp() {
		// Arrange: Create a new valid FTPFile instance before each test
		ftpFile = new FTPFile();
		// Ensure it's considered valid by setting some basic info (though not strictly necessary for setPermission)
		ftpFile.setName("testFile.txt");
		ftpFile.setType(FTPFile.FILE_TYPE);
		ftpFile.setSize(1024);
	}

	// Test Case 1: Set USER_ACCESS READ_PERMISSION to true
	@Test
	public void testSetUserReadPermissionTrue() {
		// Act
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);

		// Assert
		assertTrue("User read permission should be true", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse("User write permission should remain false", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
		assertFalse("User execute permission should remain false", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
	}

	// Test Case 2: Set USER_ACCESS WRITE_PERMISSION to true
	@Test
	public void testSetUserWritePermissionTrue() {
		// Act
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);

		// Assert
		assertTrue("User write permission should be true", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
		assertFalse("User read permission should remain false", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse("User execute permission should remain false", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
	}

	// Test Case 3: Set USER_ACCESS EXECUTE_PERMISSION to true
	@Test
	public void testSetUserExecutePermissionTrue() {
		// Act
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);

		// Assert
		assertTrue("User execute permission should be true", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
		assertFalse("User read permission should remain false", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse("User write permission should remain false", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
	}

	// Test Case 4: Set GROUP_ACCESS READ_PERMISSION to true
	@Test
	public void testSetGroupReadPermissionTrue() {
		// Act
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);

		// Assert
		assertTrue("Group read permission should be true", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse("Group write permission should remain false", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
		assertFalse("Group execute permission should remain false", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION));
		// Verify user permissions are unaffected
		assertFalse("User read permission should remain false", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
	}

	// Test Case 5: Set WORLD_ACCESS EXECUTE_PERMISSION to true
	@Test
	public void testSetWorldExecutePermissionTrue() {
		// Act
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);

		// Assert
		assertTrue("World execute permission should be true", ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
		assertFalse("World read permission should remain false", ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPPFile.READ_PERMISSION));
		assertFalse("World write permission should remain false", ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION));
		// Verify user permissions are unaffected
		assertFalse("User execute permission should remain false", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
	}

	// Test Case 6: Set USER_ACCESS READ_PERMISSION to false after setting it to true
	@Test
	public void testSetUserReadPermissionFalse() {
		// Arrange: Set the permission to true first
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		assertTrue("Precondition failed: User read permission should be true", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));

		// Act: Set the permission to false
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, false);

		// Assert
		assertFalse("User read permission should now be false", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
	}

	// Test Case 7: Set multiple permissions for USER_ACCESS to true
	@Test
	public void testSetAllUserPermissionsTrue() {
		// Act
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);

		// Assert
		assertTrue("User read permission should be true", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
		assertTrue("User write permission should be true", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
		assertTrue("User execute permission should be true", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
		// Verify other access levels remain false
		assertFalse("Group read permission should be false", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse("World read permission should be false", ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION));
	}

	// Test Case 8: Set a mix of true/false permissions across different access levels
	@Test
	public void testSetMixedPermissions() {
		// Act
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, false); // Explicitly set to false (default)
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, false);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, false);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, false);


		// Assert
		assertTrue("User read permission should be true", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse("User write permission should be false", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
		assertTrue("User execute permission should be true", ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));

		assertTrue("Group read permission should be true", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse("Group write permission should be false", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
		assertFalse("Group execute permission should be false", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION));

		assertTrue("World read permission should be true", ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION));
		assertFalse("World write permission should be false", ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION));
		assertFalse("World execute permission should be false", ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
	}

    // Test Case 9: Set permission on an invalid FTPFile instance (should not throw error, but hasPermission returns false)
    @Test
    public void testSetPermissionOnInvalidInstance() {
        // Arrange: Create an invalid instance
        FTPFile invalidFile = new FTPFile("bad raw listing");
        assertFalse("Precondition failed: File should be invalid", invalidFile.isValid());

        // Act: Attempt to set permission (should not throw an exception, but internal state is null)
        // Note: While the method might internally access the null _permissions array if not coded defensively,
        // the current implementation of setPermission doesn't check isValid().
        // However, hasPermission *does* check and returns false for invalid files.
        try {
             invalidFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
             // We expect this might throw NullPointerException if not defensively coded,
             // but the provided code allows it. The check is in hasPermission.
        } catch (NullPointerException e) {
            fail("setPermission should ideally handle invalid state gracefully, though the current code doesn't explicitly check. Test relies on hasPermission check.");
        }


        // Assert
        assertFalse("hasPermission should return false for an invalid file, regardless of setPermission calls",
                    invalidFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }

    // Test Case 10: Repeatedly set the same permission to the same value
    @Test
    public void testSetSamePermissionRepeatedly() {
        // Act
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true); // Set again

        // Assert
        assertTrue("Group write permission should be true after repeated sets", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));

        // Act
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, false);
        ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, false); // Set again

        // Assert
        assertFalse("Group write permission should be false after repeated sets", ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
    }

	// Note: Testing invalid index values (e.g., access=5, permission=7)
    // is omitted as the method signature implies they will throw
    // ArrayIndexOutOfBoundsException, which is standard Java behavior for arrays
    // and generally doesn't require explicit testing unless the method catches
    // and handles it, which this one does not.
}
```
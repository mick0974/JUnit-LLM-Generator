// FTPFile_setTypeTest.java
import org.junit.Before;
// import org.junit.After; // Not needed for this simple test class
import org.junit.Test;
import static org.junit.Assert.*;

// No need to import everything from FTPFile.java, just the class itself
// and potentially its static constants if used directly (which they are).
// import java.io.Serializable;
// import java.util.Calendar;
// import java.util.Date;
// import java.util.Formatter;
// import java.util.TimeZone;

/**
 * Test class for {@link FTPFile#setType(int)}.
 * Contains unit test cases covering typical and edge scenarios
 * for setting the type of an FTPFile instance. Tests follow the
 * Arrange-Act-Assert pattern.
 * Uses JUnit 4 framework.
 */
public class FTPFile_setTypeTest {

	private FTPFile ftpFile;

	/**
	 * Sets up a new FTPFile instance before each test.
	 */
	@Before
	public void setUp() {
		ftpFile = new FTPFile();
		// Initial state verification (optional but good practice)
		assertEquals("Default type should be UNKNOWN", FTPFile.UNKNOWN_TYPE, ftpFile.getType());
		assertTrue("Default state should be Unknown", ftpFile.isUnknown());
		assertFalse("Default state should not be File", ftpFile.isFile());
		assertFalse("Default state should not be Directory", ftpFile.isDirectory());
		assertFalse("Default state should not be Symbolic Link", ftpFile.isSymbolicLink());
	}

	/**
	 * Test setting type to FILE_TYPE.
	 */
	@Test
	public void testSetTypeToFile() {
		// Arrange: ftpFile is initialized in setUp

		// Act
		ftpFile.setType(FTPFile.FILE_TYPE);

		// Assert
		assertEquals("Type should be set to FILE_TYPE", FTPFile.FILE_TYPE, ftpFile.getType());
		assertTrue("isFile should return true for FILE_TYPE", ftpFile.isFile());
		assertFalse("isDirectory should return false for FILE_TYPE", ftpFile.isDirectory());
		assertFalse("isSymbolicLink should return false for FILE_TYPE", ftpFile.isSymbolicLink());
		assertFalse("isUnknown should return false for FILE_TYPE", ftpFile.isUnknown());
	}

	/**
	 * Test setting type to DIRECTORY_TYPE.
	 */
	@Test
	public void testSetTypeToDirectory() {
		// Arrange: ftpFile is initialized in setUp

		// Act
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);

		// Assert
		assertEquals("Type should be set to DIRECTORY_TYPE", FTPFile.DIRECTORY_TYPE, ftpFile.getType());
		assertFalse("isFile should return false for DIRECTORY_TYPE", ftpFile.isFile());
		assertTrue("isDirectory should return true for DIRECTORY_TYPE", ftpFile.isDirectory());
		assertFalse("isSymbolicLink should return false for DIRECTORY_TYPE", ftpFile.isSymbolicLink());
		assertFalse("isUnknown should return false for DIRECTORY_TYPE", ftpFile.isUnknown());
	}

	/**
	 * Test setting type to SYMBOLIC_LINK_TYPE.
	 */
	@Test
	public void testSetTypeToSymbolicLink() {
		// Arrange: ftpFile is initialized in setUp

		// Act
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);

		// Assert
		assertEquals("Type should be set to SYMBOLIC_LINK_TYPE", FTPFile.SYMBOLIC_LINK_TYPE, ftpFile.getType());
		assertFalse("isFile should return false for SYMBOLIC_LINK_TYPE", ftpFile.isFile());
		assertFalse("isDirectory should return false for SYMBOLIC_LINK_TYPE", ftpFile.isDirectory());
		assertTrue("isSymbolicLink should return true for SYMBOLIC_LINK_TYPE", ftpFile.isSymbolicLink());
		assertFalse("isUnknown should return false for SYMBOLIC_LINK_TYPE", ftpFile.isUnknown());
	}

	/**
	 * Test explicitly setting type back to UNKNOWN_TYPE.
	 */
	@Test
	public void testSetTypeToUnknown() {
		// Arrange: Set to a different type first
		ftpFile.setType(FTPFile.FILE_TYPE);
		assertEquals("Precondition: Type should be FILE_TYPE", FTPFile.FILE_TYPE, ftpFile.getType());

		// Act
		ftpFile.setType(FTPFile.UNKNOWN_TYPE);

		// Assert
		assertEquals("Type should be set back to UNKNOWN_TYPE", FTPFile.UNKNOWN_TYPE, ftpFile.getType());
		assertFalse("isFile should return false for UNKNOWN_TYPE", ftpFile.isFile());
		assertFalse("isDirectory should return false for UNKNOWN_TYPE", ftpFile.isDirectory());
		assertFalse("isSymbolicLink should return false for UNKNOWN_TYPE", ftpFile.isSymbolicLink());
		assertTrue("isUnknown should return true for UNKNOWN_TYPE", ftpFile.isUnknown());
	}

	/**
	 * Test overwriting a previously set type. Set to Directory, then to File.
	 */
	@Test
	public void testSetTypeOverwrite() {
		// Arrange: Set initial type
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals("Precondition: Type should be DIRECTORY_TYPE", FTPFile.DIRECTORY_TYPE, ftpFile.getType());
		assertTrue("Precondition: isDirectory should be true", ftpFile.isDirectory());


		// Act: Overwrite with FILE_TYPE
		ftpFile.setType(FTPFile.FILE_TYPE);

		// Assert: Check final state reflects FILE_TYPE
		assertEquals("Type should be overwritten to FILE_TYPE", FTPFile.FILE_TYPE, ftpFile.getType());
		assertTrue("isFile should return true after overwrite", ftpFile.isFile());
		assertFalse("isDirectory should return false after overwrite", ftpFile.isDirectory());
		assertFalse("isSymbolicLink should return false after overwrite", ftpFile.isSymbolicLink());
		assertFalse("isUnknown should return false after overwrite", ftpFile.isUnknown());
	}

	/**
	 * Test setting type to an integer value not defined by the constants.
	 */
	@Test
	public void testSetTypeToArbitraryValue() {
		// Arrange
		int arbitraryType = 99; // A value not among the defined constants

		// Act
		ftpFile.setType(arbitraryType);

		// Assert
		assertEquals("Type should be set to the arbitrary value", arbitraryType, ftpFile.getType());
		// Check that boolean checks still work correctly based on the set value
		assertFalse("isFile should return false for arbitrary type", ftpFile.isFile());
		assertFalse("isDirectory should return false for arbitrary type", ftpFile.isDirectory());
		assertFalse("isSymbolicLink should return false for arbitrary type", ftpFile.isSymbolicLink());
		assertFalse("isUnknown should return false for arbitrary type", ftpFile.isUnknown());
	}

	/**
	 * Test setting type to zero, which corresponds to FILE_TYPE.
	 */
	@Test
	public void testSetTypeToZero() {
		// Arrange: ftpFile is initialized in setUp

		// Act
		ftpFile.setType(0); // 0 is the value of FILE_TYPE

		// Assert
		assertEquals("Type should be set to 0", 0, ftpFile.getType());
		assertEquals("Type should be equivalent to FILE_TYPE", FTPFile.FILE_TYPE, ftpFile.getType());
		assertTrue("isFile should return true for type 0", ftpFile.isFile());
		assertFalse("isDirectory should return false for type 0", ftpFile.isDirectory());
		assertFalse("isSymbolicLink should return false for type 0", ftpFile.isSymbolicLink());
		assertFalse("isUnknown should return false for type 0", ftpFile.isUnknown());
	}

	/**
	 * Test setting type to a negative integer value.
	 */
	@Test
	public void testSetTypeToNegativeValue() {
		// Arrange
		int negativeType = -1;

		// Act
		ftpFile.setType(negativeType);

		// Assert
		assertEquals("Type should be set to the negative value", negativeType, ftpFile.getType());
		assertFalse("isFile should return false for negative type", ftpFile.isFile());
		assertFalse("isDirectory should return false for negative type", ftpFile.isDirectory());
		assertFalse("isSymbolicLink should return false for negative type", ftpFile.isSymbolicLink());
		assertFalse("isUnknown should return false for negative type", ftpFile.isUnknown());
	}

    /**
     * Test setting the type multiple times with different valid types.
     * End state should reflect the last set type.
     */
    @Test
    public void testSetTypeMultipleTimesDifferentValidTypes() {
        // Arrange: ftpFile starts as UNKNOWN

        // Act
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setType(FTPFile.DIRECTORY_TYPE); // Last set type

        // Assert
        assertEquals("Type should be the last set type (DIRECTORY_TYPE)", FTPFile.DIRECTORY_TYPE, ftpFile.getType());
        assertFalse("isFile should be false", ftpFile.isFile());
        assertTrue("isDirectory should be true", ftpFile.isDirectory());
        assertFalse("isSymbolicLink should be false", ftpFile.isSymbolicLink());
        assertFalse("isUnknown should be false", ftpFile.isUnknown());
    }

    /**
     * Test setting the type to the same value multiple times.
     */
    @Test
    public void testSetTypeSameValueMultipleTimes() {
        // Arrange: ftpFile starts as UNKNOWN

        // Act
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setType(FTPFile.FILE_TYPE); // Set again to the same value

        // Assert
        assertEquals("Type should remain FILE_TYPE", FTPFile.FILE_TYPE, ftpFile.getType());
        assertTrue("isFile should remain true", ftpFile.isFile());
        assertFalse("isDirectory should remain false", ftpFile.isDirectory());
        assertFalse("isSymbolicLink should remain false", ftpFile.isSymbolicLink());
        assertFalse("isUnknown should remain false", ftpFile.isUnknown());
    }
}
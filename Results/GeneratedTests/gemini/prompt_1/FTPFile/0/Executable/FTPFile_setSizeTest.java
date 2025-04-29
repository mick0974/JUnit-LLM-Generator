import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the FTPFile#setSize(long size) method.
 * Contains unit tests covering typical and edge cases using the Arrange-Act-Assert pattern.
 */
public class FTPFile_setSizeTest {

	private FTPFile ftpFile;

	/**
	 * Sets up a new FTPFile instance before each test.
	 */
	@Before
	public void setUp() {
		ftpFile = new FTPFile();
		// Initial state check (optional, but good practice)
		assertEquals("Initial size should be -1", -1L, ftpFile.getSize());
	}

	/**
	 * Test setting the size to zero.
	 */
	@Test
	public void testSetSizeZero() {
		// Arrange
		long expectedSize = 0L;

		// Act
		ftpFile.setSize(expectedSize);

		// Assert
		assertEquals("Size should be set to 0", expectedSize, ftpFile.getSize());
	}

	/**
	 * Test setting the size to a typical positive value.
	 */
	@Test
	public void testSetSizePositive() {
		// Arrange
		long expectedSize = 1024L;

		// Act
		ftpFile.setSize(expectedSize);

		// Assert
		assertEquals("Size should be set to 1024", expectedSize, ftpFile.getSize());
	}

	/**
	 * Test setting the size to one.
	 */
	@Test
	public void testSetSizeOne() {
		// Arrange
		long expectedSize = 1L;

		// Act
		ftpFile.setSize(expectedSize);

		// Assert
		assertEquals("Size should be set to 1", expectedSize, ftpFile.getSize());
	}

	/**
	 * Test setting the size to a large positive value (Long.MAX_VALUE).
	 */
	@Test
	public void testSetSizeMaxLong() {
		// Arrange
		long expectedSize = Long.MAX_VALUE;

		// Act
		ftpFile.setSize(expectedSize);

		// Assert
		assertEquals("Size should be set to Long.MAX_VALUE", expectedSize, ftpFile.getSize());
	}

	/**
	 * Test setting the size to -1 (which is the initial value, but testing explicit set).
	 */
	@Test
	public void testSetSizeNegativeOne() {
		// Arrange
		ftpFile.setSize(100L); // Set to something else first
		long expectedSize = -1L;

		// Act
		ftpFile.setSize(expectedSize);

		// Assert
		assertEquals("Size should be set to -1", expectedSize, ftpFile.getSize());
	}

	/**
	 * Test setting the size to another negative value.
	 * While file sizes are typically non-negative, the setter allows any long.
	 */
	@Test
	public void testSetSizeNegativeOther() {
		// Arrange
		long expectedSize = -500L;

		// Act
		ftpFile.setSize(expectedSize);

		// Assert
		assertEquals("Size should be set to -500", expectedSize, ftpFile.getSize());
	}

	/**
	 * Test overwriting a previously set positive size with another positive size.
	 */
	@Test
	public void testSetSizeOverwritePositiveWithPositive() {
		// Arrange
		ftpFile.setSize(512L); // Initial set
		long expectedSize = 2048L; // New size

		// Act
		ftpFile.setSize(expectedSize);

		// Assert
		assertEquals("Size should be overwritten to 2048", expectedSize, ftpFile.getSize());
	}

	/**
	 * Test overwriting a previously set positive size with zero.
	 */
	@Test
	public void testSetSizeOverwritePositiveWithZero() {
		// Arrange
		ftpFile.setSize(4096L); // Initial set
		long expectedSize = 0L; // New size

		// Act
		ftpFile.setSize(expectedSize);

		// Assert
		assertEquals("Size should be overwritten to 0", expectedSize, ftpFile.getSize());
	}

	/**
	 * Test overwriting a previously set positive size with a negative size.
	 */
	@Test
	public void testSetSizeOverwritePositiveWithNegative() {
		// Arrange
		ftpFile.setSize(100L); // Initial set
		long expectedSize = -10L; // New size

		// Act
		ftpFile.setSize(expectedSize);

		// Assert
		assertEquals("Size should be overwritten to -10", expectedSize, ftpFile.getSize());
	}

	/**
	 * Test setting the size on an "invalid" FTPFile instance
	 * (created using the constructor for unparseable listings).
	 * The set/get methods for size should still work directly on the field.
	 */
	@Test
	public void testSetSizeOnInvalidFile() {
		// Arrange
		FTPFile invalidFile = new FTPFile("unparseable raw listing");
		assertFalse("File should be invalid", invalidFile.isValid());
		assertEquals("Initial size of invalid file should be -1", -1L, invalidFile.getSize());
		long expectedSize = 5000L;

		// Act
		invalidFile.setSize(expectedSize);

		// Assert
		assertEquals("Size should be set correctly even on an invalid file", expectedSize, invalidFile.getSize());
	}
}
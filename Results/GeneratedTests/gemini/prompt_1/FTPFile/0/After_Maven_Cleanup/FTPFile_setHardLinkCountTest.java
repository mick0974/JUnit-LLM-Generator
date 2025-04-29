import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

// No need to import Calendar, Date, etc. as they are not used in this specific test
// import java.io.Serializable; // Not needed for test
// import java.util.Calendar; // Not needed for test
// import java.util.Date; // Not needed for test
// import java.util.Formatter; // Not needed for test
// import java.util.TimeZone; // Not needed for test

/**
 * Test class for FTPFile#setHardLinkCount(int links).
 * It contains 10 unit test cases for the method,
 * following the Arrange-Act-Assert pattern and covering typical and edge cases.
 */
public class FTPFile_setHardLinkCountTest {

    private FTPFile ftpFile;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        ftpFile = null;
    }

    /**
     * Test case 1: Set a typical positive hard link count (1).
     */
    @Test
    public void testSetHardLinkCount_PositiveValue_ShouldSetCorrectly() {
        // Arrange
        int expectedLinks = 1;

        // Act
        ftpFile.setHardLinkCount(expectedLinks);
        int actualLinks = ftpFile.getHardLinkCount();

        // Assert
        assertEquals("Hard link count should be set to 1", expectedLinks, actualLinks);
    }

    /**
     * Test case 2: Set another typical positive hard link count (5).
     */
    @Test
    public void testSetHardLinkCount_AnotherPositiveValue_ShouldSetCorrectly() {
        // Arrange
        int expectedLinks = 5;

        // Act
        ftpFile.setHardLinkCount(expectedLinks);
        int actualLinks = ftpFile.getHardLinkCount();

        // Assert
        assertEquals("Hard link count should be set to 5", expectedLinks, actualLinks);
    }

    /**
     * Test case 3: Set a larger positive hard link count (1000).
     */
    @Test
    public void testSetHardLinkCount_LargerPositiveValue_ShouldSetCorrectly() {
        // Arrange
        int expectedLinks = 1000;

        // Act
        ftpFile.setHardLinkCount(expectedLinks);
        int actualLinks = ftpFile.getHardLinkCount();

        // Assert
        assertEquals("Hard link count should be set to 1000", expectedLinks, actualLinks);
    }

    /**
     * Test case 4: Set hard link count to zero.
     * Although the constructor comment mentions 0 is invalid in listings,
     * the setter itself should accept 0.
     */
    @Test
    public void testSetHardLinkCount_ZeroValue_ShouldSetCorrectly() {
        // Arrange
        int expectedLinks = 0;

        // Act
        ftpFile.setHardLinkCount(expectedLinks);
        int actualLinks = ftpFile.getHardLinkCount();

        // Assert
        assertEquals("Hard link count should be set to 0", expectedLinks, actualLinks);
    }

    /**
     * Test case 5: Set hard link count to a negative value (-1).
     * The method doesn't validate input, so it should accept negative values.
     */
    @Test
    public void testSetHardLinkCount_NegativeValue_ShouldSetCorrectly() {
        // Arrange
        int expectedLinks = -1;

        // Act
        ftpFile.setHardLinkCount(expectedLinks);
        int actualLinks = ftpFile.getHardLinkCount();

        // Assert
        assertEquals("Hard link count should be set to -1", expectedLinks, actualLinks);
    }

    /**
     * Test case 6: Set hard link count to another negative value (-10).
     */
    @Test
    public void testSetHardLinkCount_AnotherNegativeValue_ShouldSetCorrectly() {
        // Arrange
        int expectedLinks = -10;

        // Act
        ftpFile.setHardLinkCount(expectedLinks);
        int actualLinks = ftpFile.getHardLinkCount();

        // Assert
        assertEquals("Hard link count should be set to -10", expectedLinks, actualLinks);
    }

    /**
     * Test case 7: Set hard link count to Integer.MAX_VALUE.
     */
    @Test
    public void testSetHardLinkCount_MaxValue_ShouldSetCorrectly() {
        // Arrange
        int expectedLinks = Integer.MAX_VALUE;

        // Act
        ftpFile.setHardLinkCount(expectedLinks);
        int actualLinks = ftpFile.getHardLinkCount();

        // Assert
        assertEquals("Hard link count should be set to Integer.MAX_VALUE", expectedLinks, actualLinks);
    }

    /**
     * Test case 8: Set hard link count to Integer.MIN_VALUE.
     */
    @Test
    public void testSetHardLinkCount_MinValue_ShouldSetCorrectly() {
        // Arrange
        int expectedLinks = Integer.MIN_VALUE;

        // Act
        ftpFile.setHardLinkCount(expectedLinks);
        int actualLinks = ftpFile.getHardLinkCount();

        // Assert
        assertEquals("Hard link count should be set to Integer.MIN_VALUE", expectedLinks, actualLinks);
    }

    /**
     * Test case 9: Overwrite an existing positive value with a different positive value.
     */
    @Test
    public void testSetHardLinkCount_OverwritePositiveValue_ShouldSetNewValue() {
        // Arrange
        int initialLinks = 3;
        int expectedLinks = 7;
        ftpFile.setHardLinkCount(initialLinks); // Set initial value

        // Act
        ftpFile.setHardLinkCount(expectedLinks); // Overwrite
        int actualLinks = ftpFile.getHardLinkCount();

        // Assert
        assertEquals("Hard link count should be updated from 3 to 7", expectedLinks, actualLinks);
    }

    /**
     * Test case 10: Overwrite an existing positive value with zero.
     */
    @Test
    public void testSetHardLinkCount_OverwriteWithZero_ShouldSetZero() {
        // Arrange
        int initialLinks = 10;
        int expectedLinks = 0;
        ftpFile.setHardLinkCount(initialLinks); // Set initial value

        // Act
        ftpFile.setHardLinkCount(expectedLinks); // Overwrite with zero
        int actualLinks = ftpFile.getHardLinkCount();

        // Assert
        assertEquals("Hard link count should be updated from 10 to 0", expectedLinks, actualLinks);
    }
}
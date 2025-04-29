// FTPFile_setSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

/**
 * Test class of FTPFile.
 * It contains 10 unit test cases for the
 * FTPFile#public setSize(long size) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setSizeTest {
    private FTPFile ftpFile;

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void testSetSizeTypical() {
        // Arrange
        long expectedSize = 1024;

        // Act
        ftpFile.setSize(expectedSize);

        // Assert
        assertEquals(expectedSize, ftpFile.getSize());
    }

    @Test
    public void testSetSizeNegative() {
        // Arrange
        long expectedSize = -1;

        // Act
        ftpFile.setSize(expectedSize);

        // Assert
        assertEquals(expectedSize, ftpFile.getSize());
    }

    @Test
    public void testSetSizeZero() {
        // Arrange
        long expectedSize = 0;

        // Act
        ftpFile.setSize(expectedSize);

        // Assert
        assertEquals(expectedSize, ftpFile.getSize());
    }

    @Test
    public void testSetSizeMaxLong() {
        // Arrange
        long expectedSize = Long.MAX_VALUE;

        // Act
        ftpFile.setSize(expectedSize);

        // Assert
        assertEquals(expectedSize, ftpFile.getSize());
    }

    @Test
    public void testSetSizeMinLong() {
        // Arrange
        long expectedSize = Long.MIN_VALUE;

        // Act
        ftpFile.setSize(expectedSize);

        // Assert
        assertEquals(expectedSize, ftpFile.getSize());
    }


    @Test
    public void testSetSizeSameValue() {
        // Arrange
        long initialSize = 512;
        long newSize = 512;

        // Act
        ftpFile.setSize(initialSize);
        ftpFile.setSize(newSize);

        // Assert
        assertEquals(newSize, ftpFile.getSize());
    }

    @Test
    public void testSetSizeAfterGet() {
        // Arrange
        long expectedSize = 2048;

        // Act
        ftpFile.setSize(expectedSize);
        long retrievedSize = ftpFile.getSize();

        // Assert
        assertEquals(expectedSize, retrievedSize);
    }

    @Test
    public void testSetSizeEmptyString() {
        // Arrange
        String input = "";

        // Act & Assert
        try {
            ftpFile.setSize(Long.parseLong(input));
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // Expected exception
        }
    }

    @Test
    public void testSetSizeWhitespace() {
        // Arrange
        String input = " ";

        // Act & Assert
        try {
            ftpFile.setSize(Long.parseLong(input));
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            // Expected exception
        }
    }
}
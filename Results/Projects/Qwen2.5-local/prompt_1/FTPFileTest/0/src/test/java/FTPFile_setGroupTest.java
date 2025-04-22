// FTPFile_setGroupTest.java
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
 * FTPFile#public setGroup(String group) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setGroupTest {
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
    public void testSetGroup_validGroupName() {
        // Arrange
        String groupName = "testGroup";

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_nullGroupName() {
        // Arrange
        String groupName = null;

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertNull(ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_emptyGroupName() {
        // Arrange
        String groupName = "";

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertTrue(ftpFile.getGroup().isEmpty());
    }

    @Test
    public void testSetGroup_specialCharactersGroupName() {
        // Arrange
        String groupName = "!@#$%^&*()_+{}|:\"<>?";

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_spaceCharacterGroupName() {
        // Arrange
        String groupName = " ";

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }


    @Test
    public void testSetGroup_unicodeGroupName() {
        // Arrange
        String groupName = "\u00E5\u00F6\u00FC"; // special characters in utf-8

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_sameGroupTwice() {
        // Arrange
        String groupName = "testGroup";

        // Act
        ftpFile.setGroup(groupName);
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(groupName, ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_afterOtherMethods() {
        // Arrange
        String groupName = "testGroup";
        String userName = "testUser";
        String fileName = "testFile.txt";

        // Act
        ftpFile.setName(fileName);
        ftpFile.setUser(userName);
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals(userName, ftpFile.getUser());
        assertEquals(groupName, ftpFile.getGroup());
        assertEquals(fileName, ftpFile.getName());
    }
}
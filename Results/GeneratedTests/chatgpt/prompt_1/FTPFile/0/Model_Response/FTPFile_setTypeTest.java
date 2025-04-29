Here's the test class `FTPFile_setTypeTest` with 10 unit test cases for the `FTPFile#setType(int type)` method using JUnit 4. Each test case follows the Arrange-Act-Assert pattern:

```java
public class FTPFile_setTypeTest {

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
    public void testSetTypeFile() {
        // Arrange
        int type = FTPFile.FILE_TYPE;

        // Act
        ftpFile.setType(type);

        // Assert
        assertEquals(FTPFile.FILE_TYPE, ftpFile.getType());
    }

    @Test
    public void testSetTypeDirectory() {
        // Arrange
        int type = FTPFile.DIRECTORY_TYPE;

        // Act
        ftpFile.setType(type);

        // Assert
        assertEquals(FTPFile.DIRECTORY_TYPE, ftpFile.getType());
    }

    @Test
    public void testSetTypeSymbolicLink() {
        // Arrange
        int type = FTPFile.SYMBOLIC_LINK_TYPE;

        // Act
        ftpFile.setType(type);

        // Assert
        assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, ftpFile.getType());
    }

    @Test
    public void testSetTypeUnknown() {
        // Arrange
        int type = FTPFile.UNKNOWN_TYPE;

        // Act
        ftpFile.setType(type);

        // Assert
        assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
    }

    @Test
    public void testSetTypeTwice() {
        // Arrange
        int initialType = FTPFile.DIRECTORY_TYPE;
        int newType = FTPFile.FILE_TYPE;

        // Act
        ftpFile.setType(initialType);
        ftpFile.setType(newType);

        // Assert
        assertEquals(FTPFile.FILE_TYPE, ftpFile.getType());
    }

    @Test
    public void testSetTypeInvalidLow() {
        // Arrange
        int invalidType = -1;

        // Act
        ftpFile.setType(invalidType);

        // Assert
        assertEquals(invalidType, ftpFile.getType());
    }

    @Test
    public void testSetTypeInvalidHigh() {
        // Arrange
        int invalidType = 10;

        // Act
        ftpFile.setType(invalidType);

        // Assert
        assertEquals(invalidType, ftpFile.getType());
    }

    @Test
    public void testInitialTypeIsUnknown() {
        // Assert
        assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
    }

    @Test
    public void testSetTypeNotAffectingOtherProperties() {
        // Arrange
        ftpFile.setName("example.txt");
        long expectedSize = 1024L;
        ftpFile.setSize(expectedSize);
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Assert
        assertEquals("example.txt", ftpFile.getName());
        assertEquals(expectedSize, ftpFile.getSize());
    }

    @Test
    public void testSetTypeToNotChangeTimestamp() {
        // Arrange
        Calendar now = Calendar.getInstance();
        ftpFile.setTimestamp(now);
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Assert
        assertEquals(now, ftpFile.getTimestamp());
    }
}
```

This code covers normal, edge, and invalid cases for the `setType(int type)` method on the `FTPFile` class. Each test checks how the method performs within the contract of the method's intended functionality.
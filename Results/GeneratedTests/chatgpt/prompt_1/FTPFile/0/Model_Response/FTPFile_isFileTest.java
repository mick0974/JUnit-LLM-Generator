```java
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
    public void testIsFileWhenTypeIsFileType() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertTrue("Expected isFile() to return true when type is FILE_TYPE", result);
    }

    @Test
    public void testIsFileWhenTypeIsDirectoryType() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("Expected isFile() to return false when type is DIRECTORY_TYPE", result);
    }

    @Test
    public void testIsFileWhenTypeIsSymbolicLinkType() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("Expected isFile() to return false when type is SYMBOLIC_LINK_TYPE", result);
    }

    @Test
    public void testIsFileWhenTypeIsUnknownType() {
        // Arrange
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("Expected isFile() to return false when type is UNKNOWN_TYPE", result);
    }

    @Test
    public void testIsFileWithDefaultType() {
        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertFalse("Expected isFile() to return false for default type (UNKNOWN_TYPE)", result);
    }

    @Test
    public void testIsFileAfterMultipleTypeChanges() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertTrue("Expected isFile() to return true after setting type to FILE_TYPE", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFileWithInvalidType() {
        // Arrange
        ftpFile.setType(999);

        // Act
        ftpFile.isFile();
        // No need for Assert as we are expecting an exception
    }

    @Test
    public void testIsFileAfterSetInvalidAndThenValidType() {
        try {
            // Arrange
            ftpFile.setType(999);
        } catch (IllegalArgumentException e) {
            // Ignore invalid type exception for testing purposes
        }
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = ftpFile.isFile();

        // Assert
        assertTrue("Expected isFile() to return true after setting a valid FILE_TYPE", result);
    }

    @Test
    public void testIsFileWithAnotherInstance() {
        // Arrange
        FTPFile anotherFtpFile = new FTPFile();
        anotherFtpFile.setType(FTPFile.FILE_TYPE);

        // Act
        boolean resultOriginal = ftpFile.isFile();
        boolean resultNew = anotherFtpFile.isFile();

        // Assert
        assertFalse("Expected isFile() to return false for original instance", resultOriginal);
        assertTrue("Expected isFile() to return true for another instance with FILE_TYPE", resultNew);
    }

    @Test
    public void testIsFileConsistency() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act & Assert
        assertTrue("Expected isFile() to return consistent results", ftpFile.isFile());
        assertTrue("Expected isFile() to return consistent results", ftpFile.isFile());
    }
}
```
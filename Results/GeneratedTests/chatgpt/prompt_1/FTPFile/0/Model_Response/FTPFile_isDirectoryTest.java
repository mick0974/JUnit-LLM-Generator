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
    public void testIsDirectoryWhenTypeIsDirectory() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertTrue("File should be a directory", result);
    }

    @Test
    public void testIsDirectoryWhenTypeIsFile() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("File should not be a directory", result);
    }

    @Test
    public void testIsDirectoryWhenTypeIsSymbolicLink() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("File should not be a directory", result);
    }

    @Test
    public void testIsDirectoryWhenTypeIsUnknown() {
        // Arrange
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("File should not be a directory", result);
    }

    @Test
    public void testIsDirectoryWithDefaultType() {
        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("File should not be a directory with default type", result);
    }

    @Test
    public void testIsDirectoryWithInvalidType() {
        // This should theoretically not happen as types are controlled by constants,
        // but adding for coverage
        ftpFile.setType(999);

        // Act
        boolean result = ftpFile.isDirectory();

        // Assert
        assertFalse("File should not be a directory for an invalid type", result);
    }
}
```

This test suite ensures the `FTPFile#isDirectory()` method behaves correctly under various conditions, following the Arrange-Act-Assert pattern. The setup and teardown methods handle object creation and cleanup, verifying that the proper file type states yield expected results.
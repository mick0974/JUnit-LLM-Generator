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
    public void testSetPermission_UserReadPermission() {
        // Arrange
        int access = FTPFile.USER_ACCESS;
        int permission = FTPFile.READ_PERMISSION;
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);

        // Assert
        assertTrue(ftpFile.hasPermission(access, permission));
    }

    @Test
    public void testSetPermission_UserWritePermission() {
        // Arrange
        int access = FTPFile.USER_ACCESS;
        int permission = FTPFile.WRITE_PERMISSION;
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);

        // Assert
        assertTrue(ftpFile.hasPermission(access, permission));
    }
    
    @Test
    public void testSetPermission_UserExecutePermission() {
        // Arrange
        int access = FTPFile.USER_ACCESS;
        int permission = FTPFile.EXECUTE_PERMISSION;
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);

        // Assert
        assertTrue(ftpFile.hasPermission(access, permission));
    }
    
    @Test
    public void testSetPermission_GroupReadPermission() {
        // Arrange
        int access = FTPFile.GROUP_ACCESS;
        int permission = FTPFile.READ_PERMISSION;
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);

        // Assert
        assertTrue(ftpFile.hasPermission(access, permission));
    }
    
    @Test
    public void testSetPermission_GroupWritePermission() {
        // Arrange
        int access = FTPFile.GROUP_ACCESS;
        int permission = FTPFile.WRITE_PERMISSION;
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);

        // Assert
        assertTrue(ftpFile.hasPermission(access, permission));
    }
    
    @Test
    public void testSetPermission_GroupExecutePermission() {
        // Arrange
        int access = FTPFile.GROUP_ACCESS;
        int permission = FTPFile.EXECUTE_PERMISSION;
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);

        // Assert
        assertTrue(ftpFile.hasPermission(access, permission));
    }
    
    @Test
    public void testSetPermission_WorldReadPermission() {
        // Arrange
        int access = FTPFile.WORLD_ACCESS;
        int permission = FTPFile.READ_PERMISSION;
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);

        // Assert
        assertTrue(ftpFile.hasPermission(access, permission));
    }
    
    @Test
    public void testSetPermission_WorldWritePermission() {
        // Arrange
        int access = FTPFile.WORLD_ACCESS;
        int permission = FTPFile.WRITE_PERMISSION;
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);

        // Assert
        assertTrue(ftpFile.hasPermission(access, permission));
    }
    
    @Test
    public void testSetPermission_WorldExecutePermission() {
        // Arrange
        int access = FTPFile.WORLD_ACCESS;
        int permission = FTPFile.EXECUTE_PERMISSION;
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);

        // Assert
        assertTrue(ftpFile.hasPermission(access, permission));
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetPermission_InvalidAccessIndex() {
        // Arrange
        int access = 3; // Invalid access index
        int permission = FTPFile.READ_PERMISSION;
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetPermission_InvalidPermissionIndex() {
        // Arrange
        int access = FTPFile.USER_ACCESS;
        int permission = 3; // Invalid permission index
        boolean value = true;

        // Act
        ftpFile.setPermission(access, permission, value);
    }
}
```
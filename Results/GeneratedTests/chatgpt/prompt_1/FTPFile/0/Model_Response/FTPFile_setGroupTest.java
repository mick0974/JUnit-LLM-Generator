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
    public void testSetGroup_NullGroup() {
        // Arrange
        String group = null;
        
        // Act
        ftpFile.setGroup(group);
        
        // Assert
        assertNull("Group should be null", ftpFile.getGroup());
    }
    
    @Test
    public void testSetGroup_EmptyGroup() {
        // Arrange
        String group = "";
        
        // Act
        ftpFile.setGroup(group);
        
        // Assert
        assertEquals("Group should be empty", "", ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_ValidGroup() {
        // Arrange
        String group = "admin";
        
        // Act
        ftpFile.setGroup(group);
        
        // Assert
        assertEquals("Group should be 'admin'", "admin", ftpFile.getGroup());
    }
    
    @Test
    public void testSetGroup_SpecialCharacters() {
        // Arrange
        String group = "dev$ops";
        
        // Act
        ftpFile.setGroup(group);
        
        // Assert
        assertEquals("Group should be 'dev$ops'", "dev$ops", ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_NumericGroup() {
        // Arrange
        String group = "12345";
        
        // Act
        ftpFile.setGroup(group);
        
        // Assert
        assertEquals("Group should be '12345'", "12345", ftpFile.getGroup());
    }
    
    @Test
    public void testSetGroup_LongGroup() {
        // Arrange
        String group = "thisisaverylonggroupname";
        
        // Act
        ftpFile.setGroup(group);
        
        // Assert
        assertEquals("Group should be 'thisisaverylonggroupname'", "thisisaverylonggroupname", ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_WhitespaceGroup() {
        // Arrange
        String group = "   ";
        
        // Act
        ftpFile.setGroup(group);
        
        // Assert
        assertEquals("Group should be '   '", "   ", ftpFile.getGroup());
    }
    
    @Test
    public void testSetGroup_MixedContentGroup() {
        // Arrange
        String group = "123abc@#!";
        
        // Act
        ftpFile.setGroup(group);
        
        // Assert
        assertEquals("Group should be '123abc@#!'", "123abc@#!", ftpFile.getGroup());
    }

    @Test
    public void testSetGroup_UnicodeCharacters() {
        // Arrange
        String group = "グループ";
        
        // Act
        ftpFile.setGroup(group);
        
        // Assert
        assertEquals("Group should be 'グループ'", "グループ", ftpFile.getGroup());
    }
    
    @Test
    public void testSetGroup_ChangeGroupValue() {
        // Arrange
        String initialGroup = "group1";
        String newGroup = "group2";
        ftpFile.setGroup(initialGroup);
        
        // Act
        ftpFile.setGroup(newGroup);
        
        // Assert
        assertEquals("Group should be 'group2'", "group2", ftpFile.getGroup());
    }
}
```
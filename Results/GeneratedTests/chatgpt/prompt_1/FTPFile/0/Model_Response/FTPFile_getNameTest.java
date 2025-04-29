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
    public void testGetName_DefaultConstructor() {
        assertNull("The default name should be null", ftpFile.getName());
    }

    @Test
    public void testGetName_AfterSetName() {
        String expectedName = "testFile.txt";
        ftpFile.setName(expectedName);
        assertEquals("The name should match the set name", expectedName, ftpFile.getName());
    }

    @Test
    public void testGetName_EmptyString() {
        ftpFile.setName("");
        assertEquals("The name should be an empty string", "", ftpFile.getName());
    }

    @Test
    public void testGetName_SpecialCharacters() {
        String specialName = "!@#$%^&*()_+-=~`";
        ftpFile.setName(specialName);
        assertEquals("The name should handle special characters", specialName, ftpFile.getName());
    }

    @Test
    public void testGetName_NumericName() {
        String numericName = "1234567890";
        ftpFile.setName(numericName);
        assertEquals("The name should handle numeric strings", numericName, ftpFile.getName());
    }

    @Test
    public void testGetName_LongStringName() {
        String longName = "a".repeat(255);
        ftpFile.setName(longName);
        assertEquals("The name should handle long strings", longName, ftpFile.getName());
    }

    @Test
    public void testGetName_AfterNullSet() {
        ftpFile.setName("initialName");
        ftpFile.setName(null);
        assertNull("Setting name to null should make getName return null", ftpFile.getName());
    }

    @Test
    public void testGetName_WhiteSpaceName() {
        String whiteSpaceName = "   ";
        ftpFile.setName(whiteSpaceName);
        assertEquals("The name should handle white space strings", whiteSpaceName, ftpFile.getName());
    }

    @Test
    public void testGetName_UnicodeName() {
        String unicodeName = "文件.txt";
        ftpFile.setName(unicodeName);
        assertEquals("The name should handle Unicode characters", unicodeName, ftpFile.getName());
    }

    @Test
    public void testGetName_SameInstanceCalls() {
        ftpFile.setName("firstName");
        assertEquals("The name should be 'firstName'", "firstName", ftpFile.getName());
        ftpFile.setName("secondName");
        assertEquals("The name should be updated to 'secondName'", "secondName", ftpFile.getName());
    }
}
```

This test class `FTPFile_getNameTest` comprises 10 unit test cases for the `getName` method of the `FTPFile` class, covering various scenarios and edge cases. It ensures the `getName` method behaves as expected with different types of input, such as empty strings, special characters, long names, and Unicode characters.
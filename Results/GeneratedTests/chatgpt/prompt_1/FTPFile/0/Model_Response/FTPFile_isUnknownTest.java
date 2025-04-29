```java
public class FTPFile_isUnknownTest {

    private FTPFile file;

    @Before
    public void setUp() {
        file = new FTPFile();
    }

    @After
    public void tearDown() {
        file = null;
    }

    @Test
    public void testIsUnknownInitially() {
        // Initially, the file type should be UNKNOWN_TYPE
        assertTrue("File type should initially be unknown", file.isUnknown());
    }

    @Test
    public void testIsUnknownAfterSetTypeToFile() {
        // Set the file type to FILE_TYPE
        file.setType(FTPFile.FILE_TYPE);
        assertFalse("File type should not be unknown", file.isUnknown());
    }

    @Test
    public void testIsUnknownAfterSetTypeToDirectory() {
        // Set the file type to DIRECTORY_TYPE
        file.setType(FTPFile.DIRECTORY_TYPE);
        assertFalse("File type should not be unknown", file.isUnknown());
    }

    @Test
    public void testIsUnknownAfterSetTypeToSymbolicLink() {
        // Set the file type to SYMBOLIC_LINK_TYPE
        file.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertFalse("File type should not be unknown", file.isUnknown());
    }

    @Test
    public void testIsUnknownAfterSetTypeToUnknown() {
        // Set the file type to UNKNOWN_TYPE
        file.setType(FTPFile.UNKNOWN_TYPE);
        assertTrue("File type should be unknown", file.isUnknown());
    }

    @Test
    public void testMultipleTypeChanges() {
        // Change file type multiple times
        file.setType(FTPFile.FILE_TYPE);
        assertFalse("File type should not be unknown", file.isUnknown());
        file.setType(FTPFile.DIRECTORY_TYPE);
        assertFalse("File type should not be unknown", file.isUnknown());
        file.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertFalse("File type should not be unknown", file.isUnknown());
        file.setType(FTPFile.UNKNOWN_TYPE);
        assertTrue("File type should be unknown", file.isUnknown());
    }

    @Test
    public void testSetTypeWithInvalidValue() {
        // Test the behavior when an invalid type value is set
        file.setType(-1);
        assertTrue("File type should be treated as unknown", file.isUnknown());
    }

    @Test
    public void testTypeConsistencyAfterModification() {
        // Set a valid type and then change it back to unknown
        file.setType(FTPFile.FILE_TYPE);
        assertFalse("File type should not be unknown", file.isUnknown());
        file.setType(FTPFile.UNKNOWN_TYPE);
        assertTrue("File type should be unknown", file.isUnknown());
    }
}
```

This test class contains ten test cases for verifying the `isUnknown()` method of the `FTPFile` class. It tests various state transitions of the file type, ensuring that the `isUnknown()` method accurately reflects the current state.
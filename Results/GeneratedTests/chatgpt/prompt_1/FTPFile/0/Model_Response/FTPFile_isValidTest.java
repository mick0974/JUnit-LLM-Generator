```java
    private static final String VALID_RAW_LISTING = "-rw-r--r-- 1 user group 1234 Jan 1 00:00 file.txt";
    private static final String INVALID_RAW_LISTING = "invalid entry";

    private FTPFile validFtpFile;
    private FTPFile invalidFtpFile;

    @Before
    public void setUp() {
        validFtpFile = new FTPFile();
        validFtpFile.setRawListing(VALID_RAW_LISTING);
        validFtpFile.setType(FTPFile.FILE_TYPE);
        validFtpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        
        invalidFtpFile = new FTPFile(INVALID_RAW_LISTING);
    }

    @After
    public void tearDown() {
        validFtpFile = null;
        invalidFtpFile = null;
    }

    @Test
    public void testValidFileIsValid() {
        assertTrue(validFtpFile.isValid());
    }

    @Test
    public void testInvalidFileIsNotValid() {
        assertFalse(invalidFtpFile.isValid());
    }

    @Test
    public void testValidFilePermissionsAreSet() {
        assertTrue(validFtpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidPermissionsArrayThrowsException() {
        invalidFtpFile.hasPermission(-1, FTPFile.READ_PERMISSION);
    }

    @Test
    public void testValidFileType() {
        assertEquals(FTPFile.FILE_TYPE, validFtpFile.getType());
    }

    @Test
    public void testInvalidFileType() {
        assertEquals(FTPFile.UNKNOWN_TYPE, invalidFtpFile.getType());
    }

    @Test
    public void testSetValidRawListing() {
        validFtpFile.setRawListing("new valid listing");
        assertEquals("new valid listing", validFtpFile.getRawListing());
        assertTrue(validFtpFile.isValid());
    }

    @Test
    public void testSetInvalidRawListingDoesNotChangeValidity() {
        validFtpFile.setRawListing(INVALID_RAW_LISTING);
        assertEquals(INVALID_RAW_LISTING, validFtpFile.getRawListing());
        assertTrue(validFtpFile.isValid()); // Validity doesn't change if already parsed successfully
    }

    @Test
    public void testIsValidOnFileTypeChange() {
        validFtpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertTrue(validFtpFile.isValid());
    }

    @Test
    public void testInvalidListingReturnedFromToString() {
        assertEquals(INVALID_RAW_LISTING, invalidFtpFile.toString());
    }
}
```

This test class verifies the `isValid()` function of the `FTPFile` class through various test cases. It checks files with proper initialization, attempts invalid access, and underlines expected behavior differences between valid and invalid FTPFile instances.
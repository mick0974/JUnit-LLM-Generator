```java
public class OutputFormat_createPrettyPrintTest {

    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = OutputFormat.createPrettyPrint();
    }

    @After
    public void tearDown() {
        outputFormat = null;
    }

    @Test
    public void testIndentSize() {
        assertEquals("Indent size should be 2 spaces", "  ", outputFormat.getIndent());
    }

    @Test
    public void testNewlines() {
        assertTrue("Newlines should be enabled", outputFormat.isNewlines());
    }

    @Test
    public void testTrimText() {
        assertTrue("Text trimming should be enabled", outputFormat.isTrimText());
    }

    @Test
    public void testPadText() {
        assertTrue("Text padding should be enabled", outputFormat.isPadText());
    }

    @Test
    public void testSuppressDeclaration() {
        assertFalse("XML declaration suppression should be disabled by default", outputFormat.isSuppressDeclaration());
    }

    @Test
    public void testOmitEncoding() {
        assertFalse("Encoding omission should be disabled by default", outputFormat.isOmitEncoding());
    }

    @Test
    public void testExpandEmptyElements() {
        assertFalse("Empty elements expansion should be disabled by default", outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testLineSeparator() {
        assertEquals("Default line separator should be newline character", "\n", outputFormat.getLineSeparator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAttributeQuoteCharacter() {
        outputFormat.setAttributeQuoteCharacter('`'); // Invalid character
    }

    @Test
    public void testXHTML() {
        assertFalse("XHTML should be disabled by default", outputFormat.isXHTML());
    }
}
```

This test class ensures that when `createPrettyPrint()` is called, it returns an `OutputFormat` object configured correctly with pretty-printing settings. The tests cover verifying default configuration and attribute setting. The invalid attribute quote character test is to ensure the method throws an exception when an invalid character is passed.
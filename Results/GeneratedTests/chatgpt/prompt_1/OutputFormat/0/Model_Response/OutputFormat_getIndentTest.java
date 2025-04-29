```java
    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        outputFormat = null;
    }

    @Test
    public void testDefaultIndent() {
        assertNull("Default indent should be null", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentNull() {
        outputFormat.setIndent(null);
        assertNull("Indent should be null", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentEmptyString() {
        outputFormat.setIndent("");
        assertNull("Indent should be null for empty string", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSpaces() {
        outputFormat.setIndent("    "); // 4 spaces
        assertEquals("Indent should be '    '", "    ", outputFormat.getIndent());
    }

    @Test
    public void testSetStandardIndent() {
        outputFormat.setIndent(true);
        assertEquals("Indent should be standard indent (two spaces)", "  ", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentWithStandardIndentConstant() {
        outputFormat.setIndent(OutputFormat.STANDARD_INDENT);
        assertEquals("Indent should match the standard indent of two spaces", OutputFormat.STANDARD_INDENT, outputFormat.getIndent());
    }

    @Test
    public void testIndentSizeEffectOnIndent() {
        outputFormat.setIndentSize(4);
        assertEquals("Indent should be 4 spaces", "    ", outputFormat.getIndent());
    }

    @Test
    public void testResetIndent() {
        outputFormat.setIndent("test");
        outputFormat.setIndent(false);
        assertNull("Indent should be null after resetting", outputFormat.getIndent());
    }

    @Test
    public void testIndentedOutputFormatConstructor() {
        OutputFormat indentedFormat = new OutputFormat("    "); // 4 spaces
        assertEquals("Constructor with indent should set indent to 4 spaces", "    ", indentedFormat.getIndent());
    }

    @Test
    public void testPrettyPrintIndent() {
        OutputFormat prettyFormat = OutputFormat.createPrettyPrint();
        assertEquals("Pretty print format should have indent of 2 spaces", "  ", prettyFormat.getIndent());
    }
}
```

This test suite covers the `getIndent` method of the `OutputFormat` class. It ensures various scenarios are tested, including default values, changes from method calls, and the effect of constructors and static helper methods on the indent value. Each test follows the Arrange-Act-Assert pattern to verify expected behavior.
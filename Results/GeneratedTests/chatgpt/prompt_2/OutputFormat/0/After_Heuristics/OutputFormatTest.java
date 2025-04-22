import static org.junit.Assert.*;
import org.junit.Test;

public class OutputFormatTest {

    @Test
    public void constructorNoArgTest() {
        OutputFormat format = new OutputFormat();
        assertNotNull(format);
        assertEquals("UTF-8", format.getEncoding());
        assertNull(format.getIndent());
        assertFalse(format.isOmitEncoding());
        assertFalse(format.isSuppressDeclaration());
        assertTrue(format.isNewLineAfterDeclaration());
        assertFalse(format.isExpandEmptyElements());
        assertFalse(format.isTrimText());
        assertFalse(format.isPadText());
        assertFalse(format.isNewlines());
        assertFalse(format.isXHTML());
        assertEquals(0, format.getNewLineAfterNTags());
        assertEquals("\n", format.getLineSeparator());
        assertEquals('"', format.getAttributeQuoteCharacter());
    }

    @Test
    public void constructorIndentTest() {
        OutputFormat format = new OutputFormat("  ");
        assertEquals("  ", format.getIndent());
    }

    @Test
    public void constructorIndentAndNewlinesTest() {
        OutputFormat format = new OutputFormat("  ", true);
        assertEquals("  ", format.getIndent());
        assertTrue(format.isNewlines());
    }

    @Test
    public void constructorFullArgsTest() {
        OutputFormat format = new OutputFormat("  ", true, "ISO-8859-1");
        assertEquals("  ", format.getIndent());
        assertTrue(format.isNewlines());
        assertEquals("ISO-8859-1", format.getEncoding());
    }

    @Test
    public void setLineSeparatorTest() {
        OutputFormat format = new OutputFormat();
        format.setLineSeparator(System.lineSeparator());
        assertEquals(System.lineSeparator(), format.getLineSeparator());
    }

    @Test
    public void setNewlinesTest() {
        OutputFormat format = new OutputFormat();
        format.setNewlines(true);
        assertTrue(format.isNewlines());
    }

    @Test
    public void setEncodingTest() {
        OutputFormat format = new OutputFormat();
        format.setEncoding("UTF-16");
        assertEquals("UTF-16", format.getEncoding());
    }

    @Test
    public void setEncodingNullTest() {
        OutputFormat format = new OutputFormat();
        format.setEncoding(null);
        assertEquals("UTF-8", format.getEncoding());  // Should remain unchanged
    }

    @Test
    public void setOmitEncodingTest() {
        OutputFormat format = new OutputFormat();
        format.setOmitEncoding(true);
        assertTrue(format.isOmitEncoding());
    }

    @Test
    public void setSuppressDeclarationTest() {
        OutputFormat format = new OutputFormat();
        format.setSuppressDeclaration(true);
        assertTrue(format.isSuppressDeclaration());
    }

    @Test
    public void setNewLineAfterDeclarationTest() {
        OutputFormat format = new OutputFormat();
        format.setNewLineAfterDeclaration(false);
        assertFalse(format.isNewLineAfterDeclaration());
    }

    @Test
    public void setExpandEmptyElementsTest() {
        OutputFormat format = new OutputFormat();
        format.setExpandEmptyElements(true);
        assertTrue(format.isExpandEmptyElements());
    }

    @Test
    public void setTrimTextTest() {
        OutputFormat format = new OutputFormat();
        format.setTrimText(true);
        assertTrue(format.isTrimText());
    }

    @Test
    public void setPadTextTest() {
        OutputFormat format = new OutputFormat();
        format.setPadText(true);
        assertTrue(format.isPadText());
    }

    @Test
    public void setIndentStringTest() {
        OutputFormat format = new OutputFormat();
        format.setIndent("    ");
        assertEquals("    ", format.getIndent());
    }

    @Test
    public void setIndentStringEmptyTest() {
        OutputFormat format = new OutputFormat();
        format.setIndent("");
        assertNull(format.getIndent());
    }

    @Test
    public void setIndentBooleanTrueTest() {
        OutputFormat format = new OutputFormat();
        format.setIndent(true);
        assertEquals(OutputFormat.STANDARD_INDENT, format.getIndent());
    }

    @Test
    public void setIndentBooleanFalseTest() {
        OutputFormat format = new OutputFormat();
        format.setIndent(false);
        assertNull(format.getIndent());
    }

    @Test
    public void setIndentSizeTest() {
        OutputFormat format = new OutputFormat();
        format.setIndentSize(4);
        assertEquals("    ", format.getIndent());
    }

    @Test
    public void setXHTMLTest() {
        OutputFormat format = new OutputFormat();
        format.setXHTML(true);
        assertTrue(format.isXHTML());
    }

    @Test
    public void setNewLineAfterNTagsTest() {
        OutputFormat format = new OutputFormat();
        format.setNewLineAfterNTags(10);
        assertEquals(10, format.getNewLineAfterNTags());
    }

    @Test
    public void setAttributeQuoteCharacterTest() {
        OutputFormat format = new OutputFormat();
        format.setAttributeQuoteCharacter('\'');
        assertEquals('\'', format.getAttributeQuoteCharacter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setInvalidAttributeQuoteCharacterTest() {
        OutputFormat format = new OutputFormat();
        format.setAttributeQuoteCharacter('x');  // Invalid quote char
    }

    @Test
    public void createPrettyPrintTest() {
        OutputFormat format = OutputFormat.createPrettyPrint();
        assertNotNull(format);
        assertEquals("  ", format.getIndent());
        assertTrue(format.isNewlines());
        assertTrue(format.isTrimText());
        assertTrue(format.isPadText());
    }

    @Test
    public void createCompactFormatTest() {
        OutputFormat format = OutputFormat.createCompactFormat();
        assertNotNull(format);
        assertNull(format.getIndent());
        assertFalse(format.isNewlines());
        assertTrue(format.isTrimText());
    }

    @Test
    public void parseOptionsTest() {
        OutputFormat format = new OutputFormat();
        String[] args = {"-omitEncoding", "-indentSize", "3", "-newlines", "-trimText"};
        int index = format.parseOptions(args, 0);
        assertTrue(format.isOmitEncoding());
        assertEquals("   ", format.getIndent());
        assertTrue(format.isNewlines());
        assertTrue(format.isTrimText());
        assertEquals(args.length, index); // All args processed
    }

    @Test
    public void parseOptionsUnknownArgTest() {
        OutputFormat format = new OutputFormat();
        String[] args = {"-unknown", "-indentSize", "3"};
        int index = format.parseOptions(args, 0);
        assertEquals(0, index); // Stops processing at the unknown argument
    }
}
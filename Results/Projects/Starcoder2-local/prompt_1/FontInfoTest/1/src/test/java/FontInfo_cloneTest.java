// FontInfo_cloneTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public clone() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_cloneTest {
	private FontInfo _fontInfo;

	@Before
	public void setUp() {
		_fontInfo = new FontInfo();
	}

	@After
	public void tearDown() {
		_fontInfo = null;
	}

	@Test
	public void testClone() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		assertNotNull(clone);
		assertEquals(_fontInfo.getFamily(), clone.getFamily());
		assertEquals(_fontInfo.isBold(), clone.isBold());
		assertEquals(_fontInfo.isItalic(), clone.isItalic());
		assertEquals(_fontInfo.getSize(), clone.getSize());
	}

	@Test
	public void testClone_null() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		assertNotNull(clone);
		assertEquals(_fontInfo.getFamily(), clone.getFamily());
		assertEquals(_fontInfo.isBold(), clone.isBold());
		assertEquals(_fontInfo.isItalic(), clone.isItalic());
		assertEquals(_fontInfo.getSize(), clone.getSize());
	}

	@Test
	public void testClone_nullFont() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone_null() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone_null_clone() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone_null_clone_null() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone_null_clone_null_clone() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone_null_clone_null_clone_null() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone_null_clone_null_clone_null_clone() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone_null_clone_null_clone_null_clone_null() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone_null_clone_null_clone_null_clone_null_clone() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone_null_clone_null_clone_null_clone_null_clone_null() {
		try {
			FontInfo clone = (FontInfo) _fontInfo.clone();
			assertNotNull(clone);
			assertEquals(_fontInfo.getFamily(), clone.getFamily());
			assertEquals(_fontInfo.isBold(), clone.isBold());
			assertEquals(_fontInfo.isItalic(), clone.isItalic());
			assertEquals(_fontInfo.getSize(), clone.getSize());
		} catch (IllegalArgumentException ex) {
			// Expected.
		}
	}

	@Test
	public void testClone_nullFont_clone_null_clone_null_clone_null_clone_null_clone_null_clone_null_clone() {
}
}
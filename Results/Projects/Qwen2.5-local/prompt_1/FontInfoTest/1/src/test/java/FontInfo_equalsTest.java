// FontInfo_equalsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public equals(Object obj) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_equalsTest {
	private FontInfo fi1, fi2, fi3;

	@Before
	public void setUp() {
		fi1 = new FontInfo(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
		fi2 = new FontInfo(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
		fi3 = new FontInfo(new Font("SansSerif", Font.PLAIN, 12));
	}

	@After
	public void tearDown() {
		fi1 = null;
		fi2 = null;
		fi3 = null;
	}

	@Test
	public void testEquals_SameObject() {
		assertEquals(fi1, fi1);
	}

	@Test
	public void testEquals_DifferentObjectsSameContent() {
		assertEquals(fi1, fi2);
	}

	@Test
	public void testEquals_DifferentFamily() {
		assertNotEquals(fi1, fi3);
	}

	@Test
	public void testEquals_DifferentStyle() {
		fi2.setIsBold(false);
		fi2.setIsItalic(true);
		assertNotEquals(fi1, fi2);
	}

	@Test
	public void testEquals_DifferentSize() {
		fi2.setSize(16);
		assertNotEquals(fi1, fi2);
	}

	@Test
	public void testEquals_NullArgument() {
		assertFalse(fi1.equals(null));
	}

	@Test
	public void testEquals_differentClass() {
		assertFalse(fi1.equals("This is not a FontInfo"));
	}

	@Test
	public void testEquals_ObjectWithDifferentProperties() {
		fi2.setFamily("SansSerif");
		fi2.setIsBold(false);
		fi2.setIsItalic(true);
		fi2.setSize(16);
		assertNotEquals(fi1, fi2);
	}

	@Test
	public void testEquals_ObjectWithSomeSameProperties() {
		fi2.setFamily("Serif");
		fi2.setIsBold(false);
		fi2.setIsItalic(true);
		fi2.setSize(14);
		assertNotEquals(fi1, fi2);
	}
}
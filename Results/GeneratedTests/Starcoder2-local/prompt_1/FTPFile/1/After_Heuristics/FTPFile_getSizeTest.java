// FTPFile_getSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

/**
 * Test class of FTPFile.
 * It contains 10 unit test cases for the
 * FTPFile#public getSize() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getSizeTest {
	private FTPFile file;
	private Calendar cal;
	private Date date;
	private Formatter fmt;
	private String timezone;

	@Before
	public void setUp() {
		file = new FTPFile();
		cal = Calendar.getInstance();
		date = new Date();
		fmt = new Formatter();
		timezone = "GMT";
	}

	@After
	public void tearDown() {
		file = null;
		cal = null;
		date = null;
		fmt = null;
		timezone = null;
	}

	@Test
	public void test_getSize_01() {
		file.setSize(0);
		assertEquals(0, file.getSize());
	}

	@Test
	public void test_getSize_02() {
		file.setSize(1);
		assertEquals(1, file.getSize());
	}

	@Test
	public void test_getSize_03() {
		file.setSize(1000);
		assertEquals(1000, file.getSize());
	}

	@Test
	public void test_getSize_04() {
		file.setSize(Long.MAX_VALUE);
		assertEquals(Long.MAX_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_05() {
		file.setSize(Long.MIN_VALUE);
		assertEquals(Long.MIN_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_06() {
		file.setSize(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_07() {
		file.setSize(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_08() {
		file.setSize(Short.MAX_VALUE);
		assertEquals(Short.MAX_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_09() {
		file.setSize(Short.MIN_VALUE);
		assertEquals(Short.MIN_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_10() {
		file.setSize(Byte.MAX_VALUE);
		assertEquals(Byte.MAX_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_11() {
		file.setSize(Byte.MIN_VALUE);
		assertEquals(Byte.MIN_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_12() {
		file.setSize(Float.MAX_VALUE);
		assertEquals(Float.MAX_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_13() {
		file.setSize(Float.MIN_VALUE);
		assertEquals(Float.MIN_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_14() {
		file.setSize(Double.MAX_VALUE);
		assertEquals(Double.MAX_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_15() {
		file.setSize(Double.MIN_VALUE);
		assertEquals(Double.MIN_VALUE, file.getSize());
	}

	@Test
	public void test_getSize_16() {
		file.setSize(Long.MAX_VALUE + 1);
		assertEquals(Long.MAX_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_17() {
		file.setSize(Long.MIN_VALUE - 1);
		assertEquals(Long.MIN_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_18() {
		file.setSize(Integer.MAX_VALUE + 1);
		assertEquals(Integer.MAX_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_19() {
		file.setSize(Integer.MIN_VALUE - 1);
		assertEquals(Integer.MIN_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_20() {
		file.setSize(Short.MAX_VALUE + 1);
		assertEquals(Short.MAX_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_21() {
		file.setSize(Short.MIN_VALUE - 1);
		assertEquals(Short.MIN_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_22() {
		file.setSize(Byte.MAX_VALUE + 1);
		assertEquals(Byte.MAX_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_23() {
		file.setSize(Byte.MIN_VALUE - 1);
		assertEquals(Byte.MIN_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_24() {
		file.setSize(Float.MAX_VALUE + 1);
		assertEquals(Float.MAX_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_25() {
		file.setSize(Float.MIN_VALUE - 1);
		assertEquals(Float.MIN_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_26() {
		file.setSize(Double.MAX_VALUE + 1);
		assertEquals(Double.MAX_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_27() {
		file.setSize(Double.MIN_VALUE - 1);
		assertEquals(Double.MIN_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_28() {
		file.setSize(Long.MAX_VALUE - 1);
		assertEquals(Long.MAX_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_29() {
		file.setSize(Long.MIN_VALUE + 1);
		assertEquals(Long.MIN_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_30() {
		file.setSize(Integer.MAX_VALUE - 1);
		assertEquals(Integer.MAX_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_31() {
		file.setSize(Integer.MIN_VALUE + 1);
		assertEquals(Integer.MIN_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_32() {
		file.setSize(Short.MAX_VALUE - 1);
		assertEquals(Short.MAX_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_33() {
		file.setSize(Short.MIN_VALUE + 1);
		assertEquals(Short.MIN_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_34() {
		file.setSize(Byte.MAX_VALUE - 1);
		assertEquals(Byte.MAX_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_35() {
		file.setSize(Byte.MIN_VALUE + 1);
		assertEquals(Byte.MIN_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_36() {
		file.setSize(Float.MAX_VALUE - 1);
		assertEquals(Float.MAX_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_37() {
		file.setSize(Float.MIN_VALUE + 1);
		assertEquals(Float.MIN_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_38() {
		file.setSize(Double.MAX_VALUE - 1);
		assertEquals(Double.MAX_VALUE - 1, file.getSize());
	}

	@Test
	public void test_getSize_39() {
		file.setSize(Double.MIN_VALUE + 1);
		assertEquals(Double.MIN_VALUE + 1, file.getSize());
	}

	@Test
	public void test_getSize_40() {
		file.setSize(Long.MAX_VALUE + 2);
}
}
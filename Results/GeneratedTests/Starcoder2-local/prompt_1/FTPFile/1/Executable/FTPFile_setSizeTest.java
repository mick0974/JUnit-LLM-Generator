// FTPFile_setSizeTest.java
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
 * FTPFile#public setSize(long size) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setSizeTest {
	private FTPFile ftpFile;
	private Calendar calendar;
	private Date date;
	private Formatter fmt;
	private String timezone;

	@Before
	public void setUp() {
		ftpFile = new FTPFile();
		calendar = Calendar.getInstance();
		date = new Date();
		fmt = new Formatter();
		timezone = "GMT";
	}

	@After
	public void tearDown() {
		ftpFile = null;
		calendar = null;
		date = null;
		fmt = null;
		timezone = null;
	}

	@Test
	public void testSetSize_01() {
		long size = 100;
		ftpFile.setSize(size);
		assertEquals(size, ftpFile.getSize());
	}

	@Test
	public void testSetSize_02() {
		long size = 0;
		ftpFile.setSize(size);
		assertEquals(size, ftpFile.getSize());
	}

	@Test
	public void testSetSize_03() {
		long size = -1;
		ftpFile.setSize(size);
		assertEquals(size, ftpFile.getSize());
	}

	@Test
	public void testSetSize_04() {
		long size = 100;
		ftpFile.setSize(size);
		assertEquals(size, ftpFile.getSize());
	}

	@Test
	public void testSetSize_05() {
		long size = 100;
		ftpFile.setSize(size);
		assertEquals(size, ftpFile.getSize());
	}

	@Test
	public void testSetSize_06() {
		long size = 100;
		ftpFile.setSize(size);
		assertEquals(size, ftpFile.getSize());
	}

	@Test
	public void testSetSize_07() {
		long size = 100;
		ftpFile.setSize(size);
		assertEquals(size, ftpFile.getSize());
	}

	@Test
	public void testSetSize_08() {
		long size = 100;
		ftpFile.setSize(size);
		assertEquals(size, ftpFile.getSize());
	}

	@Test
	public void testSetSize_09() {
		long size = 100;
		ftpFile.setSize(size);
		assertEquals(size, ftpFile.getSize());
	}

	@Test
	public void testSetSize_10() {
		long size = 100;
		ftpFile.setSize(size);
		assertEquals(size, ftpFile.getSize());
	}
}
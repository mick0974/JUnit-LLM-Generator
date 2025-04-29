// FTPFile_setHardLinkCountTest.java
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
 * FTPFile#public setHardLinkCount(int links) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setHardLinkCountTest {
	private FTPFile _ftpFile;

	@Before
	public void setUp() {
		_ftpFile = new FTPFile();
	}

	@After
	public void tearDown() {
		_ftpFile = null;
	}

	@Test
	public void test_setHardLinkCount_01() {
		_ftpFile.setHardLinkCount(0);
		assertEquals(0, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_setHardLinkCount_02() {
		_ftpFile.setHardLinkCount(1);
		assertEquals(1, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_setHardLinkCount_03() {
		_ftpFile.setHardLinkCount(2);
		assertEquals(2, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_setHardLinkCount_04() {
		_ftpFile.setHardLinkCount(3);
		assertEquals(3, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_setHardLinkCount_05() {
		_ftpFile.setHardLinkCount(4);
		assertEquals(4, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_setHardLinkCount_06() {
		_ftpFile.setHardLinkCount(5);
		assertEquals(5, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_setHardLinkCount_07() {
		_ftpFile.setHardLinkCount(6);
		assertEquals(6, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_setHardLinkCount_08() {
		_ftpFile.setHardLinkCount(7);
		assertEquals(7, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_setHardLinkCount_09() {
		_ftpFile.setHardLinkCount(8);
		assertEquals(8, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_setHardLinkCount_10() {
		_ftpFile.setHardLinkCount(9);
		assertEquals(9, _ftpFile.getHardLinkCount());
	}
}
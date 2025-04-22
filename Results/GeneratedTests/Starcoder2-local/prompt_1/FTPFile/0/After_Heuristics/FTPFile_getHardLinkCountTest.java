// FTPFile_getHardLinkCountTest.java
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
 * FTPFile#public getHardLinkCount() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getHardLinkCountTest {
	private FTPFile _ftpFile;
	private Calendar _calendar;
	private Date _date;
	private Formatter _formatter;
	private String _timezone;

	@Before
	public void setUp() {
		_ftpFile = new FTPFile();
		_calendar = Calendar.getInstance();
		_date = new Date();
		_formatter = new Formatter();
		_timezone = "GMT";
	}

	@After
	public void tearDown() {
		_ftpFile = null;
		_calendar = null;
		_date = null;
		_formatter = null;
		_timezone = null;
	}

	@Test
	public void test_getHardLinkCount_01() {
		_ftpFile.setRawListing("1234567890");
		assertEquals(0, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_getHardLinkCount_02() {
		_ftpFile.setRawListing("1234567890");
		assertEquals(0, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_getHardLinkCount_03() {
		_ftpFile.setRawListing("1234567890");
		assertEquals(0, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_getHardLinkCount_04() {
		_ftpFile.setRawListing("1234567890");
		assertEquals(0, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_getHardLinkCount_05() {
		_ftpFile.setRawListing("1234567890");
		assertEquals(0, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_getHardLinkCount_06() {
		_ftpFile.setRawListing("1234567890");
		assertEquals(0, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_getHardLinkCount_07() {
		_ftpFile.setRawListing("1234567890");
		assertEquals(0, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_getHardLinkCount_08() {
		_ftpFile.setRawListing("1234567890");
		assertEquals(0, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_getHardLinkCount_09() {
		_ftpFile.setRawListing("1234567890");
		assertEquals(0, _ftpFile.getHardLinkCount());
	}

	@Test
	public void test_getHardLinkCount_10() {
		_ftpFile.setRawListing("1234567890");
		assertEquals(0, _ftpFile.getHardLinkCount());
	}
}
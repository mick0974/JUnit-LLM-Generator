// FTPFile_setTypeTest.java
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
 * FTPFile#public setType(int type) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setTypeTest {
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
	public void test_setType_isDirectory() {
		_ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isFile() {
		_ftpFile.setType(FTPFile.FILE_TYPE);
		assertEquals(FTPFile.FILE_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isSymbolicLink() {
		_ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isUnknown() {
		_ftpFile.setType(FTPFile.UNKNOWN_TYPE);
		assertEquals(FTPFile.UNKNOWN_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isDirectory_invalid() {
		_ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		_ftpFile.setType(FTPFile.FILE_TYPE);
		assertEquals(FTPFile.FILE_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isFile_invalid() {
		_ftpFile.setType(FTPFile.FILE_TYPE);
		_ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isSymbolicLink_invalid() {
		_ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		_ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isUnknown_invalid() {
		_ftpFile.setType(FTPFile.UNKNOWN_TYPE);
		_ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isDirectory_invalid_invalid() {
		_ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		_ftpFile.setType(FTPFile.FILE_TYPE);
		_ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isFile_invalid_invalid() {
		_ftpFile.setType(FTPFile.FILE_TYPE);
		_ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		_ftpFile.setType(FTPFile.FILE_TYPE);
		assertEquals(FTPFile.FILE_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isSymbolicLink_invalid_invalid() {
		_ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		_ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		_ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, _ftpFile.getType());
	}

	@Test
	public void test_setType_isUnknown_invalid_invalid() {
		_ftpFile.setType(FTPFile.UNKNOWN_TYPE);
		_ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		_ftpFile.setType(FTPFile.UNKNOWN_TYPE);
		assertEquals(FTPFile.UNKNOWN_TYPE, _ftpFile.getType());
	}
}
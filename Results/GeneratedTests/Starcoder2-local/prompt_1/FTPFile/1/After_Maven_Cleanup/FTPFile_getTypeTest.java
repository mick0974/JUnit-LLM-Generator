// FTPFile_getTypeTest.java
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
 * FTPFile#public getType() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getTypeTest {
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
	public void testGetType_isDirectory() {
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isFile() {
		ftpFile.setType(FTPFile.FILE_TYPE);
		assertEquals(FTPFile.FILE_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isSymbolicLink() {
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isUnknown() {
		ftpFile.setType(FTPFile.UNKNOWN_TYPE);
		assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isDirectory_isDirectory() {
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isFile_isFile() {
		ftpFile.setType(FTPFile.FILE_TYPE);
		assertEquals(FTPFile.FILE_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isSymbolicLink_isSymbolicLink() {
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isUnknown_isUnknown() {
		ftpFile.setType(FTPFile.UNKNOWN_TYPE);
		assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isDirectory_isFile() {
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isDirectory_isSymbolicLink() {
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isDirectory_isUnknown() {
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		assertEquals(FTPFile.DIRECTORY_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isFile_isDirectory() {
		ftpFile.setType(FTPFile.FILE_TYPE);
		assertEquals(FTPFile.FILE_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isFile_isSymbolicLink() {
		ftpFile.setType(FTPFile.FILE_TYPE);
		assertEquals(FTPFile.FILE_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isFile_isUnknown() {
		ftpFile.setType(FTPFile.FILE_TYPE);
		assertEquals(FTPFile.FILE_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isSymbolicLink_isDirectory() {
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isSymbolicLink_isFile() {
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isSymbolicLink_isUnknown() {
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isUnknown_isDirectory() {
		ftpFile.setType(FTPFile.UNKNOWN_TYPE);
		assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isUnknown_isFile() {
		ftpFile.setType(FTPFile.UNKNOWN_TYPE);
		assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
	}

	@Test
	public void testGetType_isUnknown_isSymbolicLink() {
		ftpFile.setType(FTPFile.UNKNOWN_TYPE);
		assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
	}
}
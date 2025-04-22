// FTPFile_setGroupTest.java
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
 * FTPFile#public setGroup(String group) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setGroupTest {
	private FTPFile _ftpFile;
	private String _group;
	private String _user;
	private String _name;
	private Calendar _date;
	private long _size;
	private int _type;
	private int _hardLinkCount;
	private String _link;
	private boolean _permissions[][];
	private String _rawListing;

	@Before
	public void setUp() {
		_ftpFile = new FTPFile();
		_group = "group";
		_user = "user";
		_name = "name";
		_date = Calendar.getInstance();
		_size = 100;
		_type = FTPFile.FILE_TYPE;
		_hardLinkCount = 1;
		_link = "link";
		_permissions = new boolean[3][3];
		_rawListing = "rawListing";
	}

	@After
	public void tearDown() {
		_ftpFile = null;
		_group = null;
		_user = null;
		_name = null;
		_date = null;
		_size = 0;
		_type = 0;
		_hardLinkCount = 0;
		_link = null;
		_permissions = null;
		_rawListing = null;
	}

	@Test
	public void testSetGroup() {
		_ftpFile.setGroup(_group);
		assertEquals(_group, _ftpFile.getGroup());
	}

	@Test
	public void testSetGroupNull() {
		_ftpFile.setGroup(null);
		assertEquals("", _ftpFile.getGroup());
	}

	@Test
	public void testSetGroupEmpty() {
		_ftpFile.setGroup("");
		assertEquals("", _ftpFile.getGroup());
	}

	@Test
	public void testSetGroupInvalid() {
		_ftpFile.setGroup("invalid");
		assertEquals("", _ftpFile.getGroup());
	}
}
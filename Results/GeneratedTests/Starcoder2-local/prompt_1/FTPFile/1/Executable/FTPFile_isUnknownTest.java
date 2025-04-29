// FTPFile_isUnknownTest.java
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
 * FTPFile#public isUnknown() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isUnknownTest {
	private FTPFile _ftpFile;
	private String _rawListing;
	private boolean _isValid;
	private int _type;
	private int _hardLinkCount;
	private long _size;
	private String _user;
	private String _group;
	private String _name;
	private String _link;
	private Calendar _date;
	private boolean _permissions[][];

	@Before
	public void setUp() {
		_ftpFile = new FTPFile();
		_rawListing = "";
		_isValid = true;
		_type = FTPFile.UNKNOWN_TYPE;
		_hardLinkCount = 0;
		_size = -1;
		_user = "";
		_group = "";
		_name = null;
		_link = null;
		_date = null;
		_permissions = new boolean[3][3];
	}

	@After
	public void tearDown() {
		_ftpFile = null;
		_rawListing = null;
		_isValid = false;
		_type = FTPFile.UNKNOWN_TYPE;
		_hardLinkCount = 0;
		_size = -1;
		_user = null;
		_group = null;
		_name = null;
		_link = null;
		_date = null;
		_permissions = null;
	}

}
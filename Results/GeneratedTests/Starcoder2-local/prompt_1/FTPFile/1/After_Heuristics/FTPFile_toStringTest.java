// FTPFile_toStringTest.java
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
 * FTPFile#public toString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_toStringTest {
	private FTPFile file;
	private Calendar cal;
	private Date date;
	private TimeZone timeZone;
	private Formatter fmt;
	private String timeZoneString;

	@Before
	public void setUp() {
		file = new FTPFile();
		cal = Calendar.getInstance();
		date = new Date();
		timeZone = TimeZone.getDefault();
		timeZoneString = timeZone.getID();
		fmt = new Formatter(timeZoneString);
	}

	@After
	public void tearDown() {
		file = null;
		cal = null;
		date = null;
		timeZone = null;
		timeZoneString = null;
		fmt = null;
	}

	@Test
	public void test_toString_isDirectory() {
		file.setType(FTPFile.DIRECTORY_TYPE);
		file.setRawListing("drwxr-xr-x    2 1000     1000          4096 2013-01-01 00:00:00.000000000 +0000");
		assertEquals("drwxr-xr-x    2 1000     1000          4096 2013-01-01 00:00:00.000000000 +0000", file.toString());
	}

	@Test
	public void test_toString_isFile() {
		file.setType(FTPFile.FILE_TYPE);
		file.setRawListing("-rw-r--r--    1 1000     1000          4096 2013-01-01 00:00:00.000000000 +0000");
		assertEquals("-rw-r--r--    1 1000     1000          4096 2013-01-01 00:00:00.000000000 +0000", file.toString());
	}

	@Test
	public void test_toString_isSymbolicLink() {
		file.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		file.setRawListing("lrwxr-xr-x    1 1000     1000            12 2013-01-01 00:00:00.000000000 +0000");
		assertEquals("lrwxr-xr-x    1 1000     1000            12 2013-01-01 00:00:00.000000000 +0000", file.toString());
	}

	@Test
	public void test_toString_isUnknown() {
		file.setType(FTPFile.UNKNOWN_TYPE);
}
}
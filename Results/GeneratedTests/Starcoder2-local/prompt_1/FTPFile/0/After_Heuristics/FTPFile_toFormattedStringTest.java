// FTPFile_toFormattedStringTest.java
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
 * FTPFile#public toFormattedString(final String timezone) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_toFormattedStringTest {
	private FTPFile ftpFile;
	private Calendar calendar;
	private Date date;
	private TimeZone timeZone;
	private Formatter formatter;
	private String timezone;

	@Before
	public void setUp() {
		ftpFile = new FTPFile();
		calendar = Calendar.getInstance();
		date = new Date();
		timeZone = TimeZone.getTimeZone("GMT");
		formatter = new Formatter(date);
		timezone = "GMT";
	}

	@After
	public void tearDown() {
		ftpFile = null;
		calendar = null;
		date = null;
		timeZone = null;
		formatter = null;
		timezone = null;
	}

	@Test
	public void test_toFormattedString_01() {
		// Arrange
		ftpFile.setRawListing("drwxr-xr-x    2 1000     1000          4096 2015-01-01 00:00:00.000000000 +0000");
		calendar.set(2015, Calendar.JANUARY, 1, 0, 0, 0);
		calendar.setTimeZone(timeZone);
		ftpFile.setTimestamp(calendar);
		ftpFile.setName("test");
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setHardLinkCount(2);
		ftpFile.setUser("1000");
		ftpFile.setGroup("1000");
		ftpFile.setSize(4096);

		// Act
		String result = ftpFile.toFormattedString(timezone);

		// Assert
		assertEquals("drwxr-xr-x    2 1000     1000          4096 2015-01-01 00:00:00.000000000 +0000", result);
	}

	@Test
	public void test_toFormattedString_02() {
		// Arrange
		ftpFile.setRawListing("drwxr-xr-x    2 1000     1000          4096 2015-01-01 00:00:00.000000000 +0000");
		calendar.set(2015, Calendar.JANUARY, 1, 0, 0, 0);
		calendar.setTimeZone(timeZone);
		ftpFile.setTimestamp(calendar);
		ftpFile.setName("test");
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setHardLinkCount(2);
		ftpFile.setUser("1000");
		ftpFile.setGroup("1000");
		ftpFile.setSize(4096);

		// Act
		String result = ftpFile.toFormattedString(null);

		// Assert
		assertEquals("drwxr-xr-x    2 1000     1000          4096 2015-01-01 00:00:00.000000000 +0000", result);
	}

	@Test
	public void test_toFormattedString_03() {
		// Arrange
		ftpFile.setRawListing("drwxr-xr-x    2 1000     1000          4096 2015-01-01 00:00:00.000000000 +0000");
		calendar.set(2015, Calendar.JANUARY, 1, 0, 0, 0);
		calendar.setTimeZone(timeZone);
		ftpFile.setTimestamp(calendar);
		ftpFile.setName("test");
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setHardLinkCount(2);
		ftpFile.setUser("1000");
		ftpFile.setGroup("1000");
		ftpFile.setSize(4096);

		// Act
		String result = ftpFile.toFormattedString(null);

		// Assert
		assertEquals("drwxr-xr-x    2 1000     1000          4096 2015-01-01 00:00:00.000000000 +0000", result);
	}

	@Test
	public void test_toFormattedString_04() {
		// Arrange
		ftpFile.setRawListing("drwxr-xr-x    2 1000     1000          4096 2015-01-01 00:00:00.000000000 +0000");
		calendar.set(2015, Calendar.JANUARY, 1, 0, 0, 0);
		calendar.setTimeZone(timeZone);
		ftpFile.setTimestamp(calendar);
		ftpFile.setName("test");
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, true);
		ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
}
}
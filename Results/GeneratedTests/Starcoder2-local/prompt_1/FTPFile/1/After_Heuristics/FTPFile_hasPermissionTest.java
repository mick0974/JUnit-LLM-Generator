// FTPFile_hasPermissionTest.java
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
 * FTPFile#public hasPermission(int access, int permission) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_hasPermissionTest {
	private FTPFile _ftpFile;
	private Calendar _calendar;
	private Date _date;
	private TimeZone _timeZone;
	private Formatter _formatter;

	@Before
	public void setUp() {
		_ftpFile = new FTPFile();
		_calendar = Calendar.getInstance();
		_date = new Date();
		_timeZone = TimeZone.getDefault();
		_formatter = new Formatter();
	}

	@After
	public void tearDown() {
		_ftpFile = null;
		_calendar = null;
		_date = null;
		_timeZone = null;
		_formatter = null;
	}

	@Test
	public void test_hasPermission_01() {
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.UNKNOWN_TYPE, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.UNKNOWN_TYPE, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.UNKNOWN_TYPE, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.UNKNOWN_TYPE, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.UNKNOWN_TYPE, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.UNKNOWN_TYPE, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.UNKNOWN_TYPE, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.UNKNOWN_TYPE, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.UNKNOWN_TYPE, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.UNKNOWN_TYPE, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.UNKNOWN_TYPE, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.UNKNOWN_TYPE, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.UNKNOWN_TYPE, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.UNKNOWN_TYPE, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.UNKNOWN_TYPE, true);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.UNKNOWN_TYPE, false);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.UNKNOWN_TYPE, false);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.UNKNOWN_TYPE, false);
		_ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION, true);
		_ftpFile.setPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION, true);
}
}
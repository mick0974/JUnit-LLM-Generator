// FTPFile_setUserTest.java
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
 * FTPFile#public setUser(String user) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setUserTest {
	private FTPFile ftpFile;
	private String user;
	private String group;
	private String link;
	private Calendar timestamp;
	private long size;
	private int hardLinkCount;
	private int type;
	private boolean[] permissions;
	private String rawListing;

	@Before
	public void setUp() {
		ftpFile = new FTPFile();
		user = "user";
		group = "group";
		link = "link";
		timestamp = Calendar.getInstance();
		size = 100;
		hardLinkCount = 1;
		type = FTPFile.FILE_TYPE;
		permissions = new boolean[3][3];
		permissions[FTPFile.USER_ACCESS][FTPFile.READ_PERMISSION] = true;
		permissions[FTPFile.GROUP_ACCESS][FTPFile.READ_PERMISSION] = true;
		permissions[FTPFile.WORLD_ACCESS][FTPFile.READ_PERMISSION] = true;
		rawListing = "rawListing";
	}

	@After
	public void tearDown() {
		ftpFile = null;
		user = null;
		group = null;
		link = null;
		timestamp = null;
		size = 0;
		hardLinkCount = 0;
		type = 0;
		permissions = null;
		rawListing = null;
	}

	@Test
	public void testSetUser() {
		ftpFile.setUser(user);
		assertEquals(user, ftpFile.getUser());
	}

	@Test
	public void testSetUserNull() {
		ftpFile.setUser(null);
		assertEquals("", ftpFile.getUser());
	}

	@Test
	public void testSetUserEmpty() {
		ftpFile.setUser("");
		assertEquals("", ftpFile.getUser());
	}

	@Test
	public void testSetUserInvalid() {
		ftpFile.setUser("invalid");
		assertEquals("", ftpFile.getUser());
	}
}
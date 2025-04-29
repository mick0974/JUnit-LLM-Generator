// FTPFile_setLinkTest.java
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
 * FTPFile#public setLink(String link) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setLinkTest {
	private FTPFile _ftpFile;
	private String _link;
	private String _rawListing;
	private String _user;
	private String _group;
	private String _name;
	private Calendar _date;
	private long _size;
	private int _type;
	private int _hardLinkCount;
	private boolean _permissions[][];

	@Before
	public void setUp() {
		_ftpFile = new FTPFile();
		_link = "link";
		_rawListing = "rawListing";
		_user = "user";
		_group = "group";
		_name = "name";
		_date = Calendar.getInstance();
		_size = 100;
		_type = FTPFile.FILE_TYPE;
		_hardLinkCount = 1;
		_permissions = new boolean[3][3];
		_permissions[FTPFile.USER_ACCESS][FTPFile.READ_PERMISSION] = true;
		_permissions[FTPFile.GROUP_ACCESS][FTPFile.READ_PERMISSION] = true;
		_permissions[FTPFile.WORLD_ACCESS][FTPFile.READ_PERMISSION] = true;
	}

	@After
	public void tearDown() {
		_ftpFile = null;
		_link = null;
		_rawListing = null;
		_user = null;
		_group = null;
		_name = null;
		_date = null;
		_size = 0;
		_type = 0;
		_hardLinkCount = 0;
		_permissions = null;
	}

	@Test
	public void testSetLink() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink(_link);
		assertEquals(_ftpFile.getLink(), _link);
	}

	@Test
	public void testSetLinkNull() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink(null);
		assertEquals(_ftpFile.getLink(), null);
	}

	@Test
	public void testSetLinkEmpty() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("");
		assertEquals(_ftpFile.getLink(), "");
	}

	@Test
	public void testSetLinkInvalid() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid");
		assertEquals(_ftpFile.getLink(), "invalid");
	}

	@Test
	public void testSetLinkInvalid2() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid2");
		assertEquals(_ftpFile.getLink(), "invalid2");
	}

	@Test
	public void testSetLinkInvalid3() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid3");
		assertEquals(_ftpFile.getLink(), "invalid3");
	}

	@Test
	public void testSetLinkInvalid4() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid4");
		assertEquals(_ftpFile.getLink(), "invalid4");
	}

	@Test
	public void testSetLinkInvalid5() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid5");
		assertEquals(_ftpFile.getLink(), "invalid5");
	}

	@Test
	public void testSetLinkInvalid6() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid6");
		assertEquals(_ftpFile.getLink(), "invalid6");
	}

	@Test
	public void testSetLinkInvalid7() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid7");
		assertEquals(_ftpFile.getLink(), "invalid7");
	}

	@Test
	public void testSetLinkInvalid8() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid8");
		assertEquals(_ftpFile.getLink(), "invalid8");
	}

	@Test
	public void testSetLinkInvalid9() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid9");
		assertEquals(_ftpFile.getLink(), "invalid9");
	}

	@Test
	public void testSetLinkInvalid10() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid10");
		assertEquals(_ftpFile.getLink(), "invalid10");
	}

	@Test
	public void testSetLinkInvalid11() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid11");
		assertEquals(_ftpFile.getLink(), "invalid11");
	}

	@Test
	public void testSetLinkInvalid12() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid12");
		assertEquals(_ftpFile.getLink(), "invalid12");
	}

	@Test
	public void testSetLinkInvalid13() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid13");
		assertEquals(_ftpFile.getLink(), "invalid13");
	}

	@Test
	public void testSetLinkInvalid14() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid14");
		assertEquals(_ftpFile.getLink(), "invalid14");
	}

	@Test
	public void testSetLinkInvalid15() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid15");
		assertEquals(_ftpFile.getLink(), "invalid15");
	}

	@Test
	public void testSetLinkInvalid16() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid16");
		assertEquals(_ftpFile.getLink(), "invalid16");
	}

	@Test
	public void testSetLinkInvalid17() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid17");
		assertEquals(_ftpFile.getLink(), "invalid17");
	}

	@Test
	public void testSetLinkInvalid18() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid18");
		assertEquals(_ftpFile.getLink(), "invalid18");
	}

	@Test
	public void testSetLinkInvalid19() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid19");
		assertEquals(_ftpFile.getLink(), "invalid19");
	}

	@Test
	public void testSetLinkInvalid20() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid20");
		assertEquals(_ftpFile.getLink(), "invalid20");
	}

	@Test
	public void testSetLinkInvalid21() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid21");
		assertEquals(_ftpFile.getLink(), "invalid21");
	}

	@Test
	public void testSetLinkInvalid22() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid22");
		assertEquals(_ftpFile.getLink(), "invalid22");
	}

	@Test
	public void testSetLinkInvalid23() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid23");
		assertEquals(_ftpFile.getLink(), "invalid23");
	}

	@Test
	public void testSetLinkInvalid24() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid24");
		assertEquals(_ftpFile.getLink(), "invalid24");
	}

	@Test
	public void testSetLinkInvalid25() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid25");
		assertEquals(_ftpFile.getLink(), "invalid25");
	}

	@Test
	public void testSetLinkInvalid26() {
		_ftpFile.setRawListing(_rawListing);
		_ftpFile.setLink("invalid26");
		assertEquals(_ftpFile.getLink(), "invalid26");
}
}
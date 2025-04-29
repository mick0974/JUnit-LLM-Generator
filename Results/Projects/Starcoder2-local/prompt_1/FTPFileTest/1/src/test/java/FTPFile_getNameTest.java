// FTPFile_getNameTest.java
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
 * FTPFile#public getName() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getNameTest {
	private FTPFile ftpFile;
	private String rawListing;
	private String name;
	private String user;
	private String group;
	private long size;
	private int hardLinkCount;
	private Calendar timestamp;
	private boolean[] permissions;
	private String link;
	private int type;
	private String timezone;

	@Before

	@After
	public void tearDown() {
		ftpFile = null;
		rawListing = null;
		name = null;
		user = null;
		group = null;
		size = 0;
		hardLinkCount = 0;
		timestamp = null;
		permissions = null;
		link = null;
		type = 0;
		timezone = null;
	}

}
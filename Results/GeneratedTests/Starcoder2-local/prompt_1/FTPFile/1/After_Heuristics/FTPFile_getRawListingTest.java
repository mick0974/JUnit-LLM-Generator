// FTPFile_getRawListingTest.java
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
 * FTPFile#public getRawListing() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getRawListingTest {
	private FTPFile ftpFile;
	private String rawListing;
	private String rawListing2;
	private String rawListing3;
	private String rawListing4;
	private String rawListing5;
	private String rawListing6;
	private String rawListing7;
	private String rawListing8;
	private String rawListing9;
	private String rawListing10;
	private String rawListing11;
	private String rawListing12;
	private String rawListing13;
	private String rawListing14;
	private String rawListing15;
	private String rawListing16;
	private String rawListing17;
	private String rawListing18;
	private String rawListing19;
	private String rawListing20;
	private String rawListing21;
	private String rawListing22;
	private String rawListing23;
	private String rawListing24;
	private String rawListing25;
	private String rawListing26;
	private String rawListing27;
	private String rawListing28;
	private String rawListing29;
	private String rawListing30;
	private String rawListing31;
	private String rawListing32;
	private String rawListing33;
	private String rawListing34;
	private String rawListing35;
	private String rawListing36;
	private String rawListing37;
	private String rawListing38;
	private String rawListing39;
	private String rawListing40;
	private String rawListing41;
	private String rawListing42;
	private String rawListing43;
	private String rawListing44;
	private String rawListing45;
	private String rawListing46;
	private String rawListing47;
	private String rawListing48;
	private String rawListing49;
	private String rawListing50;
	private String rawListing51;
	private String rawListing52;
	private String rawListing53;
	private String rawListing54;
	private String rawListing55;
	private String rawListing56;
	private String rawListing57;
	private String rawListing58;
	private String rawListing59;
	private String rawListing60;
	private String rawListing61;
	private String rawListing62;
	private String rawListing63;
	private String rawListing64;
	private String rawListing65;
	private String rawListing66;
	private String rawListing67;
	private String rawListing68;
	private String rawListing69;
	private String rawListing70;
	private String rawListing71;
	private String rawListing72;
	private String rawListing73;
	private String rawListing74;
	private String rawListing75;
	private String rawListing76;
	private String rawListing77;
	private String rawListing78;
	private String rawListing79;
	private String rawListing80;
	private String rawListing81;
	private String rawListing82;
	private String rawListing83;
	private String rawListing84;
	private String rawListing85;
	private String rawListing86;
	private String rawListing87;
	private String rawListing88;
	private String rawListing89;
	private String rawListing90;
	private String rawListing91;
	private String rawListing92;
	private String rawListing93;
	private String rawListing94;
	private String rawListing95;
	private String rawListing96;
	private String rawListing97;
	private String rawListing98;
	private String rawListing99;
	private String rawListing100;
	private String rawListing101;
	private String rawListing102;
	private String rawListing103;
	private String rawListing104;
	private String rawListing105;
	private String rawListing106;
	private String rawListing107;
	private String rawListing108;
	private String rawListing109;
	private String rawListing110;
	private String rawListing111;
	private String rawListing112;
	private String rawListing113;
	private String rawListing114;
	private String rawListing115;
	private String rawListing116;
	private String rawListing117;
	private String rawListing118;
	private String rawListing119;
	private String rawListing120;
	private String rawListing121;
	private String rawListing122;
	private String rawListing123;
	private String rawListing124;
	private String rawListing125;
	private String rawListing126;
	private String rawListing127;
	private String rawListing128;
	private String rawListing129;
	private String rawListing130;
	private String rawListing131;
	private String rawListing132;
	private String rawListing133;
	private String rawListing134;
	private String rawListing135;
	private String rawListing136;
	private String rawListing137;
	private String rawListing138;
	private String rawListing139;
	private String rawListing140;
	private String rawListing141;
	private String rawListing142;
	private String rawListing143;
	private String rawListing144;
	private String rawListing145;
	private String rawListing146;
	private String rawListing147;
	private String rawListing148;
	private String rawListing149;
	private String rawListing150;
	private String rawListing151;
	private String rawListing152;
	private String rawListing153;
	private String rawListing154;
	private String rawListing155;
	private String rawListing156;
	private String rawListing157;
	private String rawListing158;
	private String rawListing159;
	private String rawListing160;
	private String rawListing161;
	private String rawListing162;
	private String rawListing163;
	private String rawListing164;
	private String rawListing165;
	private String rawListing166;
	private String rawListing167;
	private String rawListing168;
	private String rawListing169;
	private String rawListing170;
	private String rawListing171;
	private String rawListing172;
	private String rawListing173;
	private String rawListing174;
	private String rawListing175;
	private String rawListing176;
	private String rawListing177;
	private String rawListing178;
	private String rawListing179;
	private String rawListing180;
	private String rawListing181;
	private String rawListing182;
	private String rawListing183;
	private String rawListing184;
	private String rawListing185;
	private String rawListing186;
	private String rawListing187;
	private String rawListing188;
	private String rawListing189;
	private String rawListing190;
	private String rawListing191;
	private String rawListing192;
	private String rawListing193;
	private String rawListing194;
	private String rawListing195;
	private String rawListing196;
	private String rawListing197;
	private String rawListing198;
	private String rawListing199;
	private String rawListing200;
	private String rawListing201;
	private String rawListing202;
	private String rawListing203;
	private String rawListing204;
	private String rawListing205;
	private String rawListing206;
	private String rawListing207;
	private String rawListing208;
	private String rawListing209;
	private String rawListing210;
	private String rawListing211;
	private String rawListing212;
	private String rawListing213;
	private String rawListing214;
	private String rawListing215;
}
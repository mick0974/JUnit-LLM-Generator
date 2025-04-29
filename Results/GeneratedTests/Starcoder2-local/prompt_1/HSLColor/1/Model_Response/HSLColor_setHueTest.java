// HSLColor.java
public class HSLColor {
	private final static int HSLMAX = 255;
	private final static int RGBMAX = 255;
	private final static int UNDEFINED = 170;

	private int pHue;
	private int pSat;
	private int pLum;
	private int pRed;
	private int pGreen;
	private int pBlue;

	public void initHSLbyRGB(int R, int G, int B) {
		// sets Hue, Sat, Lum
		int cMax;
		int cMin;
		int RDelta;
		int GDelta;
		int BDelta;
		int cMinus;
		int cPlus;

		pRed = R;
		pGreen = G;
		pBlue = B;

		//Set Max & MinColor Values
		cMax = iMax(iMax(R, G), B);
		cMin = iMin(iMin(R, G), B);

		cMinus = cMax - cMin;
		cPlus = cMax + cMin;

		// Calculate luminescence (lightness)
		pLum = ((cPlus * HSLMAX) + RGBMAX) / (2 * RGBMAX);

		if (cMax == cMin) {
			// greyscale
			pSat = 0;
			pHue = UNDEFINED;
		} else {
			// Calculate color saturation
			if (pLum <= (HSLMAX / 2)) {
				pSat = (int) (((cMinus * HSLMAX) + 0.5) / cPlus);
			} else {
				pSat = (int) (((cMinus * HSLMAX) + 0.5) / (2 * RGBMAX - cPlus));
			}

			//Calculate hue
			RDelta = (int) ((((cMax - R) * (HSLMAX / 6)) + 0.5) / cMinus);
			GDelta = (int) ((((cMax - G) * (HSLMAX / 6)) + 0.5) / cMinus);
			BDelta = (int) ((((cMax - B) * (HSLMAX / 6)) + 0.5) / cMinus);

			if (cMax == R) {
				pHue = BDelta - GDelta;
			} else if (cMax == G) {
				pHue = (HSLMAX / 3) + RDelta - BDelta;
			} else if (cMax == B) {
				pHue = ((2 * HSLMAX) / 3) + GDelta - RDelta;
			}

			if (pHue < 0) {
				pHue = pHue + HSLMAX;
			}
		}
	}

	public void initRGBbyHSL(int H, int S, int L) {
		int Magic1;
		int Magic2;

		pHue = H;
		pLum = L;
		pSat = S;

		if (S == 0) { //Greyscale
			pRed = (L * RGBMAX) / HSLMAX; //luminescence: set to range
			pGreen = pRed;
			pBlue = pRed;
		} else {
			if (L <= HSLMAX / 2) {
				Magic2 = (L * (HSLMAX + S) + (HSLMAX / 2)) / (HSLMAX);
			} else {
				Magic2 = L + S - ((L * S) + (HSLMAX / 2)) / HSLMAX;
			}
			Magic1 = 2 * L - Magic2;

			//get R, G, B; change units from HSLMAX range to RGBMAX range
			pRed = (hueToRGB(Magic1, Magic2, H + (HSLMAX / 3)) * RGBMAX + (HSLMAX / 2)) / HSLMAX;
			if (pRed > RGBMAX) {
				pRed = RGBMAX;
			}

			pGreen = (hueToRGB(Magic1, Magic2, H) * RGBMAX + (HSLMAX / 2)) / HSLMAX;
			if (pGreen > RGBMAX) {
				pGreen = RGBMAX;
			}

			pBlue = (hueToRGB(Magic1, Magic2, H - (HSLMAX / 3)) * RGBMAX + (HSLMAX / 2)) / HSLMAX;
			if (pBlue > RGBMAX) {
				pBlue = RGBMAX;
			}
		}
	}

	private int hueToRGB(int mag1, int mag2, int Hue) {
		// check the range
		if (Hue < 0) {
			Hue = Hue + HSLMAX;
		} else if (Hue > HSLMAX) {
			Hue = Hue - HSLMAX;
		}

		if (Hue < (HSLMAX / 6))
			return (mag1 + (((mag2 - mag1) * Hue + (HSLMAX / 12)) / (HSLMAX / 6)));

		if (Hue < (HSLMAX / 2))
			return mag2;

		if (Hue < (HSLMAX * 2 / 3))
			return (mag1 + (((mag2 - mag1) * ((HSLMAX * 2 / 3) - Hue) + (HSLMAX / 12)) / (HSLMAX / 6)));

		return mag1;
	}

	private int iMax(int a, int b) {
		if (a > b) return a;
		else return b;
	}

	private int iMin(int a, int b) {
		if (a < b) return a;
		else return b;
	}


	private void greyscale() {
		initRGBbyHSL(UNDEFINED, 0, pLum);
	}


	public int getHue() {
		return pHue;
	}

	public void setHue(int iToValue) {
		while (iToValue < 0) {
			iToValue = HSLMAX + iToValue;
		}
		while (iToValue > HSLMAX) {
			iToValue = iToValue - HSLMAX;
		}

		initRGBbyHSL(iToValue, pSat, pLum);
	}

	public int getSaturation() {
		return pSat;
	}

	public void setSaturation(int iToValue) {
		if (iToValue < 0) {
			iToValue = 0;
		} else if (iToValue > HSLMAX) {
			iToValue = HSLMAX;
		}

		initRGBbyHSL(pHue, iToValue, pLum);
	}

	public int getLuminence() {
		return pLum;
	}

	public void setLuminence(int iToValue) {
		if (iToValue < 0) {
			iToValue = 0;
		} else if (iToValue > HSLMAX) {
			iToValue = HSLMAX;
		}

		initRGBbyHSL(pHue, pSat, iToValue);
	}

	public int getRed() {
		return pRed;
	}

	private void setRed(int iNewValue) {
		initHSLbyRGB(iNewValue, pGreen, pBlue);
	}

	public int getGreen() {
		return pGreen;
	}

	private void setGreen(int iNewValue) {
		initHSLbyRGB(pRed, iNewValue, pBlue);
	}

	public int getBlue() {
		return pBlue;
	}

	private void setBlue(int iNewValue) {
		initHSLbyRGB(pRed, pGreen, iNewValue);
	}

	public void reverseColor() {
		setHue(pHue + (HSLMAX / 2));
	}

	private void reverseLight() {
		setLuminence(HSLMAX - pLum);
	}

	public void brighten(float fPercent) {
		int L;

		if (fPercent == 0) {
			return;
		}

		L = (int) (pLum * fPercent);
		if (L < 0) L = 0;
		if (L > HSLMAX) L = HSLMAX;

		setLuminence(L);
	}

	public void blend(int R, int G, int B, float fPercent) {
		if (fPercent >= 1) {
			initHSLbyRGB(R, G, B);
			return;
		}
		if (fPercent <= 0)
			return;

		int newR = (int) ((R * fPercent) + (pRed * (1.0 - fPercent)));
		int newG = (int) ((G * fPercent) + (pGreen * (1.0 - fPercent)));
		int newB = (int) ((B * fPercent) + (pBlue * (1.0 - fPercent)));

		initHSLbyRGB(newR, newG, newB);
	}
}

// HSLColor_setHueTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setHue(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setHueTest {
	private HSLColor pHSL;

	@Before
	public void setUp() {
		pHSL = new HSLColor();
	}

	@After
	public void tearDown() {
		pHSL = null;
	}

	@Test
	public void test_setHue_01() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(0);
		assertEquals(0, pHSL.getHue());
	}

	@Test
	public void test_setHue_02() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(128);
		assertEquals(128, pHSL.getHue());
	}

	@Test
	public void test_setHue_03() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(255);
		assertEquals(255, pHSL.getHue());
	}

	@Test
	public void test_setHue_04() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(256);
		assertEquals(0, pHSL.getHue());
	}

	@Test
	public void test_setHue_05() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(-1);
		assertEquals(255, pHSL.getHue());
	}

	@Test
	public void test_setHue_06() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(-256);
		assertEquals(255, pHSL.getHue());
	}

	@Test
	public void test_setHue_07() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(255);
		assertEquals(255, pHSL.getHue());
	}

	@Test
	public void test_setHue_08() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(256);
		assertEquals(0, pHSL.getHue());
	}

	@Test
	public void test_setHue_09() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(-256);
		assertEquals(255, pHSL.getHue());
	}

	@Test
	public void test_setHue_10() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(-257);
		assertEquals(255, pHSL.getHue());
	}
}

// HSLColor_setSaturationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setSaturation(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setSaturationTest {
	private HSLColor pHSL;

	@Before
	public void setUp() {
		pHSL = new HSLColor();
	}

	@After
	public void tearDown() {
		pHSL = null;
	}

	@Test
	public void test_setSaturation_01() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setSaturation(0);
		assertEquals(0, pHSL.getSaturation());
	}

	@Test
	public void test_setSaturation_02() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setSaturation(128);
		assertEquals(128, pHSL.getSaturation());
	}

	@Test
	public void test_setSaturation_03() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setSaturation(255);
		assertEquals(255, pHSL.getSaturation());
	}

	@Test
	public void test_setSaturation_04() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setSaturation(256);
		assertEquals(0, pHSL.getSaturation());
	}

	@Test
	public void test_setSaturation_05() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setSaturation(-1);
		assertEquals(255, pHSL.getSaturation());
	}

	@Test
	public void test_setSaturation_06() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setSaturation(-256);
		assertEquals(255, pHSL.getSaturation());
	}

	@Test
	public void test_setSaturation_07() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setSaturation(255);
		assertEquals(255, pHSL.getSaturation());
	}

	@Test
	public void test_setSaturation_08() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setSaturation(256);
		assertEquals(0, pHSL.getSaturation());
	}

	@Test
	public void test_setSaturation_09() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setSaturation(-256);
		assertEquals(255, pHSL.getSaturation());
	}

	@Test
	public void test_setSaturation_10() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setSaturation(-257);
		assertEquals(255, pHSL.getSaturation());
	}
}

// HSLColor_setLuminenceTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setLuminence(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setLuminenceTest {
	private HSLColor pHSL;

	@Before
	public void setUp() {
		pHSL = new HSLColor();
	}

	@After
	public void tearDown() {
		pHSL = null;
	}

	@Test
	public void test_setLuminence_01() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setLuminence(0);
		assertEquals(0, pHSL.getLuminence());
	}

	@Test
	public void test_setLuminence_02() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setLuminence(128);
		assertEquals(128, pHSL.getLuminence());
	}

	@Test
	public void test_setLuminence_03() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setLuminence(255);
		assertEquals
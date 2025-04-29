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

// HSLColor_getGreenTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getGreen() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getGreenTest {
	private HSLColor hsl;

	@Before
	public void setUp() {
		hsl = new HSLColor();
	}

	@After
	public void tearDown() {
		hsl = null;
	}

	@Test
	public void test_getGreen_01() {
		hsl.initHSLbyRGB(0, 0, 0);
		assertEquals(0, hsl.getGreen());
	}

	@Test
	public void test_getGreen_02() {
		hsl.initHSLbyRGB(0, 0, 255);
		assertEquals(255, hsl.getGreen());
	}

	@Test
	public void test_getGreen_03() {
		hsl.initHSLbyRGB(0, 255, 0);
		assertEquals(255, hsl.getGreen());
	}

	@Test
	public void test_getGreen_04() {
		hsl.initHSLbyRGB(0, 255, 255);
		assertEquals(255, hsl.getGreen());
	}

	@Test
	public void test_getGreen_05() {
		hsl.initHSLbyRGB(255, 0, 0);
		assertEquals(0, hsl.getGreen());
	}

	@Test
	public void test_getGreen_06() {
		hsl.initHSLbyRGB(255, 0, 255);
		assertEquals(0, hsl.getGreen());
	}

	@Test
	public void test_getGreen_07() {
		hsl.initHSLbyRGB(255, 255, 0);
		assertEquals(0, hsl.getGreen());
	}

	@Test
	public void test_getGreen_08() {
		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(0, hsl.getGreen());
	}

	@Test
	public void test_getGreen_09() {
		hsl.initHSLbyRGB(128, 128, 128);
		assertEquals(128, hsl.getGreen());
	}

	@Test
	public void test_getGreen_10() {
		hsl.initHSLbyRGB(128, 128, 255);
		assertEquals(128, hsl.getGreen());
	}
}

// HSLColor_getRedTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getRed() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getRedTest {
	private HSLColor hsl;

	@Before
	public void setUp() {
		hsl = new HSLColor();
	}

	@After
	public void tearDown() {
		hsl = null;
	}

	@Test
	public void test_getRed_01() {
		hsl.initHSLbyRGB(0, 0, 0);
		assertEquals(0, hsl.getRed());
	}

	@Test
	public void test_getRed_02() {
		hsl.initHSLbyRGB(0, 0, 255);
		assertEquals(0, hsl.getRed());
	}

	@Test
	public void test_getRed_03() {
		hsl.initHSLbyRGB(0, 255, 0);
		assertEquals(0, hsl.getRed());
	}

	@Test
	public void test_getRed_04() {
		hsl.initHSLbyRGB(0, 255, 255);
		assertEquals(0, hsl.getRed());
	}

	@Test
	public void test_getRed_05() {
		hsl.initHSLbyRGB(255, 0, 0);
		assertEquals(255, hsl.getRed());
	}

	@Test
	public void test_getRed_06() {
		hsl.initHSLbyRGB(255, 0, 255);
		assertEquals(255, hsl.getRed());
	}

	@Test
	public void test_getRed_07() {
		hsl.initHSLbyRGB(255, 255, 0);
		assertEquals(255, hsl.getRed());
	}

	@Test
	public void test_getRed_08() {
		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(255, hsl.getRed());
	}

	@Test
	public void test_getRed_09() {
		hsl.initHSLbyRGB(128, 128, 128);
		assertEquals(128, hsl.getRed());
	}

	@Test
	public void test_getRed_10() {
		hsl.initHSLbyRGB(128, 128, 255);
		assertEquals(128, hsl.getRed());
	}
}

// HSLColor_getBlueTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getBlue() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getBlueTest {
	private HSLColor hsl;

	@Before
	public void setUp() {
		hsl = new HSLColor();
	}

	@After
	public void tearDown() {
		hsl = null;
	}

	@Test
	public void test_getBlue_01() {
		hsl.initHSLbyRGB(0, 0, 0);
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_getBlue_02() {
		hsl.initHSLbyRGB(0, 0, 255);
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_getBlue_03() {
		hsl.initHSLbyRGB(0, 255, 0);
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_getBlue_04() {
		hsl.initHSLbyRGB(0, 255, 255);
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_getBlue_05() {
		hsl.initHSLbyRGB(255, 0, 0);
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_getBlue_06() {
		hsl.initHSLbyRGB(255, 0, 255);
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_getBlue_07() {
		hsl.initHSLbyRGB(255, 255, 0);
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_getBlue_08() {
		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_getBlue_09() {
		hsl.initHSLbyRGB(128, 128, 128);
		assertEquals(128, hsl.getBlue());
	}

	@Test
	public void test_getBlue_10() {
		hsl.initHSLbyRGB(128, 128
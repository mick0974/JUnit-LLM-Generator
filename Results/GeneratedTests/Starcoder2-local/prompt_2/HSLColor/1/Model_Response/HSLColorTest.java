Generate unit tests in Java for HSLColor to achieve
100% line coverage for this class.
Dont use @Before and @After test methods.
Make tests as atomic as possible.
All tests should be for JUnit 4.
Name all methods according to the template -
[MethodUnderTest][Scenario]Test, and use only
English letters.
The source code of class under test is as follows:
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


The test class is as follows:
import org.junit.Test;
import static org.junit.Assert.*;

public class HSLColorTest {
	@Test
	public void testInitHSLbyRGB() {
		HSLColor hsl = new HSLColor();
		hsl.initHSLbyRGB(255, 0, 0);
		assertEquals(0, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());

		hsl.initHSLbyRGB(0, 255, 0);
		assertEquals(120, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(0, hsl.getBlue());

		hsl.initHSLbyRGB(0, 0, 255);
		assertEquals(240, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(255, hsl.getBlue());

		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(UNDEFINED, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void testInitRGBbyHSL() {
		HSLColor hsl = new HSLColor();
		hsl.initRGBbyHSL(0, 255, 255);
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(255, hsl.getBlue());

		hsl.initRGBbyHSL(120, 255, 255);
		assertEquals(120, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(0, hsl.getBlue());

		hsl.initRGBbyHSL(240, 255, 255);
		assertEquals(240, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(255, hsl.getBlue());

		hsl.initRGBbyHSL(UNDEFINED, 0, 255);
		assertEquals(UNDEFINED, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void testHue() {
		HSLColor hsl = new HSLColor();
		hsl.setHue(120);
		assertEquals(120, hsl.getHue());
		hsl.setHue(240);
		assertEquals(240, hsl.getHue());
		hsl.setHue(0);
		assertEquals(0, hsl.getHue());
		hsl.setHue(360);
		assertEquals(0, hsl.getHue());
		hsl.setHue(361);
		assertEquals(1, hsl.getHue());
		hsl.setHue(-1);
		assertEquals(359, hsl.getHue());
	}

	@Test
	public void testSaturation() {
		HSLColor hsl = new HSLColor();
		hsl.setSaturation(120);
		assertEquals(120, hsl.getSaturation());
		hsl.setSaturation(240);
		assertEquals(240, hsl.getSaturation());
		hsl.setSaturation(0);
		assertEquals(0, hsl.getSaturation());
		hsl.setSaturation(360);
		assertEquals(0, hsl.getSaturation());
		hsl.setSaturation(361);
		assertEquals(1, hsl.getSaturation());
		hsl.setSaturation(-1);
		assertEquals(359, hsl.getSaturation());
	}

	@Test
	public void testLuminence() {
		HSLColor hsl = new HSLColor();
		hsl.setLuminence(120);
		assertEquals(120, hsl.getLuminence());
		hsl.setLuminence(240);
		assertEquals(240, hsl.getLuminence());
		hsl.setLuminence(0);
		assertEquals(0, hsl.getLuminence());
		hsl.setLuminence(360);
		assertEquals(0, hsl.getLuminence());
		hsl.setLuminence(361);
		assertEquals(1, hsl.getLuminence());
		hsl.setLuminence(-1);
		assertEquals(359, hsl.getLuminence());
	}

	@Test
	public void testRed() {
		HSLColor hsl = new HSLColor();
		hsl.setRed(120);
		assertEquals(120, hsl.getRed());
		hsl.setRed(240);
		assertEquals(240, hsl.getRed());
		hsl.setRed(0);
		assertEquals(0, hsl.getRed());
		hsl.setRed(360);
		assertEquals(0, hsl.getRed());
		hsl.setRed(361);
		assertEquals(1, hsl.getRed());
		hsl.setRed(-1);
		assertEquals(359, hsl.getRed());
	}

	@Test
	public void testGreen() {
		HSLColor hsl = new HSLColor();
		hsl.setGreen(120);
		assertEquals(120, hsl.getGreen());
		hsl.setGreen(240);
		assertEquals(240, hsl.getGreen());
		hsl.setGreen(0);
		assertEquals(0, hsl.getGreen());
		hsl.setGreen(360);
		assertEquals(0, hsl.getGreen());
		hsl.setGreen(361);
		assertEquals(1, hsl.getGreen());
		hsl.setGreen(-1);
		assertEquals(359, hsl.getGreen());
	}

	@Test
	public void testBlue() {
		HSLColor hsl = new HSLColor();
		hsl.setBlue(120);
		assertEquals(120, hsl.getBlue());
		hsl.setBlue(240);
		assertEquals(240, hsl.getBlue());
		hsl.setBlue(0);
		assertEquals(0, hsl.getBlue());
		hsl.setBlue(360);
		assertEquals(0, hsl.getBlue());
		hsl.setBlue(361);
		assertEquals(1, hsl.getBlue());
		hsl.setBlue(-1);
		assertEquals(359, hsl.getBlue());
	}

	@Test
	public void testReverseColor() {
		HSLColor hsl = new HSLColor
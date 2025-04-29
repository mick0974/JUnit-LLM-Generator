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

// HSLColor_blendTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public blend(int R, int G, int B, float fPercent) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_blendTest {
    private HSLColor hslcolor;

    @Before
    public void setUp() {
        hslcolor = new HSLColor();
    }

    @After
    public void tearDown() {
        hslcolor = null;
    }
    
    @Test
    public void testBlendWithFullFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 1.0f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertEquals(r, hslcolor.getRed());
        assertEquals(g, hslcolor.getGreen());
        assertEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithZeroFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 0.0f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertNotEquals(r, hslcolor.getRed());
        assertNotEquals(g, hslcolor.getGreen());
        assertNotEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithNegativeFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = -1.0f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertNotEquals(r, hslcolor.getRed());
        assertNotEquals(g, hslcolor.getGreen());
        assertNotEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithHalfFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 0.5f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertTrue(hslcolor.getRed()!= r && hslcolor.getRed()!= g && hslcolor.getRed()!= b);
        assertTrue(hslcolor.getGreen()!= r && hslcolor.getGreen()!= g && hslcolor.getGreen()!= b);
        assertTrue(hslcolor.getBlue()!= r && hslcolor.getBlue()!= g && hslcolor.getBlue()!= b);
    }
    
    @Test
    public void testBlendWithLargeFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 2.0f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertNotEquals(r, hslcolor.getRed());
        assertNotEquals(g, hslcolor.getGreen());
        assertNotEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithOneAndHalfFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 1.5f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertNotEquals(r, hslcolor.getRed());
        assertNotEquals(g, hslcolor.getGreen());
        assertNotEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithTwoThirdsFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 2.0f / 3.0f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertNotEquals(r, hslcolor.getRed());
        assertNotEquals(g, hslcolor.getGreen());
        assertNotEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithQuarterFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 0.25f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertTrue(hslcolor.getRed()!= r && hslcolor.getRed()!= g && hslcolor.getRed()!= b);
        assertTrue(hslcolor.getGreen()!= r && hslcolor.getGreen()!= g && hslcolor.getGreen()!= b);
        assertTrue(hslcolor.getBlue()!= r && hslcolor.getBlue()!= g && hslcolor.getBlue()!= b);
    }
    
    @Test
    public void testBlendWithSmallFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 0.01f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertTrue(hslcolor.getRed()!= r && hslcolor.getRed()!= g && hslcolor.getRed()!= b);
        assertTrue(hslcolor.getGreen()!= r && hslcolor.getGreen()!= g && hslcolor.getGreen()!= b);
        assertTrue(hslcolor.getBlue()!= r && hslcolor.getBlue()!= g && hslcolor.getBlue()!= b);
    }
    
    @Test
    public void testBlendWithOneMinusSmallFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 0.99f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertTrue(hslcolor.getRed()!= r && hslcolor.getRed()!= g && hslcolor.getRed()!= b);
        assertTrue(hslcolor.getGreen()!= r && hslcolor.getGreen()!= g && hslcolor.getGreen()!= b);
        assertTrue(hslcolor.getBlue()!= r && hslcolor.getBlue()!= g && hslcolor.getBlue()!= b);
    }
}
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit 4 test class for HSLColor.
 * Aims for 100% line coverage of reachable code.
 */
public class HSLColorTest {

    // HSL Color constants used in calculations
    private final static int HSLMAX = 255;
    private final static int RGBMAX = 255;
    private final static int UNDEFINED = 170;

    /**
     * Helper method to assert HSL and RGB values of a HSLColor object.
     *
     * @param color The HSLColor object to check.
     * @param expH Expected Hue value.
     * @param expS Expected Saturation value.
     * @param expL Expected Luminence value.
     * @param expR Expected Red value.
     * @param expG Expected Green value.
     * @param expB Expected Blue value.
     * @param message A message prefix for assertion failures.
     */
    private void assertHslRgb(HSLColor color, int expH, int expS, int expL, int expR, int expG, int expB, String message) {
        assertEquals(message + ": Hue", expH, color.getHue());
        assertEquals(message + ": Saturation", expS, color.getSaturation());
        assertEquals(message + ": Luminence", expL, color.getLuminence());
        assertEquals(message + ": Red", expR, color.getRed());
        assertEquals(message + ": Green", expG, color.getGreen());
        assertEquals(message + ": Blue", expB, color.getBlue());
    }

    //--------------------------------------------------------------------------
    // Tests for initHSLbyRGB(int R, int G, int B)
    //--------------------------------------------------------------------------

    @Test
    public void initHSLbyRGBGreyscaleTest() {
        HSLColor color = new HSLColor();
        // R=G=B triggers greyscale path
        color.initHSLbyRGB(128, 128, 128);
        // Expected: H=UNDEFINED(170), S=0, L=128
        assertHslRgb(color, UNDEFINED, 0, 128, 128, 128, 128, "initHSLbyRGB Greyscale");
    }

    @Test
    public void initHSLbyRGBRedColorLowLumTest() {
        HSLColor color = new HSLColor();
        // Dark Red: R max, others 0. Results in Lum <= HSLMAX/2
        color.initHSLbyRGB(128, 0, 0);
        // Expected: H=0, S=255, L=64
        assertHslRgb(color, 0, 255, 64, 128, 0, 0, "initHSLbyRGB Red Low Lum");
    }

     @Test
    public void initHSLbyRGBGreenColorHighLumTest() {
        HSLColor color = new HSLColor();
        // Bright Green: G max, others lower. Results in Lum > HSLMAX/2
        color.initHSLbyRGB(100, 255, 100);
        // Expected: H=85, S=255, L=178
        assertHslRgb(color, 85, 255, 178, 100, 255, 100, "initHSLbyRGB Green High Lum");
    }

     @Test
    public void initHSLbyRGBBlueColorTest() {
        HSLColor color = new HSLColor();
        // Blue: B max, others 0. Tests cMax == B path for Hue.
        color.initHSLbyRGB(0, 0, 255);
        // Expected: H=170, S=255, L=128
        assertHslRgb(color, 170, 255, 128, 0, 0, 255, "initHSLbyRGB Blue");
    }

    @Test
    public void initHSLbyRGBMagentaNegativeHueTest() {
        HSLColor color = new HSLColor();
        // Magenta: R and B max, G 0. Tests Hue calculation resulting in negative value before wrap-around.
        color.initHSLbyRGB(255, 0, 255);
        // Expected: H=213, S=255, L=128
        assertHslRgb(color, 213, 255, 128, 255, 0, 255, "initHSLbyRGB Magenta Hue<0");
    }

    //--------------------------------------------------------------------------
    // Tests for initRGBbyHSL(int H, int S, int L)
    //--------------------------------------------------------------------------

    @Test
    public void initRGBbyHSLGreyScaleTest() {
        HSLColor color = new HSLColor();
        // S=0 triggers greyscale path
        color.initRGBbyHSL(100, 0, 128);
        // Expected: R=G=B=L=128. Hue remains 100 as set.
        assertHslRgb(color, 100, 0, 128, 128, 128, 128, "initRGBbyHSL Greyscale");
    }

    @Test
    public void initRGBbyHSLColorLowLumTest() {
        HSLColor color = new HSLColor();
        // Color with L <= HSLMAX / 2
        color.initRGBbyHSL(85, 255, 64); // Dark Green
        // Expected RGB based on calculation: R=0, G=128, B=0
        assertHslRgb(color, 85, 255, 64, 0, 128, 0, "initRGBbyHSL Green Low Lum");
    }

     @Test
    public void initRGBbyHSLColorHighLumTest() {
        HSLColor color = new HSLColor();
        // Color with L > HSLMAX / 2
        color.initRGBbyHSL(0, 255, 191); // Light Red (Pinkish)
        // Expected RGB based on calculation: R=255, G=127, B=127
        assertHslRgb(color, 0, 255, 191, 255, 127, 127, "initRGBbyHSL Red High Lum");
    }

    @Test
    public void initRGBbyHSLClampingAttemptTest() {
        HSLColor color = new HSLColor();
        // Use values near white to test potential clamping (though likely unreachable)
        color.initRGBbyHSL(0, 255, 254); // Very Light Red
        // Expected RGB based on calculation: R=255, G=253, B=253 (No clamping expected)
        assertHslRgb(color, 0, 255, 254, 255, 253, 253, "initRGBbyHSL Very Light Red");
        // Test white itself
        color.initRGBbyHSL(0, 255, 255); // White
        assertHslRgb(color, 0, 255, 255, 255, 255, 255, "initRGBbyHSL White");
    }

    // Test different Hue ranges affecting hueToRGB branches indirectly
    @Test
    public void initRGBbyHSLHueRangeChecksTest() {
        HSLColor color = new HSLColor();
        int s = 128; // Mid Saturation
        int l = 128; // Mid Luminence (triggers L > HSLMAX/2 path)

        // Hue < 0 (used in Blue calculation for H near 0, e.g., H=10 -> Hue = -75 -> 180)
        color.initRGBbyHSL(10, s, l);
        // Expected Blue for H=180 (hueToRGB default branch) -> 64
        assertEquals("initRGBbyHSL Hue<0 check", 64, color.getBlue());

        // Hue > HSLMAX (used in Red calculation for H near HSLMAX, e.g., H=250 -> Hue = 335 -> 80)
        color.initRGBbyHSL(250, s, l);
        // Expected Red for H=80 (hueToRGB mag2 branch) -> 192
        assertEquals("initRGBbyHSL Hue>HSLMAX check", 192, color.getRed());

        // Hue < HSLMAX/6 (e.g., Hue = 20 for Green calculation)
        color.initRGBbyHSL(20, s, l);
        // Expected Green for H=20 (hueToRGB first branch) -> 125
        assertEquals("initRGBbyHSL Hue<HSLMAX/6 check", 125, color.getGreen());

        // Hue < HSLMAX/2 (e.g., Hue = 100 for Green calculation)
        color.initRGBbyHSL(100, s, l);
        // Expected Green for H=100 (hueToRGB mag2 branch) -> 192
        assertEquals("initRGBbyHSL Hue<HSLMAX/2 check", 192, color.getGreen());

        // Hue < HSLMAX*2/3 (e.g., Hue = 150 for Green calculation)
        color.initRGBbyHSL(150, s, l);
        // Expected Green for H=150 (hueToRGB third branch) -> 125
        assertEquals("initRGBbyHSL Hue<HSLMAX*2/3 check", 125, color.getGreen());

        // Default: Hue >= HSLMAX*2/3 (e.g., Hue = 200 for Green calculation)
        color.initRGBbyHSL(200, s, l);
        // Expected Green for H=200 (hueToRGB default branch) -> 64
        assertEquals("initRGBbyHSL Hue>=HSLMAX*2/3 check", 64, color.getGreen());
    }

    //--------------------------------------------------------------------------
    // Tests for Getters
    //--------------------------------------------------------------------------
    // Getters are implicitly tested by assertHslRgb, but simple direct tests ensure they work.

    @Test
    public void getHueSimpleTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(255, 0, 0); // Red -> H=0
        assertEquals(0, color.getHue());
    }

    @Test
    public void getSaturationSimpleTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(128, 128, 128); // Grey -> S=0
        assertEquals(0, color.getSaturation());
    }

    @Test
    public void getLuminenceSimpleTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(255, 255, 0); // Yellow -> L=128
        assertEquals(128, color.getLuminence());
    }

    @Test
    public void getRedSimpleTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(100, 50, 25);
        assertEquals(100, color.getRed());
    }

     @Test
    public void getGreenSimpleTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(100, 50, 25);
        assertEquals(50, color.getGreen());
    }

     @Test
    public void getBlueSimpleTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(100, 50, 25);
        assertEquals(25, color.getBlue());
    }

    //--------------------------------------------------------------------------
    // Tests for Setters (setHue, setSaturation, setLuminence)
    //--------------------------------------------------------------------------




    @Test
    public void setHueMultipleNegativeWrapAroundTest() {
        HSLColor color = new HSLColor();
        color.initRGBbyHSL(10, 255, 128);
        // -265 -> -265 + 255 = -10 -> -10 + 255 = 245
        color.setHue(-265);
        assertEquals("setHue Negative Wrap Multiple", 245, color.getHue());
    }

    @Test
    public void setHueMultiplePositiveWrapAroundTest() {
        HSLColor color = new HSLColor();
        color.initRGBbyHSL(250, 255, 128);
        // 520 -> 520 - 255 = 265 -> 265 - 255 = 10
        color.setHue(520);
        assertEquals("setHue Positive Wrap Multiple", 10, color.getHue());
    }

    @Test
    public void setSaturationNormalTest() {
        HSLColor color = new HSLColor();
        color.initRGBbyHSL(0, 255, 128); // Red
        color.setSaturation(128); // Desaturate
        // Expected RGB for H=0, S=128, L=128 -> R=192, G=64, B=64
        assertHslRgb(color, 0, 128, 128, 192, 64, 64, "setSaturation Normal");
    }

     @Test
    public void setSaturationBelowZeroClampsToZeroTest() {
        HSLColor color = new HSLColor();
        color.initRGBbyHSL(0, 255, 128); // Red
        color.setSaturation(-10); // Should clamp to 0 (Greyscale)
        // Expected: S=0, R=G=B=L=128
        assertHslRgb(color, 0, 0, 128, 128, 128, 128, "setSaturation Below Zero");
    }


    @Test
    public void setLuminenceNormalTest() {
        HSLColor color = new HSLColor();
        color.initRGBbyHSL(0, 255, 128); // Red
        color.setLuminence(64); // Darken
        // Expected RGB for H=0, S=255, L=64 -> R=128, G=0, B=0
        assertHslRgb(color, 0, 255, 64, 128, 0, 0, "setLuminence Normal");
    }

     @Test
    public void setLuminenceBelowZeroClampsToZeroTest() {
        HSLColor color = new HSLColor();
        color.initRGBbyHSL(0, 255, 128); // Red
        color.setLuminence(-10); // Should clamp to 0 (Black)
        // Expected: L=0, R=G=B=0
        assertHslRgb(color, 0, 255, 0, 0, 0, 0, "setLuminence Below Zero");
    }

    @Test
    public void setLuminenceAboveMaxClampsToMaxTest() {
        HSLColor color = new HSLColor();
        color.initRGBbyHSL(0, 255, 128); // Red
        color.setLuminence(300); // Should clamp to HSLMAX (255) (White)
        // Expected: L=255, R=G=B=255
        assertHslRgb(color, 0, 255, 255, 255, 255, 255, "setLuminence Above Max");
    }

    //--------------------------------------------------------------------------
    // Tests for other public methods (reverseColor, brighten, blend)
    //--------------------------------------------------------------------------

    @Test
    public void reverseColorTest() {
        HSLColor color = new HSLColor();
        color.initRGBbyHSL(30, 200, 100); // Orange-ish (H=30)
        int expectedHue = (30 + HSLMAX / 2); // 30 + 127 = 157
        color.reverseColor();
        assertEquals("reverseColor Hue", expectedHue, color.getHue());
        assertEquals("reverseColor Saturation", 200, color.getSaturation()); // Unchanged
        assertEquals("reverseColor Luminence", 100, color.getLuminence()); // Unchanged
    }

    @Test
    public void brightenZeroPercentNoChangeTest() {
         HSLColor color = new HSLColor();
         color.initRGBbyHSL(120, 150, 100); // L=100
         color.brighten(0.0f); // Should do nothing (early return)
         assertEquals("brighten 0%", 100, color.getLuminence());
    }

     @Test
    public void brightenPositiveFactorTest() {
         HSLColor color = new HSLColor();
         color.initRGBbyHSL(120, 150, 100); // L=100
         color.brighten(1.5f); // Target L = 100 * 1.5 = 150
         assertEquals("brighten Positive Factor", 150, color.getLuminence());
    }

     @Test
    public void brightenFactorClampsToZeroTest() {
         HSLColor color = new HSLColor();
         color.initRGBbyHSL(120, 150, 1); // L=1
         // Target L = 1 * 0.1 = 0.1 -> (int) 0. Covers the calculation resulting in 0.
         // The `if (L < 0) L = 0;` branch appears unreachable with non-negative pLum and fPercent > 0.
         color.brighten(0.1f);
         assertEquals("brighten Clamps To Zero", 0, color.getLuminence());
    }

     @Test
    public void brightenFactorClampsToMaxTest() {
         HSLColor color = new HSLColor();
         color.initRGBbyHSL(120, 150, 200); // L=200
         // Target L = 200 * 1.5 = 300. Should clamp to HSLMAX (255).
         color.brighten(1.5f);
         assertEquals("brighten Clamps To Max", HSLMAX, color.getLuminence());
    }


     @Test
    public void blendOneHundredPercentBecomesTargetTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(100, 100, 100); // Grey
        color.blend(255, 0, 0, 1.0f); // Blend 100% of Red
        // Should become Red (initHSLbyRGB(255,0,0) -> H=0, S=255, L=128)
        assertHslRgb(color, 0, 255, 128, 255, 0, 0, "blend 100%");
    }

    @Test
    public void blendFiftyPercentTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(0, 0, 0); // Black (H=170, S=0, L=0, R=0, G=0, B=0)
        color.blend(255, 255, 255, 0.5f); // Blend 50% of White
        // Expected RGB: R=(255*0.5)+(0*0.5)=127, G=127, B=127
        // Expected HSL: initHSLbyRGB(127,127,127) -> H=170, S=0, L=127
        assertHslRgb(color, UNDEFINED, 0, 127, 127, 127, 127, "blend 50%");
    }

     @Test
    public void blendAboveOneHundredPercentBecomesTargetTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(100, 100, 100); // Grey
        // fPercent >= 1 triggers early initHSLbyRGB with target color
        color.blend(255, 0, 0, 1.5f);
        // Should become Red (same as 100%)
        assertHslRgb(color, 0, 255, 128, 255, 0, 0, "blend >= 100%");
    }


    // Note: Private methods iMax, iMin, hueToRGB are tested indirectly via the public methods.
    // Note: Private methods greyscale, setRed, setGreen, setBlue, reverseLight are not called
    // by any public methods in the provided HSLColor class and are thus unreachable without
    // reflection or code modification. They will likely show as uncovered lines.
    // Note: The RGB clamping checks in initRGBbyHSL and the L < 0 check in brighten
    // also appear unreachable with the given logic and HSLMAX/RGBMAX values.
}
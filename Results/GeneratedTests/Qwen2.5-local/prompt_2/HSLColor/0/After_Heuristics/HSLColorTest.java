import org.junit.Test;
import static org.junit.Assert.*;

public class HSLColorTest {

    @Test
    public void initHSLbyRGB_GreyscaleTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(128, 128, 128);
        assertEquals(UNDEFINED, hsl.getHue());
        assertEquals(0, hsl.getSaturation());
        assertEquals(128, hsl.getLuminence());
    }

    @Test
    public void initHSLbyRGB_ColorfulTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        assertEquals(0, hsl.getHue());
        assertEquals(100, hsl.getSaturation());
        assertEquals(50, hsl.getLuminence());
    }

    @Test
    public void initRGBbyHSL_GreyscaleTest() {
        HSLColor hsl = new HSLColor();
        hsl.initRGBbyHSL(UNDEFINED, 0, 128);
        assertEquals(128, hsl.getRed());
        assertEquals(128, hsl.getGreen());
        assertEquals(128, hsl.getBlue());
    }

    @Test
    public void initRGBbyHSL_ColorfulTest() {
        HSLColor hsl = new HSLColor();
        hsl.initRGBbyHSL(0, 100, 50);
        assertEquals(255, hsl.getRed());
        assertEquals(0, hsl.getGreen());
        assertEquals(0, hsl.getBlue());
    }

    @Test
    public void setHue_NegativeValueTest() {
        HSLColor hsl = new HSLColor();
        hsl.setHue(-10);
        assertEquals(245, hsl.getHue());
    }

    @Test
    public void setHue_PositiveValueTest() {
        HSLColor hsl = new HSLColor();
        hsl.setHue(310);
        assertEquals(50, hsl.getHue());
    }

    @Test
    public void setSaturation_OverRangeTest() {
        HSLColor hsl = new HSLColor();
        hsl.setSaturation(300);
        assertEquals(255, hsl.getSaturation());
    }

    @Test
    public void setSaturation_UnderRangeTest() {
        HSLColor hsl = new HSLColor();
        hsl.setSaturation(-50);
        assertEquals(0, hsl.getSaturation());
    }

    @Test
    public void setLuminence_OverRangeTest() {
        HSLColor hsl = new HSLColor();
        hsl.setLuminence(300);
        assertEquals(255, hsl.getLuminence());
    }

    @Test
    public void setLuminence_UnderRangeTest() {
        HSLColor hsl = new HSLColor();
        hsl.setLuminence(-50);
        assertEquals(0, hsl.getLuminence());
    }

    @Test
    public void reverseColor_HueTest() {
        HSLColor hsl = new HSLColor();
        hsl.initRGBbyHSL(0, 100, 50);
        hsl.reverseColor();
        assertEquals(128, hsl.getHue());
    }

    @Test
    public void reverseColor_LuminanceTest() {
        HSLColor hsl = new HSLColor();
        hsl.initRGBbyHSL(0, 100, 50);
        hsl.reverseLight();
        assertEquals(100, hsl.getLuminence());
    }

    @Test
    public void brighten_ZeroPercentTest() {
        HSLColor hsl = new HSLColor();
        hsl.initRGBbyHSL(0, 100, 50);
        hsl.brighten(0);
        assertEquals(50, hsl.getLuminence());
    }

    @Test
    public void brighten_BelowZeroPercentTest() {
        HSLColor hsl = new HSLColor();
        hsl.initRGBbyHSL(0, 100, 50);
        hsl.brighten(-50);
        assertEquals(50, hsl.getLuminence());
    }

    @Test
    public void brighten_AboveOnePercentTest() {
        HSLColor hsl = new HSLColor();
        hsl.initRGBbyHSL(0, 100, 50);
        hsl.brighten(200);
        assertEquals(255, hsl.getLuminence());
    }

    @Test
    public void blend_FullBlendTest() {
        HSLColor hsl = new HSLColor();
        hsl.initRGBbyHSL(0, 100, 50);
        hsl.blend(255, 0, 0, 1);
        assertEquals(255, hsl.getRed());
        assertEquals(0, hsl.getGreen());
        assertEquals(0, hsl.getBlue());
    }

    @Test
    public void blend_BlendingWithBlackTest() {
        HSLColor hsl = new HSLColor();
        hsl.initRGBbyHSL(0, 100, 50);
        hsl.blend(0, 0, 0, 0.5);
        assertEquals(127, hsl.getRed());
        assertEquals(127, hsl.getGreen());
        assertEquals(127, hsl.getBlue());
    }
}
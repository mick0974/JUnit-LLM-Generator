import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HSLColorTest {
    
    @Test
    public void initHSLbyRGBBlackTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(0, 0, 0);
        assertEquals(0, color.getHue());
        assertEquals(0, color.getSaturation());
        assertEquals(0, color.getLuminence());
    }

    @Test
    public void initHSLbyRGBWhiteTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(255, 255, 255);
        assertEquals(0, color.getHue());
        assertEquals(0, color.getSaturation());
        assertEquals(255, color.getLuminence());
    }
    
    @Test
    public void initHSLbyRGBRedTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(255, 0, 0);
        assertEquals(0, color.getHue());
        assertEquals(255, color.getSaturation());
        assertEquals(128, color.getLuminence());
    }

    @Test
    public void initRGBbyHSLLowSaturationTest() {
        HSLColor color = new HSLColor();
        color.initRGBbyHSL(0, 0, 128);
        assertEquals(128, color.getRed());
        assertEquals(128, color.getGreen());
        assertEquals(128, color.getBlue());
    }

    @Test
    public void initRGBbyHSLGreenTest() {
        HSLColor color = new HSLColor();
        color.initRGBbyHSL(85, 255, 128);
        assertEquals(0, color.getRed());
        assertEquals(255, color.getGreen());
        assertEquals(0, color.getBlue());
    }

    @Test
    public void hueToRGBRangeTest() {
        HSLColor color = new HSLColor();
        int result = color.initRGBbyHSL(0, 128, 128);
        assertEquals(result, color);
    }

    @Test
    public void setHueNegativeValueTest() {
        HSLColor color = new HSLColor();
        color.setHue(-10);
        assertEquals(245, color.getHue());
    }

    @Test
    public void setHueAboveMaxTest() {
        HSLColor color = new HSLColor();
        color.setHue(265);
        assertEquals(10, color.getHue());
    }

    @Test
    public void setSaturationNegativeValueTest() {
        HSLColor color = new HSLColor();
        color.setSaturation(-10);
        assertEquals(0, color.getSaturation());
    }

    @Test
    public void setSaturationAboveMaxTest() {
        HSLColor color = new HSLColor();
        color.setSaturation(300);
        assertEquals(255, color.getSaturation());
    }
    
    @Test
    public void setLuminenceNegativeValueTest() {
        HSLColor color = new HSLColor();
        color.setLuminence(-10);
        assertEquals(0, color.getLuminence());
    }

    @Test
    public void setLuminenceAboveMaxTest() {
        HSLColor color = new HSLColor();
        color.setLuminence(300);
        assertEquals(255, color.getLuminence());
    }

    @Test
    public void reverseColorTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(255, 0, 0);
        color.reverseColor();
        assertEquals(128, color.getHue());
    }

    @Test
    public void brightenTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(0, 0, 0);
        color.brighten(2.0f);
        assertEquals(0, color.getLuminence());
    }

    @Test
    public void blendTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(255, 0, 0);
        color.blend(0, 255, 0, 0.5f);
        assertEquals(128, color.getRed());
        assertEquals(128, color.getGreen());
        assertEquals(0, color.getBlue());
    }
}
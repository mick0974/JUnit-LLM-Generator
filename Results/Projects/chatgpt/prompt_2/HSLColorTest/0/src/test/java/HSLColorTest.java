import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HSLColorTest {
    

    
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
    public void brightenTest() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(0, 0, 0);
        color.brighten(2.0f);
        assertEquals(0, color.getLuminence());
    }

}
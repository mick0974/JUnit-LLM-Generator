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
    public void getHue_SetHueTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.setHue(120);
        assertEquals(120, hsl.getHue());
    }

    @Test
    public void getSaturation_SetSaturationTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.setSaturation(50);
        assertEquals(50, hsl.getSaturation());
    }

    @Test
    public void getLuminence_SetLuminenceTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.setLuminence(75);
        assertEquals(75, hsl.getLuminence());
    }

    @Test
    public void getRed_SetRedTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.setRed(128);
        assertEquals(128, hsl.getRed());
    }

    @Test
    public void getGreen_SetGreenTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.setGreen(128);
        assertEquals(128, hsl.getGreen());
    }

    @Test
    public void getBlue_SetBlueTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.setBlue(128);
        assertEquals(128, hsl.getBlue());
    }

    @Test
    public void reverseColorTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.reverseColor();
        assertEquals(127, hsl.getHue());
    }

    @Test
    public void reverseLightTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.reverseLight();
        assertEquals(127, hsl.getLuminence());
    }

    @Test
    public void brightenTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.brighten(0.5f);
        assertEquals(25, hsl.getLuminence());
    }

    @Test
    public void blendTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.blend(0, 255, 0, 0.5f);
        assertEquals(128, hsl.getRed());
        assertEquals(128, hsl.getGreen());
        assertEquals(0, hsl.getBlue());
    }
}
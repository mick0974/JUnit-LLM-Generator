import org.junit.Test;
import static org.junit.Assert.*;

public class HSLColorTest {





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
    public void reverseColorTest() {
        HSLColor hsl = new HSLColor();
        hsl.initHSLbyRGB(255, 0, 0);
        hsl.reverseColor();
        assertEquals(127, hsl.getHue());
    }



}
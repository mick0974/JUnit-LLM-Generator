import org.junit.Test;
import static org.junit.Assert.*;

public class HSLColorTest {





    @Test
    public void setHue_NegativeValueTest() {
        HSLColor hsl = new HSLColor();
        hsl.setHue(-10);
        assertEquals(245, hsl.getHue());
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
    public void brighten_ZeroPercentTest() {
        HSLColor hsl = new HSLColor();
        hsl.initRGBbyHSL(0, 100, 50);
        hsl.brighten(0);
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

}
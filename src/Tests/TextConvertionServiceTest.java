package Tests;

import Services.TextConvertionService;
import org.junit.Test;
import static org.junit.Assert.*;

public class TextConvertionServiceTest {
    TextConvertionService textConvertor = new TextConvertionService();

    @Test
    public void testEmptyString() {
        assertNull(textConvertor.convert("", 100, 79));
        assertNull(textConvertor.convert("            ", 100, 79));
    }

    @Test
    public void testMusicStringWithoutNotes() {
        assertNull(textConvertor.convert("la, oo. \n? ieeei", 40, 113));
    }

    @Test
    public void testValidMusicString() {
        assertEquals("T220 I6 I6 R R I19 :CON(7,50) R I6 :CON(7,100) R I6 I6 :CON(7,50) R :CON(7,100) 21 R R R I6 R R R R :CON(7,50) I14 R R R I6 R :CON(7,100) R R R :CON(7,50) R R I6 R I6 R R :CON(7,100) 16 I6 :CON(7,50) R R :CON(7,100) R R R R I6 :CON(7,50) I15 :CON(7,100) R R I6 R I6 R :CON(7,50) I6 R R R I6 ", textConvertor.convert("Ola, eu sou a Ametista. \nVamos ser amigos? Eu já tenho 9 amigos ieeei", 220, 6));
    }
}

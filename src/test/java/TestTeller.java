import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTeller {

    TellerWaermer tellerWaermer;

    @BeforeEach
    void initTellerWaermer(){
        tellerWaermer = new TellerWaermer(TellerWaermerTyp.SMALL);
    }

    /**
     * Testtitel: Gerät einschalten wenn es ausgeschalten ist
     * Ausgangszustand: Gerät ausgeschalten --> Es können keine Teller im Gerät sein
     * Erwartet: Gerät lässt sich Einschalten
     */
    @Test
    void testEinschalten_istAus() {
        TellerWaermerResponseCodes response = tellerWaermer.turnOn();
        assertTrue(tellerWaermer.isEingeschalten());
        assertEquals(response, TellerWaermerResponseCodes.TURNON_SUCCESS);
    }

    @Test
    void testEinschalten_istAn() {
        System.out.println("stup");
    }

    @Test
    void testPeek_Leer() {
        System.out.println("stup");
    }

    @Test
    void testPeek_Voll() {
        System.out.println("stup");
    }

    @Test
    void testPeek_gefuellt() {
        System.out.println("stup");
    }

    @Test
    void testAusschalten_istAnUndLeer() {
        System.out.println("stup");
    }

    @Test
    void testAusschalten_istAnUndGefuellt() {
        System.out.println("stup");
    }

    @Test
    void testAusschalten_istAnUndVoll() {
        System.out.println("stup");
    }

    @Test
    void testAusschalten_istAus() {
        System.out.println("stup");
    }

    @Test
    void testTellerNehmen_hatTeller() {
        System.out.println("stup");
    }

    @Test
    void testTellerNehmen_istLeer() {
        System.out.println("stup");
    }

    @Test
    void testTellerPlatzieren_istNichtVoll() {
        System.out.println("stup");
    }

    @Test
    void testTellerPlatzieren_istVoll() {
        System.out.println("stup");
    }



}

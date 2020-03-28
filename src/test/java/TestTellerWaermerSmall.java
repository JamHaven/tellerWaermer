import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTellerWaermerSmall {

    TellerWaermer tellerWaermer;

    /**
     * TellerWaermer wird bei jedem Test neu angelegt - Small
     */
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
        TellerWaermerResponseCode response = tellerWaermer.turnOn();
        assertTrue(tellerWaermer.isTurnedOn());
        assertEquals(TellerWaermerResponseCode.TURNON_SUCCESS, response);
    }

    /**
     * Testtitel: Gerät einschalten wenn es eingeschalten ist
     * Ausgangszustand: Gerät eingeschaltet
     * Erwartet: Fehler, dass das Gerät nicht eingeschalten werden kann
     */
    @Test
    void testEinschalten_istAn() {
        tellerWaermer.turnOn();
        TellerWaermerResponseCode response = tellerWaermer.turnOn();
        assertTrue(tellerWaermer.isTurnedOn());
        assertEquals(TellerWaermerResponseCode.TURNON_FAILURE, response);
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

    /**
     * Testtitel: Teller platzieren auf einem nicht leeren tellerwärmer
     * Ausgangszustand: Gerät eingeschaltet
     * Erwartet: Erfolgsmeldung und Telleranzahl wurde um 1 erhöht
     */
    @Test
    void testTellerPlatzieren_istNichtVoll() {
        int countBeforePush = tellerWaermer.getPlatzierteTellerAnzahl();
        int pushCount = 3;
        tellerWaermer.turnOn();
        TellerWaermerResponseCode response = tellerWaermer.pushTeller(pushCount);
        assertEquals(TellerWaermerResponseCode.PUSH_SUCCESS, response);
        assertTrue(tellerWaermer.isPartial());
        assertEquals(countBeforePush+pushCount,tellerWaermer.getPlatzierteTellerAnzahl());
    }

    /**
     * Testtitel: Teller platzieren auf einem nicht eingeschaltenen tellerwärmer
     * Ausgangszustand: Gerät ist ausgeschalten
     * Erwartet: Fehlermeldung, dass das Gerät nicht eingeschalten ist und Anzahl ist 0
     */
    @Test
    void testTellerPlatzieren_istNichtEingeschalten() {
        int pushCount = 3;
        TellerWaermerResponseCode response = tellerWaermer.pushTeller(pushCount);
        assertEquals(TellerWaermerResponseCode.PUSH_FAILURE_TURNEDOFF, response);
        assertFalse(tellerWaermer.isPartial());
        assertTrue(tellerWaermer.isTurnedOff());
        assertEquals(0, tellerWaermer.getPlatzierteTellerAnzahl());
    }

    /**
     * Testtitel: Teller platzieren auf einem vollen tellerwärmer
     * Ausgangszustand: Gerät ist im Zustand FULL
     * Erwartet: Fehlermeldung, dass das Gerät voll ist
     */
    @Test
    void testTellerPlatzieren_istVoll() {
        int pushCount = 15;
        tellerWaermer.turnOn();
        tellerWaermer.pushTeller(pushCount);
        assertEquals(pushCount, tellerWaermer.getPlatzierteTellerAnzahl());
        assertTrue(tellerWaermer.isFull());
        assertEquals(pushCount, tellerWaermer.getPlatzierteTellerAnzahl());
        TellerWaermerResponseCode response = tellerWaermer.pushTeller(1);
        assertEquals(TellerWaermerResponseCode.PUSH_FAILURE, response);
        assertTrue(tellerWaermer.isFull());
        assertEquals(pushCount, tellerWaermer.getPlatzierteTellerAnzahl());
    }



}

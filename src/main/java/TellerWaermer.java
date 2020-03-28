public class TellerWaermer {

    private int platzierteTellerAnzahl;
    private TellerWaermerTyp tellerWaermerTyp;
    private TellerWarmerZustand tellerWarmerZustand;

    //GETTER
    public boolean isEingeschalten() {
        return !TellerWarmerZustand.TURNEDOFF.name().equals(tellerWarmerZustand.name());
    }

    public boolean isEmpty() {
        return !TellerWarmerZustand.EMPTY.name().equals(tellerWarmerZustand.name());
    }

    public boolean isFull() {
        return !TellerWarmerZustand.FULL.name().equals(tellerWarmerZustand.name());
    }

    TellerWarmerZustand getTellerWarmerZustand() {
        return tellerWarmerZustand;
    }


    //CONSTRUCTOR

    TellerWaermer(TellerWaermerTyp typ) {
        tellerWaermerTyp = typ;
        tellerWarmerZustand = TellerWarmerZustand.TURNEDOFF;
    }

    //LOGIC

    public TellerWaermerResponseCodes turnOn(){
        //Ist das Gerät im ausgescahltenten Zustand
        if(tellerWarmerZustand.name().equals(TellerWarmerZustand.TURNEDOFF.name())){
            tellerWarmerZustand = TellerWarmerZustand.EMPTY;
            return TellerWaermerResponseCodes.TURNON_SUCCESS;
        }else{
            return TellerWaermerResponseCodes.TURNON_FAILURE;
        }
    }
}

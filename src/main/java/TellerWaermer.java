public class TellerWaermer {

    private int platzierteTellerAnzahl;
    private TellerWaermerTyp tellerWaermerTyp;
    private TellerWarmerZustand tellerWarmerZustand;

    //CONSTRUCTOR

    /**
     * Jeder Tellerwärmer braucht einen Typ und beginnt nicht eingeschalten und ohne Teller
     * Ausgangszustand: TURNEDOFF
     * @param typ Um welchen Typ von Tellerwärmer handelt es sich (bestimmt Kapazität)
     */
    TellerWaermer(TellerWaermerTyp typ) {
        platzierteTellerAnzahl = 0;
        tellerWaermerTyp = typ;
        tellerWarmerZustand = TellerWarmerZustand.TURNEDOFF;
    }

    //GETTER
    public boolean isTurnedOn() {
        return !TellerWarmerZustand.TURNEDOFF.name().equals(tellerWarmerZustand.name());
    }

    public boolean isTurnedOff() {
        return TellerWarmerZustand.TURNEDOFF.name().equals(tellerWarmerZustand.name());
    }

    public boolean isEmpty() {
        return TellerWarmerZustand.EMPTY.name().equals(tellerWarmerZustand.name());
    }

    public boolean isFull() {
        return TellerWarmerZustand.FULL.name().equals(tellerWarmerZustand.name());
    }

    public boolean isPartial() {
        return TellerWarmerZustand.PARTIAL.name().equals(tellerWarmerZustand.name());
    }

    public int getPlatzierteTellerAnzahl() {
        return platzierteTellerAnzahl;
    }


    //Zustand Setzen

    /**
     * Setzt den Tellerwärmer auf einen beliebigen Zustand. Hiererfolgen minimale, zusätzliche Überprüfungen ob
     * sich der Tellerwärmer im richtigen Zustand befindet.
     * @param state Zustand indem der tellerwärmer wechseln soll
     */
    private TellerWaermerResponseCode setState(TellerWarmerZustand state) {
        switch(state){
            case TURNEDOFF:
                if(!this.isEmpty()){ //Maschine muss EMPTY sein um ausgeschalten werden zu können
                    return TellerWaermerResponseCode.STATESWITCH_FAILURE;
                }
                break;
            case EMPTY:
                if(!this.isPartial() && !this.isTurnedOff()){ //Maschine muss PARTIAL oder TURNEDOFF sein
                    return TellerWaermerResponseCode.STATESWITCH_FAILURE;
                }
                break;
            case PARTIAL:
                if(!this.isEmpty() && !this.isFull()){ //Maschine muss EMPTY oder FULL sein
                    return TellerWaermerResponseCode.STATESWITCH_FAILURE;
                }
                break;
            case FULL:
                if(!this.isPartial()){//Maschine muss PARTIAL sein
                    return TellerWaermerResponseCode.STATESWITCH_FAILURE;
                }
                break;
            default:
                return TellerWaermerResponseCode.STATESWITCH_FAILURE;
        }
        this.tellerWarmerZustand = state;
        return TellerWaermerResponseCode.STATESWITCH_SUCCESS;
    }



    //LOGIC

    /**
     * Schaltet Gerät ein
     * Erforderlicher Zustand: Ausgeschalten
     * Neuer Zustand: Eingeschalten
     * @return Erfolgs-, oder Fehlermeldung
     */
    public TellerWaermerResponseCode turnOn(){
        //Ist das Gerät im ausgeschaltenten Zustand
        if(isTurnedOff()){
            this.setState(TellerWarmerZustand.EMPTY);
            return TellerWaermerResponseCode.TURNON_SUCCESS;
        }else{
            return TellerWaermerResponseCode.TURNON_FAILURE;
        }
    }

    /**
     * Legt mehrere neuen Teller in den Tellerwärmer
     * Erforderlicher Zustand: Wird in pushSingleTeller() entschieden
     * Neuer Zustand: Wird in pushSingleTeller() entschieden
     * @param pushCount Wieviele Teller am Stapel gelegt werden sollten
     * @return Erfolgs-, oder Fehlermeldung
     */
    public TellerWaermerResponseCode pushTeller(int pushCount) {
        for (int tellerLeger = 1; tellerLeger <= pushCount; tellerLeger++) {
            //Falls PUSH nicht erfolgreich war --> Fehler
            TellerWaermerResponseCode response = pushSingleTeller();
            if(!response.equals(TellerWaermerResponseCode.PUSH_SUCCESS)){
                return response;
            }
        }
        return TellerWaermerResponseCode.PUSH_SUCCESS;
    }

    /**
     * Legt einen neuen Teller in den Tellerwärmer
     * Erforderlicher Zustand: Eingeschalten UND Tellerwärmer ist nicht voll
     * Neuer Zustand: PARTIAL --> FULL, EMPTY --> PARTIAL, PARTIAL --> PARTIAL
     * @return Erfolgs-, oder Fehlermeldung
     */
    private TellerWaermerResponseCode pushSingleTeller(){
        if(!this.isFull() && this.isTurnedOn()){
            this.platzierteTellerAnzahl = this.platzierteTellerAnzahl +1; //Teller einlegen
            //Überprüfungen auf Zustandswechsel
            if(this.platzierteTellerAnzahl == tellerWaermerTyp.getCapacity()){
                this.setState(TellerWarmerZustand.FULL);
            }else if(isEmpty()){ //Wenn nach dem erhöhen der Zustand empty ist --> Zustandswechsel
                this.setState(TellerWarmerZustand.PARTIAL);
            }
            return TellerWaermerResponseCode.PUSH_SUCCESS;
        }else if(!this.isTurnedOn()){
            return TellerWaermerResponseCode.PUSH_FAILURE_TURNEDOFF;
        }
        else{
            return TellerWaermerResponseCode.PUSH_FAILURE;
        }
    }

    public TellerWaermerResponseCode pullTeller(int pullCount) {
        for (int tellerLeger = 1; tellerLeger <= pullCount; tellerLeger++) {
            //Falls PULL nicht erfolgreich war --> Fehler
            TellerWaermerResponseCode response = pullSingleTeller();
            if(!response.equals(TellerWaermerResponseCode.PULL_SUCCESS)){
                return response;
            }
        }
        return TellerWaermerResponseCode.PULL_SUCCESS;
    }


    /**
     * Nimmt einen Teller vom Tellerwärmer
     * Erforderlicher Zustand: Eingeschalten UND Tellerwärmer ist nicht leer
     * Neuer Zustand: PARTIAL --> EMPTY, FULL --> PARTIAL, PARTIAL --> PARTIAL
     * @return Erfolgs-, oder Fehlermeldung
     */
    private TellerWaermerResponseCode pullSingleTeller() {
        if(!this.isEmpty() && this.isTurnedOn()){
            this.platzierteTellerAnzahl = this.platzierteTellerAnzahl -1; //Teller entnehmen
            //Überprüfungen auf Zustandswechsel
            if(this.platzierteTellerAnzahl == 0){
                this.setState(TellerWarmerZustand.EMPTY);
            }else if(this.isFull()){ //Wenn nach dem erhöhen der Zustand empty ist --> Zustandswechsel
                this.setState(TellerWarmerZustand.PARTIAL);
            }
            return TellerWaermerResponseCode.PULL_SUCCESS;
        }
        else if(!this.isTurnedOn()){
            return TellerWaermerResponseCode.PULL_FAILURE_TURNEDOFF;
        }
        else{
            return TellerWaermerResponseCode.PULL_FAILURE;
        }
    }


    /**
     * Aschalten des Tellerwärmers
     * Erforderlicher Zustand: Eingeschalten UND Tellerwärmer ist leer
     * Neuer Zustand: EMPTY --> TURENDOFF
     * @return Erfolgs-, oder Fehlermeldung
     */
    public TellerWaermerResponseCode turnOff() {
        if(isTurnedOn() && isEmpty()){
            setState(TellerWarmerZustand.TURNEDOFF);
            return TellerWaermerResponseCode.TURNOFF_SUCCESS;
        }else{
            return TellerWaermerResponseCode.TURNOFF_FAILURE;
        }
    }

    /**
     * Nachschauen ob ein Teller im tellerwärmer liegt
     * Erforderlicher Zustand: Nicht Leer und eingeschalten
     * Neuer Zustand: Keine Zustandsänderung
     * @return Erfolgs-, oder Fehlermeldung
     */
    public TellerWaermerResponseCode peek() {
        if(!isEmpty() && isTurnedOn()){
            return TellerWaermerResponseCode.PEEK_SUCCESS;
        }else{
            return TellerWaermerResponseCode.PEEK_FAILURE;
        }
    }
}

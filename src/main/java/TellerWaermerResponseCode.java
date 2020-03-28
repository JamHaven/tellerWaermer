enum TellerWaermerResponseCode {

    TURNON_FAILURE(500, "Tellerwaermer konnte nicht eingeschalten werden."),
    TURNOFF_FAILURE(501, "Tellerwaermer konnte nicht ausgeschalten werden. Es befinden sich noch Teller im Gerät."),
    PUSH_FAILURE(502, "Teller konnte nicht eingelegt werden. Der Tellerwaermer ist voll."),
    PULL_FAILURE(503, "Es konnte kein Teller entommen werden! Der Tellerwaermer ist leer."),
    PEEK_FAILURE(504, "'Du starrst verzweifelt in den leeren Tellerwärmer. Du fühlst nichts.' Keine Teller zum anschauen vorhanden."),
    STATESWITCH_SUCCESS(505, "Der Zustandswechsel ist fehlgeschlagen. Befindet sich der Tellerwärmer im richtigen Zustand?"),
    PUSH_FAILURE_TURNEDOFF(506, "Teller konnte nicht eingelegt werden. Der Tellerwaermer ist nicht eingeschalten."),
    PULL_FAILURE_TURNEDOFF(507, "Teller konnte nicht entnommen werden. Der Tellerwaermer ist nicht eingeschalten."),
    TURNON_SUCCESS(200, "Das Geraet wurde erfolgreich eingeschalten."),
    TURNOFF_SUCCESS(201, "Das Geraet wurde erfolgreich ausgeschalten."),
    PUSH_SUCCESS(202,"Es wurde erfolgreich ein Teller zum Stapel hinzugefuegt."),
    PULL_SUCCESS(203,"Es wurde erfolgreich ein Teller aus dem Stapel entfernt."),
    PEEK_SUCCESS(204,"Es wurden erfolgreich die Teller inspiziert."),
    STATESWITCH_FAILURE(205, "Der Zustandswechsel war erfolgreich");



    private final int code;
    private final String description;

    TellerWaermerResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}

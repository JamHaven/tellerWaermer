public enum TellerWaermerTyp {

    SMALL(15), MEDIUM(30), LARGE(50);

    int capacity;

    TellerWaermerTyp(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

}

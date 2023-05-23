class Suit extends Rom {
    Suit(String romnummer, int kvadratmeter, int antSengeplass, int etasje) {
        super(romnummer, kvadratmeter, antSengeplass, etasje);
    }

    @Override
    public String toString() {
        return String.format("Suit:\n  romnummer: %s\n  kvadratmeter: %s\n  antall sengeplasser: %s\n  etasje: %s", romnummer, kvadratmeter, antSengeplass, etasje);
    }
}
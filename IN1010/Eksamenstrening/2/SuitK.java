class SuitK extends Rom implements Kjokken {
    final int kjokkenkvadrat;

    SuitK(String romnummer, int kvadratmeter, int antSengeplass, int etasje, int kjokkenkvadrat) {
        super(romnummer, kvadratmeter, antSengeplass, etasje);
        this.kjokkenkvadrat = kjokkenkvadrat;
    }

    @Override
    public String toString() {
        return String.format("Suit med kjokken:\n  romnummer: %s\n  kvadratmeter: %s\n  antall sengeplasser: %s\n  etasje: %s", romnummer, kvadratmeter + kjokkenkvadrat, antSengeplass, etasje);
    }
}
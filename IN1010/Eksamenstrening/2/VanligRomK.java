class VanligRomK extends Rom implements Kjokken {
    final int kjokkenkvadrat;

    VanligRomK(String romnummer, int kvadratmeter, int antSengeplass, int etasje, int kjokkenkvadrat) {
        super(romnummer, kvadratmeter, antSengeplass, etasje);
        this.kjokkenkvadrat = kjokkenkvadrat;
    }

    @Override
    public String toString() {
        return String.format("Vanlig rom med kjokken:\n  romnummer: %s\n  kvadratmeter: %s\n  antall sengeplasser: %s\n  etasje: %s", romnummer, kvadratmeter + kjokkenkvadrat, antSengeplass, etasje);
    }
}
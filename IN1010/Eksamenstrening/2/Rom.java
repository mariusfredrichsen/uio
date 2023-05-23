abstract class Rom {
    final String romnummer;
    final int kvadratmeter;
    final int antSengeplass;
    final int etasje;
    boolean ledig = true;

    Rom neste = null;

    Rom(String romnummer, int kvadratmeter, int antSengeplass, int etasje) {
        this.romnummer = romnummer;
        this.kvadratmeter = kvadratmeter;
        this.antSengeplass = antSengeplass;
        this.etasje = etasje;
    }

    public abstract String toString();
}
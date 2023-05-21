public class Personbil extends Bil {
    int antPas;
    String bilnummer;
    int pris;

    public Personbil(String bilnummer, int pris, int antPas) {
        super(bilnummer, pris);
        this.bilnummer = bilnummer;
        this.pris = pris;
        this.antPas = antPas;
    }



    @Override
    public String toString() {
        return String.format("Personbil: \n bilnummer: %s \n pris: %s \n antall plasser: %s", bilnummer, pris, antPas);
    }
}
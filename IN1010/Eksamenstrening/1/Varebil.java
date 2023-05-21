public class Varebil extends Bil {
    int lasVol;
    String bilnummer;
    int pris;

    public Varebil(String bilnummer, int pris, int lasVol) {
        super(bilnummer,pris);
        this.bilnummer = bilnummer;
        this.pris = pris;
        this.lasVol = lasVol;
    }


    public String toString() {
        return String.format("Varebil: \n bilnummer: %s \n pris: %s \n laste volum: %s", bilnummer, pris, lasVol);
    }
}



public class Bil2 {
    String bilnummer;

    public Bil2(String bilnummer) {
        this.bilnummer = bilnummer;
    }

    public void skrivUt() {
        System.out.println(String.format("Jeg er en bil og har bilnummer: %s", bilnummer));
    }
}

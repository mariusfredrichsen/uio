

public class Bil3 {
    String bilnummer;
    
    public Bil3(String bilnummer) {
        this.bilnummer = bilnummer;
    }

    public void skrivUt() {
        System.out.println(String.format("Jeg er en bil og har bilnummer: %s", bilnummer));
    }

    public String hentNummer() {
        return bilnummer;
    }
}

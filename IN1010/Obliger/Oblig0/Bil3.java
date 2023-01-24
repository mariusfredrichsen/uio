public class Bil3 {
    String bilnummer;

    public Bil3(String bilnum) {
        bilnummer = bilnum;
    }

    public void skriv_ut() {
        System.out.println("Jeg er en bil");
        System.out.println("Bilnummeret mitt er: " + bilnummer);
    }    

    public String hent_bilnummer() {
        return bilnummer;
    }
}
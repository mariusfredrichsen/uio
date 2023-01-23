public class Bil2 {
    String Bilnummer;

    public Bil2(String BilNum) {
        Bilnummer = BilNum;
    }

    public void skriv_ut() {
        System.out.println("Jeg er en bil");
        System.out.println("Skiltnummeret mitt er: " + Bilnummer);
    }    
}
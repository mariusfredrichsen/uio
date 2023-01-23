public class Bil3 {
    static String Bilnummer;

    public Bil3(String BilNum) {
        Bilnummer = BilNum;
    }

    public void skriv_ut() {
        System.out.println("Jeg er en bil");
        System.out.println("Bilnummeret mitt er: " + Bilnummer);
    }    

    public static String hent_bilnummer() {
        return Bilnummer;
    }
}
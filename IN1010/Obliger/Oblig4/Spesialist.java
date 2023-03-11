public class Spesialist extends Lege implements Godkjenningsfritak{
    String kontrollKode;

    public Spesialist(String navn, String kontrollKode) {
        super(navn);
        this.kontrollKode = kontrollKode;
    }

    public String hentKontrollKode() {
        return kontrollKode;
    }

    public String toString() {
        return "Navn paa spesialist: " + navn + "\nKontrollkode: " + kontrollKode;
    }
    
}

package IN1010.Obliger.Oblig2;

public class Spesialist extends Lege implements Godkjenningsfritak{
    String kontrollKode;
    String navn;

    public Spesialist(String navn, String kontrollKode) {
        super(navn);
        this.navn = navn;
        this.kontrollKode = kontrollKode;
    }

    public String hentKontrollKode() {
        return kontrollKode;
    }

    public String toString() {
        return "Navn paa spesialist: " + navn + "\nKontrollkode: " + kontrollKode;
    }
    
}

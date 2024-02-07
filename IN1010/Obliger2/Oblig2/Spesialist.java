public class Spesialist extends Lege implements Godkjenningsfritak {
    String kontrollKode;

    public Spesialist(String navn, String kontrollKode) {
        super(navn);
        this.kontrollKode = kontrollKode;
    }


    @Override
    public String hentKontrollkode() {
        return kontrollKode;
    }

    @Override
    public String toString() {
        return String.format("Legenavn: %s", navn);
    }
    


}

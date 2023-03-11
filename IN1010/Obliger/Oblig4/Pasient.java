public class Pasient {
    String navn;
    String fodselsnummer;
    public final int id;
    static int teller;

    Koe<Resept> resepter = new Koe<>();

    public Pasient(String navn, String fodselsnummer) {
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        id = teller++;
    }

    public void leggTilResept(Resept r) {
        resepter.leggTil(r);
    }
}

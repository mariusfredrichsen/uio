


public abstract class Legemiddel {
    public final String navn;
    public int pris;
    public final double virkestoff;
    static int idTeller = 0;
    public final int id;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = idTeller++;
    }

    public int hentPris() {
        return this.pris;
    }

    public void settNyPris(int nyPris) {
        this.pris = nyPris;
    }

    @Override
    public String toString() {
        return String.format("ID: %s\nNavn: %s\nPris: %s\nVirkestoff: %s", this.id, this.navn, this.pris, this.virkestoff);
    }
}

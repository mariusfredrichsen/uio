package IN1010.Obliger.Oblig2;

abstract public class Legemiddel {
    public final String navn;
    private int pris;
    public final double virkestoff;
    public final int id;

    public Legemiddel(String navn, int pris, double virkestoff, int id) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = id;
    }

    public int hentPris() {
        return pris;
    }

    public void settNyPris(int nyPris) {
        pris = nyPris;
    }

    public String toString() {
        return "Navn: " + navn + "\nPris: " + pris + "\nVirkestoff: " + virkestoff + "\nId: " + id;
    }
}

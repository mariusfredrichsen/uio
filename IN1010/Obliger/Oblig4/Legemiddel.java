abstract public class Legemiddel {
    public final String navn;
    public int pris;
    public final double virkestoff;
    public final int id;
    public static int teller = 1;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        id = teller;
        teller++;
    }

    public int hentPris() {
        return pris;
    }

    public String hentNavn() {
        return navn;
    }

    public void settNyPris(int nyPris) {
        pris = nyPris;
    }

    public String toString() {
        return "Navn: " + navn + "\nPris: " + pris + "\nVirkestoff: " + virkestoff + "\nId: " + id;
    }
}

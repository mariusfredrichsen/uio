public class BlaaResept extends Resept {
    Legemiddel legemiddel;
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel,utskrivendeLege,pasient,reit);
        this.legemiddel = legemiddel;
    }

    public String farge() {
        return "Blaa";
    }

    public int prisAaBetale() {
        return (int)Math.round(legemiddel.hentPris() / 4);
    }
}

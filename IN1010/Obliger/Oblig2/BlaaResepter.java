public class BlaaResepter extends Resept {
    Legemiddel legemiddel;
    public BlaaResepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel,utskrivendeLege,pasientId,reit);
        this.legemiddel = legemiddel;
    }

    public String farge() {
        return "Blaa";
    }

    public int prisAaBetale() {
        return (int)Math.round(legemiddel.hentPris() / 4);
    }
}

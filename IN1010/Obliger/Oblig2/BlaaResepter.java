package IN1010.Obliger.Oblig2;

public class BlaaResepter extends Resept {
    Legemiddel legemiddel;
    public BlaaResepter(int id, Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(id,legemiddel,utskrivendeLege,pasientId,reit);
        this.legemiddel = legemiddel;
    }

    public String farge() {
        return "Blaa";
    }

    public int prisAaBetale() {
        legemiddel.settNyPris((int)Math.round(legemiddel.hentPris() / 4));
        return legemiddel.hentPris();
    }
}

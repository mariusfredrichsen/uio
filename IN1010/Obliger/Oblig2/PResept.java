public class PResept extends HviteResepter {
    Legemiddel legemiddel;
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel,utskrivendeLege,pasientId,reit);
        this.legemiddel = legemiddel;
    }

    public int prisAaBetale() {
        if (legemiddel.hentPris() > 108) {
            legemiddel.settNyPris(legemiddel.hentPris() - 108);
        } else {
            legemiddel.settNyPris(0);
        }
        return legemiddel.hentPris();
    }
}

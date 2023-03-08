public class PResept extends HviteResepter {
    Legemiddel legemiddel;
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel,utskrivendeLege,pasientId,reit);
        this.legemiddel = legemiddel;
    }

    public int prisAaBetale() {
        if (legemiddel.hentPris() > 108) {
            return legemiddel.hentPris() - 108;
        } else {
            return 0;
        }
    }
}

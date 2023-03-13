public class PResept extends HvitResept {
    Legemiddel legemiddel;
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel,utskrivendeLege,pasient,reit);
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

public class MilResept extends HvitResept {
    Legemiddel legemiddel;
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel,utskrivendeLege,pasient, 3);
        this.legemiddel = legemiddel;
    }

    public int prisAaBetale() {
        return 0;
    }
}

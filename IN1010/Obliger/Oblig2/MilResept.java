public class MilResept extends HviteResepter {
    Legemiddel legemiddel;
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId) {
        super(legemiddel,utskrivendeLege,pasientId, 3);
        this.legemiddel = legemiddel;
    }

    public int prisAaBetale() {
        return 0;
    }
}

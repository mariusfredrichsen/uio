package IN1010.Obliger.Oblig2;

public class MilResept extends HviteResepter {
    Legemiddel legemiddel;
    public MilResept(int id, Legemiddel legemiddel, Lege utskrivendeLege, int pasientId) {
        super(id,legemiddel,utskrivendeLege,pasientId, 3);
        this.legemiddel = legemiddel;
    }

    public int prisAaBetale() {
        legemiddel.settNyPris(0);
        return legemiddel.hentPris();
    }
}

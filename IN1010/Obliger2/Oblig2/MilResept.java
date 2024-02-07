


public class MilResept extends HvitResept {

    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId) {
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }

    @Override
    public String farge() {
        return "Hvit";
    }

    @Override
    public int prisAaBetale() {
        return 0;
    }


}

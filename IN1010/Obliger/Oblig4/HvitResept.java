public class HvitResept extends Resept {
    
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel,utskrivendeLege,pasient,reit);
    }

    public String farge() {
        return "Hvit";
    }

    public int prisAaBetale() {
        return 0; //subklassene har sin egen metoder for dette.
    }
}

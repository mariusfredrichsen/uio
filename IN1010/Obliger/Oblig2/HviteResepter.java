public class HviteResepter extends Resept {
    
    public HviteResepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel,utskrivendeLege,pasientId,reit);
    }

    public String farge() {
        return "Hvit";
    }

    public int prisAaBetale() {
        return 0; //subklassene har sin egen metoder for dette.
    }
}

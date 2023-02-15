package IN1010.Obliger.Oblig2;

public class HviteResepter extends Resept {
    
    public HviteResepter(int id, Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(id,legemiddel,utskrivendeLege,pasientId,reit);
    }

    public String farge() {
        return "Hvit";
    }

    public int prisAaBetale() {
        return 0; //subklassene har sin egen metoder for dette.
    }
}



public class BlåResept extends Resept {
    
    

    public BlåResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public String farge() {
        return "Blå";
    }

    @Override
    public int prisAaBetale() {
        return (int) Math.round(legemiddel.hentPris()*0.25);
    }
}

abstract public class Resept {
    int id;
    Legemiddel legemiddel;
    Lege utskrivendeLege;
    int pasientId;
    int reit;
    public static int teller;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        id = teller;
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
        teller++;
    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }
    

    public int hentPasientId() {
        return pasientId;
    }


    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit == 0) {
            return false;
        } else {
            reit--;
            return true;
        }
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    public String toString() {
        return "Id: " + id + "\nLegemiddel: " + legemiddel + "\nLege: " + utskrivendeLege + "\npasientId: " + pasientId + "\nReit: " + reit;
    }
}

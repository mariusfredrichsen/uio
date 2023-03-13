abstract public class Resept {
    public final int id;
    Legemiddel legemiddel;
    Lege utskrivendeLege;
    Pasient pasient;
    int reit;
    public static int teller = 1;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        id = teller;
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
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
    

    public Pasient hentPasientId() {
        return pasient;
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
        return "Id: " + id + "\nLegemiddel: " + legemiddel + "\nLege: " + utskrivendeLege + "\npasientId: " + pasient + "\nReit: " + reit;
    }
}




public abstract class Resept {
    Legemiddel legemiddel;
    Lege utskrivendeLege;
    int pasientId;
    int reit;
    int id;
    static int idTeller = 0;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
        this.id = idTeller++;
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
        this.reit--;
        if (reit < 0) {
            this.reit++;
            return false;
        }
        return true;
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    public String toString() {
        return String.format("Id: %s\nLegemiddel: %s\nLege: %s\nPasientId: %s\nReit: %s\n", id, legemiddel.navn, utskrivendeLege, pasientId, reit);
    }
}

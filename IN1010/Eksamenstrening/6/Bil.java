public abstract class Bil {
    const int MAKSFART;
    Kolonne kolonne;

    Bil neste;
    Bil forrige;

    public Bil(int maksFart, Kolonne kolonne) {
        this.MAKSFART = maksFart;
        this.kolonne = kolonne;
        this.neste = null;
        this.forrige = null;
    }

    public int hentMaksFart() {
        return this.MAKSFART;
    }

    public int finnMaksFartR() {
        if (this.neste != null) return min(this.MAKSFART, this.neste.finnMaksFartR());
        return this.MAKSFART;
    }

}

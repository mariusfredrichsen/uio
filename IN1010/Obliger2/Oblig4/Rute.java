


public abstract class Rute  {
    int rad, kol;
    Labyrint labyrint;
    Rute[] naboer; // nord, syd, vest, oest
    Boolean besokt;

    public Rute(int rad, int kol, Labyrint labyrint) {
        this.rad = rad;
        this.kol = kol;
        this.labyrint = labyrint;
        this.naboer = new Rute[4];
        this.besokt = false;
    }

    public void finn(Rute fra) {
        this.besokt = true;
        for (Rute nabo : naboer) {
            if (nabo != fra && nabo != null && !nabo.besokt) nabo.finn(this);
        }
    }

    public void leggTilNabo(Rute nabo) {
        for (int i = 0; i < naboer.length; i++) {
            if (naboer[i] == null) {
                naboer[i] = nabo;
                break;
            }
        }
    }


}

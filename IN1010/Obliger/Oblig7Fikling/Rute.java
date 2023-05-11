public abstract class Rute {
    private int rad, kol;
    Labyrint lab;
    Rute nord, vest, syd, oest;

    Rute(int rad, int kol, Labyrint lab) {
        this.rad = rad;
        this.kol = kol;
        this.lab = lab;
    }

    public void settNord(Rute nord) {
        this.nord = nord;
    }

    public void settVest(Rute vest) {
        this.vest = vest;
    }

    public void settSyd(Rute syd) {
        this.syd = syd;
    }

    public void settOest(Rute oest) {
        this.oest = oest;
    }

    public abstract void finn(Rute fra);
}
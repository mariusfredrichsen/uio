public abstract class Rute {
    private int rad, kol;
    Labyrint lab;
    Rute nord;
    Rute syd;
    Rute vest;
    Rute oest;

    Rute(int rad, int kol, Labyrint lab, Rute nord, Rute syd, Rute vest, Rute oest) {
        this.rad = rad;
        this.kol = kol;
        this.lab = lab;
        this.nord = nord;
        this.syd = syd;
        this.vest = vest;
        this.oest = oest;
    }
}
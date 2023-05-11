public class SortRute extends Rute {
    Rute nord, vest, syd, oest;
    
    SortRute(int rad, int kol, Labyrint lab) {
        super(rad, kol, lab);
        this.nord = super.nord;
        this.vest = super.vest;
        this.syd = super.syd;
        this.oest = super.oest;
    }

    public void finn(Rute fra) {
        //gjoer ingenting
    }

    public String toString() {
        return "#";
    }
}

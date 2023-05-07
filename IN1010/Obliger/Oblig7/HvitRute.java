public class HvitRute extends Rute {
    int rad, kol;

    HvitRute(int rad, int kol, Labyrint lab) {
        super(rad, kol, lab);
        this.rad = rad;
        this.kol = kol;
    }

    @Override
    public void finn(Rute fra) {
        //spoer alle sammen rundt seg utenom "fra"
        super.besoekt();
        if (nord != fra && nord != null && !nord.besoekt) {
            nord.finn(this);
        }
        if (oest != fra && oest != null && !oest.besoekt) {
            oest.finn(this);
        }
        if (syd != fra && syd != null && !syd.besoekt) {
            syd.finn(this);
        }
        if (vest != fra && vest != null && !vest.besoekt) {
            vest.finn(this);
        }

    }

    public String toString() {
        return ". ";
    }
}

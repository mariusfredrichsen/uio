public class HvitRute extends Rute {

    HvitRute(int rad, int kol, Labyrint lab) {
        super(rad, kol, lab);
    }

    public void finn(Rute fra) {
        //spoer alle sammen rundt seg utenom "fra"
        if (nord != fra && nord != null) {
            nord.finn(this);
        }
        if (vest != fra && vest != null) {
            vest.finn(this);
        }
        if (syd != fra && syd != null) {
            syd.finn(this);
        }
        if (oest != fra && oest != null) {
            oest.finn(this);
        }
    }

    public String toString() {
        return ".";
    }
}

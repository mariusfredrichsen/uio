public class Aapning extends HvitRute {
    int rad, kol;
    Aapning(int rad, int kol, Labyrint lab) {
        super(rad, kol, lab);
        this.rad = rad;
        this.kol = kol;
    }

    @Override
    public void finn(Rute fra) {
        //ferdig, her er utveien
        System.out.println(String.format("(%s , %s)", rad, kol));
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
}




public class Aapning extends HvitRute {
    

    public Aapning(int rad, int kol, Labyrint labyrint) {
        super(rad, kol, labyrint);
    }

    @Override
    public void finn(Rute fra) {
        for (Rute nabo : naboer) {
            if (nabo != fra && nabo != null) nabo.finn(this);
        }
        System.out.println(String.format("Aapning paa: %s , %s", rad, kol));
    }

    public String toString() {
        return "0";
    }
}

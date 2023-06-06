public class Sted {
    String navn;

    MestStoyANT mestStoy;

    Maaling forste = null;
    Maaling siste = null;

    Sted(String navn, MestStoyANT mestStoy) {
        this.navn = navn;
        this.mestStoy = mestStoy;
    }

    void settInnMaaling(Maaling m) {
        Maaling peker = forste;
        if (peker == null) {
            forste = m;
            siste = m;
            return;
        }
        while (peker.neste != null) {
            peker = peker.neste;
        }
        if (peker.tidspunkt <= m.tidspunkt) {
            peker.neste = m;
            mestStoy.settInnANT(m);
        }
    }

    double hoyestStoy() {
        double hoyest = 0;
        for (Maaling m : mestStoy.maalinger) {
            if (m.stoyNivaa > hoyest) {
                hoyest = m.stoyNivaa;
            }
        }
        return hoyest;
    }

    void slaaSammen() {
        Maaling peker = forste;

        while (peker.neste != null) {
            if (peker.tidspunkt == peker.neste.tidspunkt) {
                peker.antall++;
                peker.neste = peker.neste.neste;
            } else {
                peker = peker.neste;
            }
        }
    }
}

public class Bilkollektiv {
    Bil[] alleBiler;
    final int AB;
    
    Bil hode = null, hale = null;


    public Bilkollektiv(int AB) {
        this.AB = AB;
        alleBiler = new Bil[AB];
    }

    void lagBilPris() {
        Bil peker = alleBiler[0];
        int forrigePris = 0;
        
        for (int i = 0; i < AB; i++) {
            int minstPris = Integer.MAX_VALUE;
            for (int j = 0; j < AB; j++) {
                if (alleBiler[j].pris < minstPris && alleBiler[j].pris > forrigePris) {
                    minstPris = alleBiler[j].pris;
                    peker = alleBiler[j];
                }
            }
            forrigePris = minstPris;

            if (hode == null) {
                hode = peker;
                hale = peker;
                continue;
            }
    
            peker.forrige = hale;
            hale.neste = peker;
            hale = peker;
        }
    }

    void taUtBil(Bil b) {
        Bil peker = hode;
        for (int i = 0; i < AB; i++) {
            if (peker == b) {
                if (peker == hode && peker ==  hale) {
                    hode = null;
                    hale = null;
                    return;
                }
                if (peker == hode) {
                    hode = peker.neste;
                    hode.forrige = null;
                    return;
                }
                if (peker == hale) {
                    peker.forrige = hale;
                    hale.neste = null;
                    return;
                }
                peker.forrige.neste = peker.neste;
                peker.neste.forrige = peker.forrige;
                return;
            }
            peker = peker.neste;
        }
    }

    Bil velgBil(Dialog d) {
        Bil peker = hode;
        if (d.svarJaEllerNei("Er du interessert i aa laane en elbil?")) {
            while (true) {
                if (peker instanceof Elektrisk) {
                    System.out.println(peker);
                    if (d.svarJaEllerNei("Er du interessert i aa laane denne bilen?")) break;
                }
                if (peker == hale) {
                    peker = peker.neste;
                    break;
                }
                if (peker.neste != null) peker = peker.neste;
            }
        } else {
            while (peker != null) {
                System.out.println(peker);
                if (d.svarJaEllerNei("Er du interessert i aa laane denne bilen?")) break;
                peker = peker.neste;
            }
        }
        if (peker == null) {
            System.out.println("Ingen bil ingen aarsak, ha en fin dag videre.");
            return null;
        } else {
            taUtBil(peker);
            return peker;
        }
    }

    Bil velgBilR(Dialog d) {
        return hode.finnBilR(d, d.svarJaEllerNei("Er du interessert i aa laane en elbil?"));
    }


}

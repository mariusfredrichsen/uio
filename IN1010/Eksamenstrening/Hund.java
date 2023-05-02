class Hund implements Comparable<Hund> {
    String navn;
    Kull mittKull;
    Tidspunkt minFodselstid;
    Hund neste = null;

    Hund(Kull k, String navn, Tidspunkt fodt) {
        this.navn = navn;
        mittKull = k;
        minFodselstid = fodt;
    }

    @Override
    public int compareTo(Hund h) {
        // Oppgave 2b
        return minFodselstid.compareTo(h.minFodselstid);
    }

    public Hund mor() {
        // Oppgave 2a
        return mittKull.mor;
    }

    public Hund far () {
        // Oppgave 2a
        return mittKull.far;
    }

    public boolean erHelsosken(Hund h) {
        // Oppgave 2c
        if (h.mor() == mor() && h.far() == far()) return true;
        return false;
    }

    public boolean erHalvsosken(Hund h) {
        // Oppgave 2c
        if (!erHelsosken(h) && (h.mor() == mor() || h.far() == far())) return true;
        return false;
    }

    public Hund finnEldsteKjenteOpphav() {
        // Oppgave 2d
        if (mittKull.mor == null) {
            if (mittKull.far == null) {
                return this;
            }
            return mittKull.far.finnEldsteKjenteOpphav();
        }
        return mittKull.mor.finnEldsteKjenteOpphav();

        Hund mamma = mittKull.mor.finnEldsteKjenteOpphav();
        Hund pappa = mittKull.far.finnEldsteKjenteOpphav();

        if (mamma.compareTo(pappa) > 0) {
            return mamma;
        } else {
            return pappa;
        }
    }
}  

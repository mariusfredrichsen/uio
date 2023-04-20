public class Modell {
    
    Verden verden;
    Celle[][] celler;

    Modell(int rad, int kol) {
        verden = new Verden(rad, kol);
        celler = verden.hentCeller();
    }

    public boolean byttStatus(int rad, int kol) {
        Celle celle = celler[rad][kol];
        if (celle.erLevende()) celle.settDoed();
        else celle.settLevende();
        return celle.erLevende();
    }

    public void oppdater() {
        verden.oppdatering();
    }

    public Celle[][] hentCeller() {
        return celler;
    }

    public int hentAntLevende() {
        return verden.hentRutenett().antallLevende();
    }



}

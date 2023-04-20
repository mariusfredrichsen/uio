public class Verden {
    int antRader;
    int antKolonner;
    int genNr = 0;
    public Rutenett rutenett;

    public Verden(int antRader, int antKolonner) { //konstruktøren
        this.antRader = antRader;
        this.antKolonner = antKolonner;

        rutenett = new Rutenett(antRader, antKolonner);
        rutenett.fyllMedCeller();
        rutenett.kobleAlleCeller();
    }

    public Celle[][] hentCeller() {
        return rutenett.hentRutene();
    }

    public Rutenett hentRutenett() {
        return rutenett;
    }

    public void tegn() { //bruker den kjempe kule "tegnRutenett" metoden i rutenett i tillegg til jeg oppgir gen nr og antall levende
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
        System.out.println("Generasjon nr " + genNr + ":");
        rutenett.tegnRutenett();
        System.out.println("\nDet er " + rutenett.antallLevende() + " levende celler.");
    }

    public void oppdatering() { //kjører først "telleLevendeNaboer" så "oppdaterStatus" metodene på alle rutene 
        for (int x = 0; x < rutenett.antRader; x++) {
            for (int y = 0; y < rutenett.antKolonner; y++) {
                rutenett.hentCelle(x, y).tellLevendeNaboer();
            }
        }
        for (int x = 0; x < rutenett.antRader; x++) {
            for (int y = 0; y < rutenett.antKolonner; y++) {
                rutenett.hentCelle(x, y).oppdaterStatus();
            }
        }
        genNr++;
    }


}

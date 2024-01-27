


public class Verden {
    Rutenett rutenett;
    int antRader;
    int antKolonner;
    public int genNr;


    public Verden(int antRader, int antKolonner) {
        rutenett = new Rutenett(antRader, antKolonner);
        genNr = 0;
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
        this.antRader = antRader;
        this.antKolonner = antKolonner;
    }

    public void tegn() {
        rutenett.tegnRutenett();
        System.out.println(String.format("Antall levende: %s\nGenerasjon: %s", rutenett.antallLevende(), genNr));
    }

    public void oppdatering() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                rutenett.hentCelle(rad, kol).tellLevendeNaboer();
            }
        }
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                rutenett.hentCelle(rad, kol).oppdaterStatus();
            }
        }
        genNr++;
    }
}

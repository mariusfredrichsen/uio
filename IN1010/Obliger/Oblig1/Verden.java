public class Verden {
    int antRader;
    int antKolonner;
    int genNr = 0;
    Rutenett rutenett;

    public Verden(int antRader, int antKolonner) {
        this.antRader = antRader;
        this.antKolonner = antKolonner;

        rutenett = new Rutenett(antRader, antKolonner);
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }

    public void tegn() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
        System.out.println("Generasjon nr " + genNr + ":");
        rutenett.tegnRutenett();
        System.out.println("\nDet er " + rutenett.antallLevende() + " levende celler.");
    }

    public void oppdatering() {
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

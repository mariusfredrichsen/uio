


public class Rutenett {
    public int antRader;
    public int antKolonner;
    public Celle[][] rutene;

    public Rutenett(int antRader, int antKolonner) {
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutene = new Celle[antRader][antKolonner];
    }

    public void lagCelle(int rad, int kol) {
        Celle celle = new Celle();
        if (Math.random() <= 0.3333) celle.settLevende();
        rutene[rad][kol] = celle;
    }

    public void fyllMedTilfeldigeCeller() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                lagCelle(rad, kol);
            }
        }
    }

    public Celle hentCelle(int rad, int kol) {
        if (rad < 0 || kol < 0 || rad >= antRader || kol >= antKolonner) {
            return null;
        }
        return rutene[rad][kol];
    }

    public void tegnRutenett() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                System.out.print(rutene[rad][kol].hentStatusTegn());
            }
            System.out.println();
        }
    }

    public void settNaboer(int rad, int kol) {
        Celle celle = rutene[rad][kol];
        for (int r = -1; r < 2; r++) {
            for (int k = -1; k < 2; k++) {
                if (Math.abs(r) + Math.abs(k) != 0) celle.leggTilNabo(hentCelle(rad+r, kol+k));
            }
        }
    }

    public void kobleAlleCeller() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                settNaboer(rad, kol);
            }
        }
    }

    public int antallLevende() {
        int antLevende = 0;
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                if (rutene[rad][kol].erLevende()) antLevende++;
            }
        }
        return antLevende;
    }


}

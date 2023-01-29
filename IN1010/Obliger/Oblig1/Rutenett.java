import java.util.Random;
import java.lang.Math;

public class Rutenett {
    int antRader;
    int antKolonner;
    Celle[][] rutene;

    public Rutenett(int antRader, int antKolonner) {
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutene = new Celle[antRader][antKolonner];
    }

    public void lagCelle(int rad, int kol) {
        Celle celle = new Celle();
        Random random = new Random();
        if (random.nextInt(0, 3) == 0) {
            celle.settLevende();
        }
        rutene[rad][kol] = celle;
    }

    public void fyllMedTilfeldigeCeller() {
        for (int x = 0; x < antRader; x++) {
            for (int y = 0; y < antKolonner; y++) {
                this.lagCelle(x, y);
            }
        }
    }

    public Celle hentCelle(int rad, int kol) {
        if (rad > antRader - 1 || kol > antKolonner - 1 || rad < 0 || kol < 0) {
            return null;
        } else {
            return rutene[rad][kol];
        }
    }

    public void tegnRutenett() {
        for (int x = 0; x < antRader; x++) {
            System.out.println();
            for (int i = 0; i < antKolonner; i++) {
                System.out.print("+---");
            } System.out.print("+");
            System.out.println();
            System.out.print("|");
            for (int y = 0; y < antKolonner; y++) {
                System.out.print(" " + rutene[x][y].hentStatusTegn() + " |");
            }
        }
        System.out.println();
        for (int i = 0; i < antKolonner; i++) {
            System.out.print("+---");
        } System.out.print("+");
    }

    public void settNaboer(int rad, int kol) {
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (Math.abs(x) + Math.abs(y) != 0) {
                    rutene[rad][kol].leggTilNabo(this.hentCelle(rad + x, kol + y));
                }
            }
        }
    }

    public void kobleAlleCeller() {
        for (int x = 0; x < antRader; x++) {
            for (int y = 0; y < antKolonner; y++) {
                this.settNaboer(x, y);
            }
        }
    }

    public int antallLevende() {
        int teller = 0;
        for (int x = 0; x < antRader; x++) {
            for (int y = 0; y < antKolonner; y++) {
                if (rutene[x][y].erLevende()) {
                    teller++;
                }
            }
        }
        return teller;
    }

    public void hjelp() {
        for (int x = 0; x < antRader; x++) {
            for (int y = 0; y < antKolonner; y++) {
                rutene[x][y].tellLevendeNaboer();
            }
        }
    }
}

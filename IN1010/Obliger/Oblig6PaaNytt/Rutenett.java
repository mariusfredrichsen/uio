import java.lang.Math;

public class Rutenett {
    int antRader;
    int antKolonner;
    public Celle[][] rutene;

    public Rutenett(int antRader, int antKolonner) { //konstruktøren
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutene = new Celle[antRader][antKolonner];
    }

    public Celle[][] hentRutene() {
        return rutene;
    }

    public void lagCelle(int rad, int kol) {
        Celle celle = new Celle();
        celle.settDoed();
        rutene[rad][kol] = celle;
    }

    public void fyllMedCeller() { //kjører "lagCelle" metoden på alle rutene i rutenettet
        for (int x = 0; x < antRader; x++) {
            for (int y = 0; y < antKolonner; y++) {
                this.lagCelle(x, y);
            }
        }
    }

    public Celle hentCelle(int rad, int kol) { //returner en celle så lenge den er innafor rutenettet
        if (rad > antRader - 1 || kol > antKolonner - 1 || rad < 0 || kol < 0) {
            return null;
        } else {
            return rutene[rad][kol];
        }
    }

    public void tegnRutenett() { //kul skriv ut metode baster på det som var i oppgaveteksten
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

    public void settNaboer(int rad, int kol) { //går igjennom alle rutene ved siden av en celle og kjører metoden "leggTilNabo"
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (Math.abs(x) + Math.abs(y) != 0) {
                    rutene[rad][kol].leggTilNabo(this.hentCelle(rad + x, kol + y));
                }
            }
        }
    }

    public void kobleAlleCeller() { //kjører "settNaboer" metoden på alle rutene
        for (int x = 0; x < antRader; x++) {
            for (int y = 0; y < antKolonner; y++) {
                this.settNaboer(x, y);
            }
        }
    }

    public int antallLevende() { //teller antall levende celler det er i totalt i rutenettet
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
}

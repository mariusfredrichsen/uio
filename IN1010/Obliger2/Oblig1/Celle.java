


public class Celle {
    public Boolean levende;
    public Celle[] naboer;
    public int antNaboer;
    public int antLevendeNaboer;

    public Celle() {
        levende = false;
        naboer = new Celle[8];
        antNaboer = 0;
        antLevendeNaboer = 0;
    }

    public void settLevende() {
        levende = true;
    }

    public void settDoed() {
        levende = false;
    }

    public Boolean erLevende() {
        return levende;
    }

    public char hentStatusTegn() {
        if (erLevende()) {
            return 'O';
        } else {
            return '.';
        }
    }

    public void leggTilNabo(Celle nabo) {
        for (int i = 0; i < naboer.length; i++) {
            if (naboer[i] == null && nabo != null) {
                naboer[i] = nabo;
                antNaboer++;
                break;
            }
        }
    }

    public void tellLevendeNaboer() {
        antLevendeNaboer = 0;
        for (Celle celle : naboer) {
            if (celle != null) {
                if (celle.erLevende()) antLevendeNaboer++;
            }
        }
    }

    public void oppdaterStatus() {
        if (antLevendeNaboer < 2 || antLevendeNaboer > 3) {
            settDoed();
        } else if (this.antLevendeNaboer == 3) {
            this.settLevende();
        }
    }
}
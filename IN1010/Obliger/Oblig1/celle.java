public class Celle {
    boolean levende = false;
    Celle[] naboer = new Celle[8];
    int antNaboer = 0;
    int antLevendeNaboer = 0;

    public void settDoed() {
        levende = false;
    }

    public void settLevende() {
        levende = true;
    }

    public boolean erLevende() {
        return levende;
    }

    public char hentStatusTegn() {
        if (levende) {
            return 'O';
        } return '.';
    }

    public void leggTilNabo(Celle nabo) {
        for (int i = 0; i < naboer.length; i++) {
            if (naboer[i] == null) {
                naboer[i] = nabo;
                break;
            }
        } 
        antNaboer = 0;
        for (Celle celle : naboer) {
            if (celle != null) {
                antNaboer++;
            }
        }
    }
    
    public void tellLevendeNaboer() {
        int antLevende = 0;
        for (Celle nabo : naboer) {
            if (nabo != null) {
                if (nabo.erLevende()) {
                    antLevende++;
                }
            }
        }
        antLevendeNaboer = antLevende;
    }

    public void oppdaterStatus() {
        if (levende && (antLevendeNaboer < 2 || antLevendeNaboer > 3)) {
            levende = false;
        } else if (!levende && antLevendeNaboer == 3) {
            levende = true;
        }
    }
}

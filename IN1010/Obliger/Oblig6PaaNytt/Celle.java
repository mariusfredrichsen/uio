public class Celle {
    boolean levende = false;
    Celle[] naboer = new Celle[8];
    int antNaboer = 0;
    int antLevendeNaboer = 0; //konstruktøren til Celle

    public void settDoed() {
        levende = false;
    }

    public void settLevende() {
        levende = true;
    }

    public boolean erLevende() {
        return levende;
    }

    public char hentStatusTegn() { //returner en char som er bare en enkel bokstav
        if (levende) {
            return 'O';
        } return '.';
    }

    public void leggTilNabo(Celle nabo) {
        for (int i = 0; i < naboer.length; i++) { //looper igjennom arrayen for å finne en ledig plass
            if (naboer[i] == null) {
                naboer[i] = nabo;
                break; 
            }
        } 
        antNaboer = 0;
        for (Celle celle : naboer) { //teller antall ikke null-objekter
            if (celle != null) {
                antNaboer++;
            }
        }
    }
    
    public void tellLevendeNaboer() { //går igjennom arrayen for å sjekke om de er levende eller ikke og teller
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

    public void oppdaterStatus() { //en if sjekk med en logikk som sjekker om cellen skal være dø eller levende
        if (levende && (antLevendeNaboer < 2 || antLevendeNaboer > 3)) {
            levende = false;
        } else if (!levende && antLevendeNaboer == 3) {
            levende = true;
        }
    }
}

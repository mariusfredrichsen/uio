public class Modell {
    // Brettet innholder 
    // - blank der det er ledig (initialverdier)
    // X der maskinen har satt
    // O der brukeren har satt
    char[][] brett = new char[3][3];
    int antTrekk = 0;
    boolean spilletErFerdig = false;
    Modell () {
	    for (int rx = 0;  rx < 3;  ++rx)
	        for (int kx = 0;  kx < 3;  ++kx)
	        	brett[rx][kx] = ' ';
    }
    boolean lovligTrekk (int r, int k) {
		return brett[r][k] == ' ';
    }
    void noterTrekk (int r, int k, char spiller) {
		brett[r][k] = spiller;
		++antTrekk;
    }
    void noterVinner (String vinner) {
		spilletErFerdig = true;
    }
    void noterUavgjort () {
		spilletErFerdig = true;
    }
    boolean erSpilletFerdig () {
		return spilletErFerdig;
    }
    boolean erBrettetFullt () {
		return antTrekk == 9;
    }
    boolean harVunnet (char s) {
		return
	    // Sjekk radene
	    brett[0][0]==s && brett[0][1]==s && brett[0][2]==s ||
	    brett[1][0]==s && brett[1][1]==s && brett[1][2]==s ||
	    brett[2][0]==s && brett[2][1]==s && brett[2][2]==s ||
	    // Sjekk kolonnene
	    brett[0][0]==s && brett[1][0]==s && brett[2][0]==s ||
	    brett[0][1]==s && brett[1][1]==s && brett[2][1]==s ||
	    brett[0][2]==s && brett[1][2]==s && brett[2][2]==s ||
	    // Sjekk diagonalene
	    brett[0][0]==s && brett[1][1]==s && brett[2][2]==s ||
	    brett[0][2]==s && brett[1][1]==s && brett[2][0]==s;
    }
}
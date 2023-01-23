package IN1010.Oppgaver;

public class Baat {
    private static int antProd = 0;
    private int prodnr;
    private String merke;

    public Baat(String mrk) {
        prodnr = antProd;
        antProd++;
        merke = mrk;
    }

    public String hentInfo() {
        return "Produksjonsnummer: " + prodnr + ", merke: " + merke;
    }
}

class Baathus {
    String[] Baater = {};
    int antallPlasser;

    public Baathus(int nPlasser) {
        antallPlasser = nPlasser;
    }

    public void settInn (Baat enBaat) {

    }
}
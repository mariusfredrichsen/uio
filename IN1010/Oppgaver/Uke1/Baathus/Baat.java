package IN1010.Oppgaver.Uke1.Baathus;

class Baat {

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
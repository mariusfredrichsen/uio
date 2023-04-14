public class Subsekvens {
    public final String subsekvens;
    private int antall;

    public Subsekvens(String subsekvens) {
        this.subsekvens = subsekvens;
        antall = 1;
    }

    public int hentAntall() {
        return antall;
    }

    public void endreAntall(int antall) {
        this.antall += antall;
    }

    public String toString() {
        return String.format("(%s,%s)",subsekvens,antall);
    }
}

public class Subsekvens {
    public final String subsekvens;
    private int antall;

    public Subsekvens(String subsekvens, int antall) {
        this.subsekvens = subsekvens;
        this.antall = antall;
    }

    public void oekAntall(int antall) {
        this.antall += antall;
    }

    public int hentAntall() {
        return antall;
    }

    public String toString() {
        return String.format("%s,%s",subsekvens, antall);
    }
}

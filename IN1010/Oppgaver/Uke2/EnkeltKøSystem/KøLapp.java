


public class KøLapp {
    static int teller = 0;
    int nummer;

    public KøLapp() {
        nummer = teller++;
    }

    public int hentNummer() {
        return nummer;
    }
}

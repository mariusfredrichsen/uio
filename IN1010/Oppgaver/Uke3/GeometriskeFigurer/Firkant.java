

public class Firkant extends Figur {
    int bredde;
    int lengde;

    public Firkant(int bredde, int lengde) {
        this.bredde = bredde;
        this.lengde = lengde;
    }

    public double areal() {
        return bredde*lengde;
    }
}

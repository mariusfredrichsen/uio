

public class Kvadrat extends Figur{
    int bredde;
    int lengde;

    public Kvadrat(int bredde, int lengde) {
        this.bredde = bredde;
        this.lengde = lengde;
    }

    public double areal() {
        return bredde*lengde;
    }
}

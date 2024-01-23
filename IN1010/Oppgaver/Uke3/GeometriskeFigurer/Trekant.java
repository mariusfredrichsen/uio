

public class Trekant extends Figur{
    int bredde;
    int lengde;

    public Trekant(int bredde, int lengde) {
        this.bredde = bredde;
        this.lengde = lengde;
    }

    @Override
    public double areal() {
        return bredde*lengde/2;
    }
}

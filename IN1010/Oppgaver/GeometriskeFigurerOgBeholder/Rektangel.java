package IN1010.Oppgaver.GeometriskeFigurerOgBeholder;

public class Rektangel extends Figurer {
    double lengde;
    double bredde;

    public Rektangel(double lengde, double bredde) {
        this.lengde = lengde;
        this.bredde = bredde;
    }

    public double areal() {
        return bredde*lengde;
    }
}

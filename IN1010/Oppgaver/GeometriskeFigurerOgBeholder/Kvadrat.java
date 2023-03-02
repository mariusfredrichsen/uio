package IN1010.Oppgaver.GeometriskeFigurerOgBeholder;

public class Kvadrat extends Figurer {
    double sideLengde;

    public Kvadrat(double sideLengde) {
        this.sideLengde = sideLengde;
    }

    public double areal() {
        return sideLengde*sideLengde;
    }
}

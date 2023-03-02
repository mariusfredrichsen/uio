package IN1010.Oppgaver.GeometriskeFigurerOgBeholder;

public class Trekant extends Figurer {
    double grunnLinje;
    double hoeyde;

    public Trekant(double grunnLinje, double hoeyde) {
        this.grunnLinje = grunnLinje;
        this.hoeyde = hoeyde;
    }

    public double areal() {
        return (grunnLinje*hoeyde)/2;
    }
}

package IN1010.Oppgaver.GeometriskeFigurerI;

public class Sirkel implements Figur {
    private double radius;
    double areal;
    double omkrets;

    public Sirkel(double radius) {
        this.radius = radius;
    }

    public double beregnAreal() {
        return Math.PI * radius * radius;
    }

    public double beregnOmkrets() {
        return Math.PI * radius * 2;
    }
}

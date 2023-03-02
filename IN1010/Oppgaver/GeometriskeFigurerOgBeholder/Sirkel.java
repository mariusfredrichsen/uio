package IN1010.Oppgaver.GeometriskeFigurerOgBeholder;

public class Sirkel extends Figurer {
    double radius;
    
    public Sirkel(double radius) {
        this.radius = radius;
    }

    public double areal() {
        return Math.PI*radius*radius;
    }
}

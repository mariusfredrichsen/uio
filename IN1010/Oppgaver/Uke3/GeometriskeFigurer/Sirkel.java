

public class Sirkel extends Figur {
    int radius;
    public Sirkel(int radius) {
        this.radius = radius;
    }

    public double areal() {
        return Math.PI*radius*radius;
    }
}

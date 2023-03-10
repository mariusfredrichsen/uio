package IN1010.Oppgaver.Uke6.GeometriskeFigurerI;

public class Rektangel implements Figur {
    private int bredde;
    private int hoyde;
    double areal;
    double omkrets;

    public Rektangel(int bredde, int hoyde) {
        this.bredde = bredde;
        this.hoyde = hoyde;
    }

    public double beregnAreal() {
        return bredde * hoyde;
    }

    public double beregnOmkrets() {
        return 2 * bredde + 2 * hoyde;
    }
}

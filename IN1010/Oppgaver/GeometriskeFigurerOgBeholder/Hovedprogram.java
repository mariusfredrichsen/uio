package IN1010.Oppgaver.GeometriskeFigurerOgBeholder;

public class Hovedprogram {
    public static void main(String[] args) {
        Figurer[] figurer = new Figurer[4];
        figurer[0] = new Kvadrat(4);
        figurer[1] = new Rektangel(3, 7);
        figurer[2] = new Sirkel(3);
        figurer[3] = new Trekant(2, 3);

        double sumAreal = 0;

        for (int i = 0; i < 4; i++) {
            System.out.println(figurer[i].areal());
            sumAreal += figurer[i].areal();
        }
        System.out.println(sumAreal);
    }
}

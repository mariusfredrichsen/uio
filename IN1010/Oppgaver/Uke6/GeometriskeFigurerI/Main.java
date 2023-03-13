package IN1010.Oppgaver.Uke6.GeometriskeFigurerI;

public class Main {
    public static void main(String[] args) {
        Rektangel rektangel = new Rektangel(10, 5);
        Sirkel sirkel = new Sirkel(5.3);

        System.out.println("Areal: " + rektangel.beregnAreal() + " og omkrets: " + rektangel.beregnOmkrets());
        System.out.println("Areal: " + sirkel.beregnAreal() + " og omkrets: " + sirkel.beregnOmkrets());

    }
}

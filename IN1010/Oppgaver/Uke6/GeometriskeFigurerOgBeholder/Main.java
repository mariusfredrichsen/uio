package IN1010.Oppgaver.Uke6.GeometriskeFigurerOgBeholder;

public class Main {
    public static void main(String[] args) {
        Beholder<Sirkel> sirkelBeholder = new Beholder<>();
        Kvadrat kvadrat = new Kvadrat(20);
        Sirkel sirkel = new Sirkel(16);
        sirkelBeholder.settInn(sirkel);
        sirkelBeholder.settInn(kvadrat);
    }
}

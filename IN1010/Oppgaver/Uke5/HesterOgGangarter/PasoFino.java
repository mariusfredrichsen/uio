package IN1010.Oppgaver.Uke5.HesterOgGangarter;
public class PasoFino extends Hest implements KanToelte {
    public PasoFino(String n, int a) {
        super(n, a);
    }

    public void toelt() {
        System.out.println(navn + " gaar en firtaktig gangart med hevede bein.");
    }
}

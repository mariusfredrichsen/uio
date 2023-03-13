package IN1010.Oppgaver.Uke5.HesterOgGangarter;
public class Islandshest extends Hest implements KanPasse, KanToelte{
    public Islandshest(String n, int a) {
        super(n, a);
    }

    public void toelt() {
        System.out.println(navn + " gaar en firtaktig gangart med hevede bein.");
    }

    public void pass() {
        System.out.println(navn + " gaar en toaktig gangart");
    }
}

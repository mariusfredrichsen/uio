package IN1010.Oppgaver.Parkeringsplass;

public class Parkeringsplass <E> {
    private E parkertKjoretoy;

    public void parkerKjoretoy(E parkertKjoretoy) {
        this.parkertKjoretoy = parkertKjoretoy;
    }

    public void kjorUtKjoretoy(E parkerKjoretoy) {
        this.parkertKjoretoy = null;
    }

    public E hentUt() {
        return parkertKjoretoy;
    }
}

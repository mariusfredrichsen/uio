package IN1010.Oppgaver.Parkeringsplass;

public class Hovedprogram {
    public static void main(String[] args) {
        Parkeringsplass<Bil> bilParkering = new Parkeringsplass<>();
        Parkeringsplass<Motorsykkel> motorsykkelParkering = new Parkeringsplass<>();

        Bil bil1 = new Bil(1298952, 5);
        Motorsykkel motorsykkel = new Motorsykkel(38983291, 43);

        bilParkering.parkerKjoretoy(bil1);
        motorsykkelParkering.parkerKjoretoy(motorsykkel);
        motorsykkelParkering.kjorUtKjoretoy(motorsykkel);
        motorsykkelParkering.parkerKjoretoy(bil1);
    }
}

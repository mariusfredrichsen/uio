import java.util.concurrent.CountDownLatch;

public class OrdFinnere implements Runnable {
    int start;
    int slutt;
    Monitor m;
    String onsketOrd;
    String[] ordListe;
    CountDownLatch barriere;

    public OrdFinnere(int start, int slutt, Monitor m, String onsketOrd, String[] ordListe, CountDownLatch barriere) {
        this.start = start;
        this.slutt = slutt;
        this.m = m;
        this.onsketOrd = onsketOrd;
        this.ordListe = ordListe;
        this.barriere = barriere;
    }

    public void run() {
        for (int i = start; i < slutt; i++) {
            if (ordListe[i].contains(onsketOrd)) m.tellOpp();
        }
        barriere.countDown();
    }
}

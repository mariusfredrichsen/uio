import java.io.File;
import java.util.concurrent.CountDownLatch;

public class LeseTrad implements Runnable {
    String filnavn;
    Monitor2 m2;
    CountDownLatch barriere;

    public LeseTrad(String filnavn, Monitor2 m2, CountDownLatch barriere) {
        this.filnavn = filnavn;
        this.m2 = m2;
        this.barriere = barriere;
    }

    public void run() {
        m2.settInn(m2.lesFil(new File(filnavn)));
        barriere.countDown();
    }
}

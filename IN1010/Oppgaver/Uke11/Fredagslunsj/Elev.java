package IN1010.Oppgaver.Uke11.Fredagslunsj;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Elev implements Runnable {
    boolean matType;
    CountDownLatch barriere;
    Stemmeboks stemmeboks;

    public Elev(CountDownLatch barriere, Stemmeboks stemmeboks) {
        matType = new Random().nextBoolean();
        this.barriere = barriere;
        this.stemmeboks = stemmeboks;
    }

    public void run() {
        stemmeboks.stem(new Random().nextBoolean());
        barriere.countDown();
    }
    
}

package IN1010.Oppgaver.Uke11.Postkontor;

import java.util.concurrent.CountDownLatch;

public class Postmann implements Runnable {
    Postkontor postkontor;
    int pakker;
    CountDownLatch barriere;

    public Postmann(Postkontor postkontor, int pakker, CountDownLatch barriere) {
        this.postkontor = postkontor;
        this.pakker = pakker;
        this.barriere = barriere;
    }

    public void run() {
        for (int i = 0; i < pakker; i++) {
            postkontor.leverPost(new Post("LMAO", "Albert"));
            barriere.countDown();
        }
    }
}

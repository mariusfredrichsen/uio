package IN1010.Oppgaver.Uke11.Postkontor;

import java.util.concurrent.CountDownLatch;

public class Kunde implements Runnable {
    String mottakerNavn;
    Postkontor postkontor;
    
    public Kunde(String mottakerNavn, Postkontor postkontor) {
        this.mottakerNavn = mottakerNavn;
        this.postkontor = postkontor;
    }

    public void run() {
        System.out.println(postkontor.hentPost(mottakerNavn));
    }
}

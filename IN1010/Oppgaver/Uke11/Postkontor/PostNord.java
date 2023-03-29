package IN1010.Oppgaver.Uke11.Postkontor;

import java.util.concurrent.CountDownLatch;

public class PostNord {
    public static void main(String[] args) {
        int antFolk = 150;
        int antPakker = 200;
        CountDownLatch barriere = new CountDownLatch(antPakker);
        Postkontor postkontor = new Postkontor(barriere);
        new Thread(new Postmann(postkontor, antPakker, barriere)).start();
        for (int i = 0; i < antFolk; i++) {
            new Thread(new Kunde("Albert", postkontor)).start();
        }
    }
}

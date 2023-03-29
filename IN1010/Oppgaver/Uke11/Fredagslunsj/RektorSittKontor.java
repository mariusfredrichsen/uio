package IN1010.Oppgaver.Uke11.Fredagslunsj;

import java.util.concurrent.CountDownLatch;

public class RektorSittKontor {
    public static void main(String[] args) {
        int antElever = 425;
        CountDownLatch barriere = new CountDownLatch(antElever);
        Stemmeboks stemmeboks = new Stemmeboks();

        for (int i = 0; i < antElever; i++) {
            new Thread(new Elev(barriere, stemmeboks)).start();
        }

        try {
            barriere.await();
            stemmeboks.skrivUtResultat();
        } catch (InterruptedException e) {
            System.out.println("Noe skjedde.");
        }
    }
}

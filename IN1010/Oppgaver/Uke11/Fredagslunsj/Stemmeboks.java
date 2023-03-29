package IN1010.Oppgaver.Uke11.Fredagslunsj;

import java.util.concurrent.locks.*;

public class Stemmeboks {
    Lock laas;
    int pannekakeTeller = 0;
    int grotTeller = 0;

    public Stemmeboks() {
        laas = new ReentrantLock();
    }

    public void stem(boolean type) {
        if (type) {
            pannekakeTeller++;
        } else {
            grotTeller++;
        }
    }

    public void skrivUtResultat() {
        System.out.println(pannekakeTeller + " vil ha pannekaker.");
        System.out.println(grotTeller + " vil ha grot.");
    }
}

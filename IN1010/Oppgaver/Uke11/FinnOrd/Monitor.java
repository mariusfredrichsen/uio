package IN1010.Oppgaver.Uke11.FinnOrd;

import java.util.concurrent.locks.*;

public class Monitor {
    String[] ordListe;
    Lock laas;
    String onsketOrd;

    public Monitor(String[] ordListe, String onsketOrd) {
        this.ordListe = ordListe;
        laas = new ReentrantLock();
        this.onsketOrd = onsketOrd;
    }

    public boolean lesLinje(String ord) {
        laas.lock();
        try {
            if (ord.equals(onsketOrd)) return true;
            return false;
        } finally {
            laas.unlock();
        }
    }
}

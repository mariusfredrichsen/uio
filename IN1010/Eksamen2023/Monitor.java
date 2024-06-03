import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Monitor {
    Lock lås;
    Condition ikkeTomt;
    int antTråder;
    ArrayList<Skinnegående> sListe;

    public Monitor(int antTråder) {
        this.antTråder = antTråder;
        
        lås = new ReentrantLock();
        sListe = new ArrayList<>();
        ikkeTomt = lås.newCondition();
    }

    public void leggTil(Skinnegående s) {
        lås.lock();
        try {
            sListe.add(s);
            ikkeTomt.signal();
        } finally {
            lås.unlock();
        }
    }

    // feil
    public void ferdigLeting() {
        lås.lock(); 
        try {
            antTråder--;
            ikkeTomt.signal();
        } finally {
            lås.unlock();
        }
    }

    // feil
    public Skinnegående hentNeste() throws InterruptedException {
        lås.lock();
        try {
            while (sListe.size() == 0 && antTråder > 0) {
                ikkeTomt.await();
            }
            if (sListe.size() != 0) return sListe.remove(0);
            return null;
        } finally {
            lås.unlock();
        }
    }
}

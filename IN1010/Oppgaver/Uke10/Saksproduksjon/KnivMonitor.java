package IN1010.Oppgaver.Uke10.Saksproduksjon;
import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class KnivMonitor {
    ArrayList<Kniv> knivBeholder = new ArrayList<>();
    Lock laas = new ReentrantLock();
    Condition merEnnEn = laas.newCondition();

    public void settInn(Kniv kniv) {
        laas.lock();
        try {
            knivBeholder.add(kniv);
            if (knivBeholder.size() > 1) {
                merEnnEn.signal();
            }
        } finally {
            laas.unlock();
        }
    }

    public ArrayList<Kniv> taUtTo() throws InterruptedException {
        while (knivBeholder.size() < 2) merEnnEn.await();
        ArrayList<Kniv> toKniver = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            toKniver.add(knivBeholder.remove(0));
        }
        return toKniver;
    }

    public int hentAntKniver() {
        return knivBeholder.size();
    }
}

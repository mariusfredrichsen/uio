package IN1010.Oppgaver.Uke9;
import java.util.concurrent.locks.*;


public class Tabell {
    Lock laas;
    Condition harForrige;
    int fellesTeller = -1;

    public Tabell() {
        laas = new ReentrantLock();
        harForrige = laas.newCondition();
    }

    public void skrivUt(int tall, int id) {
        laas.lock();
        try {
            while (tall-1 != fellesTeller) harForrige.await();
            fellesTeller++;
            System.out.println(tall + " nummer: " + id);
            harForrige.signalAll();
        } catch (InterruptedException e) {
            System.out.println("Now skjedde.");
        } finally {
            laas.unlock();
        }
    }
}

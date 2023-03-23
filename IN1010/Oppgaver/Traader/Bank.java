import java.util.concurrent.locks.*;

public class Bank {
    int antall = 200;
    Lock laas;
    Condition ikkeTom;
    Condition ikkeFull;
    private final int MAKS_KAP;

    public Bank(int maksAnt) {
        MAKS_KAP = maksAnt;
        laas =  new ReentrantLock();
        ikkeTom = laas.newCondition();
        ikkeFull = laas.newCondition();
    }

    public void leggInn(int ant) throws InterruptedException {
        laas.lock();
        try {
            while (antall >= MAKS_KAP) {
                ikkeFull.await();
            }
            antall += ant;
            System.out.println("Lagt til " + ant + "kr i banken, totalt: " + antall);
            ikkeTom.signal();
        } finally {
            laas.unlock();
        }
        
    }

    public void taUt(int ant) throws InterruptedException {
        laas.lock();
        try {
            while (antall <= 0) {
                ikkeTom.await();
            }
            antall -= ant;
            System.out.println("Ranet " + ant + "kr fra banken, totalt: " + antall);
            ikkeFull.signal();
        } finally {
            laas.unlock();
        }
    }

    public static void main(String[] args) {

    }
}

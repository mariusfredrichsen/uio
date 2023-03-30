import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.*;
import java.io.File;

public class Monitor2 {
    SubsekvensRegister s;
    Lock laas;
    Condition merEnnEn;
    CountDownLatch barriere;

    public Monitor2(SubsekvensRegister s, CountDownLatch barriere) {
        this.s = s;
        laas = new ReentrantLock();
        merEnnEn = laas.newCondition();
        this.barriere = barriere;
    }

    public void settInn(HashMap<String,Subsekvens> hash) {
        laas.lock();
        try {
            s.settInn(hash);
        } finally {
            laas.unlock();
        }
    }

    public void settInnFlettet(HashMap<String,Subsekvens> hash) {
        laas.lock();
        try {
            s.settInn(hash);
            merEnnEn.signalAll();
        } finally {
            laas.unlock();
        }
    }

    public ArrayList<HashMap<String,Subsekvens>> taUtTo() {
        laas.lock();
        try {
            while (s.hentAnt() > 1 && barriere.getCount() == 0) merEnnEn.await();
            ArrayList<HashMap<String,Subsekvens>> toHash = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                toHash.add(s.taUt());
            }
            return toHash;
        
        } catch (InterruptedException e) {
            System.out.println("Noe skjedde.");
        } finally {
            laas.unlock();
        }
        return null;
    }

    public int hentAnt() {
        return s.hentAnt();
    }

    public HashMap<String,Subsekvens> lesFil(File fil) {
        laas.lock();
        try {
            return SubsekvensRegister.lesFil(fil);
        } finally {
            laas.unlock();
        }
    }

    public HashMap<String,Subsekvens> slaaSammen(HashMap<String,Subsekvens> hash1, HashMap<String,Subsekvens> hash2) {
        laas.lock();
        try {
            return SubsekvensRegister.slaaSammen(hash1, hash2);
        } finally {
            laas.unlock();
        }
    }

}

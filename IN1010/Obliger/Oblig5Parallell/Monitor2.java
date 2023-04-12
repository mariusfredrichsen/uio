import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.*;

public class Monitor2 {
    SubsekvensRegister s;
    Lock laas;
    Condition merEnnEn;
    int teller = 0;

    public Monitor2(SubsekvensRegister s) {
        this.s = s;
        laas = new ReentrantLock();
        merEnnEn = laas.newCondition();
    }

    public ArrayList<HashMap<String,Subsekvens>> hentBeholder() {
        return s.hentBeholder();
    }

    public void settInn(HashMap<String,Subsekvens> hash) {
        laas.lock();
        try {
            s.settInn(hash);
            System.out.println("Skrevet fil " + teller++);
            merEnnEn.signalAll();
        } finally {
            laas.unlock();
        }
    }

    public void settInnFlettet(HashMap<String,Subsekvens> flettetHash) {
        laas.lock();
        try {
            s.settInn(flettetHash);
            System.out.println("asdasdasd");
            merEnnEn.signalAll();
        } finally {
            laas.unlock();
        }
    }

    public HashMap<String,Subsekvens> taUt() {
            return s.taUt();
    }

    public ArrayList<HashMap<String,Subsekvens>> taUtTo() {
        laas.lock();
        try {
            if (antHash() >= 2) {
                ArrayList<HashMap<String,Subsekvens>> toHash = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    toHash.add(s.taUt());
                }
                return toHash;
            } else {
                merEnnEn.await();
            }
        } catch (InterruptedException e) {
            System.out.println("Noe skjedde.");
        } finally {
            laas.unlock();
        }
        return null;
    }

    public int antHash() {
        return s.antHash();
    }

    public static HashMap<String,Subsekvens> lesFil(String filNavn) {
        return SubsekvensRegister.lesFil(filNavn);
    }

    public static HashMap<String,Subsekvens> slaaSammen(HashMap<String,Subsekvens> hash1, HashMap<String,Subsekvens> hash2) {
        return SubsekvensRegister.slaaSammen(hash1, hash2);
    }
}

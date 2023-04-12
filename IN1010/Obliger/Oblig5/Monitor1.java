import java.util.HashMap;
import java.util.concurrent.locks.*;

public class Monitor1 {
    SubsekvensRegister s;
    Lock laas;

    public Monitor1(SubsekvensRegister s) {
        this.s = s;
        laas = new ReentrantLock();
    }

    public void settInn(HashMap<String,Subsekvens> hash) {
        laas.lock();
        try {
            s.settInn(hash);
        } finally {
            laas.unlock();
        }
    }

    public HashMap<String,Subsekvens> taUt() {
        return s.taUt();
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class FletteTrad implements Runnable {
    Monitor2 m2;
    CountDownLatch barriere;
    

    public FletteTrad(Monitor2 m2, CountDownLatch barriere) {
        this.m2 = m2;
        this.barriere = barriere;
    }

    public void run() {
        ArrayList<HashMap<String,Subsekvens>> toHash = m2.taUtTo();
        if (toHash == null) return;
        HashMap<String,Subsekvens> hash1 = toHash.remove(0);
        HashMap<String,Subsekvens> hash2 = toHash.remove(0);
        if (hash1 == null) {
            m2.settInnFlettet(hash2);
            return;
        }
        else if (hash2 == null) {
            m2.settInnFlettet(hash1);
            return;
        }    
        m2.settInnFlettet(m2.slaaSammen(hash1,hash2));
        barriere.countDown();
    }
}
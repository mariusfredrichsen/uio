import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class FletteTrad implements Runnable {
    Monitor2 m2;
    CountDownLatch barriere;
    int tel = 0;
    

    public FletteTrad(Monitor2 m2, CountDownLatch barriere) {
        this.m2 = m2;
        this.barriere = barriere;
    }

    public void run() {
        while (barriere.getCount() > 0 && !(m2.hentAnt() < 2)) {
            try {
                ArrayList<HashMap<String,Subsekvens>> toHash = m2.taUtTo();
                HashMap<String,Subsekvens> hash1 = toHash.remove(0);
                HashMap<String,Subsekvens> hash2 = toHash.remove(0);  
                m2.settInnFlettet(m2.slaaSammen(hash1,hash2));
                barriere.countDown();
            } catch (NullPointerException e) {
                return;
            }
        }
    }
}
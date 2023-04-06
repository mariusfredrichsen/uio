import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class FletteTrad implements Runnable {
    Monitor2 m;
    CountDownLatch barriere;

    public FletteTrad(Monitor2 m, CountDownLatch barriere) {
        this.m = m;
        this.barriere = barriere;
    }

    public void run() {
        while (barriere.getCount() > 0 && !(m.antHash() < 2)) {
            try {
                ArrayList<HashMap<String,Subsekvens>> toHash = m.taUtTo();
                m.settInnFlettet(SubsekvensRegister.slaaSammen(toHash.remove(0),toHash.remove(0)));
                barriere.countDown();
            } catch (NullPointerException e) {
                return;
            }
        }
    }
}

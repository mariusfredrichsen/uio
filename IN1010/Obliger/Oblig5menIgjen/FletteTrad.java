import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class FletteTrad implements Runnable {
    Monitor2 m;

    public FletteTrad(Monitor2 m) {
        this.m = m;
    }

    public void run() {
        while (!(m.antHash() < 2)) {
            try {
                ArrayList<HashMap<String,Subsekvens>> toHash = m.taUtTo();
                m.settInnFlettet(SubsekvensRegister.slaaSammen(toHash.remove(0),toHash.remove(0)));
            } catch (NullPointerException e) {
                return;
            }
        }
    }
}

import java.util.ArrayList;
import java.util.HashMap;

public class FletteTrad implements Runnable {
    Monitor2 m;

    public FletteTrad(Monitor2 m) {
        this.m = m;
    }

    public void run() {
        while (true) {
            ArrayList<HashMap<String,Subsekvens>> toHash = m.taUtTo();
            if (toHash == null) {
                System.out.println("ASDadsa");
                break; 
            }
            m.settInnFlettet(SubsekvensRegister.slaaSammen(toHash.remove(0),toHash.remove(0)));
        }
    }
}

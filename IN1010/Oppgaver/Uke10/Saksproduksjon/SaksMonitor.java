package IN1010.Oppgaver.Uke10.Saksproduksjon;
import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class SaksMonitor {
    ArrayList<Saks> sakser = new ArrayList<>();
    Lock laas = new ReentrantLock();

    public void settInn(Saks saks) {
        laas.lock();
        try {
            sakser.add(saks);
        } finally {
            laas.unlock();
        }
    }

    public void skrivUtAntSakser() {
        System.out.println(sakser.size());
    }
}

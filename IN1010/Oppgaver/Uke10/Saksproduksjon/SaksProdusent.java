package IN1010.Oppgaver.Uke10.Saksproduksjon;
import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class SaksProdusent implements Runnable {
    KnivMonitor knivM;
    SaksMonitor saksM;
    Lock laas;

    public SaksProdusent(KnivMonitor knivM, SaksMonitor saksM) {
        this.knivM = knivM;
        this.saksM = saksM;
        laas = new ReentrantLock();
    }

    public void run() {
        laas.lock();
        try {
            while (knivM.hentAntKniver() != 1) {
                System.out.println(knivM.hentAntKniver() + " kniver i saks monitor");
                saksM.skrivUtAntSakser();
                ArrayList<Kniv> toKniver = new ArrayList<>();
                toKniver = knivM.taUtTo();
                saksM.settInn(new Saks(toKniver));
            }
        } catch (InterruptedException e) {
        } finally {
            laas.unlock();
        }
    }
}

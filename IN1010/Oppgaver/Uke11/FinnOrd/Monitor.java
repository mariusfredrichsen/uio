import java.util.concurrent.locks.*;

public class Monitor {
    Lock laas;
    int teller = 0;

    public Monitor() {
        laas = new ReentrantLock();
    }

    public void tellOpp() {
        laas.lock();
        try {
            teller++;
        } finally {
            laas.unlock();
        }
    }

    public int oppgiAnkomster() {
        return teller;
    }
}

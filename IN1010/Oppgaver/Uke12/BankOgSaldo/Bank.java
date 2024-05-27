import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Bank {
    int beløp;
    Lock lås;
    Condition harPenger;

    public Bank() {
        this.beløp = 100;
        lås = new ReentrantLock();
        harPenger = lås.newCondition();
    }

    public Bank(int beløp) {
        this.beløp = beløp;
    }

    public void ta() throws InterruptedException {
        lås.lock();
        try {
            while (beløp <= 0) {
                harPenger.await();
            }
            this.beløp -= 100;

        } finally {
            lås.unlock();
        }
    }

    public void gi() {
        lås.lock();
        try {
            this.beløp += 100;
            harPenger.signalAll();
        } finally {
            lås.unlock();
        }
    }

    public int saldo() {
        lås.lock();
        try {
            return this.beløp;
        } finally {
            lås.unlock();
        }
    }


}

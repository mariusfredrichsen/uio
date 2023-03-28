package IN1010.Oppgaver.Uke10.BankOgSaldo;
import java.util.concurrent.locks.*;

public class Bank {
    private int penger;
    Lock laas = new ReentrantLock();
    Condition ikkeTom;

    public Bank(int startAnt) {
        penger = startAnt;
        ikkeTom = laas.newCondition();
    }


    public void gi(int ant) {
        laas.lock();
        try {
            penger += ant;
            saldo();
            ikkeTom.signal();
        } finally {
            laas.unlock();
        }
    }

    public void ta(int ant) throws InterruptedException{
        laas.lock();
        try {
            while (penger < ant) ikkeTom.await();
            penger -= ant;
            saldo();
        } finally {
            laas.unlock();
        }
    }

    public void saldo() {
        System.out.println("Det er " + penger + "kr i banken.");
    }
}

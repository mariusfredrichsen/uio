package IN1010.Oppgaver.Uke10.BankOgSaldo;

public class DaarligMedborger implements Runnable {
    Bank bank;
    private int antKjor;
    private int antBulk;
    private int teller = 0;

    public DaarligMedborger(Bank bank, int antKjor, int antBulk) {
        this.bank = bank;
        this.antKjor = antKjor;
        this.antBulk = antBulk;
    }

    public void run() {
        while (teller < antKjor) {
            try {
                bank.ta(antBulk);
                teller++;
            } catch (InterruptedException e) {}
        }
    }
}

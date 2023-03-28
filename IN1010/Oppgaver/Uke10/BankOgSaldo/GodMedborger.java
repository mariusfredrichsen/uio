package IN1010.Oppgaver.Uke10.BankOgSaldo;

public class GodMedborger implements Runnable {
    Bank bank;
    private int antKjor;
    private int antBulk;
    private int teller = 0;

    public GodMedborger(Bank bank, int antKjor, int antBulk) {
        this.bank = bank;
        this.antKjor = antKjor;
        this.antBulk = antBulk;
    }

    public void run() {
        while (teller < antKjor) {
            bank.gi(antBulk);
            teller++;
        }
    }
}

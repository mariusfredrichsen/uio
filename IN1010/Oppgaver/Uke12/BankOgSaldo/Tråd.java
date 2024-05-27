public class Tråd implements Runnable {
    Bank bank;

    public Tråd(Bank bank) {
        this.bank = bank;
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                bank.ta();
            } catch(InterruptedException e) {}
            bank.gi();
            System.out.println(bank.saldo());
        }
    }
}

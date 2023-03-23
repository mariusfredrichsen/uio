public class Person implements Runnable {
    private Bank bank;
    private final int ANTALL_TRANSAKSJONER;
    private int antall = 0;

    public Person(int ant, Bank bank) {
        ANTALL_TRANSAKSJONER = ant;
        this.bank = bank;
    }

    public void run() {
        try {
            while (antall < ANTALL_TRANSAKSJONER) {
                bank.leggInn(200);
                antall++;
                Thread.sleep((long) (500 * Math.random()));
            }
        } catch (InterruptedException e) {
            System.out.println("Noe skjedde.");
        }
    }
}

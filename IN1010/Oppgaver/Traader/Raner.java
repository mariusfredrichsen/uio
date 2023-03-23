public class Raner implements Runnable{
    private Bank bank;
    private final int ANTALL_RAN;
    private int antall = 0;
    
    public Raner(Bank bank, int ant) {
        this.bank = bank;
        ANTALL_RAN = ant;
    }

    public void run() {
        try {
            while (antall < ANTALL_RAN) {
                bank.taUt(200);
                antall++;
                Thread.sleep((long) (500 * Math.random()));
            }
        } catch (InterruptedException e) {
            System.out.println("Noe Skjedde.");
        }
        
    }
}

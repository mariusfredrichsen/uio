package IN1010.Oppgaver.Uke9;

public class Teller implements Runnable{
    int start;
    int slutt;
    int teller = 0;

    public Teller(int start, int slutt) {
        this.start = start;
        this.slutt = slutt;
    }

    public void run() {
        try {
            while(teller < slutt) {
                if (teller % 10 == start) {
                    System.out.println(teller);
                }
                teller++;
                Thread.sleep((long) (1));
            }
        } catch(InterruptedException e) {
            System.out.println("Noe gikk galt :)");
        }
    }
}

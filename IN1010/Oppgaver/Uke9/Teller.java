package IN1010.Oppgaver.Uke9;

public class Teller implements Runnable {
    private int START;
    private int SLUTT;
    int teller = 0;
    Tabell tabell;
    int id;
    
    public Teller(int start, int slutt, int id, Tabell tabell) {
        START = start;
        SLUTT = slutt;
        this.tabell = tabell;
        this.id = id;
    }

    public void run() {
        while (teller < SLUTT) {
            if (teller % 10 == START) {
                tabell.skrivUt(teller, id);
            }
            teller++;
        }
    }
    
}

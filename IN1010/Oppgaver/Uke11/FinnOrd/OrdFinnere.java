package IN1010.Oppgaver.Uke11.FinnOrd;

public class OrdFinnere implements Runnable {
    int start;
    int slutt;
    Monitor m;
    private teller = 0;

    public OrdFinnere(int start, int slutt, Monitor m) {
        this.start = start;
        this.slutt = slutt;
        this.m = m;
    }

    public void run() {
        for (int i = start; i < slutt; i++) {
            if(m.lesLinje(m.ordListe[i])) ;
        }
    }
}

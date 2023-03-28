package IN1010.Oppgaver.Uke10.Saksproduksjon;

public class KnivProdusent implements Runnable {
    KnivMonitor knivmonitor;
    private int antProdusert = 0;
    private int antSkalProduseres;

    public KnivProdusent(KnivMonitor knivmonitor, int antProduseres) {
        this.knivmonitor = knivmonitor;
        this.antSkalProduseres = antProduseres;
    }

    public void run() {
        while (antProdusert < antSkalProduseres) {
            Kniv kniv = new Kniv();
            knivmonitor.settInn(kniv);
            antProdusert++;
        }
    }
}

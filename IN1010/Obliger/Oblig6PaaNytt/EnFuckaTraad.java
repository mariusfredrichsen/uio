public class EnFuckaTraad implements Runnable {
    Kontroll kontroll;
    GUI gui;

    EnFuckaTraad(Kontroll kontroll, GUI gui) {
        this.kontroll = kontroll;
        this.gui = gui;
    }

    public void run() {
        while (true) {
            kontroll.oppdater();
            gui.oppdater(kontroll.hentCeller());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
    
}

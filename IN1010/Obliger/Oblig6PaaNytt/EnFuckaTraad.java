public class EnFuckaTraad implements Runnable {
    Kontroll kontroll;
    GUI gui;
    boolean pauset = false;

    EnFuckaTraad(Kontroll kontroll, GUI gui) {
        this.kontroll = kontroll;
        this.gui = gui;
    }

    public void pause() {
        pauset = !pauset;
    }

    public void run() {
        while (true) {
            if (!pauset) {
                kontroll.oppdater();
                gui.oppdater(kontroll.hentCeller());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
            }
        }
    }
    
}

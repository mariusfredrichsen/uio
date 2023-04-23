public class EnFuckaTraad implements Runnable {
    Kontroll kontroll;
    GUI gui;
    boolean pauset = false;
    int timer = 2000;

    EnFuckaTraad(Kontroll kontroll, GUI gui) {
        this.kontroll = kontroll;
        this.gui = gui;
    }

    public void pause() {
        pauset = !pauset;
    }

    public void oekTimer() {
        timer += 100;
    }

    public void minkTimer() {
        if (timer != 100) timer -= 100;
    }

    public void run() {
        while (true) {
            if (!pauset) {
                kontroll.oppdater();
                gui.oppdater(kontroll.hentCeller());
                try {
                    Thread.sleep(timer);
                } catch (InterruptedException e) {}
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
            }
        }
    }
    
}

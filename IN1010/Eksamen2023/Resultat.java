public class Resultat implements Runnable {

    Monitor monitor;

    public Resultat(Monitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        try {
            while (true) {
                Skinnegående s = monitor.hentNeste();
                if (s == null) {
                    return;
                }
                System.out.println(s.hentId());
            }
        } catch (InterruptedException e) {
        }
    }
}

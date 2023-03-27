package IN1010.Oppgaver.Uke10.Tallmonitor;

public class Nedover implements Runnable {
    int storstVerdi;
    Tallmonitor tallmonitor;

    public Nedover(Tallmonitor tallmonitor) {
        storstVerdi = Integer.MAX_VALUE;
        this.tallmonitor = tallmonitor;
    }

    public void run() {
        while(tallmonitor.settStorste(storstVerdi)) {
            storstVerdi--;
        }
        System.out.println(storstVerdi);
    }
}

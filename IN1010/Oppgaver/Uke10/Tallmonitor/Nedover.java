package IN1010.Oppgaver.Uke10.Tallmonitor;

public class Nedover implements Runnable {
    int storstVerdi;
    Tallmonitor tallmonitor;

    public Nedover(Tallmonitor tallmonitor) {
        storstVerdi = 10000;
        this.tallmonitor = tallmonitor;
    }

    @Override
    public void run() {
        while(tallmonitor.settStorste(storstVerdi)) {
            storstVerdi--;
        }
        System.out.println(storstVerdi);
    }
}

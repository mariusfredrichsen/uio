package IN1010.Oppgaver.Uke10.Tallmonitor;

public class Oppover implements Runnable {
    int minstVerdi;
    Tallmonitor tallmonitor;

    public Oppover(Tallmonitor tallmonitor) {
        minstVerdi = -10000;
        this.tallmonitor = tallmonitor;
    }

    @Override
    public void run() {
        while(tallmonitor.settStorste(minstVerdi)) {
            minstVerdi++;
        }
        System.out.println(minstVerdi);
    }
}

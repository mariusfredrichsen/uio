package IN1010.Oppgaver.Uke10.Tallmonitor;
import java.util.concurrent.locks.*;

public class Tallmonitor {
    int detMinste = 10000;
    int detStorste = -10000;
    Lock laas = new ReentrantLock();

    public boolean settMinste(int tall) {
        if (tall > detStorste) {
            detMinste = tall;
            System.out.println(tall);
            return true;
        }
        return false;
    }

    public boolean settStorste(int tall) {
        if (tall < detMinste) {
            detStorste = tall;
            System.out.println(tall);
            return true;
        }
        return false;
    }
}

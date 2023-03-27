package IN1010.Oppgaver.Uke10.Tallmonitor;
import java.util.concurrent.locks.*;

public class Tallmonitor {
    int detMinste = Integer.MIN_VALUE;
    int detStorste = Integer.MAX_VALUE;
    Lock laas = new ReentrantLock();

    public boolean settMinste(int tall) {
            if (tall < detStorste) {
                detMinste = tall;
                return true;
            }
            return false;
    }

    public boolean settStorste(int tall) {
            if (tall >= detMinste) {
                detStorste = tall;
                return true;
            }
            return false;
    }
}

import java.util.concurrent.locks.*;

public class HotellMonitor {
    Lock laas;
    int[] lengde;

    HotellMonitor(HotellKjede hotellKjede) {
        lengde = new int[hotellKjede.KJEDE_MAKS_ANTALL_SENGEPLASSER];
        laas = new ReentrantLock();
    }

    public void rapporterLedigRom(int[] ledige) {
        laas.lock();
        try {
            for (int i = 0; i < ledige.length; i++) {
                lengde[i] += ledige[i];
            }
        } finally {
            laas.unlock();
        }
    }

    int[] hentLedigeRom() {
        return lengde;
    }
}

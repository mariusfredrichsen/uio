public class HotellKjede {
    final int ANTALL_HOTELLER = 0;
    final int KJEDE_MAKS_ANTALL_SENGEPLASSER = 0;
    Hotell[] alleHoteller = null;

    void skrivUtLedigeRomMedTrader() {
        HotellMonitor m = new HotellMonitor(this);

        Thread[] traader = new Thread[ANTALL_HOTELLER];

        for (int i = 0; i < ANTALL_HOTELLER; i++) {
            Thread traad = new Thread(new HotellTraad(m, alleHoteller[i]));
            traader[i] = traad;
            traad.start();
        }

        for (Thread traad : traader) {
            try {
                traad.join();
            } catch(InterruptedException e) {;}
        }

        for (int antLedig : m.hentLedigeRom()) {
            System.out.println(antLedig);
        }
    }
}

public class HotellTraad implements Runnable {
    HotellMonitor m;
    Hotell h;

    HotellTraad(HotellMonitor m, Hotell h) {
        this.m = m;
        this.h = h;
    }

    @Override
    public void run() {
        int[] antLedigRom = h.ledigeRom();
        m.rapporterLedigRom(antLedigRom);
    }
}

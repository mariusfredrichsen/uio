public class Leter implements Runnable {
    Tog tog;
    String startID;
    Monitor monitor;

    public Leter(Tog tog, String startID, Monitor monitor) {
        this.tog = tog;
        this.startID = startID;
        this.monitor = monitor;
    }



    @Override
    public void run() {
        
        for (Skinnegående s : tog) {
            if (s.hentId().startsWith(startID)) {
                monitor.leggTil(s);
            }
        }
        monitor.ferdigLeting();
        
    }
}

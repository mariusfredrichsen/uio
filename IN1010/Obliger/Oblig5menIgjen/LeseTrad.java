public class LeseTrad implements Runnable {
    String filNavn;
    Monitor2 m;

    public LeseTrad(String filNavn, Monitor2 m) {
        this.filNavn = filNavn;
        this.m = m;
    }

    public void run() {
        m.settInn(SubsekvensRegister.lesFil(filNavn));
    }
}

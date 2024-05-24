public class Parkeringshus {
    private Parkeringsplass<Motorsykkel>[] etasje1;
    private Parkeringsplass<Bil>[] etasje2;

    public Parkeringshus() {
        this.etasje1 = new Parkeringsplass[10];
        this.etasje2 = new Parkeringsplass[10];
    }

}

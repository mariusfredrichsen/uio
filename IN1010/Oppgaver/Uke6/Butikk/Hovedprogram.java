public class Hovedprogram {
    public static void main(String[] args) {
        Butikk butikk = new Butikk();

        butikk.entreButikk(new Person("1", "a"));
        butikk.entreButikk(new Person("2", "b"));
        butikk.entreButikk(new Person("3", "c"));
        butikk.entreButikk(new Person("4", "d"));
        butikk.entreButikk(new Person("5", "e"));

        butikk.kassa();
    }
}

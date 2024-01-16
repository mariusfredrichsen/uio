


public class TestBåthus {
    public static void main(String[] args) {
        Båthus båthus = new Båthus(3);

        Båt b1 = new Båt("1");
        Båt b2 = new Båt("2");
        Båt b3 = new Båt("3");
        Båt b4 = new Båt("4");

        båthus.settInn(b1);
        båthus.settInn(b2);
        båthus.settInn(b3);
        båthus.settInn(b4);

        båthus.skrivBåter();
    }
}

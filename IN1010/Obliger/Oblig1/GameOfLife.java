public class GameOfLife {
    public static void main(String[] args) {
        Verden verden = new Verden(8, 12);
        verden.tegn();

        for (int i = 0; i < 20; i++) {
            verden.oppdatering();
            verden.tegn();
        }
    }
}

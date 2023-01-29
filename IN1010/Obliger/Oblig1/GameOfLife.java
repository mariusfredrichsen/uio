import java.util.Date;

public class GameOfLife {
    public static void main(String[] args) throws InterruptedException {
        Verden verden = new Verden(8, 12);
        verden.tegn();

        for (int i = 0; i < 20; i++) {
            Thread.sleep(500);
            verden.oppdatering();
            verden.tegn();
        }
    }
}

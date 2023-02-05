public class GameOfLife {
    public static void main(String[] args) throws InterruptedException {
        Verden verden = new Verden(8, 12); //lager verden
        verden.tegn();

        for (int i = 0; i < 20; i++) { //Oppdaterer verden 20 ganger med 0.5s intervaller
            Thread.sleep(500);
            verden.oppdatering();
            verden.tegn();
        }
    }
}

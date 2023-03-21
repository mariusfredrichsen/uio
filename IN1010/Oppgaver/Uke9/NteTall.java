package IN1010.Oppgaver.Uke9;

public class NteTall {
    public static void main(String[] args) {
        Teller etEllerAnnet = new Teller(5, 1000);
        new Thread(etEllerAnnet).start();
    }
}

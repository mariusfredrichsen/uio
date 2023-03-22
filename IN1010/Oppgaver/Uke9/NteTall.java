package IN1010.Oppgaver.Uke9;

public class NteTall {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Teller etEllerAnnet = new Teller(i, 1000);
            new Thread(etEllerAnnet).start();
        }
    }
}

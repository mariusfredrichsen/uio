package IN1010.Oppgaver.Uke9;

public class NteTall {
    public static void main(String[] args) {
        Tabell tabell = new Tabell();
        for (int i = 0; i < 10; i++) {
            new Thread(new Teller(i, 10000, i, tabell)).start();
        }
    }
}

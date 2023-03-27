package IN1010.Oppgaver.Uke10.Tallmonitor;

public class TallmonitorHoved {
    public static void main(String[] args) {
        Tallmonitor tallmonitor = new Tallmonitor();
        new Thread(new Nedover(tallmonitor)).start();
        new Thread(new Oppover(tallmonitor)).start();
    }
}

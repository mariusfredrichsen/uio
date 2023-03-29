package IN1010.Oppgaver.Uke10.Tallmonitor;

public class TallmonitorHoved {
    public static void main(String[] args) { //FUNKER IKKE :D:D:D:D:D
        Tallmonitor tallmonitor = new Tallmonitor();
        new Thread(new Oppover(tallmonitor)).start();
        new Thread(new Nedover(tallmonitor)).start();
    }
}

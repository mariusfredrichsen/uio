package IN1010.Oppgaver.BilerOgBusser;

public class Varebil extends Kjoretoy{
    int maxLastevekt;
    public Varebil(String registernummer, String fabrikkmerke, String eier, int maxLastevekt) {
        super(registernummer, fabrikkmerke, eier);
        this.maxLastevekt = maxLastevekt;
    }

    public void aarsavgift() {
        System.out.println("aarsavgiften er paa " + maxLastevekt*4);
    }
}
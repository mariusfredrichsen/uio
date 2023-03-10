package IN1010.Oppgaver.Uke4.BilerOgBusser;

public class Personbil extends Kjoretoy{
    int antSitteplasser;
    public Personbil(String registernummer, String fabrikkmerke, String eier, int antSitteplasser) {
        super(registernummer, fabrikkmerke, eier);
        this.antSitteplasser = antSitteplasser;
    }

    public void aarsavgift() {
        System.out.println("aarsavgiften er paa 3000");
    }
}

package IN1010.Oppgaver.BilerOgBusser;

public class Buss extends Kjoretoy{
    int antSitteplasser;
    public Buss(String registernummer, String fabrikkmerke, String eier, int antSitteplasser) {
        super(registernummer, fabrikkmerke, eier);
        this.antSitteplasser = antSitteplasser;
    }
}

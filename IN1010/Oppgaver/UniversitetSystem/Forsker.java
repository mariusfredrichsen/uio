package IN1010.Oppgaver.UniversitetSystem;

public class Forsker extends Ansatt {
    String fagområde;

    public Forsker(String navn, int alder, int timeLønn, String fagområde) {
        super(navn, alder, timeLønn);
        this.fagområde = fagområde;
    }
}
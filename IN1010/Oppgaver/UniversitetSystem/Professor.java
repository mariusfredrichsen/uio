package IN1010.Oppgaver.UniversitetSystem;

public class Professor extends Forsker {
    String kurs;

    public Professor(String navn, int alder, int timeLønn, String fagområde, String kurs) {
        super(navn, alder, timeLønn, fagområde);
        this.kurs = kurs;
    }
}

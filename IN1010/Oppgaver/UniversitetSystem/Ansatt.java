package IN1010.Oppgaver.UniversitetSystem;

public class Ansatt extends Person {
    int timeLønn;

    public Ansatt(String navn, int alder, int timeLønn) {
        super(navn, alder);
        this.timeLønn = timeLønn;
    }
}

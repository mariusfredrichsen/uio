package IN1010.Oppgaver.Uke4.UniversitetSystem;

public class Ansatt extends Person {
    int timeLønn;

    public Ansatt(String navn, int alder, int timeLønn) {
        super(navn, alder);
        this.timeLønn = timeLønn;
    }
}

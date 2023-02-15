package IN1010.Oppgaver.UniversitetSystem;

public class BachelorStudent extends Student{
    boolean tilgang = false;

    public BachelorStudent (String navn, int alder, String lærested, String[] kurs) {
        super(navn, alder, lærested, kurs);
    }

    public boolean harTilgang() {
        return tilgang;
    }
}

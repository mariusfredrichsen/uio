package IN1010.Oppgaver.Uke4.UniversitetSystem;

public class MasterStudent extends Student {
    boolean tilgang = true;

    public MasterStudent(String navn, int alder, String lærested, String[] kurs) {
        super(navn, alder, lærested, kurs);
    }

    public boolean harTilgang() {
        return tilgang;
    }
}

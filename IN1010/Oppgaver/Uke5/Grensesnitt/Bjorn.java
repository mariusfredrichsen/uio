package IN1010.Oppgaver.Uke5.Grensesnitt;

public class Bjorn implements Rovdyr {
    public void jakt(Planteetere planteetere) {
        System.out.println("Jeg jakter på " + planteetere);
    }

    public String toString() {
        return "Bjorn";
    }
}

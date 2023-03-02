package IN1010.Oppgaver.Grensesnitt;

public class Elg implements Planteetere {
    public void beskytt(Rovdyr rovdyr) {
        System.out.println("Jeg beskytter meg mot " + rovdyr);
    }

    public String toString() {
        return "Elg";
    }
}

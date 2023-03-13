package IN1010.Oppgaver.Uke5.Grensesnitt;

public class Sau implements Planteetere {
    public void beskytt(Rovdyr rovdyr) {
        System.out.println("Jeg beskytter meg mot " + rovdyr);
    }

    public String toString() {
        return "Sau";
    }
}

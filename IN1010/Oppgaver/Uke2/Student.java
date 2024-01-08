


public class Student {
    String navn;
    int quizScore;
    int antQuiz;

    public Student(String navn) {
        this.navn = navn;
        this.quizScore = 0;
        this.antQuiz = 0;
    }

    public String hentNavn() {
        return navn;
    }

    public void leggTilQuizScore(int score) {
        quizScore += score;
        antQuiz++;
    }

    public int hentTotalScore() {
        return quizScore;
    }

    public float hentgjennomsnittligScore() {
        return quizScore / antQuiz;
    }
}

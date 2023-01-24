package IN1010.Oppgaver.Quiz;

public class Person {
    String navn;
    int quiz_score;
    int ant_quiz;

    public Person(String navn, int quiz_score, int ant_quiz) {
        this.navn = navn;
        this.quiz_score = quiz_score;
        this.ant_quiz = ant_quiz;
    }

    public String hentNavn() {
        return navn;
    }

    public void leggTilQuizScore(int score) {
        quiz_score += score;
        ant_quiz++;
    }

    public int hentTotalScore() {
        return quiz_score;
    }

    public int hentGjennomsnittligScore() {
        return (quiz_score/ant_quiz);
    }
}

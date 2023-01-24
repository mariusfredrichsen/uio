package IN1010.Oppgaver.Quiz;

public class Hovedprogram {
    public static void main(String[] args) {
        Person joakim = new Person("Joakim", 0, 0);
        Person kristin = new Person("Kristin", 0, 0);
        Person dag = new Person("Dag", 0, 0);
    
        joakim.leggTilQuizScore(40);
        joakim.leggTilQuizScore(30);
        dag.leggTilQuizScore(70);
        dag.leggTilQuizScore(90);
        kristin.leggTilQuizScore(30);
        kristin.leggTilQuizScore(10);

        System.out.println(joakim.hentGjennomsnittligScore());
        System.out.println(dag.hentGjennomsnittligScore());
        System.out.println(kristin.hentGjennomsnittligScore());
    }
}

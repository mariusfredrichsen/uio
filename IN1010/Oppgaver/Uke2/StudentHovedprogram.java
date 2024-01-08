


public class StudentHovedprogram {
    public static void main(String[] args) {
        Student s1 = new Student("Joakim");
        Student s2 = new Student("Kristin");
        Student s3 = new Student("Dag");

        s1.leggTilQuizScore(1);
        s1.leggTilQuizScore(2);
        s2.leggTilQuizScore(3);
        s2.leggTilQuizScore(4);
        s3.leggTilQuizScore(5);
        s3.leggTilQuizScore(6);

        System.out.println(s1.hentTotalScore());
        System.out.println(s1.hentgjennomsnittligScore());
        System.out.println(s2.hentTotalScore());
        System.out.println(s2.hentgjennomsnittligScore());
        System.out.println(s3.hentTotalScore());
        System.out.println(s3.hentgjennomsnittligScore());
        

    }
}

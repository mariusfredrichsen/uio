package IN1010.Oppgaver.UniversitetSystem;

abstract public class Student extends Person {
    String lærested;
    String[] kurs;

    public Student(String navn, int alder, String lærested, String[] kurs) {
        super(navn, alder);
        this.lærested = lærested;
        this.kurs = kurs;
    }
}

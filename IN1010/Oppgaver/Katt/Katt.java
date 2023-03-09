package IN1010.Oppgaver.Katt;

public class Katt implements Comparable<Katt>{
    String name;
    int age;

    public Katt (String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Katt cat) {
        return age - cat.age;
    }

    public String toString() {
        return "Katten heter: " + name + " og alderen er: " + age;
    }

}

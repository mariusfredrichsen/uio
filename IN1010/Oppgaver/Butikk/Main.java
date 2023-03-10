package IN1010.Oppgaver.Butikk;

public class Main {
    public static void main(String[] args) {
        Butikk butikk = new Butikk();

        butikk.entreButikk(new Person("Benjamin", "Hva mener du?"));
        butikk.entreButikk(new Person("David Andreas", "Lego Lord of The Rings Kløvendal"));
        butikk.entreButikk(new Person("JC", "Buttpluggs"));
        butikk.entreButikk(new Person("Marius", "Fisk"));
        butikk.entreButikk(new Person("Albert", "Frokost"));

        for (Person person : butikk) {
            System.out.println(person);
        }
    }
}

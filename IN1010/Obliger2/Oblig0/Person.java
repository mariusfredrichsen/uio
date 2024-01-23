

public class Person {
    Bil3 bil;

    public Person(Bil3 bil) {
        this.bil = bil;
    }

    public void skrivUt() {
        System.out.println(String.format("Jeg er en person og har bil med bilnummer: %s", bil.hentNummer()));
    }
}

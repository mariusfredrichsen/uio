package IN1010.Oppgaver.Uke7.Butikk;

public class Person {
    String navn;
    String data;
    public Person neste;

    public Person(String navn, String data) {
        this.navn = navn;
        this.data = data;
    }

    public String toString() {
        return "Navn: " + navn + " ønsker å kjøpe: " + data;
    }
}
public class Person {
    private String navn;
    private String gjenstand;
    private Person neste;

    public Person(String navn, String gjenstand) {
        this.navn = navn;
        this.gjenstand = gjenstand;
        this.neste = null;
    }

    public void settInn(Person neste) {
        this.neste = neste;
    }

    public Person hent() {
        Person tmp = this.neste;
        this.neste = null
        return tmp;
    }


}
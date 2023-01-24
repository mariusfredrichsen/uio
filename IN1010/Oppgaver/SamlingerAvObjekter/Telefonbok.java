package IN1010.Oppgaver.SamlingerAvObjekter;
import java.util.ArrayList;

public record Telefonbok() {
    public static void main(String[] args) {
        Person viljar = new Person("Viljar", "90935103", "Eidsvoll");
        Person erlend = new Person("Erlend", "48023262", "Oslo");
        Person marius = new Person("Marius", "94141698", "Olav m. Troviksvei 14");

        /*Person[] personer;
        personer = new Person[10];

        personer[0] = viljar;
        personer[1] = erlend;
        personer[2] = marius;

        for (int i = 0; i < 10; i++) {
            if (personer[i] != null) {
                personer[i].skrivInfo();
            }
        }*/
        
        ArrayList<Person> personer =new ArrayList<Person>();
        personer.add(viljar);
        personer.add(erlend);
        personer.add(marius);

        for (Person person : personer) {
            person.skrivInfo();
        }

    }
}

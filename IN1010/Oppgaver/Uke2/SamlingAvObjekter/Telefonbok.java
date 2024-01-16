import java.util.ArrayList;
import java.util.HashMap;

public class Telefonbok {
    public static void main(String[] args) {
        Person p1 = new Person("En", "1", "1.");
        Person p2 = new Person("To", "2", "2.");
        Person p3 = new Person("Tre", "3", "3.");

        // oppgave b
        Person[] personer1 = new Person[10];
        personer1[0] = p1;
        personer1[1] = p2;
        personer1[2] = p3;
        
        for (int i = 0; i < personer1.length; i++) {
            if (personer1[i] != null) {
                personer1[i].skrivInfo();
            }
        }

        // oppgave c
        ArrayList<Person> personer2 = new ArrayList<Person>();
        personer2.add(p1);
        personer2.add(p2);
        personer2.add(p3);

        for (Person person : personer2) person.skrivInfo();

        // oppgave d
        HashMap<String,Person> personer3 = new HashMap<String,Person>();
        personer3.put(p1.hentNavn(),p1);
        personer3.put(p2.hentNavn(),p2);
        personer3.put(p3.hentNavn(),p3);

        for (Person person : personer3.values()) person.skrivInfo();
    }
}

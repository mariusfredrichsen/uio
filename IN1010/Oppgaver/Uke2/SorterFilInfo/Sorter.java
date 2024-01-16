import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Sorter {
    public static void main(String[] args) {
        File fil = null;
        Scanner scan = null;
        try {
            fil = new File("navn.txt");
            scan = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }

        ArrayList<Person> personer = new ArrayList<Person>();
        ArrayList<Hund> hunder = new ArrayList<Hund>();

        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split(" ");
            if (line[0].equals("P")) {
                personer.add(new Person(line[1]));
            } else if (line[0].equals("H")) {
                hunder.add(new Hund(line[1]));
            }
        }

        System.out.println("Personer:");
        for (Person person : personer) {
            System.out.println(person.hentNavn());
        }

        System.out.println("\nHunder:");
        for (Hund hund : hunder) {
            System.out.println(hund.hentNavn());
        }
    }
}

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class Sorter {
  public static void main(String[] args) throws FileNotFoundException {
    File fil = new File("navn.txt");
    Scanner scan = new Scanner(fil);

    ArrayList<Hund> hunder = new ArrayList<>();
    ArrayList<Person> personer = new ArrayList<>();

    while (scan.hasNextLine()) {
      String linje_deler[] = scan.nextLine().split(" ");
      if (linje_deler[0].equals("H")) {
        hunder.add(new Hund(linje_deler[1]));
      } else if (linje_deler[0].equals("P")) {
        personer.add(new Person(linje_deler[1]));
      }
    }

    for (Hund hund : hunder) {
      System.out.println("Hunde navn " + hund.hent_navn());
    }
    for (Person person : personer) {
      System.out.println("Person navn: " + person.hent_navn());
    }
  }
}
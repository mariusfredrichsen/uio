package IN1010.Oppgaver.Studentsystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Studentsystem {
    HashMap<String, ArrayList<String>> emneStudenter = new HashMap<>();

    public void lesFil(String filnavn) throws FileNotFoundException {
        File fil = new File(filnavn);
        Scanner scan = new Scanner(fil);
        String fag = "0";
        String linje;

        while (scan.hasNextLine()) {
            linje = scan.nextLine();
            if (linje.substring(0,1).equals("*")) {
                fag = linje.substring(1);
                emneStudenter.put(fag, new ArrayList<String>());
            } else {
                emneStudenter.get(fag).add(linje);
            }
        }
    }

}

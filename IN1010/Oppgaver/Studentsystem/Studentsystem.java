package IN1010.Oppgaver.Studentsystem;

import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Studentsystem {
    HashMap<String, ArrayList<String>> emneStudenter = new HashMap<>();

    public void lesFil(String filnavn) throws FileNotFoundException {
        File fil = new File(filnavn);
        Scanner scan = new Scanner(fil);

        while (scan.hasNextLine()) {
            String linje = scan.nextLine();
            if (linje.substring(0,1) == "*") {
                emneStudenter.put(linje.substring(1), new ArrayList<>());
            }
        }
    }

    public void mestStudenter() {

    }

    public void mestFag() {

    }

    public void alleSomTarEtFag() {

    }

    public void leggTilStudent(String student, String fag) {

    }

    public void fjernStudent(String student, String fag) {

    }

    public void leggTilFag(String fag) {

    }
}
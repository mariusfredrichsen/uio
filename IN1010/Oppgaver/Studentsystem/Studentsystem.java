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
        String fag = "";

        while (scan.hasNextLine()) {
            String linje = scan.nextLine();
            if (linje.substring(0,1).equals("*")) {
                fag = linje.substring(1);
                ArrayList<String> studenter = new ArrayList<>();
                emneStudenter.put(fag, studenter);
            } else {
                ArrayList<String> studenter = emneStudenter.get(fag);
                studenter.add(linje);
                emneStudenter.put(fag, studenter);
            }
        }
    }

    public void mestStudenter() {
        String stoerstFag = "";
        int antStudenter = 0;

        for (String fag : emneStudenter.keySet()) {
            if (emneStudenter.get(fag).size() > antStudenter) {
                stoerstFag = fag;
                antStudenter = emneStudenter.get(fag).size();
            }
        }

        System.out.println("Faget med flest studenter er " + stoerstFag);
    }

    public void mestFag() {
        ArrayList<String> alleStudenter = new ArrayList<>();
        for (String fag : emneStudenter.keySet()) {
            for (String student : emneStudenter.get(fag)) {
                alleStudenter.add(student);
            }
        }

        HashMap<String,Integer> teller = new HashMap<>();
        for (String student : alleStudenter) {
            if (teller.containsKey(student)) {
                teller.put(student, teller.get(student)+1);
            } else {
                teller.put(student, 1);
            }
        }
        
        int antFag = 0;
        String studentMestFag = "";
        for (String student : teller.keySet()) {
            if (teller.get(student) > antFag) {
                studentMestFag = student;
                antFag = teller.get(student);
            }
        }

        System.out.println("Studenten med flest fag er " + studentMestFag + " med " + antFag + " antall fag");
    }

    public void alleSomTarEtFag() {
        ArrayList<String> alleStudenter = new ArrayList<>();
        for (String fag : emneStudenter.keySet()) {
            for (String student : emneStudenter.get(fag)) {
                alleStudenter.add(student);
            }
        }

        HashMap<String,Integer> teller = new HashMap<>();
        for (String student : alleStudenter) {
            if (teller.containsKey(student)) {
                teller.put(student, teller.get(student)+1);
            } else {
                teller.put(student, 1);
            }
        }

        ArrayList<String> studenterEtFag = new ArrayList<>();
        for (String student : teller.keySet()) {
            if (teller.get(student) == 1) {
                studenterEtFag.add(student);
            }
        }

        System.out.println("Studenter med et fag er:");
        for (String student : studenterEtFag) {
            System.out.println(student);
        }
    }

    public void leggTilStudent(String student, String fag) {
        ArrayList<String> alleStudenter = new ArrayList<>();
        for (String fag1 : emneStudenter.keySet()) {
            for (String student1 : emneStudenter.get(fag1)) {
                alleStudenter.add(student1);
            }
        }

        if (emneStudenter.containsKey(fag) && alleStudenter.contains(student)) {
            ArrayList<String> studenter = emneStudenter.get(fag);
            if (!(studenter.contains(student))) {
                studenter.add(student);
                emneStudenter.put(fag, studenter);
            } else {
                System.out.println("denne studenten tar allerede dette faget.");
            }
        } else if (emneStudenter.containsKey(fag)) {
            System.out.println("studenten eksisterer ikke.");
        } else {
            System.out.println("faget eksisterer ikke.");
        }
    }

    public void fjernStudent(String student, String fag) {
        ArrayList<String> alleStudenter = new ArrayList<>();
        for (String fag1 : emneStudenter.keySet()) {
            for (String student1 : emneStudenter.get(fag1)) {
                alleStudenter.add(student1);
            }
        }

        if (emneStudenter.containsKey(fag) && alleStudenter.contains(student)) {
            ArrayList<String> studenter = emneStudenter.get(fag);
            if ((studenter.contains(student))) {
                studenter.remove(student);
                emneStudenter.put(fag, studenter);
            } else {
                System.out.println("denne studenten tar allerede dette faget.");
            }
        } else if (emneStudenter.containsKey(fag)) {
            System.out.println("studenten eksisterer ikke.");
        } else {
            System.out.println("faget eksisterer ikke.");
        }
    }

    public void leggTilFag(String fag) {
        ArrayList<String> studenter = new ArrayList<>();
        emneStudenter.put(fag, studenter);
    }
}
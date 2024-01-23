import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class Studentsystem {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> emneStudent = new HashMap<String, ArrayList<String>>();
        ArrayList<String> studenterListe = new ArrayList<String>();

        Scanner scan = null;
        File fil = null;

        try {
            fil = new File("emnestudenter.txt");
            scan = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Fil ikke funnet");
        }

        String emne = "";
        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            if (line.startsWith("*")) {
                emne = line.substring(1);
                emneStudent.put(emne, new ArrayList<String>());
                continue;
            } else {
                emneStudent.get(emne).add(line);
                if (!studenterListe.contains(line)) studenterListe.add(line);
            }
        }
        scan = new Scanner(System.in);

        System.out.println(emneStudent);
        String input = "";
        while (!input.equals("q")) {
            System.out.println("""
                    1. Fag med flest studenter
                    2. Student med flest fag
                    3. Skriv ut alle studentene som tar et fag
                    4. Skrive ut alle fag en student tar
                    5. Legge til en student til et fag
                    q. Avslutt
                    """);
            input = scan.nextLine();
            if (input.equals("1")) {
                int max = 0;
                String eOut = "";
                for (String e : emneStudent.keySet()) {
                    if (max < emneStudent.get(e).size()) {
                        max = emneStudent.get(e).size();
                        eOut = e;
                    }
                }
                System.out.println(eOut);
            } else if (input.equals("2")) {
                HashMap<String,Integer> studenter = new HashMap<String,Integer>();
                for (String e : emneStudent.keySet()) {
                    for (String student : emneStudent.get(e)) {
                        if (studenter.containsKey(student)) {
                            studenter.put(student, studenter.get(student)+1);
                        }
                    }
                }

                int max = 0;
                String sOut = "";
                for (String student : studenter.keySet()) {
                    if (studenter.get(student) > max) {
                        sOut = student;
                    }
                }
                System.out.println(sOut);

            } else if (input.equals("3")) {
                for (String e : emneStudent.keySet()) System.out.println(e);
                System.out.println("Skriv inn en fagkose");
                String userInput = scan.nextLine();
                if (emneStudent.containsKey(userInput)) {
                    for (String s : emneStudent.get(userInput)) System.out.println(s);
                }
            } else if (input.equals("4")) {
                for (String student : studenterListe) {
                    System.out.println(student);
                }
                System.out.println("Skriv et navn");
                scan = new Scanner(System.in);
                input = scan.nextLine();
                HashMap<String,Integer> studenter = new HashMap<String,Integer>();
                if (studenterListe.contains(input)) {
                    for (String e : emneStudent.keySet()) {
                        if (studenter.containsKey(emneStudent.get(e))) {
                            
                        }
                    }
                }
            }
        }
    }
}

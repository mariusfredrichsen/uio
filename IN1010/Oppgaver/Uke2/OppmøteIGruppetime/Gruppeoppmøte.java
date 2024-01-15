import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Gruppeoppmøte {
    private String[] navn = new String[14];
    private boolean[] oppmøte = new boolean[14];

    // Fyll inn med metodene nevnt over.
    public void lesFraFil() {
        Scanner scan = null;
        File fil = null;

        try {
            fil = new File("gruppeliste.txt");
            scan = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }

        int i = 0;
        while (scan.hasNextLine() && i < navn.length) {
            navn[i] = scan.nextLine();
            oppmøte[i++] = false;
        }

        scan.close();
    }

    public void registrerStudent() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Skriv navn på student");
        String n = scan.nextLine();

        for (int i = 0; i < navn.length; i++) {
            if (navn[i].equals(n)) {
                oppmøte[i] = true;
            }
        }
    }

    public void skrivUtOppmøte() {
        for (int i = 0; i < navn.length; i++) {
            System.out.print(navn[i]);
            if (oppmøte[i]) {
                System.out.println(" har møtt opp");
            } else {
                System.out.println(" har ikke møtt opp");
            }
        }
    }
}
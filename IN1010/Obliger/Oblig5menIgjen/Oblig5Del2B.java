import java.io.FileNotFoundException;
import java.lang.InterruptedException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Oblig5Del2B {
    public static void main(String[] args) {
        SubsekvensRegister s = new SubsekvensRegister();
        Monitor2 m = new Monitor2(s);
        
        Scanner scan = null;
        try {
            scan = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }

        ArrayList<Thread> leseTraader = new ArrayList<>();
        while (scan.hasNextLine()) {
            LeseTrad leseTrad = new LeseTrad(args[0] + "/" + scan.nextLine(), m);
            Thread t = new Thread(leseTrad);
            leseTraader.add(t);
            t.start();
        }

        
        for (Thread t : leseTraader) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Noe skjedde.");
            }
        }
        System.out.println("Ferdig med aa lese");

        int antallFlettere = 8;
        ArrayList<Thread> fletteTraader = new ArrayList<>();
        for (int i = 0; i < antallFlettere; i++) {
            FletteTrad fletteTrad = new FletteTrad(m);
            Thread trad = new Thread(fletteTrad);
            fletteTraader.add(trad);
            trad.start();
        }

        for (Thread t : fletteTraader) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Noe skjedde");
            }
        }

        HashMap<String,Subsekvens> enHash = m.taUt();

        int maxTeller = 0;
        Subsekvens peker = null;
        for (Subsekvens sub : enHash.values()) {
            if (sub.hentAntall() > maxTeller) {
                maxTeller = sub.hentAntall();
                peker = sub;
            }
        }

        System.out.println(peker);

    }
}

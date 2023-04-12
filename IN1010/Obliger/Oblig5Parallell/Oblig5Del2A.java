import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Oblig5Del2A {
    public static void main(String[] args) {
        SubsekvensRegister s = new SubsekvensRegister();
        Monitor2 m = new Monitor2(s);
        
        Scanner scan = null;
        try {
            scan = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }

        int teller = -1;
        ArrayList<Thread> traader = new ArrayList<>();
        while (scan.hasNextLine()) {
            LeseTrad leseTrad = new LeseTrad(args[0] + "/" + scan.nextLine(), m);
            Thread t = new Thread(leseTrad);
            traader.add(t);
            t.start();
            teller++;
        }

        for (Thread t : traader) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Noe skjedde.");
            }
        }

        for (int i = 0; i < teller; i++) {
            s.settInn(SubsekvensRegister.slaaSammen(s.taUt(), s.taUt()));
        }

        int maxTeller = 0;
        Subsekvens peker = null;
        for (Subsekvens sub : s.taUt().values()) {
            if (sub.hentAntall() > maxTeller) {
                maxTeller = sub.hentAntall();
                peker = sub;
            }
        }

        System.out.println(peker);

    }
}

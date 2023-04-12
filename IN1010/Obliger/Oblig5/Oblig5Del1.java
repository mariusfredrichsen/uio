import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

public class Oblig5Del1 {
    public static void main(String[] args) {
        SubsekvensRegister s = new SubsekvensRegister();
        
        Scanner scan = null;
        try {
            scan = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }

        int teller = -1;
        while (scan.hasNextLine()) {
            HashMap<String,Subsekvens> hash = SubsekvensRegister.lesFil(args[0] + "/" + scan.nextLine());
            s.settInn(hash);
            teller++;
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

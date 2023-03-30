import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Oblig5Del2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("metadata.csv"));
        SubsekvensRegister s = new SubsekvensRegister();
        
        int teller = 0;
        while (scan.hasNextLine()) {
            teller++;
            scan.nextLine();
        }
        CountDownLatch barriere1 = new CountDownLatch(teller);
        CountDownLatch barriere2 = new CountDownLatch(teller - 1);
        Monitor2 m = new Monitor2(s, barriere2);

        scan = new Scanner(new File("metadata.csv"));
        while (scan.hasNextLine()) {
            new Thread(new LeseTrad(scan.nextLine(), m, barriere1)).start();
        }

        try {
            barriere1.await();
        } catch (InterruptedException e ) {
            System.out.println("Noe skjedde.");
        }

        for (int i = 0; i < 8; i++) {
            new Thread(new FletteTrad(m, barriere2)).start();
        }

        try {
            System.out.println("Venter");
            barriere2.await();
        } catch (InterruptedException e) {
            System.out.println("Noe skjedde.");
        }

        System.out.println(s.hentAnt());

        int antall = 0;
        Subsekvens peker = null;
        for (Subsekvens subS : s.taUt().values()) { //tar den siste hashmapen og leser av den
            if (subS.hentAntall() > antall) {
                antall = subS.hentAntall();
                peker = subS;
            }
        }
        System.out.println(peker);
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Worlde {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(args[1]));
        String onsketOrd = args[0];
        int antLinjer = Integer.parseInt(scan.nextLine());
        String[] ordListe = new String[antLinjer];
        
        int teller = 0;
        while (scan.hasNext()) {
            ordListe[teller] = scan.nextLine();
            teller++;
        }
        
        int deleTeller = 8;
        for (int i = deleTeller; i < antLinjer; i++) {
            if (antLinjer % deleTeller == 0) {
                break;
            }
            deleTeller++;
        }
    
        CountDownLatch barriere = new CountDownLatch(deleTeller);
        Monitor monitor = new Monitor();

        for (int i = 0; i < deleTeller; i++) {
            OrdFinnere ordFinner = new OrdFinnere(i * antLinjer/deleTeller, ((i+1) * antLinjer/deleTeller - 1), monitor, onsketOrd, ordListe, barriere);
            Thread t = new Thread(ordFinner);
            t.start();
        }

        try {
            barriere.await();
            System.out.println(monitor.oppgiAnkomster());
        } catch (InterruptedException e) {
            System.out.println("Noe gikk galt");
        }

    }
}

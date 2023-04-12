import java.io.FileNotFoundException;
import java.lang.InterruptedException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class Oblig5Hele {
    public static void main(String[] args) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {}
        
        int tellerSyk = 0;
        int tellerFrisk = 0;
        while (scan.hasNextLine()) {
            if (scan.nextLine().split(",")[1].equals("True")) tellerSyk++;
            else tellerFrisk++;
        }

        CountDownLatch barriereSyk = new CountDownLatch(tellerSyk-1);
        CountDownLatch barriereFrisk = new CountDownLatch(tellerFrisk-1);

        SubsekvensRegister sykS = new SubsekvensRegister();
        Monitor2 sykM = new Monitor2(sykS);
        
        SubsekvensRegister friskS = new SubsekvensRegister();
        Monitor2 friskM = new Monitor2(friskS);
        
        try {
            scan = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }

        System.out.println("Leser og fletter filer");

        int antallFlettere = 8;
        int traadTeller = 0;
        ArrayList<Thread> traader = new ArrayList<>();
        while (scan.hasNextLine()) {
            if (traadTeller++ < antallFlettere * 2) {
                if (traadTeller % 2 == 0) {
                    Thread tS = new Thread(new FletteTrad(sykM, barriereSyk));
                    traader.add(tS);
                    tS.start();
                } else {
                    Thread tF = new Thread(new FletteTrad(friskM, barriereFrisk));
                    traader.add(tF);
                    tF.start();
                }
            }
            String[] linje = scan.nextLine().split(",");
            if (Boolean.parseBoolean(linje[1])) { //har viruset
                LeseTrad leseTrad = new LeseTrad(args[0] + "/" + linje[0], sykM);
                Thread t = new Thread(leseTrad);
                traader.add(t);
                t.start();
            } else if (!Boolean.parseBoolean(linje[1])) { //har ikke viruset
                LeseTrad leseTrad = new LeseTrad(args[0] + "/" + linje[0], friskM);
                Thread t = new Thread(leseTrad);
                traader.add(t);
                t.start();
            }
        }
        
        for (Thread t : traader) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Noe skjedde.");
            }
        }

        System.out.println("Ferdig med aa lese og flette");

        HashMap<String,Subsekvens> sykHash = sykM.taUt();
        HashMap<String,Subsekvens> friskHash = friskM.taUt();
        
        HashMap<String,Subsekvens> samHash = new HashMap<>();

        for (String subsekvens : sykHash.keySet()) { //Setter inn alle subsekvenser i sykHash som har 7 flere forekomster enn de friske sinn inn i samHash
            if (friskHash.containsKey(subsekvens)) {
                if (sykHash.get(subsekvens).hentAntall() >= friskHash.get(subsekvens).hentAntall() + 7) {
                    samHash.put(subsekvens, sykHash.get(subsekvens));
                }
            } else if (sykHash.get(subsekvens).hentAntall() >= 7) {
                samHash.put(subsekvens, sykHash.get(subsekvens));
            }
        }
        
        System.out.println("Subsekvenser i de syke sitt som dukker opp 7ganger mer enn i de friskes sine:");
        for (Subsekvens sub : samHash.values()) {
            System.out.println(sub);
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;

public class Oblig5Del2 {
    public static void main(String[] args) {
        double startTime = System.currentTimeMillis();
        Scanner scan = null;
        try {
            scan = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {}
        
        int antSyke = 0;
        int antFrisk = 0;
        while(scan.hasNextLine()) {
            String[] linje = scan.nextLine().split(",");
            if (Boolean.parseBoolean(linje[1])) antSyke++;
            else if (!Boolean.parseBoolean(linje[1])) antFrisk++;
        }

        System.out.println(antSyke + " syke");
        System.out.println(antFrisk + " friske");
        
        CountDownLatch barrierLese = new CountDownLatch(antSyke + antFrisk);
        CountDownLatch barrierFletteS = new CountDownLatch(antSyke-1);
        CountDownLatch barrierFletteF = new CountDownLatch(antFrisk-1);

        SubsekvensRegister sykS = new SubsekvensRegister();
        Monitor2 sykM = new Monitor2(sykS, barrierFletteS);

        SubsekvensRegister friskS = new SubsekvensRegister();
        Monitor2 friskM = new Monitor2(friskS, barrierFletteF);

        try {
            scan = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {}

        while(scan.hasNextLine()) {
            String[] linje = scan.nextLine().split(",");
            if (Boolean.parseBoolean(linje[1])) { //har viruset
                new Thread(new LeseTrad(args[0] + "/" + linje[0], sykM, barrierLese)).start();
            } else if (!Boolean.parseBoolean(linje[1])) { //har det ikke
                new Thread(new LeseTrad(args[0] + "/" + linje[0], friskM, barrierLese)).start();
            }
        }

        try {
            System.out.println("Venter paa lese traader");
            barrierLese.await();
        } catch (InterruptedException e) {}

        ArrayList<Thread> traadListeS = new ArrayList<>();
        ArrayList<Thread> traadListeF = new ArrayList<>();
        int antallFlettere = 8;
        for (int i = 0; i < antallFlettere * 2; i++) {
            if (i % 2 == 0) {
                Thread tS = new Thread(new FletteTrad(sykM, barrierFletteS));
                traadListeS.add(tS);
                tS.start();
            } else {
                Thread tF = new Thread(new FletteTrad(friskM, barrierFletteF));
                traadListeS.add(tF);
                tF.start();
            }
        }
        try {
            for (Thread t : traadListeS) {
                t.join();
            }
            for (Thread t : traadListeF) {
                t.join();
            }
        } catch (InterruptedException e) {}

        try {
            System.out.println("Venter paa flette traader for syke");
            System.out.println("Venter paa flette traader for friske");
            barrierFletteS.await();
            barrierFletteF.await();
        } catch (InterruptedException e) {}

        HashMap<String,Subsekvens> sykHash = sykM.taUt();
        HashMap<String,Subsekvens> friskHash = friskM.taUt();
        HashMap<String,Subsekvens> samHash = new HashMap<>();

        for (String subsekvens : sykHash.keySet()) {
            samHash.put(subsekvens,sykHash.get(subsekvens));
            if (friskHash.containsKey(subsekvens)) {
                samHash.get(subsekvens).senkAntall(friskHash.get(subsekvens).hentAntall());
            }
        }

        Subsekvens peker = null;
        int flestForekomst = 0;
        for (Subsekvens sub : samHash.values()) {
            if (sub.hentAntall() > flestForekomst) {
                peker = sub;
                flestForekomst = sub.hentAntall();
            }
        }

        System.out.println(peker);
        double endTime   = System.currentTimeMillis();
        double totalTime = (endTime - startTime)/1000;
        System.out.println("Tid: " + totalTime);
    }
}

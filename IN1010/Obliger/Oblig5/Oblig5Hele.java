import java.io.FileNotFoundException;
import java.lang.InterruptedException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Oblig5Hele {
    public static void main(String[] args) {
        Scanner scan = null;

        try {
            scan = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }

        SubsekvensRegister sykS = new SubsekvensRegister();
        Monitor2 sykM = new Monitor2(sykS);

        SubsekvensRegister friskS = new SubsekvensRegister();
        Monitor2 friskM = new Monitor2(friskS);

        // System.out.println("Leser filer");

        ArrayList<Thread> leseTraader = new ArrayList<>();
        while (scan.hasNextLine()) {
            String[] linje = scan.nextLine().split(",");
            if (Boolean.parseBoolean(linje[1])) { //har viruset
                LeseTrad leseTrad = new LeseTrad(args[0] + "/" + linje[0], sykM);
                Thread t = new Thread(leseTrad);
                leseTraader.add(t);
                t.start();
            } else if (!Boolean.parseBoolean(linje[1])) { //har ikke viruset
                LeseTrad leseTrad = new LeseTrad(args[0] + "/" + linje[0], friskM);
                Thread t = new Thread(leseTrad);
                leseTraader.add(t);
                t.start();
            }
        }
        
        for (Thread t : leseTraader) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Noe skjedde.");
            }
        }
        // System.out.println("Ferdig med aa lese");

        ArrayList<Thread> traadListe = new ArrayList<>();
        int antallFlettere = 8;
        for (int i = 0; i < antallFlettere * 2; i++) { //lager tråder for hver monitor avhengig om det er partall eller oddetall
            if (i % 2 == 0) {
                Thread tS = new Thread(new FletteTrad(sykM));
                tS.start();
                traadListe.add(tS);
            } else {
                Thread tF = new Thread(new FletteTrad(friskM));
                tF.start();
                traadListe.add(tF);
            }
        }

        try {
            for (Thread t : traadListe) {
                t.join();
            }
        } catch (InterruptedException e) {}

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

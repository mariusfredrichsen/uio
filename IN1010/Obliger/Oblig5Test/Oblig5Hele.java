import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;


public class Oblig5Hele {

    static final int ANT_TRAADER = 8;
    public static void main (String[] args) throws FileNotFoundException, InterruptedException{
        
        Scanner sc = new Scanner(new File(args[0] + "/metadata.csv"));

        SubsekvensRegister subsekvensFriske = new SubsekvensRegister();
        SubsekvensRegister subsekvensSyke = new SubsekvensRegister();

        /*to beholdere, en for DNA-sekvenser fra folk som har blitt smittet og de som ikke har blitt det*/
        Monitor2 monitorFriske = new Monitor2(subsekvensFriske);
        Monitor2 monitorSyke = new Monitor2(subsekvensSyke);
        ArrayList<Thread> traader = new ArrayList<Thread>();
        ArrayList<Thread> flettetraader = new ArrayList<Thread>();

        while(sc.hasNextLine()) {


            String mappe = args[0];
            String[] readMeta = sc.nextLine().trim().split(",");
            
            String filnavn = readMeta[0];
            String bool = readMeta[1];
            /*om personen har blitt smittet skal det legges til i monitorSyke beholderen*/
            if (bool.equals("True")) {

                LeseTrad lese = new LeseTrad(mappe + "/" + filnavn, monitorSyke);
                Thread lesetraad = new Thread(lese);

                lesetraad.start();
                traader.add(lesetraad);
                
                    
                    
                }
                else {
                /*om personen ikke har blitt smittet skal de subsekvensene legges til i monitorFriske beholderen */
                    LeseTrad lese2 = new LeseTrad(mappe + "/" + filnavn, monitorFriske);
                    Thread lesetraad2 = new Thread(lese2);
                    lesetraad2.start();
                    traader.add(lesetraad2);
                    
                }
             
            }
            try {
                /*alle lesetrådene har blitt lagt til i traader, venter på at lesetrådene er ferdige å lese*/
                for (Thread traad : traader) {
                    traad.join();
                    
                }  
                
            } catch(InterruptedException e) {}
            /*oppretter flettetråder for friske og syke*/
            FletteTrad flette = new FletteTrad(monitorSyke);
            FletteTrad flette2 = new FletteTrad(monitorFriske);
            
            /*legger til flettetråder i flettetraader*/
            for (int i = 0; i < ANT_TRAADER; i++) {
                Thread flettetraad = new Thread(flette);
                flettetraad.start();
                flettetraader.add(flettetraad);
                

            }
            /*legger til flettetråder i flettetraader*/
            for (int i = 0; i <= ANT_TRAADER; i++) {
                Thread flettetraad2 = new Thread(flette2);
                flettetraad2.start();
                flettetraader.add(flettetraad2);
        
            }
            
            try {

                for (Thread flettetraad : flettetraader) {
                    flettetraad.join();
                    
                }  
                
            } catch(InterruptedException e) {}
            

                /*lagrer monitorFriske og monitorSyke i variabler slik at jeg kan bruke dem i for-each løkke siden jeg bare skal fjerne dem en gang*/
                HashMap<String, Subsekvens> hashmapFrisk = monitorFriske.fjern();
                HashMap<String, Subsekvens> hashmapSyk = monitorSyke.fjern();
                HashMap<String, Subsekvens> resultatet = new HashMap<>();

                for (String subsekvens : hashmapSyk.keySet()) {
                    if (hashmapFrisk.containsKey(subsekvens)) {
                        if (hashmapFrisk.containsKey(subsekvens) && hashmapSyk.get(subsekvens).hentForekomst() > hashmapFrisk.get(subsekvens).hentForekomst() + 6) {
                            resultatet.put(subsekvens, hashmapSyk.get(subsekvens));
                        }

                    }
                    else if (hashmapSyk.get(subsekvens).hentForekomst() > 6){
                        resultatet.put(subsekvens, hashmapSyk.get(subsekvens));
                    }
                    
                }
                System.out.println("7 ganger mer: ");
                for (Subsekvens subs : resultatet.values()) {
                    System.out.println(subs);
                }
                // /*subsekvenspekeren skal peke til den subsekvensen som skal være den siste forekomsten */
                // Subsekvens subsekvenspeker = null;
                // int stoerste = 0;

            //     /*legger til alt som er i hashmapSyk inni resultatet*/
            //     for (String subsekvens : hashmapSyk.keySet()) {
            //         resultatet.put(subsekvens, hashmapSyk.get(subsekvens));
            //     }
            //     /*om subsekvens finnes i hashmapFrisk og i resultatet (hashmapSyk), da skal antallet i resultatet minskes for å finne den største forskjellen
            //      * gjør ingenting om tallet blir negativt, fordi er ute etter den største forskjellen*/
            //     for (String subsekvens1 : hashmapFrisk.keySet()) {

            //         if (resultatet.containsKey(subsekvens1)) {

            //             resultatet.get(subsekvens1).minsk(hashmapFrisk.get(subsekvens1).hentForekomst());
            //             }  
            //         }
                
            //     /*itererer gjennom resultatet for å finne den største forskjellen som nå da er det største nummeret i resultatet*/
            //     for (String subsekvens : resultatet.keySet()) {
            //         if (resultatet.get(subsekvens).hentForekomst() > stoerste) {
            //             subsekvenspeker = resultatet.get(subsekvens);
            //             stoerste = resultatet.get(subsekvens).hentForekomst();
            //         }
            //     }
            //    /*printer ut det endelige resultatet */
            //     System.out.println("" + subsekvenspeker);
                
                
                    
    }
            
}

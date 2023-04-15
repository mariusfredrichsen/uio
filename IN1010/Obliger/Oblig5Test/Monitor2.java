import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.ArrayList;

public class Monitor2 implements SubsekvensRegisterInterface, Iterable<HashMap<String, Subsekvens>>{

    Lock laas = new ReentrantLock();
    SubsekvensRegister peker;
    boolean skrevetFerdig = false;
    Condition hashmapKlar = laas.newCondition();

    public Monitor2(SubsekvensRegister subsekvensRegister) {
        peker = subsekvensRegister;
    }
    
    public void settInn(HashMap<String, Subsekvens> subHashMap){
        laas.lock();
        try {
            peker.settInn(subHashMap);
            /*signaliserer at det har blitt satt inn en hashmap*/
            hashmapKlar.signalAll();
        }
        finally {
            laas.unlock();
        }
    }

    public void taUt(int pos){
        
        peker.taUt(pos);

    }
    public int stoerrelse() {
        laas.lock();
        try {
            return peker.stoerrelse();

        }finally {
            laas.unlock();
        } 

    }

    public boolean erFerdig() {
        return skrevetFerdig;
    }

    public HashMap<String, Subsekvens> fjern() {

        return peker.fjern();

    }

    public ArrayList<HashMap<String, Subsekvens>> fjernTo() {
        laas.lock();

        try {
            /*om programmet ikke er ferdig og om størrelsen på monitoren er mindre enn 1, altså 0, kunne også vært this.størrelse == 0
             * da skal den vente på at det er noe i monitoren*/
            while(!erFerdig() && this.stoerrelse() < 1) {
                hashmapKlar.await();
                
            }
            // /*nå er det noe i monitoren, men sjekker om det er 2 eller flere, fordi om den er mindre, skal den vente mer*/
            // if(this.stoerrelse() < 2) {
            //     hashmapKlar.signalAll();
                
            // }
            /*lager en ArrayList for å beholde to hashmaps*/
            ArrayList<HashMap<String, Subsekvens>> toHash = new ArrayList<HashMap<String, Subsekvens>>();

            /*legger til to hashmaps, og samtidig fjerner det fra monitoren*/
            toHash.add(this.fjern());
            toHash.add(this.fjern());
           /*returnerer ArrayList*/
            return toHash;

        }catch(InterruptedException e) {
            System.out.println("Interrupted");
            /*metoden må returnere noe, så returnerer null*/
            return null;
        }
        finally {
            laas.unlock();
        }
        
    }
    /*toString metode for å kunne printe ut det som er inne i monitoren*/
    public String toString() {
        for (HashMap<String, Subsekvens> map : peker) {
            System.out.println(map);
        }
        return "";
    }

    public Iterator<HashMap<String, Subsekvens>> iterator() {
        return peker.iterator();
    }

    
}

class LeseTrad implements Runnable {
    Monitor2 monitor;
    String filnavn;

    public LeseTrad(String filnavn, Monitor2 monitor) {
        this.filnavn = filnavn;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        

        try {
            /*leser en gang, så kan egentlig kanskje bare ha en if-sjekk*/
            while(!monitor.erFerdig()) {
                /*lager hashmap fra fil og setter inn i monitoren*/
                HashMap<String, Subsekvens> lag = SubsekvensRegister.lagerHash(filnavn);
                monitor.settInn(lag);
                return;
    
            }
  
        }catch (FileNotFoundException e) {}

    }
    
}

class FletteTrad implements Runnable {
    Monitor2 monitor;

    public FletteTrad(Monitor2 monitor) {
        this.monitor = monitor;

    }

    public void run() {

        /*om monitor sin størrelse er over 1 og at monitoren ikke er ferdig da skal det flettes hashmaps*/
        while(monitor.stoerrelse() > 1 && !monitor.erFerdig()) {
            /*bruker monitor.fjernTo() for å hente ArrayList med to hashmaps*/
            ArrayList<HashMap<String, Subsekvens>> toHash = monitor.fjernTo();
            /*fjerner de hashmappene fra ArrayList som blir slått sammen*/
            HashMap<String, Subsekvens> slaaSammen = SubsekvensRegister.slaaSammenHash(toHash.remove(0), toHash.remove(0));
            /*setter tilbake den sammenslåtte hashmappen*/
            monitor.settInn(slaaSammen);
            
        }
    }
        
}

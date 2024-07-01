


// Oppgave 1 (Hierarkietegning)



// Oppgave 2

// Antar at Fly er abstrakt med tanke på hvordan teksten formulerte det
abstract class Fly implements Motordrevet {
    int antMotor; // ikke final hvis de blir slitt ut over tid eller mister en?
    int mtow;
    int trekkraft;
    final String id;
    
    Fly neste;
    
    public Fly(int antMotor, int mtow, int trekkraft, String id) {
        this.antMotor = antMotor;
        this.mtow = mtow;
        this.trekkraft = trekkraft;
        this.id = id;
        
        this.neste = null;
    }
    
    public String hentId() {
        return this.id;
    }
    
    public int hentMotorAntall() {
        return this.antMotor;
    }
    
    public int hentMTOW() {
        return this.mtow;
    }
    
    public int trekkraft() {
        return this.trekkraft;
    }
    
    // Oppgave 4b
    public int maksVektR() {
        if (neste == null) return mtow;
        else {
            int nesteVekt = neste.maksVektR();
            if (nesteVekt > this.mtow) return nesteVekt;
            else return this.mtow;
        }
        // skal være en lettere syntax for dette med bare 
        // max(neste.marksVektR(), this.mtow), evt lage en hjelpemetode
    }
}

// Antar at MotorFly er abstrakt med tanke på hvordan teksten formulerte det
abstract class MotorFly extends Fly {
    
    public MotorFly(int antMotor, int mtow, int trekkraft, String id) {
        super(antMotor, mtow, trekkraft, id);
    }
}



class SeilFly extends Fly {
    int minSynkHastighet;
    
    public SeilFly(int antMotor, int mtow, int trekkraft, String id, int minSynkHastighet) {
        super(antMotor, mtow, trekkraft, id);
        
        this.minSynkHastighet = minSynkHastighet;
    }
    
    @Override
    public int trekkraft() {
        return 0;
    }
}



class LasteFly extends MotorFly {
    int maksVekt;
    
    public LasteFly(int antMotor, int mtow, int trekkraft, String id, int maksVekt) {
        super(antMotor, mtow, trekkraft, id);
        
        this.maksVekt = maksVekt;
    }
}




class PassasjerFly extends MotorFly {
    int antMaksPas;
    
    public PassasjerFly(int antMotor, int mtow, int trekkraft, String id, int antMaksPas) {
        super(antMotor, mtow, trekkraft, id);
        
        this.antMaksPas = antMaksPas;
    }
}

interface Motordrevet {
    int trekkraft();
}



// Oppgave 3

import java.util.Iterator;

class Flyformasjon implements Iterable<Fly> {
    Fly første;
    
    // Oppgave 3a
    public Flyformasjon() {
        this.første = null;
    }
    
    // Oppgave 3b
    public void leggTil(Fly f) {
        // håndter tom liste
        if (første == null) {
            første = f;
        
        // ellers legg til bakerst
        } else {
            Fly p = første;
            while (p.neste != null) {
                p = p.neste;
            }
            // p peker på siste
            p.neste = f;
        }
    }
    
    // Oppgave 3c
    public boolean erMed(String id) {
        // håndter tom liste
        if (første == null) return false;
        else {
            // iterer over helt til slutten
            Fly p = første;
            while (p != null) {
                if (p.hentId().equals(id)) {
                    return true;
                }
                p = p.neste;
            }
        }
        // er ikke med
        return false;
    }
    
    // Oppgave 3d
    public Fly taUt(String id) {
        // er ikke med i listen
        if (!(this.erMed(id))) return null;
        else {
            // iterer over helt til slutten
            Fly p = første;
            while (p.neste != null) {
                if (p.neste.hentId().equals(id)) {
                    // stopp på Fly'et før slik at man kan koble sammen lenken
                    p.neste = p.neste.neste;
                    return p.neste;
                }
                p = p.neste;
            }
        }
    }
    
    // Oppgave 3e
    public Iterator<Fly> iterator() {
        return new FlyIterator();
    }
    
    class FlyIterator extends Iterable<Fly> {
        Fly denne;
        
        public FlyIterator() {
            this.denne = første;
        }
        
        @Override
        public boolean hasNext() {
            return denne != null;
        }
        
        @Override
        public Fly next() {
            Fly tmp = denne;
            denne = denne.neste;
            return tmp;
        }
    }
    
    // Oppgave 3f
    public PassasjerFly[] hentPassasjerFly() {
        int antPassasjerFly = this.antPassasjerFly();
        PassasjerFly[] pFly = new PassasjerFly[antPassasjerFly];
        int i = 0;
        
        for (Fly f : this) {
            if (f instanceof PassasjerFly) pfly[i++] = (PassasjerFly) f;
        }
        
        return pFly;
    }
    
    // hjelpemetode
    private int antPassasjerFly() {
        int n = 0;
        for (Fly f : this) {
            if (f instanceof PassasjerFly) n++;
        }
        return n;
    }
    
    // Oppgave 4a
    public int totalVekt() {
        int totalMTOW = 0;
        for (Fly f : this) {
            totalMTOW += f.hentMTOW();
        }
        return totalMTOW;
    }
    
    // Oppgave 4b (Rekursjon)
    public int maksVekt() {
        // håndter tom liste
        if (første == null) return 0;
        return første.maksVektR();
    }
}



// Oppgave 5
import java.util.concurrent.locks.*;
import java.util.Random;

// Oppgave 5a (Monitor)
class Rullebane {
    Lock lås;
    Condition avventStartTillatelse;
    Condition avventFly;
    
    int antFly;
    
    public Rullebane() {
        lås = new ReentrantLock();
        avventStartTillatelse = lås.newCondition();
        avventFly = lås.newCondition();
        
        antFly = 0; // antall fly på banen
    }
    
    
    public void sjekkAvganger() throws InterruptedException {
        lås.lock();
        try {
            // venter på et fly
            while (antFly == 0) {
                avventFly.await();
            }
            
            // signaliserer kun en tråd til å starte
            avventStartTillatelse.signal();
        } finally {
            lås.unlock();
        }
    }
    
    public void hentStartTillatelse(Fly f) throws InterruptedException {
        lås.lock();
        try {
            antFly++; // øk antall først så signaliser
            avventFly.signal();
            
            System.out.println(String.format("Fly med id: %s venter i kø!", f.hentId()));
            
            avventStartTillatelse.await();

            System.out.println(String.format("Fly med id: %s tar av!", f.hentId()));
            
            antFly--;
        } finally {
            lås.unlock();
        }
    }
}



class Flygeleder implements Runnable {
    Rullebane rullebane;
    
    public Flygeleder(Rullebane rullebane) {
        this.rullebane = rullebane;    
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                rullebane.sjekkAvganger();
                // Hvis 1000 er 1 sekund ganges det med 60
                delay(1000 * 60);
            }
            
        } catch(InterruptedException e) {}
    }
    
}

class Pilot implements Runnable {
    Rullebane rullebane;
    Fly f;
    
    public Pilot(Fly f) {
        this.rullebane = rullebane; 
        this.f = f;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                rullebane.hentStartTillatelse(f);
                
                delay(Random(120, 240) * 1000); // flyr tilfeldig lang tid
                // husker ikke syntax men tanken er å ha det så "asynkront"
                // som mulig og antar at flyene kommer tilbake til samme rullebane
            }
        } catch (InterruptedException e) {}
    }
}



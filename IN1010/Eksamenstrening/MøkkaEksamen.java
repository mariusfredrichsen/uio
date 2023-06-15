import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

//Står i oppgaven "kan" brukes 
interface Motordrevet {
    boolean fossilt();
    int trekkraft();
}

abstract class Skinnegående {
    String id;
    int sporvidde;
    
    Skinnegående neste = null;
    Skinnegående forrige = null;
    
    Skinnegående(String id, int sporvidde) {
        this.id = id;
        this.sporvidde = sporvidde;
    }
    
    
    String hentId() {
        return id;
    }
    
    int hentSporvidde() {
        return sporvidde;
    }
    
    //rekursiv metode for 4d
    void sjekkSporvidde(int sporvidde) {
        if (this.sporvidde != sporvidde) {
            throw new FeilSporvidde();
        }
        if (neste != null) neste.sjekkSporvidde(this.sporvidde);
    }
}

class Lokomotiv extends Skinnegående {
    
}

class LokomotivM extends Skinnegående implements Motordrevet {
    boolean fossilt;
    int trekkraft;
    
    LokomotivM(boolean fossilt, int trekkraft) {
        this.fossilt = fossilt;
        this.trekkraft = trekkraft;
    }
    
    boolean fossilt() {
        return fossilt;
    }
    
    int trekkraft() {
        return trekkraft;
    }
}

abstract class Vogn extends Skinnegående {
    int lengde;
    
    Vogn(int lengde) {
        this.lengde = lengde;
    }
}

class Godsvogn extends Vogn {
    double maksVekt;
    
    Godsvogn(int lengde, double maksVekt) {
        super(lengde);
        this.maksVekt = maksVekt;
    }
}

class GodsvognM extends Skinnegående implements Motorisert {
    boolean fossilt;
    int trekkraft;
    
    GodsvognM(boolean fossilt, int trekkraft) {
        this.fossilt = fossilt;
        this.trekkraft = trekkraft;
    }
    
    boolean fossilt() {
        return fossilt;
    }
    
    int trekkraft() {
        return trekkraft;
    }
}

class Passasjervogn extends Vogn {
    int maksPlass;
    
    Passasjervogn(int lengde, int maksPlass) {
        super(lengde);
        this.maksPlass = maksPlass;
    }
    
}

class PassasjervognM extends Skinnegående implements Motordrevet {
    boolean fossilt;
    int trekkraft;
    
    PassasjervognM(boolean fossilt, int trekkraft) {
        this.fossilt = fossilt;
        this.trekkraft = trekkraft;
    }
    
    boolean fossilt() {
        return fossilt;
    }
    
    int trekkraft() {
        return trekkraft;
    }
}

class Tog implements Iterable<Skinnegående> {
    Skinnegående første = null;
    Skinnegående siste = null;
    
    void leggTil(Skinnegående s) {
        Skinnegående peker = første;
        //hvis lenkelisten skulle være tom
        if (peker == null) {
            første = s;
            siste = s;
            return;
        }
        //setter inn sist
        siste.neste = s;
        s.forrige = siste;
        siste = s;
    }
    
    Skinnegående taUt(Skinnegående s) {
        Skinnegående peker = første;
        Skinnegående returner = s;
        //hvis s er første objektet
        if (første == s) {
            første = første.neste;
            første.forrige = null;
            return returner;
        }
        //hvis s er siste objektet
        if (siste == s) {
            siste.forrige.neste = null;
            siste = siste.forrige;
            return returner;
        }
        
        //ikke første
        while (true) {
            if (peker == s) {
                peker.forrige.neste = peker.neste;
                peker.neste.forrige = peker.forrige;
                return returner;
            }
        }
    }
    
    Skinnegående finnOgTaUt(String id) {
        Skinnegående peker = første;
        while(peker.neste != null) {
            if (peker.hentId().equals(id)) {
                break;
            }
            peker = peker.neste;
        }
        //sittende igjen med peker = riktig skinnegående
        return taUt(peker);
    }
    
    void leggTilForan(Skinnegående s1, Skinnegående s2) {
        //finn s1
        Skinnegående peker = første;
        while (peker.neste != null) {
            if (peker == s1) {
                break;
            }
            peker = peker.neste;
        }
        //sitter med peker = s1
        peker.forrige = s2;
        s2.neste = peker;
        //setter in for ny første
        if (peker == første) første = s2;
    }
    
    class TogIterator implements Iterator<Skinnegående> {
        Skinnegående peker = første;
        
        @Override
        public boolean hasNext() {
            return peker != null;
        }
        
        @Override
        public Skinnegående next() {
            Skinnegående ut = peker;
            peker = peker.neste;
            return ut;
        }
    }
    
    public Iterator<Skinnegående> iterator() {
        return new TogIterator();
    }
    
    Passasjervogn[] hentPassasjervogner() {
        int antPasVogn = 0;
        for (Skinnegående s : this) {
            if (s instanceof Passasjervogn) {
                antPasVogn++;
            }
        }
        
        Passasjervogn[] pasVogner = new Passasjervogn[antPasVogn];
        int i = 0;
        for (Skinnegående s : this) {
            if (s instanceof Passasjervogn) {
                pasVogner[i++] = s;
            }
        }
        
        return pasVogner;
    }
    
    void sjekkSporvidde() {
        int førsteSporvidde = første.hentSporvidde();
        for (Skinnegående s : this) {
            if (s.hentSporvidde() != førsteSporvidde) {
                throw new FeilSporvidde();
            }
        }
    }
    
    void leggTilSikker(Skinnegående s) {
        leggTil(s);
        try {
            sjekkSporvidde();
        } catch (FeilSporvidde e) {
            taUt(s);
        }
    }
    
    //rekursiv måte å kaste FeilSporvidde på / sjekke
    void sjekkSporviddeR() throws FeilSporvidde {
        if (første != null) {
            første.sjekkSporvidde(første.hentSporvidde());
        }
    }
    
}

class FeilSporvidde extends Exception {
    FeilSporvidde() {
        super("Tog har vogner av forskjellige sporvidder");
    }
}

class Leter implements Runnable {
    Tog t;
    Monitor m;
    String id;
    
    Leter(Tog t, Monitor m, String id) {
        this.t = t;
        this.m = m;
        this.id = id;
    }
    
    public void Run() {
        for (Skinnegående s : t) {
            if (s.hentId().startsWith(id)) {
                m.leggTil(s);
            }
        }
        m.ferdigLeting();
    }
    
}

class Resultat implements Runnable {
    Monitor m;
    
    Resultat(Monitor m) {
        this.m = m;
    }

    public void Run() {
        while(true) {
            Skinnegående s = m.hentNeste();
            if (s == null) {
                return;
            }
            System.out.println(s.hentId());
        }
    }



    
}

class Monitor {
    int antLeteTraad;
    Skinnegående peker = null;
    Lock laas;
    Condition nyRef;
    
    Monitor(int antLeteTraad) {
        this.antLeteTraad = antLeteTraad;
        laas = new ReentrantLock(); //usikker på syntax her og
        nyRef = laas.newCondition(); //husker ikke syntax for conditions
    }
    
    void leggTil(Skinnegående s) {
        laas.lock(); //laaser slik at bare en tråd kan sende av gangen
        try {
            peker = s;
            nyRef.signal(); //signaliserer at det er en ny referanse der
        } finally {
            laas.unlock();
        }
    }
    
    void ferdigLeting() {
        if (--antLeteTraad == 0) {
            peker = null; //gir kun null når alle er ferdig
            nyRef.signal();
        }
    }
    
    Skinnegående hentNeste() {
        try {
            nyRef.await(); //venter til signal blir gitt (gitt at Resultat tråden starter først)
        } catch (InterruptedException e) {;}
        return peker;
    }
    
    
}
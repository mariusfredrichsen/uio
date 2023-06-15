import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.Iterator;

abstract class Skinngående{
    String id;
    int spor_bredde;
    String drivstoff;
    int strømKraft;
    
    Skinngående neste;
    Skinngående forrige;
    
    public Skinngående(String id, int spor_bredde, String drivstoff, int strømKraft){
        this.id = id;
        this.spor_bredde = spor_bredde;
        this.drivstoff = drivstoff;
        this.strømKraft = strømKraft;
    }
    
    public int hentId(){
        return id;
    }
    
    public int hentSporvidde(){
        return spor_bredde;
    }
}

public class Lokmotiv extends Skinngående implements Motordrevet{
    
    public Lokmotiv(String id, int spor_bredde, String drivstoff, int strømKraft){
        super(id, spor_bredde, drivstoff, strømKraft);
    }
    
    public boolean fossilt(){
        // Antar at strengen drivstoff ser slik ut "bensin", "kull", "ved" eller "elektrisitet"
        if (!drivstoff.equals("elektrisitet")){
            return true;
        }
        return false;
    }
    
    public int trekkraft(){
        return strømKraft;
    }
    
}

public class Vogn extends Skinngående{
    public Vogn(){
        super(String id, int spor_bredde, String drivstoff, int strømKraft);
    }
    
}

public class Godsvogn extends Vogn implements Motordrevet{
    public Godsvogn(String, int spro_bredde){
        super(id, spor_bredde, drivstoff, int strømKraft);
    }
    
    public boolean fossilt(){
        if (!drivstoff.equals("elektrisitet")){
            return true;
        }
        return false;
    }
    
    public int trekkraft(){
        return strømKraft;
    }
    
    
}

public class Passasjervogn extends Vogn implements Motordrevet{
    public Passasjervogn(){
        super(String id, int spor_bredde, String drivstoff, int strømKraft);
    }
    
    public boolean fossilt(){
        if (!drivstoff.equals("elektrisitet")){
            return true;
        }
        return false;
    }
    
    public int trekkraft(){
        return strømKraft;
    }
}

interface Motordrevet{
    public boolean fossilt();
    public int trekkraft();
}


public class Tog{
    Skinngående hode;
    Skinngående hale;
    int størrelse;
    
    // Hjelpe metode for tråder oppgaven
    public int størrelse(){
        return størrelse;
    }
    
    public Tog(Skinngående hode, Skinngående hale){
        this.hode = hode;
        this.hale = hale;
    }
    
    //  Skal legge til toget sist i listen
    public void leggTil(Skinngående tog){
        if(hode == null){
            hode = tog;
            hale = tog;
        }else{
            hale.neste = tog;
            tog.forrige = hale;
            hale = tog;
        }
        størrelse++;
    }
    
    // Tar ut den første vognen
    public Skinngående taUt(){
        Skinngående peker = hode;
        hode = hode.neste;
        hode.forrige = null;
        peker.neste = null;
        størrelse--;
        return peker; // Returnerer referanse til objekt
    }
    
    // Fjerner vognen
    public Skinngående finnOgTaUt(String id){
        Skinngående peker = hode;
        while(peker.id != id && peker.neste != null){
            peker = peker.neste;
        }
        
        if(peker.forrige == null){
            hode = peker.neste;
        }else{
            peker.forrige.neste = peker.neste;
        }
        
        if(temp.neste == null){
            hale = peker.forrige;
        }else{
            peker.neste.forrige = peker.forrige;
        }
        
        størrelse--;
        
        return peker;
    }
    
    public void leggTilForan(Skinngående togFinnes, togLeggTil){
        Skinngående peker = hode;
        while(peker != togFinnes && peker.neste != null){
            peker = peker.neste;
        }
        
        if(peker == hode){
            if(hode != null){
                hode.forrige = togLeggTil;
                togLeggTil.neste = hode;
            }else{
                hale = togLeggTil;
            }
            hode = n;
        }
        
        else if(peker == hale){
            if(hale != null){
                hale.forrige = togLeggTil;
            }
            togLeggTil.neste = hale;
        }
        
        else{
            peker.forrige.neste = togLeggTil;
            togLeggTil.forrige = peker.forrige;
            
            peker.forrige = togLeggTil;
            togLeggTil.neste = peker;
        }
        
        størrelse++;
    }
    
    // Returnerer en array med referanser til alle passasjervogn objekter
    public Passasjervogn[] hentPassasjervogner(){
        
        int antVogner = 0;
        Skinngående peker = hode;
        
        while(peker.neste != null){
            if(peker.equals(Passasjervogn)){
                antVogner++;
            }
        }
        
        Passasjervogn[] vogner = new Passasjervogn[antVogner];
        
        Skinngående passasjerPeker = hode;
        int teller = 0;
        while(passasjerPeker.neste != null){
            if(peker.equals(Passasjervogn)){
                vogner[teller] = passasjerPeker;
                passasjerPeker = passasjerPeker.neste;
                teller++;
            }
        }
    }
    
    public void sjekkSporvidde(int spor_bredde){
        Skinngående peker = hode;
        int indeks = 0;
        while(peker.neste != null){
            if(peker.spor_bredde != spor_bredde){
                throw new FeilSporvidde(spor_bredde, indeks);
            }
            indeks++;
        }
    }
    
    // Sjekker om toget er innenfor sporvidde og legger til bakerst
    public void leggTilSikker(Skinngående tog){
        
        sjekkSporvidde(tog.spor_bredde);
        
        // Hvis den så er gyldig vil programmet fortsette
        
        if(hode == null){
            hode = tog;
            hale = tog;
        }else{
            hale.neste = tog;
            tog.forrige = hale;
            hale = tog;
        }
        
    }


    public void sjekkRekusivt(int spor_bredde){
        Skinngående peker = hode;
        int indeks = 0;
        
        // Sjekke om det første toget er riktig spor bredde
        if(peker.spor_bredde != spor_bredde){
            throw new FeilSporvidde(spor_bredde, indeks);
        }
        
        // Hvis ikke sjekker vi det andre toget i listen
        if(neste != null){
            
            // Dersom det finnes kaller vi rekusivt på den neste bilen
            return peker.neste.sjekkRekusivt(peker.neste.spor_bredde);
        }
        return null;  // Returnerer null dersom alle er sikkre
    }
    
    
    
    class TogIterator implements Iterator<Skinngående>{
        Skinngående peker = hode;
        
        public boolean hasNext(){
            return peker != null;
        }
        
        public Skinngående next(){
            Skinngående temp = peker;
            peker = peker.neste;
            return temp;
        }
        
        public Iterator<Skinngående> iterator(){
            return new TogIterator();
        }
    }
}


public class FeilSporvidde extends RuntimeException{
    FeilSporvidde(int spor_bredde, int indeks){
        super("FeilSporvidde exception : " + spor_bredde + " på indeks : " + indeks);
    }
}



public class Leter implements Runnable{
    Monitor monitor;
    Tog liste;
    String bestemtTekstStreng;
    
    public Leter(Monitor monitor, Skinngående tog, String bestemtTekstStreng){
        this.monitor = monitor;
        this.tog = tog;
        this.bestemtTekstStreng = bestemtTekstStreng;
    }
    
    
    public void run(){
        for(Skinngående vogn : liste){
            if(vogn.id.startsWith(bestemtTekstStreng)){
                monitor.leggTil(vogn);
            }
        }
        
        monitor.ferdigLeting(); // Signaliserer at tråden er ferdig
    }
}

public class Resultat implements Runnable{
    
    Monitor monitor;
    
    public Resultat(Monitor monitor){
        this.monitor = monitor;
    }
    
    public void run(){
        
        try{
            if(monitor.hentNeste() != null){
                System.out.println(monitor.hentNeste().id);
            }            
        }catch(InterruptedException e) {
        }

    }
    
}

public class Monitor{
    ArrayList<Skinngående> liste = new ArrayList<>();
    int antallVogner = liste.størrelse();
    Lock laas = new ReentrantLock();
    Condition ikkeTom = laas.newCondition();
    
    public void leggTil(Skinngående tog){
        // Trenger ikke låses fordi vi vil bare legge til så mange tog som mulig
        liste.add(tog);
    }
    
    public void ferdigLeting(){
        laas.lock();
        try{
            ikkeTom.signalAll();
        }finally{
            laas.unlock();
        }
    }
    
    public Skinngående hentNeste() throws InterruptedException{
        laas.lock();
        try {
            
            while(liste.size() == 0){
                ikkeTom.await();
            }
            
            int antall = antallVogner;
            antallVogner--;
            if(antall < 0){
                return liste[antall-1];
            }
            return null;

        }finally{
            laas.unlock();
        }
    }
}
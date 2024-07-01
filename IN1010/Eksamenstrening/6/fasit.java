import java.util.Iterator;
import java.util.NoSuchElementException;

// Stein Gjessing, 14. august 2023
 
 // Oppgave 1 Tegning av klasehierarki  5 poeng
 
 
 // Oppgave 2   Programmering av klassehierarkiet  15 poeng
 
 interface Lederbil { 
     int egnethet();
 }
 
 // Oppgave 1
 // Det er én superklasse, Bil. 
 // Det skal ikke være objekter av denne så den er abstrakt.
 // Velger så to subklasser av Bil, Personbil og Lastebil.
 // Lederbiler er igjen subklasser av disse to og implementerer 
 // interface Lederbil
 
 abstract class Bil {
     Bil neste, forrige; 
     int maksfart; 
     Kolonne minKolonne;    
     Bil (int m) {maksfart = m;}  
     
// Oppgave 5:
     int finnMaksfartR() {       
         if (neste == null) return maksfart;
         else {
            int resultat = neste.finnMaksfartR();
            return ((resultat < maksfart)? resultat : maksfart);
         }  
     }
 }
 
 class Personbil extends Bil {
 	int antPass;
        Personbil (int m, int ant) {
            super(m);
            antPass = ant;
        } 
 }
 
 class Lastebil extends Bil {
        int maksVekt;
        Lastebil (int m, int maks) {
            super(m);
            maksVekt = maks;
        } 
 }
 
 class LederPersonbil extends Personbil implements Lederbil {
 		int minEgnet;
        LederPersonbil (int m, int ant, int egn) {
            super(m,ant); 
            minEgnet = egn;
        } 
        @Override 
        public int egnethet() {
            return minEgnet;
        }
 }
 
 class LederLastebil extends Lastebil implements Lederbil {
  		int minEgnet;
        LederLastebil (int m, int vekt, int egn) {
             super(m, vekt);   
             minEgnet = egn;
        } 
        @Override 
        public int egnethet() {
                return minEgnet;
        }
 }
 
// Oppgave 3  Kolonne  20 poeng  
  
class Kolonne implements Iterable<Lederbil> {
  private Bil første, siste;
  private int antall = 0;  // Må ikke ha med antall
  
  public void settInn(Bil b) throws FeilBilUnntak {   
     if (antall == 0) {  
        if (b instanceof Lederbil) {
            første = siste = b;
            antall++;
            b.minKolonne = this;
        } else  throw new FeilBilUnntak("Må være lederbil først");
     } else {
        // Nå er listen ikke tom
        // Kan også ha en instansvariabel som sier om hva lags kolonne det er blitt.
        if (første instanceof Lastebil && b instanceof Personbil) 
                  throw new FeilBilUnntak("Kan ikke sette inn personbil i lastebilkolonne");
        if (første instanceof Personbil && b instanceof Lastebil) 
                  throw new FeilBilUnntak("Kan ikke sette inn lastebil i personbilkolonne");
        siste.neste = b;
        b.forrige = siste;
        b.neste = null;     
        siste = b; 
        antall++;
        b.minKolonne = this;
     }
  }
  
  public void taUt(Bil b) throws FeilBilUnntak { 
  	  if (b.minKolonne != this) 
  	         throw new FeilBilUnntak("Bilen er ikke i denne kolonnen");
  	  // bilen er i denne kolonnen
      if (b == første) {
         if (siste == b) {   // Det var eneste bil i kolonnen
             siste = første = null;
             antall = 0;
         } 
         // Skal ta ut første.  Flere enn én bil i kolonnen:
         if (b.neste instanceof Lederbil) {
            første = første.neste; 
            første.forrige = null;    
            b.neste = null;
            antall--;
         }
         else throw new FeilBilUnntak("Kan ikke ha kolonne uten lederbil");
      }
      else {  // ikke første som skal ut. To biler eller flere i kolonnen
          if(siste == b) {
              siste = siste.forrige;
              b.forrige = null;
              siste.neste = null;
              antall--;
          } else {
         	  // fjern midt i kolonnen:
          	  b.neste.forrige = b.forrige;
          	  b.forrige.neste = b.neste;
         	  b.forrige = b.neste = null;
         	  antall--;
          }
      }
      b.minKolonne = null;
  }
 
  // Oppgave 4  Iterator  15 poeng
  
   public Iterator<Lederbil> iterator() {
        return new KolonneIterator();
   }

    class KolonneIterator implements Iterator<Lederbil> {
        Bil n;
        
        KolonneIterator () {
            n = første;
            while ( n != null && !(n instanceof 
                 Lederbil)) n = n.neste;
        }

        public boolean hasNext() {
            return n != null;
        }

        public Lederbil next() {
            if (n == null) throw new NoSuchElementException();
            // n != null
            Lederbil s = (Lederbil)n;
            n = n.neste;
            while ( n != null &&  !(n instanceof Lederbil)) 
                 n = n.neste;
            return s;
        }
    }

  // Oppgave 5    Rekursiv metode  10 poeng
 
  private int finnMaksfartR() {         
     if (første == null) return Integer.MAX_VALUE;
     return første.finnMaksfartR();
  }

  
  // Oppagve 6   Finn beste lederbiler 15 poeng 
  
    Lederbil [] finnBesteEgnet () {  
      Lederbil [] svar = new Lederbil[5];
      int ind = 0;
      boolean startfasen = true; 
      for (Lederbil s: this) {
          if (ind == 5) {        
            startfasen = false;
          }       
          if (startfasen) {
             svar[ind] = s;
             ind++;           
          } else { 
             int minIndeks = beregnMinimumIndeks(svar);
             if (s.egnethet() >  svar[minIndeks].egnethet())
                svar[minIndeks] = s;
          }       
        }
      return svar;
    }
 
    static int beregnMinimumIndeks(Lederbil[] let) {
        // precondition:  Hele arrayen er full av Lederbiler
       int ind = 0;
       int verdiIndeks= 0;
       int verdi = Integer.MAX_VALUE;
       for (Lederbil l: let) {
          if (l.egnethet() < verdi  ){
               verdi = l.egnethet(); 
               verdiIndeks = ind;    
          } 
          ind++;      
       }
       return verdiIndeks;
    }
}

// Del av oppgave 3:

class FeilBilUnntak extends RuntimeException {
      FeilBilUnntak(String s){
          super(s);      
      }
}

// Oppgave 7    Tegning av datastruktur   5 poeng

// Oppgave 8    Generisk klasse  10 poeng


class KolonneG <E extends Bil> {
  private E første, siste;
  private int antall = 0;
  
  public void settInn(E b)  {   
     if (antall == 0) {  
            første = siste = b;
            antall++;
     } else {
        // Nå er listen ikke tom     
        siste.neste = b;
        b.forrige = siste;
        b.neste = null;     
        siste = b; 
        antall++;
     }
  }
  
  public E taUtSiste() { 
      if (siste == null) return null;
      E ut = siste;
      antall--;
      if (siste == første) {
              siste = første = null;
              return ut;
      } 
      // Flere enn en bil i kolonnen
      siste = (E)siste.forrige;
      siste.neste = null;
      ut.forrige = null;
      return ut;
  }
}

 // Oppgave 9  Bruk av generisk klasse  5 poeng  

class Kolonnekjøring {
  public static void main (String[] args) {
  
        System.out.println( "Versjon 14. august 2023");
        System.out.println( "Svar på oppgaven til eksamen:");
         System.out.println();        
         KolonneG<Lastebil> kol = new KolonneG<>(); 
         LederLastebil ledLast = new LederLastebil(190,2000,7);
         kol.settInn(ledLast);
         Lastebil last = kol.taUtSiste();
         System.out.println("Egnethet: " + ((Lederbil)last).egnethet());
 		 System.out.println();
 		 System.out.println( "Slutt svar på oppgaven til eksamen.");
 		 System.out.println();

        
  
  // Slutt oppgave 9
  
  
  // Resten er til litt ad hoc testformål:
  // Ikke en del av oppgaven
  
   		 System.out.println( "Litt testing:");
   		 System.out.println();
  
  
        Kolonne p = new Kolonne();
        Kolonne l = new Kolonne();

     try {

        System.out.println( "Personbiler i kolonne \"p\" : ");
        System.out.println();
     
        Bil pb1 = new LederPersonbil(160,6,8);
        p.settInn(pb1);       
        System.out.println("Satt inn lederpersonbil med maksfart: " + pb1.maksfart);
        
        Bil pb2 = new LederPersonbil(160,6,9);
        p.settInn(pb2);       
        System.out.println("Satt inn lederpersonbil med maksfart: " + pb2.maksfart);

        p.settInn(new LederPersonbil(170, 5, 7));
        System.out.println( "Satt inn lederpersonbil med maksfart 170");
        p.settInn(new Personbil(180,4));
        System.out.println( "Satt inn personbil med maksfart 180");    
        Bil pb3 = new Personbil(160,6);
        p.settInn(pb3);       
        System.out.println("Satt inn personbil med maksfart: " + pb3.maksfart);
        p.settInn(new Personbil(190,3)); 
        System.out.println( "Satt inn personbil med maksfart 190");
        p.settInn(new LederPersonbil(185, 5, 7));
        System.out.println( "Satt inn lederpersonbil med maksfart 185");
        Bil pb4 = new LederPersonbil(195,6,9);
        p.settInn(pb4);       
        System.out.println("Satt inn lederpersonbil med maksfart: " + pb4.maksfart);
        
        System.out.println();
            
        System.out.println( "Itererer over personbilene:");
        for (Lederbil b: p) System.out.println(((Bil)b).maksfart);
        System.out.println();
        
        System.out.println( "Tar ut første lederpersonbil");      
        p.taUt(pb1); 
        System.out.println("Tok ut personbil med maksfart: " + pb1.maksfart);
         
        System.out.println( "Itererer over personbilene:");
        for (Lederbil b: p) System.out.println(((Bil)b).maksfart);
        System.out.println();
        
        System.out.println( "Tar ut siste lederpersonbil");      
        p.taUt(pb4); 
        System.out.println("Tok ut personbil med maksfart: " + pb4.maksfart);
        
        System.out.println( "Itererer over personbilene:");
        for (Lederbil b: p) System.out.println(((Bil)b).maksfart);
        System.out.println();

        System.out.println();
        
        
        System.out.println( "Lastebiler i kolonne \"l\" : ");
        System.out.println();
        Bil pl1 = new LederLastebil(150,5000,6);
        l.settInn(pl1);
        System.out.println("Satt inn lederlastebil med maksfart: " + pl1.maksfart);
        
        Bil pl2= new LederLastebil(200,3000,8);
        l.settInn(pl2);
        System.out.println("Satt inn lederlastebil med maksfart: " + pl2.maksfart);


        l.settInn(new Lastebil(180,4000));
        System.out.println( "Satt inn lastebil med maksfart 180");
        l.settInn(new Lastebil(160,3500));       
         System.out.println( "Satt inn lastebil med maksfart 160");
        l.settInn(new Lastebil(190,5500)); 
         System.out.println( "Satt inn lastebil med maksfart 190");
         
        System.out.println();
                 
        System.out.println( "Itererer over lastebilene:");
        for (Lederbil b: l) System.out.println(((Bil)b).maksfart);
        
              System.out.println();       System.out.println();
              
    } catch (FeilBilUnntak e) { System.out.println(e.getMessage());}
    
    
    System.out.println("Feilmeldinger:");  
    System.out.println();          
    
    try { 
       Kolonne kol2 = new Kolonne();
       kol2.settInn(new Lastebil(180,4000));    
    } catch (FeilBilUnntak e) { System.out.println(e.getMessage());}
    
    try {  
       Kolonne kol3 = new Kolonne();
       kol3.settInn(new LederLastebil(180,4000,12));
       kol3.settInn(new Personbil(190,6));
    } catch (FeilBilUnntak e) { System.out.println(e.getMessage());}
     
    try {
        Kolonne kol4 = new Kolonne();
        Bil pl10 = new LederLastebil(150,5000,6);
        kol4.settInn(pl10);
        kol4.settInn(new Lastebil(190,60000));
        kol4.taUt(pl10); 
    } catch (FeilBilUnntak e) { System.out.println(e.getMessage());}
    
    try {
        Kolonne kol5 = new Kolonne();
        Bil pl10 = new LederLastebil(150,5000,6);
        kol5.settInn(pl10);
        kol5.settInn(new Lastebil(190,60000));
        Bil pl11 = new Lastebil(150,5000);
        kol5.taUt(pl11); 
    } catch (FeilBilUnntak e) { System.out.println(e.getMessage());}
    
    
    
    System.out.println(); 
    System.out.println("Takk for nå");
    System.out.println(); 
       
        
  }

}


 
 
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {
    public static void main(String[] args) {    	
	   int antallPlass = Integer.parseInt(args[0]);
	   int antallLage  = Integer.parseInt(args[1]);
       FellesBord bord = new FellesBord(antallPlass);
       Kokk  kokk = new Kokk(bord,antallLage);
	   new Thread(kokk).start();
	   Servitor  servitor1 = new Servitor(bord,antallLage);
       new Thread(servitor1).start();
       Servitor  servitor2 = new Servitor(bord,antallLage);
       new Thread(servitor2).start();
    }   
}

class FellesBord {  // En monitor
    private Lock bordlas;
	private Condition ikkeTomtBord;
	private Condition ikkeFulltBord;
	private int antallPaBordet = 0;
	private final int BORD_KAPASITET;
    /* Invariant: 0 <= antallPaBordet <= BORD_KAPASITET */

	public FellesBord (int ant) {
		BORD_KAPASITET = ant;
	    bordlas = new ReentrantLock(); 
    	ikkeTomtBord  = bordlas.newCondition();  
    	ikkeFulltBord = bordlas.newCondition();  	
    }
    	 
	void settTallerken() throws InterruptedException {
	    bordlas.lock();
	    try {
	        while (antallPaBordet >= BORD_KAPASITET) {
		    /* sa lenge det er  BORD_KAPASITET tallerkner
                               pa bordet er det ikke lov a sette pa flere. */
               ikkeFulltBord.await();                             
            }  
            // Na er antallPaBordet < BORD_KAPASITET.  Bordet er ikke fullt.
	        antallPaBordet++;
	        // Invarianten holder	+  antallPaBordet > 0    
	        System.out.println("Antall paa bordet: " + antallPaBordet);
	        ikkeTomtBord.signal(); /* Si fra til den som tar tallerkener. */
	    }
	    finally {
	        bordlas.unlock();
	    }    
	}

    void hentTallerken() throws InterruptedException  {
    	bordlas.lock();
	    try {
        while (antallPaBordet == 0) {			
            /* Sa lenge det ikke er noen talerkener pa
			      bordet er det ikke lov a ta en */
           ikkeTomtBord.await();
        } 
        // Na er antallPaBordet > 0
	    antallPaBordet --;    
	    // Invarianten holder + antallPaBordet < BORD_KAPASITET
	    ikkeFulltBord.signal(); /* si fra til den som setter tallerkener pa bordet. */
        }
        finally {
	    	bordlas.unlock();
	    }
	}
} // slutt class FellesBord;

class Kokk implements Runnable {
    private FellesBord bord;
    private final int ANTALL;
    private int laget = 0;
    Kokk(FellesBord bord, int ant) {
		this.bord = bord;
		ANTALL = ant;
	}

	public void run() {
	    try { 
	    	while(ANTALL != laget) {
            	laget ++;
            	System.out.println("Kokken lager tallerken nr: " + laget);
            	bord.settTallerken();  // lag og lever tallerken             
            	Thread.sleep((long) (3000));
        	}
        } catch (InterruptedException e) {System.out.println("Uventet stopp 1");}
         // Kokken er ferdig
    }
}

class Servitor implements Runnable {
    private FellesBord bord;
    private final int ANTALL; 
    private int servert = 0;
    Servitor(FellesBord bord, int ant) {
         this.bord = bord;
		ANTALL = ant;
    }

    public void run() {
    	try { 
        	 while (ANTALL != servert)  {
	            bord.hentTallerken(); /* hent tallerken og server */
	            servert++;
                System.out.println("Kelner serverer nr:" +  servert);
                Thread.sleep((long) (200));
            }
        } catch (InterruptedException e) { System.out.println("Uventet stopp 2");}
         // Servitoren er ferdig
    }
}



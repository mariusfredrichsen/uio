public class Kontroll {
    GUI gui;
    Modell modell;

    Kontroll () {
	    gui = new GUI(this);
	    modell = new Modell();
    }
    void startSpillet () {
	    laMaskinenTrekke();
    }
    void avsluttSpillet () {
	    System.exit(0);
    }
    void laMaskinenTrekke () {
	    int r, k;
	    do {
	        r = trekk(0,2);  k = trekk(0,2);
	    } while (! modell.lovligTrekk(r,k));
	    modell.noterTrekk(r, k, 'X');
	    gui.markerTrekk(r, k, 'X');  
	    if (modell.harVunnet('X')) {
	        modell.noterVinner("Maskinen");
	        gui.visStatus('X' + " vant");  
	    }
    }
    private int trekk (int a, int b) {
	    // Trekk et tilfeldig heltall x slik at a <= x <= b.
	    return (int)(Math.random()*(b-a+1)) + a;
    }   
    void brukervalg (int r, int k) {
	    if (modell.erSpilletFerdig()) {
	        gui.visStatus("Ingen flere trekk!");
	        return;
	    }
	    if (! modell.lovligTrekk(r, k)) {
	        gui.visStatus("Ulovlig trekk!");
	        return;
	    }     
	    modell.noterTrekk(r, k, 'O');
	    gui.markerTrekk(r, k, 'O');
	    if (modell.harVunnet('O')) {
	        gui.visStatus('O' + " vant");  
	        System.out.println("Jeg vant");
	        modell.noterVinner("Du");
	        return;
	    } 
	    laMaskinenTrekke();
	    if (modell.erSpilletFerdig()) 
	        return;
	    if (modell.erBrettetFullt()) {
	        modell.noterUavgjort();
	        gui.visStatus("Uavgjort");
	    }
        else 
	        gui.visStatus("Velg en rute");
    }
}

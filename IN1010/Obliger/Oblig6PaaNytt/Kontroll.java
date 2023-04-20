public class Kontroll {
	int rad, kol;
    GUI gui;
    Modell modell;
	boolean ferdig = false;
	boolean pauset = true;

    Kontroll (int rad, int kol) {
		this.rad = rad;
		this.kol = kol;
	    modell = new Modell(rad, kol);
	    gui = new GUI(this);
    }

	public int hentRad() {
		return rad;
	}

	public int hentKol() {
		return kol;
	}

	public int hentAntLevende() {
		return modell.hentAntLevende();
	}

	public Celle[][] hentCeller() {
		return modell.hentCeller();
	}
    
	public void oppdater() {
		modell.oppdater();
    }

	public void pause() {
		//TODO
	}

	public void byttStatus(int rad, int kol) {
		if (modell.byttStatus(rad, kol)) gui.byttStatus(rad, kol, true);
		else gui.byttStatus(rad, kol, false);
	}

    public void avslutt() {
	    System.exit(0);
    }

    //LOOP SPILLET TIL BRUKER PAUSER ELLER HVA NÅ EN
}

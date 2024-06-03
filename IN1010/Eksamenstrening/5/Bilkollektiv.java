class Bilkollektiv {
	final int AB;
	Bil[] alleBilene;
	Bil start, slutt; // Liste av ledige biler

	Bilkollektiv(int ant) {
		AB = ant;
		alleBilene = new Bil[AB];
		start = slutt = null;
	}

	void lagBilPris() {
		int minForrigePris = -1;
		for (int n = 1; n <= AB; ++n) {
			// Finn den billigste bilen blant de som er igjen:
			Bil billigst = null;
			for (int i = 0; i < AB; ++i) {
				Bil b = alleBilene[i];
				if (b.PRIS > minForrigePris && (billigst == null || b.PRIS < billigst.PRIS))
					billigst = b;
			}

			// Sett bilen inn sist i listen:
			if (start == null) {
				start = slutt = billigst;
			} else {
				slutt.neste = billigst;
				billigst.forrige = slutt;
				slutt = billigst;
			}
			minForrigePris = billigst.PRIS;
		}
	}

	void visBilene() {
		/* Til testing (ikke bedt om i oppgaven) */
		System.out.println("Test: Bilene er (sortert):");
		Bil b = start;
		while (b != null) {
			System.out.println("   " + b);
			b = b.neste;
		}
		System.out.println();
	}

	void taUtBil(Bil b) {
		if (b == start && start == slutt) {
			start = slutt = null;
		} else if (b == start) {
			start = start.neste;
			start.forrige = null;
		} else if (b == slutt) {
			slutt = slutt.forrige;
			slutt.neste = null;
		} else {
			b.forrige.neste = b.neste;
			b.neste.forrige = b.forrige;
		}
		b.neste = b.forrige = null;
		visBilene();
	}

	Bil velgBil(Dialog d) {
		boolean kunElbil = d.svarJaEllerNei("Er du bare interessert i elbil?");
		Bil b = start;
		while (b != null) {
			if (b.erElbil() || !kunElbil) {
				if (d.svarJaEllerNei("Liker du " + b + "?")) {
					taUtBil(b);
					return b;
				}
			}
			b = b.neste;
		}
		return null;
	}

	Bil velgBilR(Dialog d) {
		boolean kunElbil = d.svarJaEllerNei("Er du bare interessert i elbil?");
		Bil b = start.finnBilR(d, kunElbil);
		if (b != null)
			taUtBil(b);
		return b;
	}

	/* Hovedprogram (ikke bedt om i oppgaven) */
	public static void main(String[] arg) {
		Bilkollektiv kol = new Bilkollektiv(3);
		kol.alleBilene[0] = new Personbil("AA00001", 350, 4);
		kol.alleBilene[1] = new ElektriskVarebil("AA00002", 745, 21, 50);
		kol.alleBilene[2] = new ElektriskPersonbil("AA00003", 310, 3, 45);
		kol.lagBilPris();
		kol.visBilene();

		Dialog d = new TastaturDialog();
		// Dialog d = new GUIDialog();
		for (int i = 1; i <= 3; ++i) {
			// Bil b = kol.velgBil(d);
			Bil b = kol.velgBilR(d);
			if (b == null)
				System.out.println("Ingen bil passet.");
			else
				System.out.println("Bil nr " + i + " er " + b + ".");
			kol.visBilene();
		}
		System.exit(0);
	}
}
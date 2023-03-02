package IN1010.Oppgaver.Parkeringsplass;

public class Bil {
    private int registernummer;
    private int antallPassasjerSeter;

    public Bil(int registernummer, int antallPassasjerSeter) {
        this.registernummer = registernummer;
        this.antallPassasjerSeter = antallPassasjerSeter;
    }

    public int hentRegisternummer() {
        return registernummer;
    }

    public int hentAntallPassasjerSeter() {
        return antallPassasjerSeter;
    }
}

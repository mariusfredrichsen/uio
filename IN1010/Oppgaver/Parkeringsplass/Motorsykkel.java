package IN1010.Oppgaver.Parkeringsplass;

import static java.lang.Math.toIntExact;

public class Motorsykkel {
    private int registernummer;
    private int motorstorrelse;

    public Motorsykkel(int registernummer, double motorstorrelse) {
        this.registernummer = registernummer;
        this.motorstorrelse = toIntExact(Math.round(motorstorrelse));
    }

    public int hentRegisternummer() {
        return registernummer;
    }

    public int hentMotorstorrelse() {
        return motorstorrelse;
    }
}

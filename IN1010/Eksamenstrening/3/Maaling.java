public class Maaling {
    int tidspunkt;
    double stoyNivaa;
    int antall;

    Maaling neste = null;

    Maaling(int tidspunkt, double stoyNivaa) {
        this.tidspunkt = tidspunkt;
        this.stoyNivaa = stoyNivaa;
        antall = 1;
    }

    
}

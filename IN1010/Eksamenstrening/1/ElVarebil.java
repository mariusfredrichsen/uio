public class ElVarebil extends Varebil implements Elektrisk{
    int batteriStorrelse;
    
    public ElVarebil(String bilnummer, int pris, int lasVol, int batteriStorrelse) {
        super(bilnummer, pris, lasVol);
        this.batteriStorrelse = batteriStorrelse;
    }

    public int batteriStorrelse() {
        return batteriStorrelse;
    }
    
}

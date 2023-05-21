public class ElPersonbil extends Personbil implements Elektrisk{
    int batteriStorrelse;
    
    public ElPersonbil(String bilnummer, int pris, int antPas, int batteriStorrelse) {
        super(bilnummer, pris, antPas);
        this.batteriStorrelse = batteriStorrelse;
    }
    
    public int batteriStorrelse() {
        return batteriStorrelse;
    }

}

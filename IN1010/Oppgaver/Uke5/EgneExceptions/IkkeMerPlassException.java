public class IkkeMerPlassException extends Exception {
    
    public IkkeMerPlassException(String boktittel) {
        this.super("Er ikke mer plass, " + boktittel + " kan ikke settes inn i bokhyllen");
    }
}

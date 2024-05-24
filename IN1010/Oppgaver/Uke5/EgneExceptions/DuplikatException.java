public class DuplikatException {
    
    public DuplikatException(String boktittel) {
        super("Boken " + boktittel + " finnes allerede i bokhyllen");
    }
}

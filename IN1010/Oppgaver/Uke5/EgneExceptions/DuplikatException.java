package IN1010.Oppgaver.Uke5.EgneExceptions;

public class DuplikatException extends Exception {
    public DuplikatException(String boktittel) {
        super("Boken " + boktittel + " finnes allerede i bokhyllen.");r
    }
}

package IN1010.Oppgaver.EgneExceptions;

public class DuplikatException extends Exception {
    public DuplikatException(String boktittel) {
        super("Boken " + boktittel + " finnes allerede i bokhyllen.");
    }
}

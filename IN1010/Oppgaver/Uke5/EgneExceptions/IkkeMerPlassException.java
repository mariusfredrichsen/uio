package IN1010.Oppgaver.Uke5.EgneExceptions;

public class IkkeMerPlassException extends Exception {
    public IkkeMerPlassException(String boktittel) {
        super("Det er ikke mer plass og det gjelder boken: " + boktittel);
    }

}
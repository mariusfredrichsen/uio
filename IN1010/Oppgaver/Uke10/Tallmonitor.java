package IN1010.Oppgaver.Uke10;

public class Tallmonitor {
    int detMinste;
    int detStorste;
    boolean invariant;

    public boolean settMinste(int tall) {
        if (tall < detStorste) {
            detMinste = tall;
            return true;
        }
        return false;
        

    }

    public boolean settStorste(int tall) {
        if (tall > detMinste) {
            detStorste = tall;
            return true;
        }
        return false;

    }
}

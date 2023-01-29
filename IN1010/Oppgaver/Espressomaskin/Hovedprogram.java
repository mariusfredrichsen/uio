package IN1010.Oppgaver.Espressomaskin;

public class Hovedprogram {
    public static void main(String[] args) {
        EspressoMaskin hovedmaskin = new EspressoMaskin();
        hovedmaskin.fyllVann(200);
        hovedmaskin.lagLungo();
        hovedmaskin.lagLungo();
    }
}

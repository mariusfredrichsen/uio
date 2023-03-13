package IN1010.Oppgaver.Uke1.Bil;

public class Bil {
    String merke;
    String eier;

    public Bil(String mrk, String e) {
        merke = mrk;
        eier = e;
    }

    public void skrivUt() {
        System.out.println("Eier: " + eier + ", Merke: "+ merke);
    }
}

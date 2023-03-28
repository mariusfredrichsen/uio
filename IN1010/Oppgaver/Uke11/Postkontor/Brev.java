package IN1010.Oppgaver.Uke11.Postkontor;

public class Brev extends Post {
    public Brev (String mottaker, String beskrivelse) {
        super(beskrivelse,mottaker);
    }

    public String toString() {
        return mottaker + " har mottatt " + beskrivelse + " av typen Brev";
    }
}

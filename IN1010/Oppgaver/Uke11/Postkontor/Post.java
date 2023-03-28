package IN1010.Oppgaver.Uke11.Postkontor;

public class Post {
    String beskrivelse;
    String mottaker;

    public Post(String beskrivelse, String mottaker) {
        this.beskrivelse = beskrivelse;
        this.mottaker = mottaker;
    }

    public String toString() {
        return mottaker + " har mottatt " + beskrivelse + " av typen Post";
    }
}

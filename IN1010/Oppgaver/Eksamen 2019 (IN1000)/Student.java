import java.util.HashMap;


public class Student {
    String brukernavn;
    String navn;
    HashMap<String,Integer> obligResultater;


    public Student(String brukernavn, String navn) {
        this.brukernavn = brukernavn;
        this.navn = navn;
        obligResultater = new HashMap<String,Integer>();
    }

    public void registrer(String oblig, int resultat) {
        obligResultater.put(oblig, resultat);
    }

    public boolean altGodkjent(int antObliger) {
        boolean out = true;
        for (String obligId : obligResultater.keySet()) {
            if (obligResultater.get(obligId) != 1) out = false;
        }

        return out && obligResultater.size() < antObliger;
    }

    public String hentBrukernavn() {
        return brukernavn;
    }
}

import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Oblig {
    static int teller = 0;
    int obligId;
    String frist;
    boolean rettet;

    public Oblig(String frist) {
        this.frist = frist;
        obligId = teller++;
        rettet = false;
    }

    public boolean klarForRetting(String dato) {
        return dato.compareTo(frist) < 0 && !rettet;
    }

    public HashMap<String, String> hentBesvarelser() {
        HashMap<String,String> out = new HashMap<String,String>();
        String filnavn = obligId + ".txt";
        try {
            File fil = new File(filnavn);
            Scanner scan = new Scanner(fil);
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(" ");
                out.put(line[0], line[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil");
        } 
        return out;
    }

    public HashMap<String,Integer> fordelRetting(HashMap<String,String> besvarelser, Retter[] rettere) {
        HashMap<String,Integer> out = new HashMap<String,Integer>();
        int i = 0;
        for (String brukernavn : besvarelser.keySet()) {
            out.put(brukernavn, rettere[i++].vurder(besvarelser.get(brukernavn)));
            if (i > rettere.length) {
                i = 0;
            }
        }

        return out;
    }
}

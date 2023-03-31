import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SubsekvensRegister {
    ArrayList<HashMap<String,Subsekvens>> hashBeholder = new ArrayList<>();

    public ArrayList<HashMap<String,Subsekvens>> hentListe() {
        return hashBeholder;
    }

    public void settInn(HashMap<String,Subsekvens> hash) {
        hashBeholder.add(hash);
    }

    public HashMap<String,Subsekvens> taUt() {
        try {
            return hashBeholder.remove(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int hentAnt() {
        return hashBeholder.size();
    }

    public static HashMap<String,Subsekvens> lesFil(File fil) {
        Scanner scan = null;
        try {
            scan = new Scanner(fil);
        } catch (FileNotFoundException e) {}

        HashMap<String,Subsekvens> hash = new HashMap<>();
        while(scan.hasNextLine()) {
            String linje = scan.nextLine();
            for (int i = 0; i < linje.length()-2; i++) {
                String subsekvens = linje.substring(i, i+3);
                hash.put(subsekvens,new Subsekvens(subsekvens, 1));
            }
        }
        return hash;
    }

    public static HashMap<String,Subsekvens> slaaSammen(HashMap<String,Subsekvens> hash1, HashMap<String,Subsekvens> hash2) {
        for (String subsekvens : hash2.keySet()) {
            if (hash1.containsKey(subsekvens)) hash1.get(subsekvens).oekAntall(hash2.get(subsekvens).hentAntall());
            else hash1.put(subsekvens, hash2.get(subsekvens));
        }
        
        return hash1;
    }
}

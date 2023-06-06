import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SubsekvensRegister {
    ArrayList<HashMap<String,Subsekvens>> hashBeholder = new ArrayList<>();

    public void settInn(HashMap<String,Subsekvens> hash) {
        hashBeholder.add(hash);
    }

    public ArrayList<HashMap<String,Subsekvens>> hentBeholder() {
        return hashBeholder;
    }

    public HashMap<String,Subsekvens> taUt() {
        try {
            return hashBeholder.remove(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int antHash() {
        return hashBeholder.size();
    }

    public static HashMap<String,Subsekvens> lesFil(String filNavn) {
        HashMap<String,Subsekvens> hash = new HashMap<>();

        Scanner scan = null;
        try {
            scan = new Scanner(new File(filNavn));
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }

        while (scan.hasNextLine()) {
            String linje = scan.nextLine();
            for (int i = 0; i < linje.length()-2; i++) {
                String subsekvens = linje.substring(i, i+3);
                hash.put(subsekvens, new Subsekvens(subsekvens));
            }
        }

        return hash;
    }

    public static HashMap<String,Subsekvens> slaaSammen(HashMap<String,Subsekvens> hash1, HashMap<String,Subsekvens> hash2) {
        //gaar igjennom en av hashene og sjekker alle subsekvensene
        for (String subsekvens : hash2.keySet()) { 
            if (hash1.containsKey(subsekvens)) {
                //hvis den andre inneholder subsekvensen tar den ut Subsekvens objektet og oeker med den andres antall forekomster
                hash1.get(subsekvens).endreAntall(hash2.get(subsekvens).hentAntall()); 
            } else {
                hash1.put(subsekvens, hash2.get(subsekvens));
            }
        }
        return hash1;
    }

}

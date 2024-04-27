import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;


public class SubsekvensRegister {
    ArrayList<HashMap<String, Subsekvens>> subsekvenser;

    public SubsekvensRegister() {
        subsekvenser = new ArrayList<>();
    }

    public static HashMap<String, Subsekvens> lesFil(String filnavn) {
        File fil;
        Scanner scan;
        try {
            fil = new File(filnavn);
            scan = new Scanner(fil);
        } catch (Exception e) {
            throw new IllegalArgumentException("Kunne ikke lese filen");
        }

        HashMap<String, Subsekvens> subsekvens = new HashMap<>();
        while (scan.hasNextLine()) {
            String linje = scan.nextLine();
            for (int i = 0; i < linje.length()-2; i++) {
                String subLinje = linje.substring(i, i+3);
                if (!subsekvens.containsKey(subLinje)) {
                    subsekvens.put(subLinje, new Subsekvens(subLinje, 1));
                }
            }
        }
        scan.close();
        return subsekvens;

    }

    public static HashMap<String, Subsekvens> slaaSammen(HashMap<String, Subsekvens> subsekvens1, HashMap<String, Subsekvens> subsekvens2) {
        for (String subsekvens : subsekvens2.keySet()) {
            if (subsekvens1.containsKey(subsekvens)) {
                subsekvens1.get(subsekvens).økAntall(subsekvens2.get(subsekvens).hentAntall());
            } else {
                subsekvens1.put(subsekvens, subsekvens2.get(subsekvens));
            }
        }
        return subsekvens1;
    }

    public void settInnSubsekvens(HashMap<String, Subsekvens> subsekvens) {
        subsekvenser.add(subsekvens);
    }

    public HashMap<String, Subsekvens> taUtSubsekvens() {
        return subsekvenser.remove(0);
    }

    public int antallSubsekvenser() {
        return subsekvenser.size();
    }
}

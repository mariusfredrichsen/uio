import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint {
    Rute[][] ruter;

    Labyrint(String filnavn) {
        Scanner scan = null;

        try {
            scan = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil");
            System.exit(1);
        }

        String[] radKol = scan.nextLine().strip().split(" ");
        int rad = Integer.parseInt(radKol[0]);
        int kol = Integer.parseInt(radKol[1]);

        ruter = new Rute[rad][kol];

        //gaar igjennom resten av filene og lager Rute objekter
        for (int r = 0; r < rad; r++) {
            char[] linje = scan.nextLine().toCharArray();

            for (int k = 0; k < kol; k++) {
                if (linje[k] == '#') {
                    ruter[r][k] = new SortRute(r, k, this);
                } else if (linje[k] == '.' && (k == 0 || k == kol-1 || r == 0 || r == rad-1)) {
                    ruter[r][k] = new Aapning(r, k, this);
                } else if (linje[k] == '.') {
                    ruter[r][k] = new HvitRute(r, k, this);
                }
            }
        }

        //gaar igjennom hele rutenettet for aa koble sammen rutene
        for (int r = 0; r < rad; r++) {
            for (int k = 0; k < kol; k++) {
                try {
                    ruter[r][k].settNord(ruter[r-1][k]);
                } catch (IndexOutOfBoundsException e) {
                    ruter[r][k].settNord(null);
                } try {
                    ruter[r][k].settVest(ruter[r][k-1]);
                } catch (IndexOutOfBoundsException e) {
                    ruter[r][k].settVest(null);
                } try {
                    ruter[r][k].settSyd(ruter[r+1][k]);
                } catch (IndexOutOfBoundsException e) {
                    ruter[r][k].settSyd(null);
                } try {
                    ruter[r][k].settOest(ruter[r][k+1]);
                } catch (IndexOutOfBoundsException e) {
                    ruter[r][k].settOest(null);
                }
            }
        }
    }

    public void finnUtveiFra(int rad, int kol) {
        //gjoer alle booleans i rutene om til false
        for (int r = 0; r < ruter.length; r++) {
            for (int k = 0; k < ruter[r].length; k++) {
                ruter[r][k].ikkeBesoekt();;
            }
        }
        
        //bruker finn paa en rute med fra som null
        ruter[rad][kol].finn(null);
    }


    public String toString() {
        System.out.println("Slik ser labyrinten ut:");
        String labyrinten = "";
        for (int rad = 0; rad < ruter.length; rad++) {
            for (int kol = 0; kol < ruter[rad].length; kol++) {
                if (ruter[rad][kol] instanceof SortRute) {
                    labyrinten += ruter[rad][kol];
                } else {
                    labyrinten += ruter[rad][kol];
                }
            }
            labyrinten += "\n\n";
        }
        return labyrinten;
    }
}
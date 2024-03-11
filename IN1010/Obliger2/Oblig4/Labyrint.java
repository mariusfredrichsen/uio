import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Labyrint {
    Rute[][] rutenett;
    int antRad;
    int antKol;

    public Labyrint(String filnavn) {
        this.lagRutenett(filnavn);
        this.kobleRuter();
    }

    public void lagRutenett(String filnavn) {
        File fil;
        Scanner scan;
                
        try {
            fil = new File(String.format("labyrinter/%s", filnavn));
            scan = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil");
            return;
        }

        String[] dim = scan.nextLine().strip().split(" ");
        this.antRad = Integer.parseInt(dim[0]);
        this.antKol = Integer.parseInt(dim[1]);
        this.rutenett = new Rute[this.antRad][this.antKol];

        int rad = 0;
        while (scan.hasNext()) {
            String linje = scan.nextLine().strip();
            for (int kol = 0; kol < this.antKol; kol++) {
                Rute rute;
                
                // bestemmer hvilken type rute som skal bli satt inn paa [rad][kol]
                if (linje.charAt(kol) == '.') {
                    if (kol == 0 || kol == this.antKol-1 || rad == 0 || rad == this.antRad-1) rute = new Aapning(rad, kol, this);
                    else rute = new HvitRute(rad, kol, this);
                } else rute = new SortRute(rad, kol, this);
                this.rutenett[rad][kol] = rute;
            }
            rad++;
        }

        scan.close();
    }

    public void leggTilNaboer(int rad, int kol) {
        Rute rute;
        for (int r = -1; r < 2; r++) {
            for (int k = -1; k < 2; k++) {
                if (Math.abs(r) + Math.abs(k) != 0 && Math.abs(r) + Math.abs(k) != 2 && rad+r >= 0 && rad+r < this.antRad && kol+k >= 0 && kol+k < this.antKol) {
                    rute = this.rutenett[rad+r][kol+k];
                    this.rutenett[rad][kol].leggTilNabo(rute);
                }
            }
        }
    }

    public void kobleRuter() {
        for (int rad = 0; rad < this.antRad; rad++) {
            for (int kol = 0; kol < this.antKol; kol++) {
                this.leggTilNaboer(rad, kol);
            }
        }
    }

    public void finnUtveiFra(int rad, int kol) {
        this.rutenett[rad][kol].finn(null);
    }

    public String toString() {
        String out = "";

        for (int rad = 0; rad < this.antRad; rad++) {
            for (int kol = 0; kol < this.antKol; kol++) {
                out += this.rutenett[rad][kol].toString();
            }
            out += "\n";
        }

        return out;
    }
}

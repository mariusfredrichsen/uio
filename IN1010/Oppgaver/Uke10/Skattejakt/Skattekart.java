package IN1010.Oppgaver.Uke10.Skattejakt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Skattekart {
    private char[][] kart;
    private int hoyde;
    private int bredde;
    boolean funnet = false;

    public Skattekart(char[][] kart, int hoyde, int bredde) {
        this.kart = kart;
        this.hoyde = hoyde;
        this.bredde = bredde;
    }

    static Skattekart lesInn(File fil) throws FileNotFoundException {
        Scanner scana = new Scanner(fil);

        String[] forsteTalla = scana.nextLine().split(" ");
        char[][] kart = new char[Integer.parseInt(forsteTalla[0])][Integer.parseInt(forsteTalla[1])];
        for (int b = 0; b < Integer.parseInt(forsteTalla[0]); b++) {
            String linje = scana.nextLine();
            for (int h = 0; h < linje.length()-1; h++) {
                kart[b][h] = linje.charAt(h);
            }
        }
        Skattekart skattekart = new Skattekart(kart, Integer.parseInt(forsteTalla[0]), Integer.parseInt(forsteTalla[1]));
        return skattekart;
    }

    public void skrivUt() {
        for (int b = 0; b < bredde; b++) {
            for (int h = 0; h < hoyde; h++) {
                if (funnet && kart[b][h] == 'X') {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean sjekk(int rad, int kol) {
        if (kart[rad][kol] == 'X') return true;
        return false;
    }
}

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class Sudoku {
    //sjekker raden, returner true hvis det er gyldig, ellers false
    public static boolean gyldigRad(int nummer, int rad, int kol, int[][] brett) {
        for (int tmpKol = 0; tmpKol < 9; tmpKol++) {
            if (brett[rad][tmpKol] == nummer && tmpKol != kol) {
                return false;
            }
        }
        return true;
    }
    //sjekker kolonnen, returner true hvis den ikke inneholder nummer i kolonnen
    public static boolean gyldigKol(int nummer, int rad, int kol, int[][] brett) {
        for (int tmpRad = 0; tmpRad < 9; tmpRad++) {
            if (brett[tmpRad][kol] == nummer && tmpRad != rad) {
                return false;
            }
        }
        return true;
    }
    //sjekker 3x3ruten
    public static boolean gyldigRute(int nummer, int rad, int kol, int[][] brett) {
        int radRute = Math.floorDiv(rad, 3);
        int kolRute = Math.floorDiv(kol, 3);

        for (int tmpRad = radRute * 3; tmpRad < radRute * 3 + 3; tmpRad++) {
            for (int tmpKol = kolRute * 3; tmpKol < kolRute * 3 + 3; tmpKol++) {
                if (tmpRad != rad && tmpKol != kol && nummer == brett[tmpRad][tmpKol]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void skrivUt(int[][] brett) {
        for (int rad = 0; rad < brett.length; rad++) {
            if (rad % 3 == 0) {
                for (int i = 0; i < brett.length-1; i++) System.out.print("---");
                System.out.print("-");
                System.out.println();
            }
            for (int kol = 0; kol < brett[rad].length; kol++) {
                if (kol % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(brett[rad][kol] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        for (int i = 0; i < brett.length-1; i++) System.out.print("---");
        System.out.print("-");
    }

    public static void loes(int[][] brett) {
        
    }
    

    public static void main(String[] args) {
        int[][] brett = new int[9][9]; // lager et 9x9 brett
        
        //legger inn tilfeldige nummerere i tilfeldige ruter
        for (int rad = 0; rad < brett.length; rad++) {
            for (int kol = 0; kol < brett[rad].length; kol++) {
                if (new Random().nextInt(100) < 50) {
                    int ruteVerdi = new Random().nextInt(9) + 1;
                    if (gyldigRad(ruteVerdi, rad, kol, brett) && gyldigKol(ruteVerdi, rad, kol, brett) && gyldigRute(ruteVerdi, rad, kol, brett)) {
                        brett[rad][kol] = ruteVerdi;
                    }
                } else {
                    brett[rad][kol] = 0;
                }
            }
        }

        JFrame vindu = new JFrame("Sudoku");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(9,9));

        //lager en array med Labels slik at jeg kan faa tak i dem senere
        JLabel[][] ruter = new JLabel[9][9];
        for (int rad = 0; rad < brett.length; rad++) {
            for (int kol = 0; kol < brett[rad].length; kol++) {
                JLabel rute = new JLabel(brett[rad][kol] + "");
                rute.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                rute.setPreferredSize(new Dimension(50,50));
                rute.setVerticalAlignment(JLabel.CENTER);
                rute.setHorizontalAlignment(JLabel.CENTER);
                ruter[rad][kol] = rute;
                rutenett.add(rute);
            }
        }
        vindu.add(rutenett);

        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);

        skrivUt(brett);
    }
}

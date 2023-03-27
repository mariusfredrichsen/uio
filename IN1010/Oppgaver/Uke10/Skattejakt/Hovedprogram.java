package IN1010.Oppgaver.Uke10.Skattejakt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hovedprogram {
    public static void main(String[] args) {
        Skattekart skattekart = null;
        try {
            File fil = new File("kart.txt");
            skattekart = Skattekart.lesInn(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Fil finnes ikke");
        }

        Scanner scan = new Scanner(System.in);
        String input = "";
        skattekart.skrivUt();
        System.out.println("Finn skatten! Gjett koordinater paa formatet (kol rad) (a for aa avslutte)");
        while (!skattekart.funnet || !input.equals("a")) {
            skattekart.skrivUt();
            input = scan.nextLine();

            int rad = Integer.parseInt(input.split(" ")[1]);
            int kol = Integer.parseInt(input.split(" ")[0]);

            if (skattekart.sjekk(rad, kol)) {
                System.out.println("Du fant skatten!");
                skattekart.skrivUt();
                continue;
            }

            System.out.println("Ingen skatt der! Gjett igjen;");
        }
    }
}

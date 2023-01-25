package IN1010.Oppgaver.EnkeltKoesystem;
import java.util.Scanner;

public class KoProgram {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        KoSystem ko_system = new KoSystem();
        int input = 0;

        while (input != 4) {
            System.out.println("**MENY FOR BILLETTSYSTEM**");
            System.out.println("1 - Trekk ny kolapp.");
            System.out.println("2 - Betjen kunde.");
            System.out.println("3 - Print antall kunder i kø.");
            System.out.println("4 - Avslutt.\n");

            input = Integer.parseInt(scan.nextLine());

            if (input == 1) {
                ko_system.trekkKoLapp();
            } else if (input == 2) {
                ko_system.betjenKunde();
            } else if (input == 3) {
                ko_system.printKunderIKo();
            }
        }

        System.out.println("Programmet avsluttes.");
    }
}

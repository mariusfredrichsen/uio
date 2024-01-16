import java.util.Scanner;


public class KøProgram {
    public static void main(String[] args) {
        KøSystem køsystem = new KøSystem();
        Scanner scan = new Scanner(System.in);
        
        String input = "";
        while (!input.equals("4")) {
            System.out.println("\n**MENY FOR BILLETTSYSTEM** \n1 - Trekk ny kolapp.\n2 - Betjen kunde.\n3 - Print antall kunder i kø.\n4 - Avslutt.");
            input = scan.nextLine();
            if (input.equals("1")) {
                køsystem.tekkKøLapp();
                System.out.println("Det står " + (køsystem.antKunder()-1) + " foran deg.");

            } else if (input.equals("2")) {
                køsystem.betjenKunde();
            
            } else if (input.equals("3")) {
                køsystem.printKunderIKø();
            
            }
        }
    }
}

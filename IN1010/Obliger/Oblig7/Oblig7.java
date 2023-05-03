import java.util.Scanner;

public class Oblig7 {
    public static void main(String[] args) {
        Labyrint labyrint = new Labyrint("labyrinter/" + args[0]);
        
        Scanner scan = new Scanner(System.in);
        String input = "";
        
        while(true) {
            System.out.println(labyrint);
            System.out.println("\nSkriv inn koorinater <rad> <kolonne> ('-1' for aa avslutte)");
            input = scan.nextLine();
            if (input.equals("-1")) break;
            System.out.println("Aapninger:");
            
            String[] radKol = input.strip().split(" ");
            int rad = Integer.parseInt(radKol[0]);
            int kol = Integer.parseInt(radKol[1]);
            labyrint.finnUtveiFra(rad, kol);

            System.out.println("\nTrykk enter for aa fortsette");
            input = scan.nextLine();
        }
        scan.close();
    }
}

import java.util.Scanner;



public class Differanse {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Oppgi verdien til x:");
        int x = scan.nextInt();

        System.out.println("Oppgi verdien til y:");
        int y = scan.nextInt();
        
        System.out.println("Differansen mellom x og y er " + (x-y) + ".");
    }
}

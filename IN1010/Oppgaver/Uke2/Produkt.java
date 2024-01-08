import java.util.Scanner;



public class Produkt {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Oppgi verdien til x:");
        int x = scan.nextInt();

        System.out.println("Oppgi verdien til y:");
        int y = scan.nextInt();

        System.out.println("Produktet av x og y er " + (x * y) + ".");
    }
}

package IN1010.Oppgaver;
import java.util.Scanner;

public class Produkt {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Oppgi verdien til x: ");
        String input1 = scan.nextLine();

        System.out.println("Oppgi verdien til y: ");
        String input2 = scan.nextLine();

        int i1 = Integer.parseInt(input1);
        int i2 = Integer.parseInt(input2);

        System.out.println("Produktet av x og y er " + (i1*i2));
    }
}
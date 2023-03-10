package IN1010.Oppgaver.Uke2;
import java.util.Scanner;

public class Differanse {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Oppgi verdien til x: ");
        String input1 = scan.nextLine();

        System.out.println("Oppgi verdien til y: ");
        String input2 = scan.nextLine();

        int i1 = Integer.parseInt(input1);
        int i2 = Integer.parseInt(input2);

        System.out.println("Differansen mellom x og y er " + (i1-i2));
    }
}

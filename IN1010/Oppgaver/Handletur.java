package IN1010.Oppgaver;
import java.util.Scanner;

public class Handletur {
    public static void main(String[] args) {
        int broed = 20;
        int melk = 15;
        int ost = 40;
        int youghurt = 12;
        int sum = 0;
        int input;

        Scanner scan = new Scanner(System.in);
        
        System.out.println("Hei! Velkommen til IFI-butikken.");
        System.out.println("Hvor mange brød vil du ha?");
        input = Integer.parseInt(scan.nextLine());
        sum += input*broed;

        System.out.println("Hvor mange melk vil du ha?");
        input = Integer.parseInt(scan.nextLine());
        sum += input*melk;

        System.out.println("Hvor mange ost vil du ha?");
        input = Integer.parseInt(scan.nextLine());
        sum += input*ost;

        System.out.println("Hvor mange youghurt vil du ha?");
        input = Integer.parseInt(scan.nextLine());
        sum += input*youghurt;

        System.out.println("Du skal betale: " + sum + "kr");
    }
}

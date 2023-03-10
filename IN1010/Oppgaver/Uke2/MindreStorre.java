package IN1010.Oppgaver.Uke2;
import java.util.Scanner;

public class MindreStorre {
    public static void main(String[] args) {
        int input1;

        Scanner scan = new Scanner(System.in);
        System.out.println("Skriv inn et tall: ");
        input1 = Integer.parseInt(scan.nextLine());

        if (input1 < 10) {
            System.out.println("Tallet er mindre enn 10");
        } else if (input1 >= 10 && input1 <= 20) {
            System.out.println("Tallet er storre enn 10 og mindre enn 20");
        } else if (input1 > 20) {
            System.out.println("Tallet er storre en 20");
        }
    }
}

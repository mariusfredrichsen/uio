import java.util.Scanner;

public class Hovedprogram {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Labyrint labyrint;

        System.out.print("Velg fil:\n>");
        String input = scan.nextLine();
        labyrint = new Labyrint(input);

        System.out.println(labyrint);

        System.out.print("Velg posisjon (rad,kol):\n>");
        String[] pos = scan.nextLine().strip().split(",");
        labyrint.finnUtveiFra(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));



        scan.close();
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FraFil {
    public static void main(String[] args) {
        File fil;
        Scanner scan = null;
        try {
            fil = new File("tekst.txt");
            scan = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen.");
        }

        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
    }
}

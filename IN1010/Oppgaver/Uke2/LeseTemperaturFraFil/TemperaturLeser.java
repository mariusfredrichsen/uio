import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class TemperaturLeser {
    public static void main(String[] args) {
        String[] strenger = new String[12];

        Scanner scan = null;
        File fil = null;
        try {
            fil = new File("temperatur.txt");
            scan = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }

        int i = 0;
        while (scan.hasNextLine()) {
            strenger[i++] = scan.nextLine();
        }

        for (String element : strenger) {
            System.out.println(element);
        }
    }
}

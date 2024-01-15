import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Linjenummer {
    public static void main(String[] args) {
        File fil = null;
        Scanner scan = null;
        try {
            fil = new File("input.txt");
            scan = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }


        int i = 1;
        while (scan.hasNextLine()) {
            System.out.println(String.format("# %s: ", i++) + scan.nextLine());
        }
    }    
}

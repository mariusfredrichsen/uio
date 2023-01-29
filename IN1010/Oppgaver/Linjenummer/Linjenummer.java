import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Linjenummer {
    public static void main(String[] args) throws FileNotFoundException {
        File fil = new File("linjenummer.txt");
        Scanner scan = new Scanner(fil);
        int teller = 0;
        
        while (scan.hasNextLine()) {
            teller++;
            System.out.println("# " + teller + ": " + scan.nextLine());
        }
    }
}

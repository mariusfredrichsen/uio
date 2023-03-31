import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;

public class Oblig5Del2 {
    public static void main(String[] args) {
        
        Scanner scan = null;
        try {
            scan = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {}

        while(scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        

    }
}

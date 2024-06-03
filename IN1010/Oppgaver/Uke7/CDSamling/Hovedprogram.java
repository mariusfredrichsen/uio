import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Collections;

public class Hovedprogram {
    public static void main(String[] args) {
        File fil;
        Scanner scan;
        try {
            fil = new File(args[0]);
            scan = new Scanner(fil);
        } catch (Exception e) {
            return;
        }

        ArrayList<CDAlbum> albumer = new ArrayList<>();
        while (scan.hasNextLine()) {
            String[] linje = scan.nextLine().strip().split(",");
            albumer.add(new CDAlbum(linje[0], linje[1], linje[2]));
        }

        Collections.sort(albumer);

        for (CDAlbum album : albumer) {
            System.out.println(album);
        }

    }
}

package IN1010.Oppgaver.Uke11.FinnOrd;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Worlde {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("mangeOrd.txt"));
        ArrayList<String> ordListe = new ArrayList<>();

        while (scan.hasNext()) {
            ordListe.add(scan.nextLine());
        }

        Monitor monitor = new Monitor((String[]) ordListe.toArray());


    }
}

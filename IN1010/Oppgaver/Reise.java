package IN1010.Oppgaver;

import java.util.ArrayList;
import java.util.Scanner;

public class Reise {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int first_input = scan.nextInt(); //antall reiser
        for (int i = 0; i < first_input; i++) { 
            ArrayList<String> liste = new ArrayList<>(); //holder paa reisene
            int nextInt = scan.nextInt();
            for (int j = 0; j < nextInt+1; j++) { //antall destinasjoner
                String linje = scan.nextLine();
                if (!liste.contains(linje) && !linje.equals("")) {
                    liste.add(linje);
                }
            }
            System.out.println(liste.size());
        }
    }
}

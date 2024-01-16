import java.util.HashMap;
import java.util.Scanner;

public class Handletur {
    public static void main(String[] args) {
        HashMap<String,Integer> varer = new HashMap<String,Integer>();
        varer.put("Broed", 20);
        varer.put("Melk", 15);
        varer.put("Ost", 40);
        varer.put("Youghurt", 12);

        Scanner scan = new Scanner(System.in);
        int kostnad = 0;

        for (String vare : varer.keySet()) {
            System.out.println("Hvor mange " + vare + " vil du ha?");
            kostnad += varer.get(vare)*scan.nextInt();
        }

        System.out.println("Du skal betale: " + kostnad + "kr.");

    }
}

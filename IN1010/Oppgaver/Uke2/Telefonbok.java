import java.util.HashMap;
import java.util.Scanner;


public class Telefonbok {
    public static void main(String[] args) {
        HashMap<String,String> telefonbok = new HashMap<String,String>();
        
        telefonbok.put("Arne", "22334455");
        telefonbok.put("Lisa", "95959595");
        telefonbok.put("Jonas", "97959795");
        telefonbok.put("Peder", "12345678");

        Scanner scan = new Scanner(System.in);
        String input = "";

        while (!input.equals("q")) {
            System.out.println("Skriv et navn for å søke etter nummeret deres (q for å avslutte):");
            input = scan.nextLine();
            System.out.println(telefonbok.get(input));
        }

        for (String navn : telefonbok.keySet()) {
            System.out.println(navn + " : " + telefonbok.get(navn));
        }
    }
}

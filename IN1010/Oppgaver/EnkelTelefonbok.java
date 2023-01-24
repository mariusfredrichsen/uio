package IN1010.Oppgaver;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.HashMap;
import java.util.Scanner;

public class EnkelTelefonbok {
    public static void main(String[] args) {
        HashMap<String,String> telefonBok = new HashMap<String,String>();
        Scanner scan = new Scanner(System.in);

        telefonBok.put("Arne", "22334455");
        telefonBok.put("Lisa", "95959595");
        telefonBok.put("Jonas", "97959795");
        telefonBok.put("Peder", "12345678");
        
        int loop = 0;
        String input;

        while (loop == 0) {
            System.out.println("Skriv inn 0 for å soeke i telefonboken eller 1 for å avslutte: ");
            loop = Integer.parseInt(scan.nextLine());
            if (loop == 0) {
                System.out.println("Skriv inn et navn for nummeret deres: ");
                input = scan.nextLine();
                if (telefonBok.containsKey(input)) {
                    System.out.println("Her er nummeret til " + input + ": " + telefonBok.get(input));
                }
            } 
        }
        
        for (String navnekey : telefonBok.keySet()) {
            System.out.println("Navn: " + navnekey + ", nummer: " + telefonBok.get(navnekey));
        }
    }
}

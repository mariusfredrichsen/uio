package IN1010.Oppgaver.Uke4.BilerOgBusser;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<String, ArrayList> eiere = new HashMap<>();
        Scanner scan = new Scanner(System.in);

        String input = "";
        while (!input.equals("4")) {
            System.out.println("Du kan gjøre følgende:\n1.Register nytt kjøretøy.\n2.Skriv informasjon om et registernummer.\n3. Skriv ut registeravgiften til en eier.\n4.Avslutt.");
            input = scan.nextLine();

            if (input.equals("1")) {
                System.out.println("Skriv inn navnet på eier:\n");
                String tempEier = scan.nextLine();
                System.out.println("Skriv inn registernummeret:\n");
                String tempRegisternummer = scan.nextLine();
                System.out.println("Skriv inn fabrikkmerket:\n");
                String tempFabrikkmerke = scan.nextLine();

                ArrayList<Kjoretoy> kjoretoy = new ArrayList<>();
                System.out.println("Hva slags type kjoretoy skal du registrere?\n1. Buss.\n2. Personbil.\n3. Varebil.\n");
                input = scan.nextLine();
                if (input.equals("1")) {
                    System.out.println("Du maa ogsaa oppgi hvor mange sitteplasser det skal vaere:\n");
                    input = scan.nextLine();
                    Buss tempKjoretoy = new Buss(tempRegisternummer, tempFabrikkmerke, tempEier, Integer.parseInt(input));
                    kjoretoy.add(tempKjoretoy);
                }
                eiere.put(tempEier, kjoretoy);
            }

        }
    }
}

package IN1010.Oppgaver.Uke2;
import java.util.Scanner;

public class SkattIRuritania {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double inntekt;
        double skatt = 0;

        System.out.println("Hva er din inntekt? ");
        inntekt = Double.parseDouble(scan.nextLine());

        if (inntekt < 10000) {
            skatt = inntekt*0.1;
        } else if (inntekt >= 10000) {
            skatt = 10000*0.1 + (inntekt-10000)*0.3;
        }

        System.out.println("Skatten din blir på " + skatt);
    }    
}

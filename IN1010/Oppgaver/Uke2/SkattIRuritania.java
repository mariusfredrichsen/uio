import java.util.Scanner;


public class SkattIRuritania {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double inntekt = scan.nextFloat();
        double oldInntekt = inntekt;

        if (inntekt < 10000) {
            inntekt *= 0.9;
        } else if (inntekt >= 10000) {
            inntekt = 9000 + ((inntekt-10000)*0.7);
        }

        System.out.println("Skatt som betales: " + (oldInntekt-inntekt));
    }
}

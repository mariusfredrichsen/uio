package IN1010.Oppgaver.Uke8;

public class BinaerUtskrift {
    public static void skrivUtBinaer(int n) {
        if (n == 0) {
            return;
        } else if (n % 2 == 1) {
            skrivUtBinaer(n / 2);
            System.out.print(1);
        } else {
            skrivUtBinaer(n / 2);
            System.out.print(0);
        }
    }

    public static void main(String[] args) {
        skrivUtBinaer(4);
    }
}

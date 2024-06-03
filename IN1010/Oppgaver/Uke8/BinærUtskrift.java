public class BinærUtskrift {
    public static void main(String[] args) {
        skrivUtBinaer(27);
    }

    public static void skrivUtBinaer(int n) {
        if (n == 0) {
            System.out.println("0");
            return;
        } else if (n > 1) {

            skrivUtBinaer(n / 2);
        }
        System.out.print(n % 2);

    }
}

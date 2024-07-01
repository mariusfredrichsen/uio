public class RekursivUtskrift {
    public static void main(String[] args) {
        rekursivUtskrift(1);
    }

    public static void rekursivUtskrift(int i) {
        System.out.println(i);
        if (i < 10) {
            rekursivUtskrift(i + 1);
        }
    }
}

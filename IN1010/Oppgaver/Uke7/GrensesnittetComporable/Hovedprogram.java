public class Hovedprogram {
    public static void main(String[] args) {
        Katt[] katter = {
            new Katt("1", 1), 
            new Katt("2", 2),
            new Katt("3", 3),
            new Katt("4", 4),
            new Katt("5", 5),
            new Katt("6", 6)
        };

        Katt eldst = katter[0];
        for (Katt katt : katter) {
            if (katt.compareTo(eldst) > 0) {
                eldst = katt;
            }
        }
        System.out.println(eldst);
    }
}

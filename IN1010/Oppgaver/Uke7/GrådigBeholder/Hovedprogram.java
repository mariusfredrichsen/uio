public class Hovedprogram {
    public static void main(String[] args) {
        GrådigBeholder<Katt> beholder = new GrådigBeholder<Katt>();
        Katt[] katter = {
            new Katt("1", 1), 
            new Katt("2", 2),
            new Katt("3", 3),
            new Katt("4", 4),
            new Katt("5", 5),
            new Katt("6", 6)
        };

        for (Katt katt : katter) {
            System.out.println(beholder.settInn(katt));
        }
    }
}

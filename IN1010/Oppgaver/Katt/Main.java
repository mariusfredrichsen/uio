package IN1010.Oppgaver.Katt;

public class Main {
    public static void main(String[] args) {
        Katt a = new Katt("a", 11);
        Katt b = new Katt("b", 13);
        Katt c = new Katt("c", 9);
        Katt d = new Katt("d", 15);

        System.out.println(a.compareTo(b));
        System.out.println(a.compareTo(c));
        System.out.println(a.compareTo(d));

        SortertArrayList<Katt> list = new SortertArrayList<>();
        list.settInn(a);
        list.settInn(b);
        list.settInn(c);
        list.settInn(d);

        for (int i = 0; i < 4; i++) {
            System.out.println(list.hentUt());
        }
    }
}

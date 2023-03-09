package IN1010.Oppgaver.Katt;

public class Main {
    public static void main(String[] args) {
        Katt a = new Katt("a", 10);
        Katt b = new Katt("b", 10);
        Katt c = new Katt("c", 9);
        Katt d = new Katt("d", 11);

        System.out.println(a.compareTo(b));
        System.out.println(a.compareTo(c));
        System.out.println(a.compareTo(d));
    }
}

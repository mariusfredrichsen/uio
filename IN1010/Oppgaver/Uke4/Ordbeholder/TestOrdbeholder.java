package IN1010.Oppgaver.Uke4.Ordbeholder;

public class TestOrdbeholder {
    public static void sjekk(String hva, boolean test) {
        if (!test) {
            System.out.println("Sjekken " + hva + " feilet!");
            System.exit(0);
        }
    }

    public static void testPopTom() {
        Ordbeholder ordBeholder = new Ordbeholder();
        sjekk("test ordbeholder returnerer null", ordBeholder.pop() == null);
        System.out.println("Alt Riktig");
    }

    public static void testSettInnReturnTrue() {
        Ordbeholder ordBeholder = new Ordbeholder();
        sjekk("test ordbeholder returnerer true", ordBeholder.settInn("ord") == true);
        System.out.println("Alt Riktig");
    }

    public static void testSettInnReturnFalse() {
        Ordbeholder ordBeholder = new Ordbeholder();
        ordBeholder.settInn("ord");
        sjekk("test ordbeholder returnerer false", ordBeholder.settInn("ord") == false);
        System.out.println("Alt Riktig");
    }

    public static void testAntallOrd() {
        Ordbeholder ordBeholder = new Ordbeholder();
        ordBeholder.settInn("1");
        ordBeholder.settInn("2");
        ordBeholder.settInn("3");
        sjekk("test ordbeholder returnerer 3", ordBeholder.antallOrd() == 3);
        System.out.println("Alt Riktig");
    }

    public static void testPopReturn() {
        Ordbeholder ordBeholder = new Ordbeholder();
        ordBeholder.settInn("1");
        ordBeholder.settInn("2");
        ordBeholder.settInn("3");
        sjekk("test ordbeholder returnerer stringen 3", ordBeholder.pop().equals("3"));
        System.out.println("Alt Riktig");
    }

    public static void main(String[] args) {
        testAntallOrd();
        testPopReturn();
        testPopTom();
        testSettInnReturnFalse();
        testSettInnReturnTrue();
    }
}

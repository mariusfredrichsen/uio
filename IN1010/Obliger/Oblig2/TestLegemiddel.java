package IN1010.Obliger.Oblig2;

public class TestLegemiddel {
    public static void sjekk(String testNavn, boolean testVerdi) {
        if (!testVerdi) {
            System.out.println(testNavn + " feilet.");
            System.exit(0);
        } else {
            System.out.println("Alt riktig!");
        }
    }

    public static void testHentPris() {
        Narkotisk nark = new Narkotisk("test", 100, 2.5, 1, 7); //Har ikke noe å si hvilke av subklassene jeg bruker siden alle har de samme metodene.
        sjekk("testHentPris == 100", nark.hentPris() == 100);
    }

    public static void testSettNyPris() {
        Narkotisk nark = new Narkotisk("test", 100, 2.5, 1, 7);
        nark.settNyPris(101);
        sjekk("testSettNyPris == 101", nark.hentPris() == 101);
    }

    public static void testId() {
        Narkotisk nark = new Narkotisk("test", 100, 2.5, 1, 7);
        sjekk("testId == 1", nark.id == 1);
    }
    public static void main(String[] args) {
        //tester
        testHentPris();
        testSettNyPris();
        testId();
    }
}

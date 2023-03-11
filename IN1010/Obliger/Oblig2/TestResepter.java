public class TestResepter {
    public static void sjekk(String testNavn, boolean testVerdi) {
        if (!testVerdi) {
            System.out.println(testNavn + " feilet.");
            System.exit(0);
        } else {
            System.out.println("Alt riktig!");
        }
    }

    public static void testHentId() {
        Narkotisk nark = new Narkotisk("test", 100, 2.5, 7);
        Lege lege = new Lege("test");
        BlaaResept blaaResept = new BlaaResept(nark, lege, 1, 1);
        sjekk("testHentId == 0", blaaResept.hentId() == 0);
    }

    public static void testHentLegemiddel() {
        Narkotisk nark = new Narkotisk("test", 100, 2.5, 7);
        Lege lege = new Lege("test");
        BlaaResept blaaResept = new BlaaResept(nark, lege, 1, 1);
        sjekk("testHentLegemiddel == nark", blaaResept.hentLegemiddel() == nark);
    }

    public static void testHentLege() {
        Narkotisk nark = new Narkotisk("test", 100, 2.5, 7);
        Lege lege = new Lege("test");
        BlaaResept blaaResept = new BlaaResept(nark, lege, 1, 1);
        sjekk("testHentLege == lege", blaaResept.hentLege() == lege);
    }

    public static void testHentPasientId() {
        Narkotisk nark = new Narkotisk("test", 100, 2.5, 7);
        Lege lege = new Lege("test");
        BlaaResept blaaResept = new BlaaResept(nark, lege, 1, 1);
        sjekk("testHentPasientId == 1", blaaResept.hentPasientId() == 1);
    }

    public static void testHentReit() {
        Narkotisk nark = new Narkotisk("test", 100, 2.5, 7);
        Lege lege = new Lege("test");
        BlaaResept blaaResept = new BlaaResept(nark, lege, 1, 1);
        sjekk("testHentReit == 1", blaaResept.hentReit() == 1);
    }

    public static void testBruk() {
        Narkotisk nark1 = new Narkotisk("test", 100, 2.5, 7);
        Lege lege = new Lege("test");
        BlaaResept blaaResept = new BlaaResept(nark1, lege, 1, 1);
        sjekk("testBruk gir true", blaaResept.bruk() == true);
        sjekk("testBruk gir false", blaaResept.bruk() == false);

        Narkotisk nark2 = new Narkotisk("test", 100, 2.5, 7);
        MilResept milResept = new MilResept(nark2, lege, 1);
        milResept.bruk();
        milResept.bruk();
        milResept.bruk();
        sjekk("testBruk, mil resept har 3 reit", milResept.bruk() == false);
    }

    public static void testFarge() {
        Narkotisk nark = new Narkotisk("test", 100, 2.5, 7);
        Lege lege = new Lege("test");
        BlaaResept blaaResept = new BlaaResept(nark, lege, 1, 1);
        sjekk("testFarge gir Blaa", blaaResept.farge().equals("Blaa"));

        MilResept milResept = new MilResept(nark, lege, 1);
        sjekk("testFarge gir Hvit", milResept.farge().equals("Hvit"));
    }

    public static void testPrisAaBetale() {
        Narkotisk nark1 = new Narkotisk("test", 100, 2.5, 7);
        Lege lege = new Lege("test");
        BlaaResept blaaResept = new BlaaResept(nark1, lege, 1, 1);
        sjekk("testPrisAaBetale, blaa resept gir 25", blaaResept.prisAaBetale() == 25);

        Narkotisk nark2 = new Narkotisk("test", 100, 2.5, 7);
        MilResept milResept = new MilResept(nark2, lege, 1);
        sjekk("testPrisAaBetale, mil resept gir 0", milResept.prisAaBetale() == 0);

        Narkotisk nark3 = new Narkotisk("test", 100, 2.5, 7);
        PResept pResept1 = new PResept(nark3, lege, 1, 1);
        sjekk("testPrisAaBetale, p-resept gir 0", pResept1.prisAaBetale() == 0);
        
        Narkotisk nark4 = new Narkotisk("test", 200, 2.5, 7);
        PResept pResept2 = new PResept(nark4, lege, 1, 1);
        sjekk("testPrisAaBetale, p-resept gir 92", pResept2.prisAaBetale() == 200-108);
    }

    public static void main(String[] args) {
        testHentId();
        testHentLegemiddel();
        testHentLege();
        testHentPasientId();
        testHentReit();
        testBruk();
        testFarge();
        testPrisAaBetale();
    }
}

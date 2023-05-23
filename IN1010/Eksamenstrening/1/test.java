public class test {
    public static void main(String[] args) {
        Bilkollektiv asd = new Bilkollektiv(5);
        asd.alleBiler[4] = new ElPersonbil("1", 1, 5, 1);
        asd.alleBiler[2] = new ElPersonbil("2", 2, 5, 1);
        asd.alleBiler[1] = new Personbil("4", 4, 5);
        asd.alleBiler[3] = new Personbil("7", 7, 5);
        asd.alleBiler[0] = new ElPersonbil("10", 10, 5, 1);

        asd.lagBilPris();

        Bil peker = asd.hode;
        for (int i = 0; i < 5; i++) {
            System.out.println(peker);
            peker = peker.neste;
        }

        TastaturDialog gui = new TastaturDialog();
        System.out.println(asd.velgBil(gui));
    }
}

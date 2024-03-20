/**
 * Egg
 */
interface Egglegger {

    int antallEgg();
} 

class Hare {
    final String navn;
    Hare (String n) { navn = n; }
}

class Påskehare extends Hare implements Egglegger {
    int antall;
    Påskehare (String n, int ant) { super(n); antall = ant; }

    @Override public int antallEgg() { return antall; }
}

class Høne implements Egglegger {
    @Override public int antallEgg() { return 1; }
}

class BrukHare {
    public static void main(String[] args) {
        Hare skogshare = new Hare("Bjørk");
        Hare sesonghare = new Påskehare("Påsen", 999);
        Høne burhøne = new Høne();

        Påskehare h1 = sesonghare;
        Egglegger h2 = sesonghare;
        Egglegger h3 = burhøne;
        Egglegger h4 = (Egglegger)skogshare;
        Påskehare h5 = (Påskehare)sesonghare;
        Egglegger h6 = (Hare)sesonghare;
        Hare h7 = sesonghare;
        Egglegger h8 = (Egglegger)sesonghare;
        Egglegger h9 = skogshare;
        Påskehare h10 = (Påskehare)skogshare;
    }
}
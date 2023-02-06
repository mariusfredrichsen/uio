import java.io.FileNotFoundException;

class Hovedprogram {
    public static void main(String[] args) throws FileNotFoundException {
        Studentsystem system = new Studentsystem();
        system.lesFil("emnestudenter.txt");
        system.mestStudenter();
        system.mestFag();
        system.alleSomTarEtFag();
        system.leggTilStudent("Ida","INF1000");
        system.fjernStudent("Ida", "INF1000");
        system.leggTilFag("IN1030");
    }
}

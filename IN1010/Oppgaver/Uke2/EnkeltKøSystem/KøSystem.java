import java.util.ArrayList;


public class KøSystem {
    ArrayList<KøLapp> kølapper;

    public KøSystem() {
        kølapper = new ArrayList<KøLapp>();
    }


    public void tekkKøLapp() {
        KøLapp kølapp = new KøLapp();
        System.out.println("Du har fått tildelt billettnr " + kølapp.hentNummer() + ".");
        kølapper.add(kølapp);
    }

    public void betjenKunde() {
        if (kølapper.size() == 0) {
            System.out.println("Det er ingen i køen");
        } else {
            System.out.println("Betjener kunde med kølapp: " + kølapper.get(0).hentNummer());
        }
    }

    public int antKunder() {
        return kølapper.size();
    }

    public void printKunderIKø() {
        System.out.println("Det står " + antKunder() + " i kø");
    }

}

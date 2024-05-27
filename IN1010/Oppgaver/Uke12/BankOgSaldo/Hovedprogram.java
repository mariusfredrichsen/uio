import java.util.ArrayList;

public class Hovedprogram {
    public static void main(String[] args) {

        Bank bank = new Bank();

        ArrayList<Tråd> trådListe = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            trådListe.add(new Tråd(bank));
        }

        for (Tråd tråd : trådListe) {
            tråd.run();
        }



    }
}

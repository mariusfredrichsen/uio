package IN1010.Oppgaver.Uke2.Brev;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Brev {
    String avsender;
    String mottaker;
    ArrayList<String> linjer = new ArrayList<String>();

    public Brev(String avsender, String mottaker) {
        this.avsender = avsender;
        this.mottaker = mottaker;
    }

    public void skrivLinje(String linje) {
        linjer.add(linje);
    }

    public void lesBrev() {
        System.out.println("Hei, " + mottaker);
        System.out.println("");
        for (String linje : linjer) {
            System.out.println(linje);
        }
        System.out.println("");
        System.out.println("Hilsen fra,");
        System.out.println(avsender);
    }
}

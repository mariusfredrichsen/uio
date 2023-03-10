package IN1010.Oppgaver.Uke2.EnkeltKoesystem;
import java.util.AbstractSequentialList;
import java.util.ArrayList;

public class KoSystem{
    ArrayList<KoLapp> ko_lapper = new ArrayList<KoLapp>();
    int billettnr = 0;

    //Lager et nytt objekt av KoLapp, printer ut informasjon om den og legger den til i listen over koLapper.     
    public void trekkKoLapp(){
        billettnr += 1;
        KoLapp ko_lapp = new KoLapp(billettnr);
        ko_lapper.add(ko_lapp);
        System.out.println("Du har fatt tildelt bilettnr " + ko_lapp.hentNummer() + ".\nDet staar " + (ko_lapper.size() - 1) + " foran deg.");
    }

    //Henter ut, printer ut informasjon og fjerner den forste kolappen i lista. Gir feilmelding dersom ingen kunder staar i ko.
    public void betjenKunde(){
        System.out.println("Kunde med bilettnr. " + ko_lapper.get(0).hentNummer() + " er betjent.");
        ko_lapper.remove(0);
    }

    //Returnerer antall kunder som er i ko.
    public int antKunder(){
        return 1;

    }

    //Printer alle kunder i ko
    public void printKunderIKo(){
        System.out.println("Det starr " + ko_lapper.size() + " personer i ko.");
    }

}
public class KullListe extends Kull {
    Hund mor, far;
    KullListe(Hund mor, Hund far) {
        super(mor, far);
    }

    public void 
    
}

public class Billett {
    int billettId;
    Sal sal;
    Sete setePlass;
    Kunde kunde;
    boolean standard;
    int pris;

    Billett(int bilettId, Sal sal, Sete setePlass, Kunde kunde, boolean standard) {
        this.bilettId = bilettId;
        this.sal = sal;
        this.setePlass = setePlass;
        this.kunde = kunde;
        this.standard = standard;

        if (kunde.alder > 5 && kunde.alder < 16) {

        } 
    }
}

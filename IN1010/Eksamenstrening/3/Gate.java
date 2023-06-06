public class Gate {
    String navn;
    StedListe stedListe;

    Gate(String navn) {
        this.navn = navn;
        stedListe = new StedListe();
    }

    Maaling[] finnMestStoyANT() {
        double hoyest = 0;
        Sted peker = null;
        for (Sted sted : stedListe) {
            double sum = 0;
            for (Maaling maaling : sted.mestStoy.hentMaaling()) {
                if (maaling != null) {
                    sum += maaling.stoyNivaa;
                }
            }
            if (sum > hoyest) {
                hoyest = sum;
                peker = sted;
            }
        }
        return peker.mestStoy.hentMaaling();
    }

    double maksStoyR() {
        return stedListe.maksListeR();
    }


    
}

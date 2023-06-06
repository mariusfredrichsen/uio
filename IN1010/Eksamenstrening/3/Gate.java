public class Gate {
    String navn;
    StedListe stedListe;

    Gate(String navn) {
        this.navn = navn;
        stedListe = new StedListe();
    }

    Maaling[] finnMestStoyANT() {
        int hoyest = 0;
        Sted peker = null;
        for (Sted sted : stedListe) {
            int sum = 0;
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
        return sted.mestStoy.hentMaaling();
    }

    double maksStoyR() {
        return stedListe.maksStoyR();
    }


    
}

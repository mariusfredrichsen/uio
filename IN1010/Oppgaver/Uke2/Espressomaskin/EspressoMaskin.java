package IN1010.Oppgaver.Uke2.Espressomaskin;

class EspressoMaskin {
    static int vannmengde;
    // Lag espresso dersom det er nok vann
    public void lagEspresso() {
        if (vannmengde >= 40) {
            vannmengde -= 40;
            System.out.println("Her har du espressoen din :)");
        } else {
            System.out.println("Er ikke nok vann desverre");
        }
    }

    // Lag lungo dersom det er nok vann
    public void lagLungo() {
        if (vannmengde >= 110) {
            vannmengde -= 110;
            System.out.println("Her har du lungoen din :)");
        } else {
            System.out.println("Er ikke nok vann desverre");
        }
    }

    // Fyll på et gitt antall milliliter vann, dersom det er plass
    public void fyllVann(int ml) {
        vannmengde += ml;
    }

    // Les av målestrekene på vanntanken og tilgjengelig vann i ml
    public int hentVannmengde() {
        return vannmengde;
    }
}
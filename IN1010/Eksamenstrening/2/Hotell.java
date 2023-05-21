class Hotell {
    final int MAX_ANT_SENGEPLASSER = 8;
    Rom forsteRom = null;

    Reservasjon forsteR = null;
    Reservasjon sisteR = null;

    void tildelRom(String navn) {
        Reservasjon peker = forsteR;
        while (peker.nesteR != null) {
            if (peker.nesteR.gjest.navn.equals(navn)) break;
            peker = peker.nesteR;
        } //naa ligger den paa reserasjonen foer den vi er etter

        if (peker.nesteR == null && peker.gjest.navn != navn) {
            System.out.println("Gjest har ikke en reservasjon");
            return;
        }

        //ta ut reservasjon
        if (peker == sisteR) {
            forsteR = null;
            sisteR = null;
        } else {
            peker.nesteR = peker.nesteR.nesteR;
        }

        for (int i = peker.onsketSengeplass; i < MAX_ANT_SENGEPLASSER; i++) {
            Rom rom = finnRom(i, peker.kjokken);
            if (rom != null) {
                System.out.println(rom);
                peker.gjest.settRom(rom);
                rom.ledig = false;
                return;
            }
        }
        if (peker.kjokken) {
            System.out.println(String.format("Fant ingen ledige rom som tilpasses oensket om %s antall sengeplasser og kjokken.", peker.onsketSengeplass));
        } else {
            System.out.println(String.format("Fant ingen ledige rom som tilpasses oensket om %s antall sengeplasser.", peker.onsketSengeplass));
        }
    }

    Rom finnRom(int antSeng, boolean kjokken) {
        Rom peker = forsteRom;
        while (peker.neste != null) {
            if (kjokken && !(peker instanceof Kjokken)) {
                peker = peker.neste;
                continue;
            }
            if (peker.antSengeplass == antSeng) return peker;
            peker = peker.neste;
        }
        return null;
    }
}

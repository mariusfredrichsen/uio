import java.util.Iterator;

class Hotell implements Iterable<Rom> {
    final int MAX_ANT_SENGEPLASSER = 8;
    final int ANTALL_ETASJER;

    Rom[] forsteRomEtasje;

    Rom forsteRom = null;

    Reservasjon forsteR = null;
    Reservasjon sisteR = null;

    Hotell (int ANTALL_ETASAJER) {
        this.ANTALL_ETASJER = ANTALL_ETASAJER;
        forsteRomEtasje = new Rom[ANTALL_ETASJER + 1];
    }

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
        taUtReservasjon(peker);

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

    void taUtReservasjon(Reservasjon r) {
        Reservasjon peker = forsteR;
        while (peker.nesteR != null) {
            if (peker == r) {
                if (peker == forsteR) {
                    peker = peker.nesteR;
                    if (peker == null) return;
                    peker.forrigeR = null;
                    return;         
                } else if (peker == sisteR) {
                    peker.forrigeR.nesteR = null;
                    sisteR = peker.forrigeR;
                    return;
                } else {
                    peker.nesteR.forrigeR = peker.forrigeR;
                    peker.forrigeR.nesteR = peker.nesteR;
                    return;
                }
            }
            peker = peker.nesteR;
        }
    }

    public class HotellIterator implements Iterator<Rom> {
        for (Rom rom : forsteRomEtasje) {

        }
    }

    @Override
    public Iterator<Rom> iterator() {
        return new HotellIterator();
    }


    int[] ledigeRom() {
        for (Rom forsteRom : forsteRomEtasje) {
            for (Rom rom : )
        }
    }
}

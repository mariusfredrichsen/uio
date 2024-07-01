import java.util.ArrayList;
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
        String liste = new "ASD";
        liste.
    }

    public class HotellIterator implements Iterator<Rom> {
        Rom denne;
        int etasjeTeller;

        HotellIterator() {
            etasjeTeller = 0;
            denne = forsteRomEtasje[etasjeTeller];
        }
        
        @Override
        public boolean hasNext() {
            if (denne != null) {
                if (++etasjeTeller < ANTALL_ETASJER) {
                    denne = forsteRomEtasje[etasjeTeller];
                    if (denne == null) {
                        return false;
                    } else {
                        return false;
                    }
                }
                return false;
            }
            return true;
        }

        @Override
        public Rom next() {
            Rom peker = denne;
            peker = peker.neste;
            return peker;
        }
    }

    @Override
    public Iterator<Rom> iterator() {
        return new HotellIterator();
    }


    int[] ledigeRom() {
        int[] antLedigRom = new int[MAX_ANT_SENGEPLASSER];
        for (int i = 1; i <= MAX_ANT_SENGEPLASSER; i++) {
            for (Rom rom : this) {
                if (rom.antSengeplass == i) {
                    antLedigRom[i - 1]++;
                }
            }
        }
        return antLedigRom;
    }
}

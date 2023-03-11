public class Lege implements Comparable<Lege> {
    String navn;

    IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();

    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    public IndeksertListe<Resept> hentResepter() {
        return utskrevneResepter;
    }

    public HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if ((legemiddel instanceof Narkotisk)) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        HvitResept hvitR = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(hvitR);
        return hvitR;
    }

    public MilResept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if ((legemiddel instanceof Narkotisk)) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        MilResept milR = new MilResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(milR);
        return milR;
    }

    public PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if ((legemiddel instanceof Narkotisk)) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        PResept pR = new PResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(pR);
        return pR;
    }

    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (!(this instanceof Spesialist) && (legemiddel instanceof Narkotisk)) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        BlaaResept blaaR = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(blaaR);
        return blaaR;
    }


    public String toString() {
        return navn;
    }

    public int compareTo(Lege lege) {
        int iTeller = 0;
        while (true) { //sjekker for hver av bokstavene helt til det ikke er mulig å sjekke flere
            try {
                if (navn.charAt(iTeller) > lege.navn.charAt(iTeller)) {
                    return 1;
                } else if (navn.charAt(iTeller) < lege.navn.charAt(iTeller)) {
                    return -1;
                }
            } catch (StringIndexOutOfBoundsException e) {
                break; //går ut av loopen slik at vi kan sjekke hvem av dem som har flere bokstaver
            }
                iTeller++;
        }
        if (navn.length() == lege.navn.length()) {
            return 0;
        } else if (navn.length() > lege.navn.length()) {
            return -1;
        } else {
            return 1;
        }
    }
}

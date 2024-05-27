import java.util.Iterator;

public class Tog implements Iterable<Skinnegående> {
    Skinnegående første;
    Skinnegående siste;
    int størrelse;

    public Tog() {
        første = null;
        siste = null;
        størrelse = 0;
    }

    // 3b
    public void leggTil(Skinnegående s) {
        if (første == null) {
            første = s;
            siste = s;
        } else {
            siste.neste = s;
            s.forrige = siste;
            siste = s;
        }
        størrelse++;
    }

    // 3c
    public Skinnegående taUt(Skinnegående s) {
        if (størrelse == 1) {
            første = null;
            siste = null;    
        } else if (første == s) {
            første = første.neste;
            første.forrige.neste = null;
            første.forrige = null;
        } else if (siste == s) {
            siste = siste.forrige;
            siste.neste.forrige = null;
            siste.neste = null;
        } else {
            Skinnegående peker = første;
            while (peker != s) {
                peker = peker.neste;
            }
            peker.forrige.neste = peker.neste;
            peker.neste.forrige = peker.forrige;
            peker.forrige = null;
            peker.neste = null;
        }

        // er garantert til å være i listen
        return s;
    }

    public Skinnegående finnOgTaUt(String id) {
        Skinnegående peker = første;
        while (peker.neste != null) {
            if (peker.hentId().equals(id)) {
                return this.taUt(peker);
            }
            peker = peker.neste;
        }
        return null;
    }

    public void leggTilForan(Skinnegående s) {
        if (første == null) {
            første = s;
            siste = s;
        } else {
            første.forrige = s;
            s.neste = første;
            første = s;
        }
    }

    public int antPassasjervogner() {
        int teller = 0;
        for (Skinnegående s : this) {
            if (s instanceof Passasjervogn) teller++;
        }
        return teller;
    }

    public Passasjervogn[] hentPassasjervogner() {
        int antPassasjervogner = this.antPassasjervogner();
        Passasjervogn[] vogner = new Passasjervogn[antPassasjervogner];
        int teller = 0;

        for (Skinnegående s : this) {
            if (s instanceof Passasjervogn) {
                vogner[teller++] = s;
            }
        }
        
        return vogner;
    }

    public void sjekkSporvidde() throws FeilSporvidde {
        if (første == null || første == siste) return;
        Skinnegående peker = første;
        while (peker.neste != null) {
            if (peker.hentId() != peker.neste.hentId()) throw new FeilSporvidde();
            peker = peker.neste;
        }
    }

    public void leggTilSikker(Skinnegående s) throws FeilSporvidde {
        if (første == null) this.leggTil(s);
        else {
            this.sjekkSporvidde();
            if (s.hentSporvidde() != første.hentSporvidde()) throw new FeilSporvidde();
            this.leggTil(s);
        }
    }

    public void sjekkSporviddeR() throws FeilSporvidde {
        if (første != null) første.sjekkSporvidde();
    }

    public Iterator<Skinnegående> iterator() {
        return new TogIterator();
    }

    class TogIterator implements Iterator<Skinnegående> {
        Skinnegående denne;

        public TogIterator() {
            denne = første;
        }

        @Override
        public boolean hasNext() {
            return this.denne != null;
        }

        @Override
        public Skinnegående next() {
            Skinnegående tmp = denne;
            denne = denne.neste;
            return tmp;
        }
    }


}

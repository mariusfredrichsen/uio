import java.util.Iterator;

public class Kolonne implements Iterable<LederBil> {
    Bil første;
    Bil siste;

    public Kolonne() {
        this.første = null;
        this.siste = null;
    }

    public void settInn(Bil b) throws FeilBilException {
        if (this.første == null) {
            if (b instanceof Lederbil) {
                this.første = b;
                this.siste = b;
            } else {
                // er ikke leder vil
                throw new FeilBilException("Første bil er ikke en leder bil");
            }
        } else {
            this.siste.neste = b;
            b.forrige = this.siste;
            this.siste = b;
        }
    }

    public Bil taUt(Bil b) throws FeilBilException {
        if (b.kolonne != this) throw new FeilBilException("Bil finnes ikke inn i kolonnen");
        if (this.første == b) {
            if (this.første.neste != null) {
                if (this.første.neste instanceof LederBil) {
                    this.første = this.første.neste;
                    this.første.forrige.neste = null;
                    this.første.forrige = null;
                } else {
                    throw new FeilBilException("Nest første bil er ikke en leder bil");
                }
            } else {
                this.første = null;
                this.site = null;
            }
        } else if (this.siste == b) {
            this.siste = this.siste.forrige;
            this.siste.neste.forrige = null;
            this.siste.neste = null;
        } else {
            b.forrige.neste = b.neste;
            b.neste.forrige = b.forrige;
            b.neste = null;
            b.forrige = null;
        }

        return b;
    }

    public Iterator<LederBil> iterator() {
        return new KolonneIterator();
    }

    private class KolonneIterator extends Iterator<LederBil> {
        Bil denne;

        public KolonneIterator() {
            this.denne = første;
        }

        public boolean hasNext() {
            return this.denne != null;
        }

        public LederBil next() {
            Bil tmp = this.denne;
            if (!(tmp.neste instanceof LederBil)) throw new NoSuchElementException();
            this.denne = (LederBil) this.denne.neste;
            return tmp;
        }
    }

    public int finnMaksFartR() {
        if (this.første != null) return this.første.finnMaksFartR();
        return null;
    }

}

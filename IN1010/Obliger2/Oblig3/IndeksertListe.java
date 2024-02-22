


public class IndeksertListe <E> extends LenkeListe<E> {
    public IndeksertListe() {
        super();
    }

    public void leggTil (int pos, E x) { 
        Node nyNode = new Node(x);
        if (pos < 0 || pos > stoerrelse) {
            throw new UgyldigListeindeks(pos);
        }

        if (hode == null) {
            hode = nyNode;
            hale = nyNode;
        } else if (pos == stoerrelse) {
            leggTil(x);
            stoerrelse -= 1;
        } else if (pos == 0) {
            hode.forrige = nyNode;
            nyNode.neste = hode;
            hode = nyNode;
        } else {
            Node peker = hode;
            for (int i = 0; i < pos; i++) {
                peker = peker.neste;
            }
            nyNode.neste = peker;
            nyNode.forrige = peker.forrige;
            peker.forrige.neste = nyNode;
            peker.forrige = nyNode;
        }
        stoerrelse += 1;
    }
    
    public void sett (int pos, E x) { 
        if (pos < 0 || pos >= stoerrelse) {
            throw new UgyldigListeindeks(pos);
        }
        Node peker = hode;
        for (int i = 0; i < pos; i++) {
            peker = peker.neste;
        }
        peker.data = x;
    }
    
    public E hent (int pos) { 
        if (pos < 0 || pos >= stoerrelse) {
            throw new UgyldigListeindeks(pos);
        }
        Node peker = hode;
        for (int i = 0; i < pos; i++) {
            peker = peker.neste;
        }
        return peker.data;
    }
    
    public E fjern (int pos) { 
        if (pos < 0 || pos >= stoerrelse) {
            throw new UgyldigListeindeks(pos);
        }
        
        Node peker = hode;
        if (pos == 0) {
            return fjern();
        } else if (pos == stoerrelse-1) {
            peker = hale;
            hale = hale.forrige;
            hale.neste = null;
            stoerrelse--;
            return peker.data;
        } else {
            for (int i = 0; i < pos; i++) {
                peker = peker.neste;
            }
            peker.forrige.neste = peker.neste;
            peker.neste.forrige = peker.forrige;
            stoerrelse--;
            return peker.data;
        }

        
        

    }
}
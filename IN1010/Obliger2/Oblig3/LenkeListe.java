


abstract class LenkeListe <E> implements Liste <E>{
    int stoerrelse;
    Node hode;
    Node hale;
    
    public LenkeListe() {
        this.stoerrelse = 0;
        this.hode = null;
    }

    class Node {
        E data;
        Node neste;
        Node forrige;

        public Node(E data) {
            this.data = data;
            this.neste = null;
            this.forrige = null;
        }
    }

    @Override
    public int stoerrelse() {
        return stoerrelse;
    }

    @Override
    public void leggTil(E x) {
        Node nyNode = new Node(x);
        if (hode == null) {
            hode = nyNode;
            hale = nyNode;
        } else {
            hale.neste = nyNode;
            nyNode.forrige = hale;
            hale = nyNode;
        }
        this.stoerrelse += 1;
    }

    @Override
    public E hent() {
        return hode.data;
    }

    @Override
    public E fjern() {
        if (hode == null) {
            throw new UgyldigListeindeks(0);
        }

        E data = hode.data;
        hode = hode.neste;
        if (hode == null) {
            hale = null;
        } else {
            hode.forrige = null;
        }
        stoerrelse -= 1;

        return data;
    }
    
    @Override
    public String toString() {
        String slange = "";
        Node peker = hode;
        while (peker != null) {
            slange += peker.data.toString();
            peker = peker.neste;
        }
        return slange;
    }
}

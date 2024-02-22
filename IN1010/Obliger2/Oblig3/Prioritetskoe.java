


public class Prioritetskoe<E extends Comparable<E>> extends LenkeListe<E>{
    public Prioritetskoe() {
        super();
    }

    @Override
    public void leggTil(E x) {
        if (hode == null || hale.data.compareTo(x) < 0) {
            super.leggTil(x);
        } else if (hode.data.compareTo(x) > 0) {
            Node nyNode = new Node(x);
            hode.forrige = nyNode;
            nyNode.neste = hode;
            hode = nyNode;
            stoerrelse++;
        } else {
            Node nyNode = new Node(x);
            Node peker = hode;
            while (peker.neste != null && peker.data.compareTo(x) < 0) {
                peker = peker.neste;
            }
            peker = peker.forrige;

            peker.neste.forrige = nyNode;
            nyNode.forrige = peker;
            nyNode.neste = peker.neste;
            peker.neste = nyNode;
            stoerrelse++;
        }
    }
}

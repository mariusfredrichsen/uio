


public class Stabel<E> extends LenkeListe<E>{
    
    public Stabel() {
        super();
    }

    @Override
    public void leggTil(E x) {
        Node nyNode = new Node(x);
        if (hode == null) {
            hode = nyNode;
            hale = nyNode;
        } else {
            hode.forrige = nyNode;
            nyNode.neste = hode;
            hode = nyNode;
        }
        stoerrelse += 1;
    }
}

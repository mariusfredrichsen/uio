import java.util.Iterator;

public class DobbellenketListe <E> implements Iterable<E> {
    
    Node hode;
    Node hale;
    int størrelse;

    public DobbellenketListe() {
        this.hode = null;
    }

    public void settInn(E element) {
        Node nyNode = new Node(element);

        if (hode == null) {
            hode = nyNode;
            hale = nyNode;
        } else {
            Node peker = hode;
            while (peker.neste != null) {
                peker = peker.neste;
            }

            peker.neste = nyNode;
            nyNode.forrige = peker;
            hale = nyNode;
        }

        størrelse++;
    }

    public Iterator<E> iterator() {
        return new DobbellenketListeIterator();
    }

    class DobbellenketListeIterator implements Iterator<E> {
        Node denne;

        public DobbellenketListeIterator() {
            this.denne = hale;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public E next() {
            E tmp = denne.element;
            denne = denne.forrige;
            return tmp;
        }
    }





    private class Node {
        E element;
        Node neste;
        Node forrige;

        public Node(E element) {
            this.element = element;
            this.neste = null;
            this.forrige = null;
        }
    }
}

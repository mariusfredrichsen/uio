import java.util.Iterator;

public class LenkeListe<E> implements Iterable<E> {

    Node hode;
    int lengde = 0;

    public void settInn(E element, Node peker) {
        if (peker == null) {
            peker = hode;
        }
        if (hode == null) {
            hode = new Node(element);
            lengde++;
        } else if (peker.neste == null) {
            peker.neste = new Node(element);
            lengde++;
        } else {
            settInn(element, peker.neste);
        }
    }

    private class Node {
        E element;
        Node neste;

        public Node(E element) {
            this.element = element;
            this.neste = null;
        }
    }

    public Iterator<E> iterator() {
        return new LenkeListeIterator();
    }

    class LenkeListeIterator implements Iterator<E> {
        Node denne;

        public LenkeListeIterator() {
            denne = hode;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public E next() {
            E tmp = denne.element;
            denne = denne.neste;
            return tmp;
        }
    }
}

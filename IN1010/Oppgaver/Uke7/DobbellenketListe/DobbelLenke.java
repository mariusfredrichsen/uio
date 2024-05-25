public class DobbelLenke <E> {
    
    Node hode;
    int lengde = 0;

    public void settInn(E element) {
        Node nyNode = new Node(element);
        if (this.hode == null) {
            hode = nyNode;
        } else {
            Node peker = hode;
            while (peker.neste != null) {
                peker = peker.neste;
            }
            peker.neste = nyNode;
            nyNode.forrige = peker;
        }
        lengde++;
    }


    private class Node {
        E element;
        Node neste, forrige;

        public Node(E element) {
            this.element = element;
            this.neste = null;
            this.forrige = null;
        }
    }
}

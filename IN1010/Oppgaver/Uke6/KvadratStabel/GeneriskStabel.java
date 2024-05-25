public class GeneriskStabel <E> {
    Node hode;



    public void leggPaa(E element) {
        Node newNode = new Node(element);
        if (hode == null) {
            hode = newNode;
        } else {
            newNode.neste = hode;
            hode = newNode;
        }
    }

    public E taAv() {
        E out = hode.element;
        hode = hode.neste;
        return out;
    }

    public Boolean erTom() {
        return hode == null;
    }

    private class Node{
        private E element;
        private Node neste;

        private Node(E element) {
            this.element = element;
        }
    }


}

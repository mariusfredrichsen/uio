abstract class Lenkeliste <E> implements Liste <E> {
    Node hode = null;

    class Node {
        E data;
        Node neste = null;
        
        public Node(E data) {
            this.data = data;
        }
    }

    @Override
    public int stoerrelse() {
        int teller = 0;
        Node nesteLenke = hode;
        
        while (nesteLenke != null) {
            teller++;
            nesteLenke = nesteLenke.neste;
        }

        return teller;
    }

    @Override
    public void leggTil(E x) {
        if (hode == null) {
            hode = new Node(x);
            return;
        }
        Node nesteLenke = hode;
        while (nesteLenke.neste != null) {
            nesteLenke = nesteLenke.neste;
        }
        
        nesteLenke.neste = new Node(x);
    }

    @Override
    public E hent() {
        if (hode != null) {
            return hode.data;
        }
        return null;
    }

    @Override
    public E fjern() {
        try {
            E tempHolder = hode.data;
            hode = hode.neste;
            return tempHolder;
        } catch (NullPointerException e) {
            throw new UgyldigListeindeks(0);
        }
    }

    @Override
    public String toString() {
        String enStreng = "";
        Node nesteLenke = hode;
        while (nesteLenke != null) {
            enStreng += nesteLenke.data + " ";
            nesteLenke = nesteLenke.neste;
        }

        return enStreng;
    }
}
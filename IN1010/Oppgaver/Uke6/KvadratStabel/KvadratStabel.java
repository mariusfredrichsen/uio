public class KvadratStabel {
    Node hode;



    public void leggPaa(Kvadrat k) {
        Node newNode = new Node(k);
        if (hode != null) {
            hode = newNode;
        } else {
            newNode.neste = hode;
            hode = newNode;
        }
    }

    public Kvadrat taAv() {
        Node tmp = hode;
        hode = hode.neste;
        return tmp.kvadrat;
    }

    public Boolean erTom() {
        return hode == null;
    }
}

public class IndeksertListe <E> extends Lenkeliste <E>{
    public void leggTil(int pos, E x) {
        if (hode == null) {
            hode = new Node(x);
        } else if (pos == 0) {
            Node tempHolder = hode;
            hode = new Node(x);
            hode.neste = tempHolder;
        } else if (pos >= super.stoerrelse()) {
            super.leggTil(x);
        } else {
            Node nesteLenke = hode;
            try {
                for (int i = 0; i < pos - 1; i++) {
                    nesteLenke = nesteLenke.neste;
                }
                
                Node nesteLenkeHolder = nesteLenke.neste; 
                Node nyNode = new Node(x);
                nyNode.neste = nesteLenkeHolder;
                nesteLenke.neste = nyNode;
            } catch (NullPointerException e) {
                throw new UgyldigListeindeks(pos);
            }
        }
    }

    public void sett(int pos, E x) {
        Node nesteLenke = hode;
        try {
            for (int i = 0; i < pos - 1; i++) {
                nesteLenke = nesteLenke.neste; //har fortsatt nullste element
            }
            final Node tempNesteLenke = nesteLenke.neste; //har første element
            nesteLenke.neste = new Node(x);
            nesteLenke = nesteLenke.neste;
            nesteLenke.neste = tempNesteLenke.neste;
        } catch (NullPointerException e) {
            throw new UgyldigListeindeks(pos);
        }
    }

    public E hent(int pos) {
        Node nesteLenke = hode;

        try {
            for (int i = 0; i < pos; i++) {
                nesteLenke = nesteLenke.neste;
            }

            return nesteLenke.data;
        } catch (NullPointerException e) {
            throw new UgyldigListeindeks(pos);
        }
    }

    public E fjern(int pos) {
        Node nesteLenke = hode;
        try {
            for (int i = 0; i < pos - 1; i++) {
                nesteLenke = nesteLenke.neste;
            }
            final Node tempNesteLenke = nesteLenke.neste; //har første element
            nesteLenke.neste = tempNesteLenke.neste;
            return tempNesteLenke.data;
        } catch (NullPointerException e) {
            throw new UgyldigListeindeks(pos);
        }
    }
}

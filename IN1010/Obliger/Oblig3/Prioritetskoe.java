public class Prioritetskoe <E extends Comparable <E>> extends Lenkeliste <E>{
    /* @Override
    public void leggTil(E x) {
        super.leggTil(x);
        for (int i = 0; i < super.stoerrelse(); i++) {
            Node nesteLenke = hode;
            for (int j = 0; j < super.stoerrelse(); j++) {
                Node tempNesteLenke = nesteLenke.neste; 
                E data1 = nesteLenke.data;
                E data2 = tempNesteLenke.data;
                if (data1.compareTo(data2) > 0) {
                    nesteLenke.data = data2;
                    nesteLenke = nesteLenke.neste;
                    nesteLenke.data = data1;
                }
                nesteLenke = nesteLenke.neste;
            }
        }
    } */
    //Koden over legger den til vanlig også bare shuffler den verdiene på riktig plass
    
    @Override
    public void leggTil(E x) {
        if (hode == null) {
            hode = new Node(x);
        } else if (hode.data.compareTo(x) > 0) {
            Node tempHolder = hode;
            hode = new Node(x);
            hode.neste = tempHolder;
        } else {
            Node nesteLenke = hode;
            while (nesteLenke != null) {
                if (nesteLenke.neste == null) {
                    super.leggTil(x);
                    break;
                }
                if (nesteLenke.neste.data.compareTo(x) > -0) {
                    Node nyNode = new Node(x);
                    nyNode.neste = nesteLenke.neste;
                    nesteLenke.neste = nyNode;
                    break;
                }
                nesteLenke = nesteLenke.neste;
            }
        }
    }
}
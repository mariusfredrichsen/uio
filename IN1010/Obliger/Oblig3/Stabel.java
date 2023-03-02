public class Stabel <E> extends Lenkeliste <E> {
    @Override
    public void leggTil(E x) {
        Node tempHolder = hode;
        hode = new Node(x);
        hode.neste = tempHolder;        
    }
}

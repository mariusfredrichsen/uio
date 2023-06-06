import java.util.Iterator;

public class StedListe implements Iterable<Sted> {
    StedNode forste = null;
    StedNode siste = null;

    class StedNode {
        Sted sted;
        
        StedNode neste = null;
        StedNode forrige = null;

        StedNode(Sted sted) {
            this.sted = sted;
        }

        double maksListeR() {
            if (neste == null) {
                return sted.hoyestStoy();
            }
            double nesteTing = neste.maksListeR();
            if (nesteTing < sted.hoyestStoy()) {
                return sted.hoyestStoy();
            }
            return nesteTing;
        }
    } 

    class StedListeIterator implements Iterator<Sted> {
        StedNode denne = forste;

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public Sted next() {
            Sted ut = denne.sted;
            denne = denne.neste;
            return ut;
        }
    }

    public Iterator<Sted> iterator() {
        return new StedListeIterator();
    }

    double maksListeR() {
        return forste.maksListeR();
    }



}

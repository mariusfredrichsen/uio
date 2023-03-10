package IN1010.Oppgaver.Uke7;

import java.util.Iterator;

public class EnkelArrayListe implements Iterable <String> {
    String[] list;
    private int maxSize;
    private int size = 0;

    public EnkelArrayListe(int maxSize) {
        this.maxSize = maxSize;
        list = new String[maxSize];
    }

    //Sjekker om det er mulig å legge til så legger det til eller ikke
    public void leggTil(String theString) {
        if (size > maxSize) {
            throw new IllegalStateException("Ikke plass til flere elementer");
        }
        list[size] = theString;
        size++;
    }

    public Iterator<String> iterator() {
        return new ListeIterator();
    }

    public class ListeIterator implements Iterator<String> {
        private int counter = 0;

        public String next() {
            counter++;
            return list[counter-1];
        }

        public boolean hasNext() {
            return counter < maxSize;
        }
    }

}
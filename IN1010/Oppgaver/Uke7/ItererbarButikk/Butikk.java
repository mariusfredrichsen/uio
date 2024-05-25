import java.util.Iterator;

public class Butikk implements Iterable<Person> {
    private Person hode;



    public void entreButikk(Person p) {
        if (hode == null) {
            hode = p;
        } else {
            Person peker = hode;
            while (peker.neste != null) {
                peker = peker.neste;
            }
            // peker har siste man i koen
            peker.settInn(p);
        }
    }

    public void kassa() {
        while (hode != null) {
            System.out.println(String.format("%s kjøper %s.", hode.navn, hode.gjenstand));
            hode = hode.neste;
        }
        System.out.println("Ingen flere kunder");
    }

    public Iterator<Person> iterator() {
        return new PersonIterator();
    }

    class PersonIterator implements Iterator<Person> {
        private Person denne;

        public PersonIterator() {
            denne = hode;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public Person next() {
            Person tmp = denne;
            denne = denne.neste;
            return tmp;
        }
    }



}

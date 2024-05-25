public class GrådigBeholder <E extends Comparable<E>> {
    private E element;

    public E settInn(E element) {
        if (this.element == null) {
            this.element = element;
            return null;
        } else if (this.element.compareTo(element) <= 0) {
            E tmp = this.element;
            this.element = element;
            return tmp;
        } else {
            return null;
        }
    }

    public E hent() {
        return element;
    }
}

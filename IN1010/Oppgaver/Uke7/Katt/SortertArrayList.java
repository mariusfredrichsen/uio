package IN1010.Oppgaver.Uke7.Katt;
import java.util.ArrayList;

public class SortertArrayList <E extends Comparable<E>> {
    
    ArrayList<E> list = new ArrayList<>();

    public void settInn(E data) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(data) >= 0) {
                list.add(i, data);
                return;
            }
        }
        list.add(data);
    }

    public E hentUt() {
        return list.remove(0);
    }
}

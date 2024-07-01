public class Hovedprogram {
    public static void main(String[] args) {
        LenkeListe<Integer> liste = new LenkeListe<>();
        liste.settInn(7, null);
        liste.settInn(8, null);
        liste.settInn(9, null);
        liste.settInn(10, null);
        liste.settInn(11, null);

        for (int i : liste) {
            System.out.println(i);
        }
        
    }
}

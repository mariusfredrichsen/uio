public class Hovedprogram {
    public static void main(String[] args) {
        DobbellenketListe<String> liste = new DobbellenketListe<>();

        liste.settInn("1");
        liste.settInn("2");
        liste.settInn("3");

        for (String asd : liste) {
            System.out.println(asd);
        }
        
    }
}

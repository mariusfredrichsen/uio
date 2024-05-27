public class Godsvogn extends Vogn {
    String id;
    int sporvidde;
    int lengde;
    double maksLast;

    public Vogn(String id, int sporvidde, int lengde, double maksLast) {
        super(id, sporvidde, lengde);
        this.maksLast = maksLast;
    }
}

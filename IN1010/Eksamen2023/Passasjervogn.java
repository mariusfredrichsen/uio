public class Passasjervogn extends Vogn {
    String id;
    int sporvidde;
    int lengde;
    int maksPassasjer;

    public Vogn(String id, int sporvidde, int lengde, int maksPassasjer) {
        super(id, sporvidde, lengde);
        this.maksPassasjer = maksPassasjer;
    }
}

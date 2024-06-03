public class Passasjervogn extends Vogn {
    int maksPassasjer;

    public Passasjervogn(String id, int sporvidde, int lengde, int maksPassasjer) {
        super(id, sporvidde, lengde);
        this.maksPassasjer = maksPassasjer;
    }
}

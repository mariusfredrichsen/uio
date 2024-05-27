public abstract class Vogn extends Skinnegående {
    String id;
    int sporvidde;
    int lengde;

    public Vogn(String id, int sporvidde, int lengde) {
        super(id, sporvidde);
        this.lengde = lengde;
    }
}

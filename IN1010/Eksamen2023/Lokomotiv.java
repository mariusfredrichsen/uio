public class Lokomotiv extends Skinnegående implements Motordrevet {
    boolean fossilt;
    int trekkraft;

    public Lokomotiv(String id, int sporvidde, boolean fossilt, int trekkraft) {
        super(id, sporvidde);
    }

    public boolean fossilt() {
        return this.fossilt;
    }

    public int trekkraft() {
        return this.trekkraft;
    }
}

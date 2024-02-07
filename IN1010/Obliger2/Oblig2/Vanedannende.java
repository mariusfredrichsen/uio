


public class Vanedannende extends Legemiddel {
    public final int styrke;

    public Vanedannende(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s\nNavn: %s\nPris: %s\nVirkestoff: %s\nStyrke: %s", this.id, this.navn, this.pris, this.virkestoff, this.styrke);
    }
}

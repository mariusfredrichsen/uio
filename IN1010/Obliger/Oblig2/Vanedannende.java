public class Vanedannende extends Legemiddel {
    int styrke;
    public Vanedannende(String navn, int pris, double virkestoff, int styrke){
        super(navn,pris,virkestoff);
        this.styrke = styrke;
    }
}
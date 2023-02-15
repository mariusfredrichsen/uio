package IN1010.Obliger.Oblig2;

public class Vanedannende extends Legemiddel {
    int styrke;
    public Vanedannende(String navn, int pris, double virkestoff, int id, int styrke){
        super(navn,pris,virkestoff, id);
        this.styrke = styrke;
    }
}
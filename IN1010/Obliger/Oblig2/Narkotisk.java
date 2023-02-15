package IN1010.Obliger.Oblig2;

public class Narkotisk extends Legemiddel {
    int styrke;
    public Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn,pris,virkestoff);
        this.styrke = styrke;
    }
}

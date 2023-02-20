package IN1010.Obliger.Oblig2;

public class Vanlig extends Legemiddel {
   
    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn,pris,virkestoff);
    }
    
    public String toString() {
        return "Navn: " + navn + "\nPris: " + pris + "\nVirkestoff: " + virkestoff;
    }
}
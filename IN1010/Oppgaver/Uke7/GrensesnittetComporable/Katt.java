public class Katt implements Comparable<Katt> {
    String navn;
    int alder;

    public Katt(String navn, int alder) {
        this.navn = navn;
        this.alder = alder;
    }

    public String toString() {
        return String.format("Katt med navnet %s og alder %s", this.navn, this.alder);
    }

    public int compareTo(Katt katt) {
        return this.alder - katt.alder;
    }
}

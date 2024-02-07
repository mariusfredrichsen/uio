

public class Lege {
    String navn;

    public Lege(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return String.format("Legenavn: %s", navn);
    }
}

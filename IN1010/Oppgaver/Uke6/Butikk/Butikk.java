public class Butikk {
    private Person hode;



    public void entreButikk(Person p) {
        if (hode == null) {
            hode = p;
        } else {
            Person peker = hode;
            while (peker.neste != null) {
                peker = peker.neste;
            }
            // peker har siste man i koen
            peker.settInn(p);
        }
    }

    public void kassa() {
        while (hode != null) {
            System.out.println(String.format("%s kjøper %s.", hode.navn, hode.gjenstand));
            hode = hode.neste;
        }
        System.out.println("Ingen flere kunder");
    }
}

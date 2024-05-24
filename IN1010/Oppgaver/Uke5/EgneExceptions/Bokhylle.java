public class Bokhylle {
    private Bok[] boeker;

    public Bokhylle (int antPlasser) {
        boeker = new Bok[antPlasser];
    }

    public void settInn(Bok b) throws IkkeMerPlassException, DuplikatException{

        for (int i = 0; i < boeker.length; i++) {
            if (boeker[i] == null) {

                boeker[i] = b;
                return;
            }
            if (boeker[i].toString().equals(b.toString())) {
                throw new DuplikatException(b.toString());
            }
        }
        throw new IkkeMerPlassException(b.toString());
    }

    public void skrivBoeker() {
        for (Bok b: boeker) {
            if (b != null) {
                System.out.println(b);
            }
        }
    }
}
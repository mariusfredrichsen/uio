package IN1010.Oppgaver.EgneExceptions;

import java.lang.invoke.CallSite;

class Bokhylle {
    private Bok[] boeker;

    public Bokhylle (int antPlasser) {
        boeker = new Bok[antPlasser];
    }

    //Forsoeker aa sette inn en bok paa foerste ledige plass i bokhyllen
    public void settInn(Bok b) throws DuplikatException, IkkeMerPlassException {

        for (int i = 0; i < boeker.length; i++) {
            if (boeker[i] == null) {
                boeker[i] = b;
                System.out.println("Satt inn bok: " + b.toString());
                return; 
            } else if (boeker[i].toString().equals(b.toString())) {
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
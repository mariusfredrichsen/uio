


public class Båthus {
    
    Båt[] båter;
    int antallPlasser;

    public Båthus(int antallPlasser) {
        båter = new Båt[antallPlasser];
        this.antallPlasser = antallPlasser;
    }

    public void settInn(Båt båt) {
        for (int i = 0; i < antallPlasser; i++) {
            if (båter[i] == null) {
                båter[i] = båt;
                return;
            }
        }
        System.out.println("Det er ikke mer plass!");
    }

    public void skrivBåter() {
        for (int i = 0; i < antallPlasser; i++) {
            System.out.println(båter[i].hentInfo());
        }
    }
}

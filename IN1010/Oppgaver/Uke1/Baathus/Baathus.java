package IN1010.Oppgaver.Uke1.Baathus;

public class Baathus {
    Baat[] baater;
    int antall_plasser;

    public Baathus(int n_plasser) {
        baater = new Baat[n_plasser];
        antall_plasser = n_plasser;
    }
    
    public void settInn(Baat baat) {
        int n = 0;

        for(int i = 0; i < antall_plasser; i++) {
            if(baater[i] == null) {
                baater[i] = baat;
                break;
            }
            n++;
        }
        if(n >= antall_plasser) {
            System.out.println("Baathuset er fult");
        }
    }

    public void SkrivBaater() {
        for (int i = 0; i < antall_plasser; i++) {
            if (baater[i] != null) {
                System.out.println(baater[i].hentInfo());
            }
        }
    }
}

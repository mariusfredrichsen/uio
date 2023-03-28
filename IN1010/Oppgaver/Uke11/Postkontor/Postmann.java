package IN1010.Oppgaver.Uke11.Postkontor;

public class Postmann implements Runnable {
    Postkontor postkontor;
    int pakker;

    public Postmann(Postkontor postkontor, int pakker) {
        this.postkontor = postkontor;
        this.pakker = pakker;
    }

    public void run() {
        for (int i = 0; i < pakker; i++) {
            postkontor.leverPost(new Post("LMAO", "Albert"));
        }
    }
}

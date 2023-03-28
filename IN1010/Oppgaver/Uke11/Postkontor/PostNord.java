package IN1010.Oppgaver.Uke11.Postkontor;

public class PostNord {
    public static void main(String[] args) {
        int antFolkOgPakker = 200;
        Postkontor postkontor = new Postkontor();
        new Thread(new Postmann(postkontor, antFolkOgPakker)).start();
        for (int i = 0; i < antFolkOgPakker; i++) {
            new Thread(new Kunde("Albert", postkontor)).start();
        }
    }
}

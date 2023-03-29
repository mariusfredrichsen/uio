package IN1010.Oppgaver.Uke11.Postkontor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.*;

public class Postkontor {
    Post[] postHaug;
    Lock laas;
    Condition fullHaug;
    Condition ikkeFullHaug;
    public int teller = 0;
    CountDownLatch barriere;

    public Postkontor(CountDownLatch barriere) {
        postHaug = new Post[10];
        laas = new ReentrantLock();
        fullHaug = laas.newCondition();
        ikkeFullHaug = laas.newCondition();
        this.barriere = barriere;
    }

    public boolean erFull(Post p) {
        for (int i = 0; i < postHaug.length; i++) {
            if (postHaug[i] == null) {
                return false;
            }
        }
        return true;
    }

    //postHaugen er full vvv
    public void leverPost(Post p) {
        laas.lock();
        try {
            while (erFull(p)) ikkeFullHaug.await();
            for (int i = 0; i < postHaug.length; i++) {
                if (postHaug[i] == null) {
                    postHaug[i] = p;
                    break;
                }
            }
            fullHaug.signalAll();
        } catch (InterruptedException e) {
        } finally {
            laas.unlock();
        }
    }



    public boolean finnesHaug(String navnMottaker) {
        for (int i = 0; i < postHaug.length; i++) {
            if (postHaug[i] != null) {
                if (postHaug[i].mottaker.equals(navnMottaker)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean ikkeTom() {
        for (int i = 0; i < postHaug.length; i++) {
            if (postHaug[i] != null) {
                return true;
            }
        }
        return false;
    }

    public Post hentPost(String navnMottaker) {
        laas.lock();
        try {
            while (finnesHaug(navnMottaker) && barriere.getCount() != 0) fullHaug.await();
            for (int i = 0; i < postHaug.length; i++) {
                if (postHaug[i] != null) {
                    if (postHaug[i].mottaker.equals(navnMottaker)) {
                        Post postPeker = postHaug[i];
                        postHaug[i] = null;
                        ikkeFullHaug.signalAll();
                        System.out.println(++teller);
                        return postPeker;
                    }
                }
            }
        } catch (InterruptedException e) {
        } finally {
            laas.unlock();
        }
        return null;
    }
}

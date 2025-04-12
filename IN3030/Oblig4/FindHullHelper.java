
public class FindHullHelper implements Runnable {

    ConvexHull ch;
    int start, end, MAX_X, MIN_X;
    IntList coHull;

    public FindHullHelper(ConvexHull ch, int start, int end, IntList coHull) {
        this.ch = ch;
        this.start = start;
        this.end = end;
        this.coHull = coHull;
        for (int i = start; i < end; i++) {
            if (ch.x[i] > ch.x[MAX_X]) {
                MAX_X = i;
            } else if (ch.x[i] < ch.x[MIN_X]) {
                MIN_X = i;
            }
        }
    }

    public void run() {
        ch.findHullSeq(MAX_X, MIN_X, coHull, start, end);
    }
}

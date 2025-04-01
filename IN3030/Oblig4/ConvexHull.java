
import java.util.Arrays;

/**
 * A basic class to allow the precode to compile. You will need to implement the
 * logic for finding what points make up the convex hull.
 *
 */
public class ConvexHull {

    int n, seed, MAX_X, MAX_Y, MIN_X, MIN_Y;
    int x[], y[];

    ConvexHull(final int n, final int seed, final NPunkter17 nPunkter17) {
        this.n = n;
        this.seed = seed;
        this.x = new int[n];
        this.y = new int[n];
        nPunkter17.fyllArrayer(x, y);
        for (int i = 0; i < n; i++) {
            if (x[i] > x[MAX_X]) {
                MAX_X = i;
            } else if (x[i] < x[MIN_X]) {
                MIN_X = i;
            }
            if (y[i] > y[MAX_Y]) {
                MAX_Y = i;
            }
        }
    }

    public double findDistanceFromLine(int f, int t, int p) {
        int a = y[f] - y[t];
        int b = x[t] - x[f];
        int c = y[t] * x[f] - y[f] * x[t];

        double distance = (a * x[p] + b * y[p] + c) / (Math.sqrt(a * a + b * b));
        return distance;
    }

    public void findHull(int f, int t, IntList coHull) {

        IntList pointsRight = new IntList();
        IntList pointsLeft = new IntList();

        for (int i = 0; i < n; i++) {
            if (i == f || i == t) {
                continue;
            }
            double distance = findDistanceFromLine(f, t, i);
            if (distance <= 0) {
                pointsRight.add(i);
            }
            if (distance >= 0) {
                pointsLeft.add(i);
            }
        }

        coHull.add(f);
        findRightRec(f, t, coHull, pointsRight);
        coHull.add(t);
        findRightRec(t, f, coHull, pointsLeft);
    }

    public void findRightRec(int f, int t, IntList coHull, IntList pointsRight) {
        int right = -1;
        double minDistance = 0;
        IntList newPointsRight = new IntList();

        for (int i = 0; i < pointsRight.size(); i++) {
            int p = pointsRight.get(i);
            double distance = findDistanceFromLine(f, t, p);
            if (distance <= minDistance) {
                if (distance == 0) {
                    if (isBetweenPoints(f, t, p)) { // same line
                        System.out.println(p);
                        minDistance = distance;
                        right = p;
                        newPointsRight.add(p);
                    }
                } else {
                    minDistance = distance;
                    right = p;
                    newPointsRight.add(p);
                }
            }
        }

        if (right != -1) {
            findRightRec(f, right, coHull, newPointsRight);
            coHull.add(right);
            findRightRec(right, t, coHull, newPointsRight);
        }
    }

    private double findDistance(int a, int b) {
        return Math.sqrt(Math.pow(x[b] - x[a], 2) + Math.pow(y[b] - y[a], 2));
    }

    private boolean isBetweenPoints(int f, int t, int p) {
        double FT = findDistance(f, t);
        double FP = findDistance(f, p);
        double PT = findDistance(p, t);
        return (FT == FP + PT);
    }

    // Find highest distance
    public void findLeftRec(int f, int t, IntList coHull) {
        int left = -1;
        double maxDistance = 0;
        for (int i = 0; i < n; i++) {
            if (i == f || i == t) {
                continue;
            }
            double distance = findDistanceFromLine(f, t, i);
            if (distance > maxDistance) {
                maxDistance = distance;
                left = i;
            }
        }

        if (left != -1) {
            findLeftRec(f, left, coHull);
            findLeftRec(left, t, coHull);
            coHull.add(left);
        }

    }

    public static void main(String[] args) {
        final int n = Integer.parseInt(args[0]);
        final int seed = 42;
        NPunkter17 nPunkter17 = new NPunkter17(n, seed);
        ConvexHull ch = new ConvexHull(n, seed, nPunkter17);

        System.out.println(Arrays.toString(ch.x));
        System.out.println(Arrays.toString(ch.y));

        IntList coHull = new IntList();
        ch.findHull(ch.MAX_X, ch.MIN_X, coHull);
        coHull.print();

        Oblig4Precode precode = new Oblig4Precode(ch, coHull);
        precode.drawGraph();

        System.out.println(ch.findDistanceFromLine(ch.MAX_X, ch.MIN_X, ch.MIN_Y));
    }

}

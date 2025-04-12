
/**
 * A basic class to allow the precode to compile. You will need to implement the
 * logic for finding what points make up the convex hull.
 *
 */
import java.util.Arrays;

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

        double distance = (a * x[p] + b * y[p] + c); // / (Math.sqrt(a * a + b * b));
        return distance;
    }

    public void findHullSeq(int f, int t, IntList coHull, int start, int end) {

        IntList pointsRight = new IntList();
        IntList pointsLeft = new IntList();

        for (int i = start; i < end; i++) {
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
            if (p == f || p == t) {
                continue;
            }
            double distance = findDistanceFromLine(f, t, p);
            if (distance <= minDistance) {
                if (distance == 0) {
                    if (isBetweenPoints(f, t, p)) {
                        minDistance = distance;
                        right = p;
                    }
                } else {
                    minDistance = distance;
                    right = p;
                }
            }
            if (distance <= 0) {
                newPointsRight.add(p);
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

    public IntList findHullPar(int f, int t, int k, IntList coHull, int n) {

        int chunk = n / k;
        Thread[] threads = new Thread[k];
        IntList[] coHulls = new IntList[k];
        for (int i = 0; i < k - 1; i++) {
            coHulls[i] = new IntList(chunk);
            threads[i] = new Thread(new FindHullHelper(this, i * chunk, (i + 1) * chunk, coHulls[i]));
            threads[i].start();
        }
        coHulls[k - 1] = new IntList(n - (chunk * (k - 1)));
        threads[k - 1] = new Thread(new FindHullHelper(this, (k - 1) * chunk, n, coHulls[k - 1]));
        threads[k - 1].start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        IntList tempCoHull = new IntList();
        for (int i = 0; i < k; i++) {
            tempCoHull.append(coHulls[i]);
        }

        IntList pointsRight = new IntList();
        IntList pointsLeft = new IntList();

        for (int i = 0; i < tempCoHull.size(); i++) {
            int p = tempCoHull.get(i);
            if (p == f || p == t) {
                continue;
            }
            double distance = findDistanceFromLine(f, t, p);
            if (distance <= 0) {
                pointsRight.add(p);
            }
            if (distance >= 0) {
                pointsLeft.add(p);
            }
        }

        coHull.add(f);
        findRightRec(f, t, coHull, pointsRight);
        coHull.add(t);
        findRightRec(t, f, coHull, pointsLeft);

        return coHull;
    }

    public void splitData(int k) {

    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Missing parameters: java ConvexHull <n> <k>");
        }
        int k = Integer.parseInt(args[1]);
        if (k == 0) {
            k = Runtime.getRuntime().availableProcessors();
        }
        final int seed = 42;
        int r = 7;
        int[] nValues = {10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
        try (java.io.FileWriter writer = new java.io.FileWriter("results.csv")) {
            writer.append("n;seq(ms);par(ms);Spdup\n");

            for (int n : nValues) {
                double[] timesSeq = new double[r];
                double[] timesPar = new double[r];
                NPunkter17 nPunkter17 = new NPunkter17(n, seed);
                ConvexHull ch = new ConvexHull(n, seed, nPunkter17);
                IntList coHullSeq = null;
                IntList coHullPar = null;

                for (int i = 0; i < r; i++) {
                    // seq
                    double startSeq = System.nanoTime();
                    coHullSeq = new IntList();
                    ch.findHullSeq(ch.MAX_X, ch.MIN_X, coHullSeq, 0, n);
                    double endSeq = System.nanoTime();
                    double timeSeq = ((endSeq - startSeq) / 1000000.0);
                    timesSeq[i] = timeSeq;

                    // par
                    double startPar = System.nanoTime();
                    coHullPar = new IntList();
                    ch.findHullPar(ch.MAX_X, ch.MIN_X, k, coHullPar, n);
                    double endPar = System.nanoTime();
                    double timePar = ((endPar - startPar) / 1000000.0);
                    timesPar[i] = timePar;
                }

                checkCoHulls(coHullSeq, coHullPar);

                Arrays.sort(timesSeq);
                Arrays.sort(timesPar);

                double timeSeq = timesSeq[r / 2];
                double timePar = timesPar[r / 2];

                double speedUp = timeSeq / timePar;

                String result = String.format("%d;%.3f;%.3f;%.3f\n", n, timeSeq, timePar, speedUp);
                writer.append(result);

                String header = String.format("%-10s %-10s %-10s %-10s", "n", "seq(ms)", "par(ms)", "Spdup");
                System.out.println(header);
                System.out.println(String.format("%-10d %-10.3f %-10.3f %-10.3f", n, timeSeq, timePar, speedUp));
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        //System.out.print("Seq");
        // coHullSeq.print();
        // System.out.print("Par");
        // coHullPar.print();

        NPunkter17 nPunkter17 = new NPunkter17(100, seed);
        ConvexHull ch = new ConvexHull(100, seed, nPunkter17);
        IntList coHullSeq = new IntList();
        IntList coHullPar = new IntList();

        ch.findHullSeq(ch.MAX_X, ch.MIN_X, coHullSeq, 0, 100);
        ch.findHullPar(ch.MAX_X, ch.MIN_X, k, coHullPar, 100);
        
        Oblig4Precode precode = new Oblig4Precode(ch, coHullSeq);
        precode.drawGraph();
        precode = new Oblig4Precode(ch, coHullPar);
        precode.drawGraph();
        // precode.writeHullPoints();
    }

    public static void checkCoHulls(IntList coHull1, IntList coHull2) {
        assert coHull1.size() == coHull2.size() : "coHulls has different size";
        int n = coHull1.size();
        for (int i = 0; i < n; i++) {
            assert coHull1.get(i) == coHull2.get(i) : "coHUlls has different values";
        }
    }

}

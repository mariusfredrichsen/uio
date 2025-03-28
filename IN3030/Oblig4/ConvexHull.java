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
            if (x[i] > x[MAX_X])
                MAX_X = i;
            else if (x[i] < x[MIN_X])
                MIN_X = i;
            if (y[i] > y[MAX_Y])
                MAX_Y = i;
        }
    }

    public static void main(String[] args) {
        final int n = 10;
        final int seed = 42;
        NPunkter17 nPunkter17 = new NPunkter17(n, seed);
        ConvexHull ch = new ConvexHull(n, seed, new NPunkter17(n, seed));
        IntList coHull = nPunkter17.lagIntList();
        Oblig4Precode precode = new Oblig4Precode(ch, coHull);
        precode.drawGraph();
    }

}

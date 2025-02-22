


public class ParBHelper implements Runnable {
    private double[][] A;
    private double[][] BT;
    private double[][] C;
    private Par p;
    private int start;
    private int end;

    public ParBHelper(double[][] A, double[][] BT, double[][] C, Par p, int start, int end) {
        this.A = A;
        this.BT = BT;
        this.C = C;
        this.p = p;
        this.start = start;
        this.end = end;
    }

    public void run() {
        int n = BT.length;
        double sum;

        for (int i = start; i < end; i++) {
            for (int j = 0; j < n; j++) {
                sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += A[i][k] * BT[j][k];
                }
                p.parTimes(C, sum, i, j);
            }
        }
    }
}

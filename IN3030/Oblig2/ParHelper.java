


public class ParHelper implements Runnable {
    private double[][] A;
    private double[][] B;
    private double[][] C;
    private Par p;
    private int start;
    private int end;

    public ParHelper(double[][] A, double[][] B, double[][] C, Par p, int start, int end) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.p = p;
        this.start = start;
        this.end = end;
    }

    public void run() {
        int n = B.length;
        double sum;

        for (int i = start; i < end; i++) {
            for (int j = 0; j < n; j++) {
                sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += A[i][k] * B[k][j];
                }
                p.parTimes(C, sum, i, j);
            }
        }
    }
}


public class ParBHelper implements Runnable {
    private double[][] A;
    private double[][] BT;
    private double[][] C;
    private int start;
    private int end;

    public ParBHelper(double[][] A, double[][] BT, double[][] C, int start, int end) {
        this.A = A;
        this.BT = BT;
        this.C = C;
        this.start = start;
        this.end = end;
    }

    public void run() {
        int n = BT.length;

        for (int k = 0; k < n; k++) {
            for (int i = start; i < end; i++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A[i][k] * BT[j][k];
                }
            }
        }
    }
}

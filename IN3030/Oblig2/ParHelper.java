
public class ParHelper implements Runnable {
    private double[][] A;
    private double[][] B;
    private double[][] C;
    private int start;
    private int end;

    public ParHelper(double[][] A, double[][] B, double[][] C, int start, int end) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.start = start;
        this.end = end;
    }

    public void run() {
        int n = B.length;

        for (int k = 0; k < n; k++) {
            for (int i = start; i < end; i++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
}

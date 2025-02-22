
public class ParAHelper implements Runnable {
    private double[][] AT;
    private double[][] B;
    private double[][] C;
    private int start;
    private int end;

    public ParAHelper(double[][] AT, double[][] B, double[][] C, int start, int end) {
        this.AT = AT;
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
                    C[i][j] += AT[k][i] * B[k][j];
                }
            }
        }
    }
}
